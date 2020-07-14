package com.example.authenticbank;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnNext(View view) {
        Intent toLoginScreen = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(toLoginScreen);
    }
}