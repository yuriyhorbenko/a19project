package com.example.my19;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

public class FragmentContainerActivity extends FragmentActivity implements Fragment1.OnFragmentInteractionListener, Fragment2.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
    }

    @Override
    public void onFragmentInteraction(Uri uri){
        //Fragment1 fragment1 = Fragment1.newInstance();
        //you can leave it empty
    }


}
