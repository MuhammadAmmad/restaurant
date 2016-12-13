package az.com.restaurant.auth;

import android.content.Intent;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import az.com.restaurant.AzBaseApp;
import az.com.restaurant.AzUniversalValues;
import az.com.restaurant.activities.AzBaseActivity;
import az.com.restaurant.activities.SplashActivity;
import az.com.restaurant.data.AzRestaurantManager;
import az.com.restaurant.http.AzHttpClient;
import az.com.restaurant.http.HandlerCallback;
import az.com.restaurant.model.AzUser;
import az.com.restaurant.util.AzSharedPreferenceUtil;
import az.com.restaurant.util.JsonUtil;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;

/**
 * Created by ziweizeng on 11/20/16.
 */

public class AzAccountManager {
    private static AzAccountManager instance;
    private static String mToken;
    private static AzUser currentUser;
    public static synchronized AzAccountManager getInstance() {
        if(instance == null) {
            instance = new AzAccountManager();
        }
        return instance;
    }

    public AzAccountManager() {
        mToken = AzSharedPreferenceUtil.getToken();
    }

    public void setToken(String token) {
        mToken = token;
        AzSharedPreferenceUtil.setToken(mToken);
    }

    public String getToken() {
        return mToken;
    }

    public void getToken(String email, String pw, final HandlerCallback callback) {
        if(email.length() > 0 && pw.length() > 0 ) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("email", email);
            params.put("password", pw);
            String body = JsonUtil.getJSONParams(params);
            try {
                AzHttpClient.post(AzUniversalValues.getAuthUrl(), new StringEntity(body), "application/json", new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            setToken(response.getString("token"));
                            if(callback != null)
                                callback.onFinishSuccessful();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            if(callback != null)
                                callback.onFinishFailed();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                        if(callback != null)
                            callback.onFinishFailed();
                    }
                });
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                if(callback != null)
                    callback.onFinishFailed();
            }
        }
    }

    public void login(final HandlerCallback callback) {
        AzHttpClient.get(AzUniversalValues.getProfileUrl(), getRequestHeaders(), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                setAccount(response);
                if (callback != null)
                    callback.onFinishSuccessful();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if(callback != null)
                    callback.onFinishFailed();
            }
        });
    }

    public void logout(AzBaseActivity activity) {
        AzSharedPreferenceUtil.clear();
        this.clear();
        AzRestaurantManager.getInstance().clear();
        Intent intent = new Intent(activity, SplashActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    private Header[] getRequestHeaders() {
        Header[] headers = {
                new BasicHeader("Content-type", "application/json")
                ,new BasicHeader("Authorization", "JWT " + getToken())};
        return headers;
    }

    private void clear() {
        currentUser = null;
        mToken = null;
    }

    private void setAccount(JSONObject accountObject) {
        currentUser = new AzUser(accountObject);
    }

    public AzUser getCurrentUser() {
        return currentUser;
    }
}
