package az.com.restaurant.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import az.com.restaurant.R;
import az.com.restaurant.activities.AzBaseActivity;
import az.com.restaurant.activities.FavoriteActivity;
import az.com.restaurant.activities.RestaurantDetailActivity;
import az.com.restaurant.adapter.RestaurantAdapter;
import az.com.restaurant.auth.AzAccountManager;
import az.com.restaurant.data.AzRestaurantManager;
import az.com.restaurant.http.HandlerCallback;
import az.com.restaurant.model.AzRestaurant;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    TextView addressTv;
    ListView itemsLv;
    ImageView favIcon;
    Activity mActivity;
    List<AzRestaurant> restaurants;
    private RestaurantAdapter restaurantAdapter;

    public HomeFragment() {
    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initInteraction() {
        favIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, FavoriteActivity.class);
                mActivity.startActivity(intent);
            }
        });

        itemsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                final AzRestaurant selectedRestaurant = (AzRestaurant) restaurantAdapter
                        .getItem(position);
                Intent intent = new Intent(mActivity, RestaurantDetailActivity.class);
                intent.putExtra("rest_id", selectedRestaurant.getId());
                mActivity.startActivity(intent);

            }
        });
    }

    private void refreshRestaurantList() {
        ((AzBaseActivity)this.getActivity()).showLoading();
        AzRestaurantManager.getInstance().loadRestaurantByLatLon(AzAccountManager.getInstance().getCurrentUser().getAddress().getLat(),
                AzAccountManager.getInstance().getCurrentUser().getAddress().getLng(), new HandlerCallback() {
                    @Override
                    public void onFinishSuccessful() {

                    }

                    @Override
                    public void onFinishFailed() {
                        ((AzBaseActivity)HomeFragment.this.getActivity()).hideLoading();
                        ((AzBaseActivity)HomeFragment.this.getActivity()).makeToast("Load failed!");
                    }

                    @Override
                    public void onFinishWithResult(Object obj) {
                        restaurants = (List<AzRestaurant>) obj;
                        restaurantAdapter = new RestaurantAdapter(HomeFragment.this.getActivity(), restaurants, true);
                        itemsLv.setAdapter(restaurantAdapter);
                        ((AzBaseActivity)HomeFragment.this.getActivity()).hideLoading();
                    }
                });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.fragment_home, container, false);
        addressTv = (TextView) ll.findViewById(R.id.address_name);
        addressTv.setText(AzAccountManager.getInstance().getCurrentUser().getAddress().getShortName());
        favIcon = (ImageView) ll.findViewById(R.id.fav_btn);
        itemsLv = (ListView) ll.findViewById(R.id.item_list);
        initInteraction();
        refreshRestaurantList();
        return  ll;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        mActivity = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
