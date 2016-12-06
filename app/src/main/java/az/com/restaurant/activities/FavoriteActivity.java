package az.com.restaurant.activities;

import android.os.Bundle;
import android.widget.ListView;

import az.com.restaurant.AzBaseApp;
import az.com.restaurant.data.AzRestaurantManager;
import az.com.restaurant.R;
import az.com.restaurant.adapter.RestaurantAdapter;

/**
 * Created by ziweizeng on 11/21/16.
 */

public class FavoriteActivity extends AzBaseActivity {
    ListView itemsLv;
    private RestaurantAdapter restaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        setTitle(AzBaseApp.getInstance().getString(R.string.screen_fav));
        itemsLv = (ListView) findViewById(R.id.fav_list);
        initInteraction();
        refreshFavList();
    }

    private void initInteraction() {

    }

    private void refreshFavList() {
        restaurantAdapter = new RestaurantAdapter(FavoriteActivity.this, AzRestaurantManager.getInstance().getFavRestaurants(), false);
        itemsLv.setAdapter(restaurantAdapter);
        hideLoading();
    }


}
