package az.com.restaurant.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ziweizeng on 11/20/16.
 */

public class AzAddress {
    private double lat;
    private double lng;
    private String printableAddress;
    private String shortName;
    private String street;
    private double defaultLat = 37.560334;
    private double defaultLon = -121.988694;
    private String id;

    public AzAddress(JSONObject object) {
        if (object != null) {
            try {
                id = object.optString("id");
                lat = object.optDouble("lat", defaultLat);
                lng = object.optDouble("lon", defaultLon);
                printableAddress = object.getString("printable_address");
                shortName = object.optString("shortname");
                street = object.optString("street");
            } catch (JSONException e) {

            }
        }
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrintableAddress() {
        return printableAddress;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public void setLng(long lng) {
        this.lng = lng;
    }

    public void setPrintableAddress(String printableAddress) {
        this.printableAddress = printableAddress;
    }
}
