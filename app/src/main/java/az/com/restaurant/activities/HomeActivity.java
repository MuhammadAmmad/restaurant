package az.com.restaurant.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import az.com.restaurant.AzBaseApp;
import az.com.restaurant.model.AzRestaurant;
import az.com.restaurant.data.AzRestaurantManager;
import az.com.restaurant.R;
import az.com.restaurant.adapter.RestaurantAdapter;
import az.com.restaurant.auth.AzAccountManager;
import az.com.restaurant.http.HandlerCallback;

/**
 * Created by ziweizeng on 11/20/16.
 */

public class HomeActivity extends AzBaseActivity {
    TextView addressTv;
    ListView itemsLv;
    ImageView favIcon;
    List<AzRestaurant> restaurants;
    private RestaurantAdapter restaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle(AzBaseApp.getInstance().getString(R.string.screen_home));
        addressTv = (TextView) findViewById(R.id.address_name);
        addressTv.setText(AzAccountManager.getInstance().getCurrentUser().getAddress().getShortName());
        favIcon = (ImageView) findViewById(R.id.fav_btn);
        itemsLv = (ListView) findViewById(R.id.item_list);
        initInteraction();
        refreshRestaurantList();
    }

    private void initInteraction() {
        favIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FavoriteActivity.class);
                HomeActivity.this.startActivity(intent);
            }
        });

        itemsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                final AzRestaurant selectedRestaurant = (AzRestaurant) restaurantAdapter
                        .getItem(position);

            }
        });
    }

    private void refreshRestaurantList() {
        showLoading();
        AzRestaurantManager.getInstance().loadRestaurantByLatLon(AzAccountManager.getInstance().getCurrentUser().getAddress().getLat(),
                AzAccountManager.getInstance().getCurrentUser().getAddress().getLng(), new HandlerCallback() {
                    @Override
                    public void onFinishSuccessful() {

                    }

                    @Override
                    public void onFinishFailed() {
                        hideLoading();
                        makeToast("Load failed!");
                    }

                    @Override
                    public void onFinishWithResult(Object obj) {
                        restaurants = (List<AzRestaurant>) obj;
                        restaurantAdapter = new RestaurantAdapter(HomeActivity.this, restaurants, true);
                        itemsLv.setAdapter(restaurantAdapter);
                        hideLoading();
                    }
                });

    }
}
