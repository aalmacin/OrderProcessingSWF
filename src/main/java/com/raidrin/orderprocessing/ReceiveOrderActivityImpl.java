package com.raidrin.orderprocessing;

import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Activities(version = "1.0")
@ActivityRegistrationOptions(defaultTaskScheduleToStartTimeoutSeconds = 300, defaultTaskStartToCloseTimeoutSeconds = 10)
public class ReceiveOrderActivityImpl implements ReceiveOrderActivity {

    @Override
    public Promise<String> receiveOrder() {
        // prompt the user to enter order information
        System.out.println("Please enter your order information:");
        Scanner scanner = new Scanner(System.in);
        String order = scanner.nextLine();

        // simulate a random processing time
        int delay = 500; // 500ms delay
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Promise.asPromise(order);
    }

}
