package az.com.restaurant.view;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by ziweizeng on 11/20/16.
 */

public class AzLoading extends ProgressDialog {

    public AzLoading(Context context) {
        this(context, "Loading...");
    }

    public AzLoading(Context context, String message) {
        super(context);
        setMessage(message);
        setCancelable(true);
        setIndeterminate(true);
    }
}
