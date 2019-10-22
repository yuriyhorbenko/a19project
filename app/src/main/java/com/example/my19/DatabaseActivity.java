package com.example.my19;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DatabaseActivity extends AppCompatActivity {
    SQLiteDatabase db;
    TextView text_lable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        final EditText edit_text = findViewById(R.id.edit_text);
        Button button = findViewById(R.id.button);
        text_lable = findViewById(R.id.text_lable);


        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_to_save = edit_text.getText().toString();
                if (!text_to_save.isEmpty()) {
                    writeData("param_name", text_to_save);
                    edit_text.setText(null);
                }
            }
        });
        readData(dbHelper);
    }


    public void readData(DBHelper dbHelper) {
        Cursor c = db.query("mytable", null, null, null, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("item_name");
            int itemValueColIndex = c.getColumnIndex("item_value");
            do {

                Integer index_value = c.getInt(idColIndex);
                String name_value = c.getString(nameColIndex);
                String item_value = c.getString(itemValueColIndex);
                // получаем значения по номерам столбцов и пишем все в лог
                Log.d("okok",
                        "ID = " + index_value +
                                ", name = " + name_value +
                                ", value = " + item_value);
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        } else {
            Log.d("okok", "0 rows");
        }
        c.close();

    }


    public long writeData(String item_name, String item_value) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("item_name", item_name);
        initialValues.put("item_value", item_value);
        long insert_results = db.insert("mytable", null, initialValues);
        Toast.makeText(this, "Текст сохранен удачно!", Toast.LENGTH_LONG).show();

        return insert_results;
    }


    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDBNew", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "item_name text,"
                    + "item_value text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
