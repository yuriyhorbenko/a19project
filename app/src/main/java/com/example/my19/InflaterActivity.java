package com.example.my19;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class InflaterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflater);

        LinearLayout container = findViewById(R.id.container);
        LayoutInflater inflater = getLayoutInflater();
        View textObj = inflater.inflate(R.layout.insertion, null, false);
        ViewGroup.LayoutParams lp1 = textObj.getLayoutParams();
        Log.d("okok", "Class of view: " + textObj.getClass().toString());
        //TextView textView = textObj.findViewById(R.id.text_View);
      //  textView.setText("my name is yura");
       // container.addView(textObj);

//        View textObj2 = inflater.inflate(R.layout.insertion,null, false);
//        TextView textView2 = textObj2.findViewById(R.id.text_View);
//        textView2.setText("my name is kolia");
//        container.addView(textObj2);
    }
}
