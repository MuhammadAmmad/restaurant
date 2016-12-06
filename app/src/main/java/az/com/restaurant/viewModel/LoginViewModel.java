package az.com.restaurant.viewModel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;

import az.com.restaurant.AzBaseApp;
import az.com.restaurant.R;
import az.com.restaurant.activities.HomeActivity;
import az.com.restaurant.activities.LoginActivity;
import az.com.restaurant.auth.AzAccountManager;
import az.com.restaurant.http.HandlerCallback;
import az.com.restaurant.util.StringUtil;

/**
 * Created by ziweizeng on 11/20/16.
 */

public class LoginViewModel {
    LoginActivity mLoginActivity;

    public LoginViewModel(LoginActivity activity) {
        mLoginActivity = activity;
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {
        // Reset errors.
        mLoginActivity.mEmailView.setError(null);
        mLoginActivity.mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mLoginActivity.mEmailView.getText().toString();
        String password = mLoginActivity.mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !StringUtil.isPasswordValid(password)) {
            mLoginActivity.mPasswordView.setError(AzBaseApp.getContext().getString(R.string.error_invalid_password));
            focusView = mLoginActivity.mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mLoginActivity.mEmailView.setError(AzBaseApp.getContext().getString(R.string.error_field_required));
            focusView = mLoginActivity.mEmailView;
            cancel = true;
        } else if (!StringUtil.isEmailValid(email)) {
            mLoginActivity.mEmailView.setError(AzBaseApp.getContext().getString(R.string.error_invalid_email));
            focusView = mLoginActivity.mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            doLogin(email, password);
        }
    }

    private void doLogin(String email, String password) {
        showProgress(true);
        AzAccountManager.getInstance().getToken(email, password, new HandlerCallback() {
            @Override
            public void onFinishSuccessful() {
                AzAccountManager.getInstance().login(new HandlerCallback() {
                    @Override
                    public void onFinishSuccessful() {
                        showProgress(false);
                        Intent intent = new Intent(mLoginActivity, HomeActivity.class);
                        mLoginActivity.startActivity(intent);
                        mLoginActivity.finish();
                    }

                    @Override
                    public void onFinishFailed() {
                        showProgress(false);
                    }

                    @Override
                    public void onFinishWithResult(Object obj) {

                    }
                });
            }

            @Override
            public void onFinishFailed() {
                showProgress(false);
            }

            @Override
            public void onFinishWithResult(Object json) {

            }
        });
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = AzBaseApp.getContext().getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginActivity.mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginActivity.mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginActivity.mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mLoginActivity.mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }

        if (show) {
            mLoginActivity.showLoading();
        } else {
            mLoginActivity.hideLoading();
        }
    }
}

