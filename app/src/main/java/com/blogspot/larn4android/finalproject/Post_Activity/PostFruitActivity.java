package com.blogspot.larn4android.finalproject.Post_Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.blogspot.larn4android.finalproject.M_Activity.FruitActivity;
import com.blogspot.larn4android.finalproject.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostFruitActivity extends AppCompatActivity {

    private ImageButton mSelectImage;
    private static final int GALLERY_REQUST = 1;
    private EditText mPostTitle,mPostDes;
    private Uri imageUri =null;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;
    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_fruit);
        mStorage = FirebaseStorage.getInstance().getReference();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Fruit");

        mSelectImage = (ImageButton)findViewById(R.id.btnImagePostFruit);
        mPostTitle = (EditText)findViewById(R.id.etFruitPostTitle);
        mPostDes = (EditText)findViewById(R.id.etFruitPostDesc);
        mProgress = new ProgressDialog(this);
        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUST);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startPosting();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void startPosting() {

        mProgress.setMessage("Posting to blog....");

        final String title_val = mPostTitle.getText().toString().trim();
        final String desc_val = mPostDes.getText().toString().trim();
        if(!TextUtils.isEmpty(title_val)&&!TextUtils.isEmpty(desc_val)&&imageUri!= null ){
            mProgress.show();
            StorageReference filePath = mStorage.child("Fruit").child(imageUri.getLastPathSegment());
            filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri downloadUri = taskSnapshot.getDownloadUrl();
                    DatabaseReference newPost = mDatabase.push();
                    newPost.child("fruitPostTitle").setValue(title_val);
                    newPost.child("fruitPostDescription").setValue(desc_val);
                    newPost.child("fruitPostImage").setValue(downloadUri.toString());
                    mProgress.dismiss();
                    startActivity(new Intent(PostFruitActivity.this, FruitActivity.class));
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_REQUST && resultCode==RESULT_OK){
            imageUri = data.getData();
            mSelectImage.setImageURI(imageUri);
        }
    }
}
