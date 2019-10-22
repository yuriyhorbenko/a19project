package com.example.my19;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class HttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);


        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpGet httppost = new HttpGet("https://auto.ria.com/newauto_blocks/marka/models?lang_id=2&category_id=1&marka_id=9");
                HttpClient httpclient = new DefaultHttpClient();
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    int status = response.getStatusLine().getStatusCode();

                    if (status == 200) { // ok
                        HttpEntity entity = response.getEntity();
                        String data = EntityUtils.toString(entity);
                        JSONObject jsonobj = new JSONObject(data);
                        JSONArray marka_arr = jsonobj.getJSONArray("models");
                        Log.d("okok", "jsonobj  =" + jsonobj);
                        Log.d("okok", "marka_arr  =" + marka_arr);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
    }
}
