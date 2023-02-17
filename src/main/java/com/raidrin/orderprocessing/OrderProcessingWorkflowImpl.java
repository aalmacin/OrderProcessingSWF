package com.raidrin.orderprocessing;

import com.amazonaws.services.simpleworkflow.flow.annotations.Execute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderProcessingWorkflowImpl implements OrderProcessingWorkflow {

    private final ReceiveOrderActivity receiveOrderActivity;
    private final ValidateOrderActivity validateOrderActivity;
    private final ChargeCustomerActivity chargeCustomerActivity;
    private final NotifyCustomerActivity notifyCustomerActivity;

    @Autowired
    public OrderProcessingWorkflowImpl(ReceiveOrderActivity receiveOrderActivity,
                                       ValidateOrderActivity validateOrderActivity,
                                       ChargeCustomerActivity chargeCustomerActivity,
                                       NotifyCustomerActivity notifyCustomerActivity) {
        this.receiveOrderActivity = receiveOrderActivity;
        this.validateOrderActivity = validateOrderActivity;
        this.chargeCustomerActivity = chargeCustomerActivity;
        this.notifyCustomerActivity = notifyCustomerActivity;
    }

    @Override
    @Execute(version = "1.0", name = "processOrder")
    public void processOrder(String orderId) {
        String order = receiveOrderActivity.receiveOrder().get();
        boolean isValid = validateOrderActivity.validateOrder(order).get();

        if (isValid) {
            chargeCustomerActivity.chargeCustomer(order).get();
            notifyCustomerActivity.notifyCustomer(order).get();
        }
    }
}

