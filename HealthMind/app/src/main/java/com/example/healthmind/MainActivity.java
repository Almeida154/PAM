package com.example.healthmind;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private int splashTime = 1000; // 1 segundo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent SplashToLogin = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(SplashToLogin);
                finish();
            }
        }, splashTime);
    }
}