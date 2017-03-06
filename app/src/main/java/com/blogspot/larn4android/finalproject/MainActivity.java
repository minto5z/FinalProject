package com.blogspot.larn4android.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.larn4android.finalproject.M_Activity.AnimalActivity;
import com.blogspot.larn4android.finalproject.M_Activity.FishActivity;
import com.blogspot.larn4android.finalproject.M_Activity.FosolActivity;
import com.blogspot.larn4android.finalproject.M_Activity.FruitActivity;
import com.blogspot.larn4android.finalproject.M_Activity.ProjoktyActivity;
import com.blogspot.larn4android.finalproject.M_Activity.ValueFosolActivity;
import com.blogspot.larn4android.finalproject.Post_Activity.TopNewsActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private TextView tv;
    private RecyclerView mTopImageList;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("TopNews");
        mTopImageList = (RecyclerView)findViewById(R.id.topRecycleView);
        mTopImageList.setHasFixedSize(true);
        mTopImageList.setLayoutManager(new LinearLayoutManager(this));



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //marquee text
        tv = (TextView) this.findViewById(R.id.TextView03);


//        recyclerView = (RecyclerView)findViewById(R.id.titleImage);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ///Button

        Button one = (Button)findViewById(R.id.btnOne);
        Button two = (Button)findViewById(R.id.btnTwo);
        Button three = (Button)findViewById(R.id.btnThree);
        Button four = (Button)findViewById(R.id.btnFour);
        Button five = (Button)findViewById(R.id.btnFive);
        Button six = (Button)findViewById(R.id.btnSix);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        // Use bounce interpolator with amplitude 0.2 and frequency 20
        BounceInterpolator interpolator = new BounceInterpolator();
        myAnim.setInterpolator(interpolator);
        one.startAnimation(myAnim);
        two.startAnimation(myAnim);
        three.startAnimation(myAnim);
        four.startAnimation(myAnim);
        five.startAnimation(myAnim);
        six.startAnimation(myAnim);


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Pressed One",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, FishActivity.class);
                startActivity(i);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Pressed One",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, AnimalActivity.class);
                startActivity(i);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Pressed One",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, FosolActivity.class);
                startActivity(i);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Pressed One",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, ValueFosolActivity.class);
                startActivity(i);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Pressed One",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, ProjoktyActivity.class);
                startActivity(i);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Pressed One",Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, FruitActivity.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();


        DatabaseReference mConditionRef = FirebaseDatabase.getInstance().getReference().child("TopTitle");
        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text  = dataSnapshot.getValue(String.class);
                tv.setText(text);
                tv.setSelected(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        FirebaseRecyclerAdapter<TopNews,BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TopNews, BlogViewHolder>(

                TopNews.class,
                R.layout.top_recycler,
                BlogViewHolder.class,
                mDatabase
        )
        {

            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, TopNews model, int position) {
                viewHolder.setNewsTitle(model.getNewsTitle());
                viewHolder.setNewsImage(getApplicationContext(),model.getNewsImage());
            }
        };
        mTopImageList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BlogViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }
        public void setNewsTitle(String newsTitle){
            TextView post_title = (TextView)mView.findViewById(R.id.top_tv);
            post_title.setText(newsTitle);
        }
        public void setNewsImage(Context ctx , String newsImage){

            ImageView post_image = (ImageView)mView.findViewById(R.id.top_image);
            //Picasso.with(ctx).load(newsImage).into(post_image);
            Picasso.with(ctx)
                    .load(newsImage)
                    //.centerCrop()
                    //.resize(post_image.getMeasuredWidth(),post_image.getMeasuredHeight())
                    .placeholder(R.drawable.fishplace)
                    .error(R.mipmap.ic_launcher)
                    .into(post_image);

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_add){
            startActivity(new Intent(MainActivity.this, TopNewsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
