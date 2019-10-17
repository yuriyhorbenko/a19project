package com.example.my19;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Context ctx = this;
//        Intent intent = getIntent();
//        String textToShow = intent.getStringExtra("text_to_get");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        String textToShow = sharedPreferences.getString("text_to_save","default text");
        TextView textView = findViewById(R.id.textView);
        textView.setText(textToShow);
    }
}
