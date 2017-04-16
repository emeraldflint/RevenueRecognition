package retryer.impl;

import retryer.Retryable;

/**
 * Created by z-dus on 16.04.2017.
 */
public class RetryableImpl implements Retryable {

    private Retryable retryable;

    public RetryableImpl(Retryable retryable) {
        this.retryable = retryable;
    }

    @Override
    public String attempt() throws Exception {
        while (true) {
            try {
                return retryable.attempt();
            } catch (Exception e) {
                System.out.println("Attempt has failed, please try again.");
            }
        }

    }
}
