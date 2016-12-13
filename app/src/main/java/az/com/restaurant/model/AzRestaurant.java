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
    String headUrl;
    String coverUrl;
    float deliverPrice;

    public AzRestaurant(JSONObject object) {
        try{
            id = object.getString("id");
            name = object.getString("name");
            address = new AzAddress(object.getJSONObject("address"));
            coverUrl = object.optString("cover_img_url");
            headUrl = object.optString("header_image_url");
            deliverPrice = object.getInt("delivery_fee");

        } catch (JSONException e) {

        }
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public float getDeliverPrice() {
        return deliverPrice;
    }

    public void setDeliverPrice(float deliverPrice) {
        this.deliverPrice = deliverPrice;
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
