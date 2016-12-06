package az.com.restaurant.http;

/**
 * Created by ziweizeng on 11/20/16.
 */

public interface HandlerCallback {
    void onFinishSuccessful();
    void onFinishFailed();
    void onFinishWithResult(Object obj);
}
