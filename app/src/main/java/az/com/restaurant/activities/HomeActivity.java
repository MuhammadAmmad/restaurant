package az.com.restaurant.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import az.com.restaurant.AzBaseApp;
import az.com.restaurant.R;
import az.com.restaurant.fragment.HomeFragment;

/**
 * Created by ziweizeng on 11/20/16.
 */

public class HomeActivity extends AzBaseActivity implements HomeFragment.OnFragmentInteractionListener{
    FrameLayout contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        contentView = (FrameLayout) findViewById(R.id.content_view);
        setTitle(AzBaseApp.getInstance().getString(R.string.screen_home));

        if (savedInstanceState == null) {
            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_view, homeFragment, "homefragment").commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
