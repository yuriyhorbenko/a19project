package com.example.my19;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DialogsActivity extends AppCompatActivity implements View.OnClickListener {

    int myHour = 14;
    int myMinute = 35;

    int myYear = 2011;
    int myMonth = 02;
    int myDay = 03;
    TextView tvTime, tvDate, alertDialog;
    Button btnDefault, btnHoriz;
    Context ctx;
    ProgressDialog pd;
    Handler h;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);
        ctx = this;
        tvTime = findViewById(R.id.tvTime);
        tvDate = findViewById(R.id.tvDate);
        alertDialog = findViewById(R.id.alertDialog);
        btnDefault = findViewById(R.id.btnDefault);
        btnHoriz = findViewById(R.id.btnHoriz);

        tvTime.setOnClickListener(this);
        tvDate.setOnClickListener(this);
        alertDialog.setOnClickListener(this);
        btnDefault.setOnClickListener(this);
        btnHoriz.setOnClickListener(this);

    }

    TimePickerDialog.OnTimeSetListener timeCallBack = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            tvTime.setText("Time is " + myHour + " hours " + myMinute + " minutes");
        }
    };
    DatePickerDialog.OnDateSetListener dateCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
            tvDate.setText("Today is " + myDay + "/" + myMonth + "/" + myYear);
        }
    };

    void showAlertDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View textObj = inflater.inflate(R.layout.lv_item, null, false);

        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        adb.setTitle("Exit");
        adb.setMessage("Do you want to save data?");
        adb.setView(textObj);
        adb.setIcon(android.R.drawable.ic_dialog_info);
        adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ctx, "Data was saved", Toast.LENGTH_LONG).show();
            }

        });
        adb.setNegativeButton("No", null);
        adb.setNeutralButton("Cancel", null);

        adb.create();
        adb.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvTime:
                TimePickerDialog timeDlg = new TimePickerDialog(this, timeCallBack, 14, 35, true);
                timeDlg.show();
                break;
            case R.id.tvDate:
                DatePickerDialog dateDlg = new DatePickerDialog(this, dateCallBack, myYear, myMonth, myDay);
                dateDlg.show();
                break;
            case R.id.alertDialog:
                showAlertDialog();
                break;
            case R.id.btnDefault:
                 pd = new ProgressDialog(this);
                pd.setTitle("Title");
                pd.setMessage("Message");
                // добавляем кнопку
                pd.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(ctx,"ProgressDialog clicked!!!",Toast.LENGTH_LONG).show();
                    }
                });
                pd.show();
                break;
//
            case R.id.btnHoriz:
                pd = new ProgressDialog(this);
                pd.setTitle("Title");
                pd.setMessage("Message");
                // меняем стиль на индикатор
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                // устанавливаем максимум
                pd.setMax(2148);
                // включаем анимацию ожидания
                pd.setIndeterminate(true);
                pd.show();
                h = new Handler() {
                    public void handleMessage(Message msg) {
                        // выключаем анимацию ожидания
                        pd.setIndeterminate(false);
                        if (pd.getProgress() < pd.getMax()) {
                            // увеличиваем значения индикаторов
                            pd.incrementProgressBy(50);
                           // pd.incrementSecondaryProgressBy(75);
                            h.sendEmptyMessageDelayed(0, 100);
                        } else {
                            pd.dismiss();
                        }
                    }
                };
                h.sendEmptyMessageDelayed(0, 2000);
                break;
            default:
                break;
        }
    }
}
