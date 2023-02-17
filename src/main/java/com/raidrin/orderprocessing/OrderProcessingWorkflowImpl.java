package com.raidrin.orderprocessing;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessingWorkflowImpl implements OrderProcessingWorkflow {

    private final ReceiveOrderActivity receiveOrderActivity;

    @Autowired
    public OrderProcessingWorkflowImpl(ReceiveOrderActivity receiveOrderActivity) {
        this.receiveOrderActivity = receiveOrderActivity;
    }

    @Override
    public void processOrder(String order) {
        Promise<String> orderPromise = receiveOrderActivity.receiveOrder();
        // continue with the workflow using the orderPromise
    }

}

