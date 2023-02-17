package com.raidrin.orderprocessing;

import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import org.springframework.stereotype.Component;

@Component
@Activities(version = "1.0")
@ActivityRegistrationOptions(defaultTaskScheduleToStartTimeoutSeconds = 300, defaultTaskStartToCloseTimeoutSeconds = 10)
public class ReceiveOrderActivityImpl implements ReceiveOrderActivity {

    @Override
    public Promise<String> receiveOrder() {
        String order = // prompt user to enter order information
        return Promise.asPromise(order);
    }

}
