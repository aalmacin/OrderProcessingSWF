package com.raidrin.orderprocessing;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;

public interface ReceiveOrderActivity {
    Promise<String> receiveOrder();
}