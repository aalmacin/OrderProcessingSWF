package com.raidrin.orderprocessing;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;

public interface ChargeCustomerActivity {
    Promise<String> chargeCustomer(String order);
}
