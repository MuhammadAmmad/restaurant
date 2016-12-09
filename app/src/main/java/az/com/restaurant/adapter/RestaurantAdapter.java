package az.com.restaurant.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import az.com.restaurant.R;
import az.com.restaurant.activities.MapsActivity;
import az.com.restaurant.data.AzRestaurantManager;
import az.com.restaurant.model.AzRestaurant;

/**
 * Created by ziweizeng on 11/21/16.
 */

public class RestaurantAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<AzRestaurant> mRestaurants = new ArrayList<AzRestaurant>();
    private FragmentActivity mActivity;
    boolean mIconClickable;

    public RestaurantAdapter(FragmentActivity activity,
                             List<AzRestaurant> restaurants, boolean iconClickable) {
        mRestaurants = restaurants;
        mActivity = activity;
        mInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mIconClickable = iconClickable;
    }

    @Override
    public int getCount() {
        return mRestaurants.size();
    }

    @Override
    public Object getItem(int position) {
        return mRestaurants.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.restaurant_item, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.item_name);
            holder.favIcon = (ImageView) convertView.findViewById(R.id.item_fav_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final AzRestaurant restaurant = mRestaurants.get(position);
        if (restaurant != null) {
            holder.name.setText(restaurant.getName());
            holder.favIcon.setImageResource(AzRestaurantManager.getInstance()
                    .isRestaurantFav(restaurant.getId()) ? R.mipmap.fav : R.mipmap.un_fav);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mActivity, MapsActivity.class);
                    mActivity.startActivity(intent);
                }
            });
            if (mIconClickable) {
                holder.favIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (AzRestaurantManager.getInstance()
                                .isRestaurantFav(restaurant.getId())) {
                            //remove fav
                            holder.favIcon.setImageResource(R.mipmap.un_fav);
                            AzRestaurantManager.getInstance().removeFavRestaurant(restaurant);
                        } else {
                            //add fav
                            holder.favIcon.setImageResource(R.mipmap.fav);
                            AzRestaurantManager.getInstance().addFavRestaurant(restaurant);
                        }

                    }
                });
            }
        }
        return convertView;
    }

    static class ViewHolder {
        TextView name;
        ImageView favIcon;
    }

}

