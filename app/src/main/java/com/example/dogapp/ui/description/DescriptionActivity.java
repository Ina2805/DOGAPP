package com.example.dogapp.ui.description;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dogapp.R;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        String description = getIntent().getStringExtra("DESCRIPTION");
        int image = getIntent().getIntExtra("IMAGE", 0);

        TextView name_description = findViewById(R.id.description);
        ImageView image_description = findViewById(R.id.imageDescription);


        name_description.setText(description);
       image_description.setImageResource(image);


    }
}