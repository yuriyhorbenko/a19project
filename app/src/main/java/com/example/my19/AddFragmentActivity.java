package com.example.my19;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class AddFragmentActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener, Fragment2.OnFragmentInteractionListener {

    final int MENU_COLOR_RED = 1;
    final int MENU_COLOR_GREEN = 2;
    final int MENU_COLOR_BLUE = 3;
    int i = 0;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fragment);

        btnAdd = findViewById(R.id.btnAdd);
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
                // fTrans.addToBackStack(null);
                fTrans.commit();
            }
        });

        // registerForContextMenu(btnAdd);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //Fragment1 fragment1 = Fragment1.newInstance();
        //you can leave it empty
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.my_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_add:
//                Log.d("okok", "menu_add");
//                return true;
//            case R.id.menu_edit:
//                Log.d("okok", "menu_edit");
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v,
//                                    ContextMenu.ContextMenuInfo menuInfo) {
//
//        switch (v.getId()) {
//            case R.id.btnAdd:
//                menu.add(0, MENU_COLOR_RED, 0, "Red");
//                menu.add(0, MENU_COLOR_GREEN, 0, "Green");
//                menu.add(0, MENU_COLOR_BLUE, 0, "Blue");
//                break;
//        }
//    }
//
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            // пункты меню для tvColor
//            case MENU_COLOR_RED:
//                btnAdd.setBackgroundColor(Color.RED);
//                break;
//            case MENU_COLOR_GREEN:
//                btnAdd.setBackgroundColor(Color.GREEN);
//                break;
//            case MENU_COLOR_BLUE:
//                btnAdd.setBackgroundColor(Color.BLUE);
//                break;
//
//        }
//        return super.onContextItemSelected(item);
//    }

//    private void httpCall() {
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("https://auto.ria.com/newauto_blocks/marka/models?lang_id=2&category_id=1&marka_id=9")
//                .build();
//
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, final Response response) throws IOException {
//                if (!response.isSuccessful()) {
//                    throw new IOException("Unexpected code " + response);
//                } else {
//                    // do something wih the result
//                }
//            }
//        });
//    }
}
