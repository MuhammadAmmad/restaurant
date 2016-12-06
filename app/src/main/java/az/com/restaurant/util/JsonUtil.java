package az.com.restaurant.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by ziweizeng on 11/20/16.
 */

public class JsonUtil {

    public static String getJSONParams(Map<String, String> params) {
        JSONObject jsonBody = new JSONObject();
        Iterator var2 = params.keySet().iterator();

        while (var2.hasNext()) {
            String key = (String) var2.next();

            try {
                jsonBody.put(key, params.get(key));
            } catch (JSONException var5) {
                Log.i("Exception", var5.toString());
            }
        }

        return jsonBody.toString();
    }
}
