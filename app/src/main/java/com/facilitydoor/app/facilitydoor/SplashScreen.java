package com.facilitydoor.app.facilitydoor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.ConnectionCarousel;
import com.facilitydoor.app.facilitydoor.connectionutils.connectionclasses.ConnectionTrend;

/**
 * Created by root on 13/6/16.
 */
public class SplashScreen extends AppCompatActivity {
    String responsetrend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        String url = "http://facilitydoor.com/api/trendservice.php";
        ConnectionTrend connectionTrend = new ConnectionTrend(url, SplashScreen.this, this);
        connectionTrend.connect();

    }

    public void parsetrend(String response) {
        if(!response.equals("error")) {
            responsetrend = response;
            ConnectionCarousel connectionCarousel = new ConnectionCarousel("http://facilitydoor.com/api/carousel.php", SplashScreen.this, this);
            connectionCarousel.connect();
        }
        else {
            Intent intent = new Intent(SplashScreen.this, SplashScreen.class);
            startActivity(intent);
        }
    }

    public void parsejsoncarousel(String response) {
        if(!response.equals("error")) {
            Intent in = new Intent(SplashScreen.this, MainActivity.class);
            in.putExtra("trend", responsetrend);
            in.putExtra("carousel", response);
            startActivity(in);
        }
        else {
            Intent intent = new Intent(SplashScreen.this, SplashScreen.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
