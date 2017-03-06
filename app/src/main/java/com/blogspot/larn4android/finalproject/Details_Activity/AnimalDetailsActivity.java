package com.blogspot.larn4android.finalproject.Details_Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.larn4android.finalproject.M_Model.PostAnimal;
import com.blogspot.larn4android.finalproject.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class AnimalDetailsActivity extends AppCompatActivity {

    private ImageView mImage;
    private TextView mTitle,mDesc;
    private ArrayList<PostAnimal> mRestaurants = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_details);


        mTitle = (TextView) findViewById(R.id.detailsanimalTitle);
        mDesc = (TextView)findViewById(R.id.detailsAnimalDesc);
        mImage = (ImageView)findViewById(R.id.detailsAnimalImage);

        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        //ArrayList<PostFish> mRestaurants =(ArrayList<PostFish>)getIntent().getSerializableExtra("restaurants");

        mRestaurants = Parcels.unwrap(getIntent().getParcelableExtra("restaurants"));

        String name = mRestaurants.get(startingPosition).getanimalPostTitle();
        String description = mRestaurants.get(startingPosition).getanimalPostDescription();
        String image = mRestaurants.get(startingPosition).getanimalPostImage();


        mTitle.setText(name);
        mDesc.setText(description);
        Picasso.with(getApplicationContext())
                .load(image)
                //.resize(200, 200)
                .placeholder(R.drawable.fishplace)
                .error(R.mipmap.ic_launcher)
                //.centerCrop()
                .into(mImage);






        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
