package com.example.my19;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText textObj;
    TextView textObjToSetup;
    Context ctx;
    final String TAG = "States";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "MainActivity: onCreate()");
        ctx = this;
        textObj = findViewById(R.id.edit_text_to_get);
        Button button = findViewById(R.id.button_to_press);
        textObjToSetup = findViewById(R.id.text_to_show);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_to_use = textObj.getText().toString();
                if (text_to_use.length() > 0) {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("text_to_save", text_to_use);
                    editor.commit();


                }

                Intent myIntent = new Intent(ctx, PagerActivity.class);
                //myIntent.putExtra("text_to_get",text_to_use);
                startActivity(myIntent);
//                String text_to_use = textObj.getText().toString();
//                if(text_to_use.length()>0){
//                    textObjToSetup.setText(text_to_use);
//                }
            }
        });
    }





    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "MainActivity: onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "MainActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity: onDestroy()");
    }
}
