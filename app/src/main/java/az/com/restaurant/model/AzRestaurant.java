package az.com.restaurant.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ziweizeng on 11/21/16.
 */

public class AzRestaurant {
    String id;
    String name;
    AzAddress address;

    public AzRestaurant(JSONObject object) {
        try{
            id = object.getString("id");
            name = object.getString("name");
            address = new AzAddress(object.getJSONObject("address"));
        } catch (JSONException e) {

        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AzAddress getAddress() {
        return address;
    }

    public void setAddress(AzAddress address) {
        this.address = address;
    }
}
