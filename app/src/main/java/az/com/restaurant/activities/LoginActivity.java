package az.com.restaurant.activities;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import az.com.restaurant.AzBaseApp;
import az.com.restaurant.R;
import az.com.restaurant.viewModel.LoginViewModel;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AzBaseActivity {
    // UI references.
    public EditText mEmailView;
    public EditText mPasswordView;
    public View mLoginFormView;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModel = new LoginViewModel(this);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        setTitle(AzBaseApp.getInstance().getString(R.string.screen_login));
    }

}

