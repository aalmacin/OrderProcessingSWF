package com.raidrin.orderprocessing;
import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Activities(version = "1.0")
@ActivityRegistrationOptions(defaultTaskScheduleToStartTimeoutSeconds = 300, defaultTaskStartToCloseTimeoutSeconds = 10)
public class ChargeCustomerActivityImpl implements ChargeCustomerActivity {

    @Override
    public Promise<String> chargeCustomer(String order) {
        // simulate a random processing time
        Random random = new Random();
        int delay = random.nextInt(1000) + 500; // random delay between 500ms and 1500ms
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // charge the customer and return a promise with the customer information
        return Promise.asPromise("customer_info");
    }

}
