package com.blogspot.larn4android.finalproject.M_Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.larn4android.finalproject.Details_Activity.ValueFosolDetailsActivity;
import com.blogspot.larn4android.finalproject.M_Model.PostValue;
import com.blogspot.larn4android.finalproject.Post_Activity.PostValueFosolActivity;
import com.blogspot.larn4android.finalproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class ValueFosolActivity extends AppCompatActivity {

    private RecyclerView mFosolList;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_fosol);
        //@Bind(R.id.fishRecyclerView) RecyclerView mRecyclerView;


        mDatabase = FirebaseDatabase.getInstance().getReference().child("ValueFosol");
        mFosolList = (RecyclerView)findViewById(R.id.valueFosolRecycleView);
        mFosolList.setHasFixedSize(true);
        mFosolList.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent = new Intent(ValueFosolActivity.this, PostValueFosolActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<PostValue,BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PostValue, BlogViewHolder>(

                PostValue.class,
                R.layout.recycler_fish,
                BlogViewHolder.class,
                mDatabase
        )
        {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, PostValue model, int position) {

                viewHolder.setvaluePostTitle(model.getvaluePostTitle());
                viewHolder.setvaluePostDescription(model.getvaluePostDescription());
                viewHolder.setvaluePostImage(getApplicationContext(),model.getvaluePostImage());

            }
        };
        mFosolList.setAdapter(firebaseRecyclerAdapter);
        // mFosolList.setOn
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View mView;
        Context mContext;
        public BlogViewHolder(View itemView){
            super(itemView);
            mView = itemView;
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        public void setvaluePostTitle(String valuePostTitle){

            TextView post_title = (TextView)mView.findViewById(R.id.post_title_fish);
            post_title.setText(valuePostTitle);
        }
        public void setvaluePostDescription(String valuePostDescription){

            TextView post_desc = (TextView)mView.findViewById(R.id.post_desc_fish);
            post_desc.setText(valuePostDescription);
        }
        public void setvaluePostImage(Context ctx , String valuePostImage){


            ImageView post_image = (ImageView)mView.findViewById(R.id.post_image_fish);
            Picasso.with(ctx)
                    .load(valuePostImage)
                    //.centerCrop()
                    //.resize(post_image.getMeasuredWidth(),post_image.getMeasuredHeight())
                    .placeholder(R.drawable.fishplace)
                    .error(R.mipmap.ic_launcher)
                    .into(post_image);

        }

        @Override
        public void onClick(View v) {

            final ArrayList<PostValue> fosols = new ArrayList<>();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("ValueFosol");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        fosols.add(snapshot.getValue(PostValue.class));
                    }

                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, ValueFosolDetailsActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    //intent.putExtra("restaurants", restaurants);
                    intent.putExtra("restaurants", Parcels.wrap(fosols));

                    mContext.startActivity(intent);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
    }

}
