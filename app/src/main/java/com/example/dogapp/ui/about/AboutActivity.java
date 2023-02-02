package com.example.dogapp.ui.about;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.dogapp.R;

public class AboutActivity extends AppCompatActivity {

    ImageView imageView1;
    ImageView imageView2;
    TextView textView;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        textView = (TextView)findViewById(R.id.aboutUsText);
        imageView1 = (ImageView)findViewById(R.id.pictureOfMeAndMyDog);
        imageView2 = (ImageView)findViewById(R.id.onlySchatzi);
        videoView = (VideoView)findViewById(R.id.video);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.back_jump;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        imageView1.setImageResource(R.drawable.schatzi_and_me);
        imageView2.setImageResource(R.drawable.only_schatzi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}