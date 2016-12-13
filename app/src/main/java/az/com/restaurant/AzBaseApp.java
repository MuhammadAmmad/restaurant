package az.com.restaurant;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by ziweizeng on 11/20/16.
 */

public class AzBaseApp extends Application {
    protected static AzBaseApp mInstance;
    protected static Context mContext;

    public static AzBaseApp getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = this.getApplicationContext();
        Fresco.initialize(this);
    }

}
