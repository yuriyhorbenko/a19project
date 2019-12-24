package com.example;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my19.R;

import java.util.List;

public class SensorsActivity extends AppCompatActivity {
    TextView tvText;
    SensorManager sensorManager;
    List<Sensor> sensors;
    Sensor sensorLight;
    SensorEventListener listenerLight = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            tvText.setText(String.valueOf(event.values[0]));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        tvText = (TextView) findViewById(R.id.tvText);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    public void onClickSensList(View v) {

        sensorManager.unregisterListener(listenerLight, sensorLight);
        StringBuilder sb = new StringBuilder();

        for (Sensor sensor : sensors) {
            sb.append("name = ").append(sensor.getName())
                    .append(", type = ").append(sensor.getType())
                    .append("\nvendor = ").append(sensor.getVendor())
                    .append(" ,version = ").append(sensor.getVersion())
                    .append("\nmax = ").append(sensor.getMaximumRange())
                    .append(", resolution = ").append(sensor.getResolution())
                    .append("\n--------------------------------------\n");
        }
        tvText.setText(sb);
    }

    public void onClickSensLight(View v) {
        sensorManager.registerListener(listenerLight, sensorLight,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listenerLight, sensorLight);
    }
}
