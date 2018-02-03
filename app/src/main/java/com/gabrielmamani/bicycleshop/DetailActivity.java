package com.gabrielmamani.bicycleshop;

import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gabrielmamani.bicycleshop.entities.bicicleta;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_LISTA = "lista";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int position = getIntent().getIntExtra(EXTRA_POSITION, 0);
        Log.e("getIntent() -> ", "-- " + position);
        bicicleta _detailbici = BicycleContentFragment.bicicletas.get(position);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page
         collapsingToolbar.setTitle(_detailbici.getNombre_bici());






        /*int postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
        Resources resources = getResources();
        String[] places = resources.getStringArray(R.array.places);
        collapsingToolbar.setTitle(places[postion % places.length]);

        String[] placeDetails = resources.getStringArray(R.array.place_details);
        */
        TextView placeDetail = (TextView) findViewById(R.id.place_detail);
        placeDetail.setText(_detailbici.getDetalle());
        //placeDetail.setText(placeDetails[postion % placeDetails.length]);

        //String[] placeLocations = resources.getStringArray(R.array.place_locations);
        TextView placeLocation =  (TextView) findViewById(R.id.place_location);
        placeLocation.setText(_detailbici.getMarca());
        //placeLocation.setText(placeLocations[postion % placeLocations.length]);

        //TypedArray placePictures = resources.obtainTypedArray(R.array.places_picture);
        ImageView placePicutre = (ImageView) findViewById(R.id.image);

        Glide.with(this)
                .load(_detailbici.getImage())
                .into(placePicutre);

        //placePicutre.setImageDrawable(placePictures.getDrawable(postion % placePictures.length()));

        //placePictures.recycle();
    }
}
