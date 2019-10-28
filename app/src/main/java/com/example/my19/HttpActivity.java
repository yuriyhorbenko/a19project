package com.example.my19;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpActivity extends AppCompatActivity {
    private static final String USER_AGENT = "Mozilla/5.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL obj = new URL("https://auto.ria.com/newauto_blocks/marka/models?lang_id=2&category_id=1&marka_id=9");
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", USER_AGENT);
                    int responseCode = con.getResponseCode();
                    System.out.println("GET Response Code :: " + responseCode);
                    if (responseCode == HttpURLConnection.HTTP_OK) { // success
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();

                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();

                        // print result
                        Log.d("okok", response.toString());
                    } else {
                        Log.d("okok", "GET request not worked");
                    }
                } catch (java.net.MalformedURLException e) {
                    e.printStackTrace();
                } catch (java.net.ProtocolException e1) {
                    e1.printStackTrace();
                } catch (java.io.IOException e2) {
                    e2.printStackTrace();
                }
            }
        });

        thread1.start();

    }
}
