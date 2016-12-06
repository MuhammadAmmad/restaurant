package az.com.restaurant.http;

import com.loopj.android.http.*;

import az.com.restaurant.AzBaseApp;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;

/**
 * Created by ziweizeng on 11/20/16.
 */

public class AzHttpClient {
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, AsyncHttpResponseHandler responseHandler) {
        client.get(url, responseHandler);
    }

    public static void get(String url, Header[] headers, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(AzBaseApp.getContext(), url, headers, params, responseHandler);
    }

    public static void post(String url, HttpEntity entity, String contentType, AsyncHttpResponseHandler responseHandler) {
        client.post(AzBaseApp.getContext(), url, entity, contentType, responseHandler);
    }
}
