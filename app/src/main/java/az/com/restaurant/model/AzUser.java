package az.com.restaurant.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ziweizeng on 11/20/16.
 */

public class AzUser {
    private String id;
    private String lastName;
    private String firstName;
    private String email;
    private AzAddress address;

    public AzUser(JSONObject object) {
        try {
            id = object.getString("id");
            lastName = object.getString("last_name");
            firstName = object.getString("first_name");
            email = object.getString("email");
            address = new AzAddress(object.optJSONObject("default_address"));
        } catch (JSONException e) {

        }
    }

    public AzAddress getAddress() {
        return address;
    }

    public void setAddress(AzAddress address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
