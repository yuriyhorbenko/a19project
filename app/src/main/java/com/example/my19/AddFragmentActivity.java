package com.example.my19;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class AddFragmentActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener, Fragment2.OnFragmentInteractionListener {

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fragment);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
                if ((i % 2) == 0) {
                    Fragment2 frag2 = new Fragment2();
                    fTrans.add(R.id.frgmCont, frag2);
                } else {
                    Fragment1 frag1 = new Fragment1();
                    fTrans.add(R.id.frgmCont, frag1);
                }
                i++;
                fTrans.commit();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //Fragment1 fragment1 = Fragment1.newInstance();
        //you can leave it empty
    }
}
