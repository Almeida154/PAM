package com.example.spacesoft;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {
    TextView hellouser, dashboard, welcome, s1, s2, s3, grana, saldo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Typeface itc = Typeface.createFromAsset(getAssets(), "ITC Avant Garde Gothic LT Bold.otf");
        Typeface js = Typeface.createFromAsset(getAssets(), "JosefinSans-Medium.ttf");

        hellouser = findViewById(R.id.hellouser);
        dashboard = findViewById(R.id.dashboard);
        welcome = findViewById(R.id.welcome);
        s1 = findViewById(R.id.section1);
        s2 = findViewById(R.id.section2);
        s3 = findViewById(R.id.section3);
        grana = findViewById(R.id.grana);
        saldo = findViewById(R.id.saldo);

        hellouser.setTypeface(itc);
        dashboard.setTypeface(itc);
        welcome.setTypeface(js);
        s1.setTypeface(js);
        s2.setTypeface(js);
        s3.setTypeface(js);
        grana.setTypeface(js);
        saldo.setTypeface(itc);
        hellouser.setText("Hi, " + LoginScreen.HomeUser);
        grana.setText("R$" + LoginScreen.HomeBalance);
    }
}
