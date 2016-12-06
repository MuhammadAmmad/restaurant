package az.com.restaurant.util;

/**
 * Created by ziweizeng on 11/20/16.
 */

public class StringUtil {

    public static boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public static boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
