package com.example.exercciosuri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt1Home, txt2Home, txt3Home, txt4Home, txt5Home, txt6Home, txt7Home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1Home = findViewById(R.id.txt1Home);
        txt2Home = findViewById(R.id.txt2Home);
        txt3Home = findViewById(R.id.txt3Home);
        txt4Home = findViewById(R.id.txt4Home);
        txt5Home = findViewById(R.id.txt5Home);
        txt6Home = findViewById(R.id.txt6Home);
        txt7Home = findViewById(R.id.txt7Home);
        Typeface font = Typeface.createFromAsset(getAssets(), "JosefinSans-Medium.ttf");
        txt1Home.setTypeface(font); txt2Home.setTypeface(font); txt3Home.setTypeface(font);
        txt4Home.setTypeface(font); txt5Home.setTypeface(font); txt6Home.setTypeface(font);
        txt7Home.setTypeface(font);


        ImageView next = findViewById(R.id.imgVgo1012);

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View View){
                Intent goto1012 = new Intent(MainActivity.this, Exer1012Activity.class);
                startActivity(goto1012);
            }
        });
    }
}
