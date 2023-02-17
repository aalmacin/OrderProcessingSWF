package com.raidrin.orderprocessing;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;

public interface NotifyCustomerActivity {
    Promise<Void> notifyCustomer(String customer);
}
