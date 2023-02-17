package com.raidrin.orderprocessing;

import com.amazonaws.services.simpleworkflow.flow.annotations.Execute;
import com.amazonaws.services.simpleworkflow.flow.annotations.Workflow;
import com.amazonaws.services.simpleworkflow.flow.annotations.WorkflowRegistrationOptions;

@Workflow
@WorkflowRegistrationOptions(defaultExecutionStartToCloseTimeoutSeconds = 3600, defaultTaskStartToCloseTimeoutSeconds = 60)
public interface OrderProcessingWorkflow {
    @Execute(version = "1.0", name = "processOrder")
    void processOrder(String order);
}

