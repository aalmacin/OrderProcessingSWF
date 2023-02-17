package com.raidrin.orderprocessing;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.flow.ActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.WorkflowWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkerConfig {

    @Autowired
    private AmazonSimpleWorkflow swfClient;

    @Autowired
    private ReceiveOrderActivity receiveOrderActivity;

    @Autowired
    private ValidateOrderActivity validateOrderActivity;

    @Autowired
    private ChargeCustomerActivity chargeCustomerActivity;

    @Autowired
    private NotifyCustomerActivity notifyCustomerActivity;

    @Autowired
    AwsConfig awsConfig;

    @Bean
    public ActivityWorker activityWorker() {
        ActivityWorker worker = new ActivityWorker(swfClient, awsConfig.domain, awsConfig.taskList);

        try {
            worker.addActivitiesImplementation(receiveOrderActivity);
            worker.addActivitiesImplementation(validateOrderActivity);
            worker.addActivitiesImplementation(chargeCustomerActivity);
            worker.addActivitiesImplementation(notifyCustomerActivity);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return worker;
    }

    @Bean
    public WorkflowWorker orderProcessingWorkflowWorker() {
        WorkflowWorker worker = new WorkflowWorker(swfClient, awsConfig.domain, awsConfig.taskList);
        try {
            worker.addWorkflowImplementationType(OrderProcessingWorkflowImpl.class);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return worker;
    }
}

