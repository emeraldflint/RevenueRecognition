package retryer.test;

import retryer.impl.ConcreteRetryable;
import retryer.impl.RetryableImpl;

/**
 * Created by z-dus on 16.04.2017.
 */
public class RetryableTest {
    public static void main(String[] args) {
        RetryableImpl retryable = new RetryableImpl(new ConcreteRetryable());
        try {
            System.out.println(retryable.attempt());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
