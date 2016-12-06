package az.com.restaurant.data;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import az.com.restaurant.AzUniversalValues;
import az.com.restaurant.http.AzHttpClient;
import az.com.restaurant.http.HandlerCallback;
import az.com.restaurant.model.AzRestaurant;
import az.com.restaurant.util.AzSharedPreferenceUtil;
import cz.msebera.android.httpclient.Header;

/**
 * Created by ziweizeng on 11/21/16.
 */

public class AzRestaurantManager {
    private static AzRestaurantManager instance;
    private List<AzRestaurant> favRestaurants = new ArrayList<>();

    public static synchronized AzRestaurantManager getInstance() {
        if(instance == null) {
            instance = new AzRestaurantManager();
        }
        return instance;
    }

    public void init() {
        if(AzSharedPreferenceUtil.getFavRestaurant() != null)
            favRestaurants = AzSharedPreferenceUtil.getFavRestaurant();
    }

    public boolean isRestaurantFav(String id) {
        if(favRestaurants.size() == 0) {
            return false;
        }
        for(AzRestaurant res : favRestaurants) {
            if(res.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public List<AzRestaurant> getFavRestaurants() {
        return favRestaurants;
    }

    public void addFavRestaurant(AzRestaurant restaurant) {
        String id = restaurant.getId();
        for(AzRestaurant res : favRestaurants) {
            if(res.getId().equals(id)) {
                return;
            }
        }
        favRestaurants.add(restaurant);
        AzSharedPreferenceUtil.addFavRestaurant(restaurant);
    }

    public void removeFavRestaurant(AzRestaurant restaurant) {
        AzRestaurant toRemove = null;
        for(AzRestaurant res : favRestaurants) {
            if(res.getId().equals(restaurant.getId())) {
                toRemove = res;
            }
        }
        if(toRemove!= null && favRestaurants.remove(toRemove))
            AzSharedPreferenceUtil.removeRestaurant(restaurant);
    }

    public void loadRestaurantByLatLon(double lat, double lng, final HandlerCallback callback) {
        AzHttpClient.get(AzUniversalValues.getResturantListUrlByLatLng(lat, lng), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                if (callback != null)
                    callback.onFinishWithResult(generateRestaurantList(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if(callback != null)
                    callback.onFinishFailed();
            }
        });
    }

    public void clear() {
        favRestaurants = new ArrayList<>();
    }

    private List<AzRestaurant> generateRestaurantList(JSONArray jsonArray) {
        List<AzRestaurant> restaurants = new ArrayList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                restaurants.add(new AzRestaurant(object));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return restaurants;
    }
}
