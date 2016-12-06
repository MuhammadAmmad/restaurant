package az.com.restaurant.activities;

import android.content.Intent;
import android.os.Bundle;

import az.com.restaurant.data.AzRestaurantManager;
import az.com.restaurant.R;
import az.com.restaurant.auth.AzAccountManager;
import az.com.restaurant.http.HandlerCallback;
import az.com.restaurant.util.AzSharedPreferenceUtil;

/**
 * Created by ziweizeng on 11/20/16.
 */

public class SplashActivity extends AzBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }


    private void init() {
        AzRestaurantManager.getInstance().init();
        if (AzSharedPreferenceUtil.getToken().length() != 0) {
            AzAccountManager.getInstance().login(new HandlerCallback() {
                @Override
                public void onFinishSuccessful() {
                    try {
                        Thread.sleep(550);
                        handleLogin(true);
                    } catch (InterruptedException e) {

                    }
                }

                @Override
                public void onFinishFailed() {
                    try {
                        Thread.sleep(550);
                        handleLogin(false);
                    } catch (InterruptedException e) {

                    }
                }

                @Override
                public void onFinishWithResult(Object json) {

                }
            });
        } else {
            try {
                Thread.sleep(750);
                handleLogin(false);
            } catch (InterruptedException e) {

            }
        }
    }

    private void handleLogin(boolean hasToken) {
        if (hasToken) {
            Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        } else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        }
    }

}
