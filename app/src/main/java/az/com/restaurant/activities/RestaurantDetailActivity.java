package az.com.restaurant.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.vision.text.Text;

import az.com.restaurant.R;
import az.com.restaurant.data.AzRestaurantManager;
import az.com.restaurant.model.AzRestaurant;

public class RestaurantDetailActivity extends AppCompatActivity {
    String restId;
    SimpleDraweeView headerImageView, coverImageView;
    TextView restNameTv, deliverPriceTv, mapTv;
    AzRestaurant currentRestuarant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        restId = extras.getString("rest_id");
        setContentView(R.layout.activity_restaurant_detail);
        headerImageView = (SimpleDraweeView) findViewById(R.id.head_image);
        coverImageView = (SimpleDraweeView) findViewById(R.id.cover_image);
        restNameTv = (TextView) findViewById(R.id.rest_name);
        deliverPriceTv = (TextView) findViewById(R.id.deliver_price);
        mapTv = (TextView) findViewById(R.id.map_link);
        currentRestuarant = AzRestaurantManager.getInstance().getRestaurantById(restId);
        mapTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantDetailActivity.this, MapsActivity.class);
                intent.putExtra("lat", currentRestuarant.getAddress().getLat());
                intent.putExtra("lon", currentRestuarant.getAddress().getLng());
                intent.putExtra("name", currentRestuarant.getName());
                RestaurantDetailActivity.this.startActivity(intent);
            }
        });

        refreshUI();
    }

    private void refreshUI() {
        //Header
        if (currentRestuarant.getHeadUrl() == null || currentRestuarant.getHeadUrl().trim().length() == 0) {
            Uri headUri = Uri.parse(currentRestuarant.getHeadUrl());
            headerImageView.setImageURI(headUri);
        }

        //Cover
        Uri coverUri = Uri.parse(currentRestuarant.getCoverUrl());
        coverImageView.setImageURI(coverUri);

        //Rest Name
        restNameTv.setText(currentRestuarant.getName());

        //Delivery price
        deliverPriceTv.setText("Delivery fee: " + currentRestuarant.getDeliverPrice() / 100);

    }
}
