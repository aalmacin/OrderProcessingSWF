package com.raidrin.orderprocessing;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.flow.ActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.WorkflowWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkerConfig {

    private final AmazonSimpleWorkflow swfClient;

    private final ReceiveOrderActivity receiveOrderActivity;

    private final ValidateOrderActivity validateOrderActivity;

    private final ChargeCustomerActivity chargeCustomerActivity;

    private final NotifyCustomerActivity notifyCustomerActivity;

    final
    AwsConfig awsConfig;

    public WorkerConfig(AmazonSimpleWorkflow swfClient, ReceiveOrderActivity receiveOrderActivity, ValidateOrderActivity validateOrderActivity, ChargeCustomerActivity chargeCustomerActivity, NotifyCustomerActivity notifyCustomerActivity, AwsConfig awsConfig) {
        this.swfClient = swfClient;
        this.receiveOrderActivity = receiveOrderActivity;
        this.validateOrderActivity = validateOrderActivity;
        this.chargeCustomerActivity = chargeCustomerActivity;
        this.notifyCustomerActivity = notifyCustomerActivity;
        this.awsConfig = awsConfig;
    }

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

