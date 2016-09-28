package edu.orangecoastcollege.cs273.ocmusicevent;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class EventDetailsActivity extends AppCompatActivity {

    private ImageView eventImageView;
    private TextView eventTextView;
    private TextView eventDetailsTextView;

    // In order to user AssetManager, need to know Context
    private Context context = (Context) this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        eventImageView = (ImageView) findViewById(R.id.eventImageView);
        eventTextView = (TextView) findViewById(R.id.eventTitleTextView);
        eventDetailsTextView = (TextView) findViewById(R.id.eventDetailsTextView);


        // Get the data from Intent
        Intent detailsIntent = getIntent();
        String title = detailsIntent.getStringExtra("Title");
        String details = detailsIntent.getStringExtra("Details");
        String imageFileName = title.replace(" ","") + ".jpeg";

        eventTextView.setText(title);
        eventDetailsTextView.setText(details);
        // Load the image from the Assets folder using the AssetManager class
        AssetManager am = context.getAssets();

        // Try to load the image file
        try {
            InputStream stream = am.open(imageFileName);
            Drawable image = Drawable.createFromStream(stream, title);
            eventImageView.setImageDrawable(image);
        }
        catch (IOException ex){
            Log.e("OC Music Events", "Cannot load image: " + imageFileName + ex);
        }


    }
}
