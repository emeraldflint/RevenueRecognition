package retryer.impl;

import retryer.Retryable;

/**
 * Created by z-dus on 16.04.2017.
 */
public class ConcreteRetryable implements Retryable {
    private static int attempts = 0;
    private static final int MAX_ATTEMPTS = 5;


    @Override
    public String attempt() throws Exception {
        if (attempts < MAX_ATTEMPTS) {
            attempts++;
            throw new Exception("Reached max number of attempts");
        } else return "Attempt successful";
    }
}
