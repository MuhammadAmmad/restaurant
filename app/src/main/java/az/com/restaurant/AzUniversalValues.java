package az.com.restaurant;

/**
 * Created by ziweizeng on 11/20/16.
 */

public class AzUniversalValues {
    public static String getUrlRoot() {
        return AzBaseApp.getContext().getString(R.string.app_root_url);
    }

    public static String getProfileUrl() {
        return getUrlRoot() + AzBaseApp.getContext().getString(R.string.endpoint_profile);
    }

    public static String getAuthUrl() {
        return getUrlRoot() + AzBaseApp.getContext().getString(R.string.endpoint_auth);
    }

    /**
     *Compose url in following format: <host>/v2/restaurant/
     * @return
     */
    public static String getBaseRestuarantUrl() {
        return getUrlRoot() + AzBaseApp.getContext().getString(R.string.endpoint_restaurant);
    }

    /**
     * Get Url to for list of restaurant acoording to location.
     * Compose url in following format: <host>/v2/restaurant/?lat=<LAT>&lng=<LNG>
     * @param lat
     * @param lng
     * @return
     */
    public static String getResturantListUrlByLatLng(double lat, double lng) {
        return getBaseRestuarantUrl() + "?lat=" + lat + "&lng=" + lng;
    }

    /**
     * Get Url for a single restaurant.
     * Compose url in following format: <host>/v2/restaurant/<restaurant_id>/
     * @param id
     * @return
     */
    public static String getRestaurantUrlById(String id) {
        return getBaseRestuarantUrl() + id;
    }

    /**
     * Get Url for menu of a restaurant
     * Compose url in following format: <host>/v2/restaurant/<restaurant_id>/menu/<menu_id>/
     * @param restaurantId
     * @param menuId
     * @return
     */
    public static String getRestuarantMenuUrlById(int restaurantId, int menuId) {
        return getBaseRestuarantUrl() + restaurantId + "/menu/" + menuId;
    }

    /**
     * Get Url for item of a restaurant
     * Compose url in following format: <host>/v2/restaurant/<restaurant_id>/item/<item_id>/
     * @param restaurantId
     * @param itemId
     * @return
     */
    public static String getRestuarantItemUrlById(int restaurantId, int itemId) {
        return getBaseRestuarantUrl() + restaurantId + "/item/" + itemId;
    }
}
