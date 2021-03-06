package com.example.my19;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity implements View.OnClickListener {
    ListView lvMain;
    TextView selectedNamesView;
    String[] names = {"Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь", "Анна", "Денис", "Андрей"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


        lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, names);

        // присваиваем адаптер списку
        lvMain.setAdapter(adapter);


        Button btnChecked = findViewById(R.id.btnChecked);
        selectedNamesView = findViewById(R.id.selected_names);

        btnChecked.setOnClickListener(this);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("okok","onItemClick position ="+position+"; id="+id);
            }
        });

        lvMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("okok","onItemSelected position ="+position+"; id="+id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("okok","onNothingSelected");

            }
        });

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d("okok","onScrollStateChanged scrollState="+scrollState);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d("okok","onScroll firstVisibleItem="+
                                firstVisibleItem+"; visibleItemCount="+
                                visibleItemCount+"totalItemCount="+totalItemCount
                        );
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnChecked:
                SparseBooleanArray sbArray = lvMain.getCheckedItemPositions();
                List<String> selectedNames = new ArrayList<String>();
                for (int i = 0; i < sbArray.size(); i++) {
                    int key = sbArray.keyAt(i);
                    if (sbArray.get(key)) {
                        selectedNames.add(names[key]);
                    }
                }

                if(selectedNames.size()>0){
                    String selectedNamesJoined = TextUtils.join(", ", selectedNames);
                    selectedNamesView.setText("Вы выбрали: "+selectedNamesJoined);
                }else {
                    selectedNamesView.setText("Вы ничего не выбрали");
                }
                break;
            default:

                break;
        }

    }
}
