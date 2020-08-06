package com.example.share;
import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // listeners of our two buttons
        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.buttonShareTextUrl:
                        shareTextUrl();
                        break;

                    case R.id.buttonShareImage:
                        shareImage();
                        break;
                }
            }
        };

        // our buttons
        findViewById(R.id.buttonShareTextUrl).setOnClickListener(handler);
        findViewById(R.id.buttonShareImage).setOnClickListener(handler);

    }

    // Method to share either text or URL.
    private void shareTextUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
        share.putExtra(Intent.EXTRA_TEXT, "http://www.youtube.com");

        startActivity(Intent.createChooser(share, "Share link!"));
    }


    // Method to share any image.
    private void shareImage() {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        final File photoFile = new File(getFilesDir(), "foo.jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(photoFile));
        startActivity(Intent.createChooser(shareIntent, "Share image using"));
    }
}