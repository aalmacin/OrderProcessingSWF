package com.raidrin.orderprocessing;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;

public interface ValidateOrderActivity {
    Promise<Boolean> validateOrder(String order);
}
