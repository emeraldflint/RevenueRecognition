package retryer;

/**
 * Created by z-dus on 16.04.2017.
 */
public interface Retryable {
    String attempt() throws Exception;
}
