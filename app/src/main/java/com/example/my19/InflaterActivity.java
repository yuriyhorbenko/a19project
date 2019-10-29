package com.example.my19;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InflaterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflater);

        LinearLayout container = findViewById(R.id.container);
        LayoutInflater inflater = getLayoutInflater();
        View textObj = inflater.inflate(R.layout.insertion, container, true);
        ViewGroup.LayoutParams lp1 = textObj.getLayoutParams();

        TextView textV = textObj.findViewById(R.id.text_View);
        textV.setText("my name is Kolia");
       // container.addView(textObj);
        ViewGroup.LayoutParams lp2 = textObj.getLayoutParams();
        View textObj2 = inflater.inflate(R.layout.insertion, null, false);
        View textObj3 = inflater.inflate(R.layout.insertion, null, false);
        container.addView(textObj2);
        container.addView(textObj3);

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
