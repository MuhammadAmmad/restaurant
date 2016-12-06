package az.com.restaurant.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import az.com.restaurant.AzBaseApp;
import az.com.restaurant.R;
import az.com.restaurant.auth.AzAccountManager;
import az.com.restaurant.view.AzLoading;

/**
 * Created by ziweizeng on 11/20/16.
 */

public class AzBaseActivity extends AppCompatActivity {
    private AzLoading loadingDialog;
    private android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        actionBarSetup();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(loadingDialog != null) {
            loadingDialog.hide();
        }
    }

    public void showLoading() {
        loadingDialog = new AzLoading(AzBaseActivity.this);
        loadingDialog.show();
    }

    public void hideLoading() {
        if(loadingDialog != null) {
            loadingDialog.hide();
        }
    }

    public void makeToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void actionBarSetup() {
        actionBar = getSupportActionBar();
        actionBar.setTitle(AzBaseApp.getInstance().getString(R.string.app_name));
    }

    public void setTitle(String title) {
        actionBar.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // This is the up button
            case R.id.action_log_out:
                AzAccountManager.getInstance().logout(AzBaseActivity.this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
 }
