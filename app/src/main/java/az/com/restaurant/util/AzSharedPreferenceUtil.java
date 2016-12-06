package az.com.restaurant.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import az.com.restaurant.AzBaseApp;
import az.com.restaurant.model.AzRestaurant;

/**
 * Created by ziweizeng on 11/20/16.
 */

public class AzSharedPreferenceUtil {
    private static String PREFERENCES_FILE = "az_shared_preference";
    private static String PREFERENCE_TOKEN = "preference_token";
    private static String FAV_RESTAURANT = "fav_restaurant";

    public static SharedPreferences mSharedPreferences = AzBaseApp.getContext()
            .getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);

    public static String getToken() {
        return getStringForKey(PREFERENCE_TOKEN);
    }

    public static void setToken(String token) {
        setStringForKey(token, PREFERENCE_TOKEN);
    }

    public static List<AzRestaurant> getFavRestaurant() {
        Gson gson = new Gson();
        List<AzRestaurant> restaurants;
        String jsonPreferences = getStringForKey(FAV_RESTAURANT);
        Type type = new TypeToken<List<AzRestaurant>>() {
        }.getType();
        restaurants = gson.fromJson(jsonPreferences, type);
        return restaurants;
    }

    public static void setFavRestaurants(List<AzRestaurant> restaurants) {
        Gson gson = new Gson();
        String jsonRests = gson.toJson(restaurants);
        setStringForKey(jsonRests, FAV_RESTAURANT);
    }

    public static void addFavRestaurant(AzRestaurant restaurant) {
        List<AzRestaurant> restaurants = getFavRestaurant();
        if(restaurants == null) {
            restaurants = new ArrayList<>();
        }
        restaurants.add(restaurant);
        setFavRestaurants(restaurants);
    }

    public static void removeRestaurant(AzRestaurant restaurant) {
        List<AzRestaurant> existsRest = getFavRestaurant();
        if(existsRest == null) {
            existsRest = new ArrayList<>();
        }
        AzRestaurant toRemove = null;
        for (AzRestaurant rest : existsRest) {
            if (rest.getId().equals(restaurant.getId())) {
                toRemove = rest;
            }
        }
        if (toRemove != null) {
            existsRest.remove(toRemove);
        }
        setFavRestaurants(existsRest);
    }

    public static String getStringForKey(String key) {
        return getStringForKey(key, "");
    }

    public static String getStringForKey(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public static void setStringForKey(String value, String key) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    //This will remove the whole saved data
    public static void clear() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
