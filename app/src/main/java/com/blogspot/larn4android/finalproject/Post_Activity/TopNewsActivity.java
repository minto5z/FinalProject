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

import com.blogspot.larn4android.finalproject.MainActivity;
import com.blogspot.larn4android.finalproject.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class TopNewsActivity extends AppCompatActivity {

    private ImageButton mSelectImage;
    private static final int GALLERY_REQUST = 1;
    private EditText mPostTitle;
    private Uri imageUri =null;
    private ProgressDialog mProgress;

    private DatabaseReference mDatabase;
    private StorageReference mStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("TopNews");
        mSelectImage = (ImageButton)findViewById(R.id.imageTop);
        mPostTitle = (EditText)findViewById(R.id.etTopNews);

        mProgress = new ProgressDialog(this);
        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_REQUST);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void startPosting() {

        mProgress.setMessage("Posting to blog....");

        final String title_val = mPostTitle.getText().toString().trim();
        if(!TextUtils.isEmpty(title_val)&&imageUri!= null ){
            mProgress.show();
            StorageReference filePath = mStorage.child("TopImage").child(imageUri.getLastPathSegment());
            filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri downloadUri = taskSnapshot.getDownloadUrl();

                    DatabaseReference newPost = mDatabase.push();
                    newPost.child("newsTitle").setValue(title_val);
                    newPost.child("newsImage").setValue(downloadUri.toString());
                    mProgress.dismiss();
                    startActivity(new Intent(TopNewsActivity.this,MainActivity.class));
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
