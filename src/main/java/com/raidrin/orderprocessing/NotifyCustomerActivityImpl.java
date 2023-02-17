package com.raidrin.orderprocessing;

import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Activities(version = "1.0")
@ActivityRegistrationOptions(defaultTaskScheduleToStartTimeoutSeconds = 300, defaultTaskStartToCloseTimeoutSeconds = 10)
public class NotifyCustomerActivityImpl implements NotifyCustomerActivity {

    @Override
    public Promise<Void> notifyCustomer(String customer) {
        // simulate a random processing time
        Random random = new Random();
        int delay = random.nextInt(1000) + 500; // random delay between 500ms and 1500ms
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // notify the customer and return a void promise
        return Promise.asPromise(null);
    }

}
