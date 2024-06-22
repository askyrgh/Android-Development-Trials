package com.askyr.videoview;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);

        mediaController = new MediaController(this);

        // Uri parser object used to hold the path to the video

        //Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video_example);// local path of video used as argument
        Uri uri = Uri.parse("https://www.radiantmediaplayer.com/media/big-buck-bunny-360p.mp4");    // URL of the video used as argument

        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);

        videoView.start();
    }


}