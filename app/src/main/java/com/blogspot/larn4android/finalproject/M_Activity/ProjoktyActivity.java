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

import com.blogspot.larn4android.finalproject.Details_Activity.ProDetailsActivity;
import com.blogspot.larn4android.finalproject.M_Model.PostProjokty;
import com.blogspot.larn4android.finalproject.Post_Activity.PostProActivity;
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

public class ProjoktyActivity extends AppCompatActivity {

    private RecyclerView mFosolList;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projokty);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Projokty");
        mFosolList = (RecyclerView)findViewById(R.id.proRecycleView);
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

                Intent intent = new Intent(ProjoktyActivity.this, PostProActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<PostProjokty,BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PostProjokty, BlogViewHolder>(

                PostProjokty.class,
                R.layout.recycler_fish,
                BlogViewHolder.class,
                mDatabase
        )
        {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, PostProjokty model, int position) {

                viewHolder.setproPostTitle(model.getproPostTitle());
                viewHolder.setproPostDescription(model.getproPostDescription());
                viewHolder.setproPostImage(getApplicationContext(),model.getproPostImage());

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
        public void setproPostTitle(String proPostTitle){

            TextView post_title = (TextView)mView.findViewById(R.id.post_title_fish);
            post_title.setText(proPostTitle);
        }
        public void setproPostDescription(String proPostDescription){

            TextView post_desc = (TextView)mView.findViewById(R.id.post_desc_fish);
            post_desc.setText(proPostDescription);
        }
        public void setproPostImage(Context ctx , String proPostImage){


            ImageView post_image = (ImageView)mView.findViewById(R.id.post_image_fish);
            Picasso.with(ctx)
                    .load(proPostImage)
                    //.centerCrop()
                    //.resize(post_image.getMeasuredWidth(),post_image.getMeasuredHeight())
                    .placeholder(R.drawable.fishplace)
                    .error(R.mipmap.ic_launcher)
                    .into(post_image);

        }

        @Override
        public void onClick(View v) {

            final ArrayList<PostProjokty> fosols = new ArrayList<>();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Projokty");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        fosols.add(snapshot.getValue(PostProjokty.class));
                    }

                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, ProDetailsActivity.class);
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
