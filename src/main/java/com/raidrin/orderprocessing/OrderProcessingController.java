package com.raidrin.orderprocessing;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderProcessingController {

    private final OrderProcessingWorkflow orderProcessingWorkflow;

    public OrderProcessingController(OrderProcessingWorkflow orderProcessingWorkflow) {
        this.orderProcessingWorkflow = orderProcessingWorkflow;
    }

    @PostMapping("/orders")
    public void processOrder(@RequestBody Order order) {
        orderProcessingWorkflow.processOrder(order.toString());
    }

}
