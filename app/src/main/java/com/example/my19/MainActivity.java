package com.example.my19;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText textObj;
    TextView textObjToSetup;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;
        textObj = findViewById(R.id.edit_text_to_get);
        Button button = findViewById(R.id.button_to_press);
        textObjToSetup = findViewById(R.id.text_to_show);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_to_use = textObj.getText().toString();
                if(text_to_use.length()>0) {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("text_to_save", text_to_use);
                    editor.commit();

                    Intent myIntent = new Intent(ctx, DescriptionActivity.class);
                    //myIntent.putExtra("text_to_get",text_to_use);
                    startActivity(myIntent);
                }
//                String text_to_use = textObj.getText().toString();
//                if(text_to_use.length()>0){
//                    textObjToSetup.setText(text_to_use);
//                }
            }
        });
    }
}
