package com.example.braziliansteamsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CheckBox SaoPaulo, Borussia, Santos, Flamengo, Pato, Bragantino, Barcelona, Liverpool;
    TextView answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SaoPaulo = findViewById(R.id.spfc);
        Borussia = findViewById(R.id.bd);
        Santos = findViewById(R.id.sfc);
        Flamengo = findViewById(R.id.crf);
        Pato = findViewById(R.id.pcfc);
        Bragantino = findViewById(R.id.rbb);
        Barcelona = findViewById(R.id.fcb);
        Liverpool = findViewById(R.id.lfc);
        answer = findViewById(R.id.result);
    }

    public void testar(View view) {
        if(SaoPaulo.isChecked() && Santos.isChecked() && Flamengo.isChecked() && Bragantino.isChecked() && Borussia.isChecked() && Pato.isChecked() && Barcelona.isChecked() && Liverpool.isChecked()){
            answer.setText("Errado! Preste atenção.");
        }
        if(SaoPaulo.isChecked() && Santos.isChecked() && Flamengo.isChecked() && Bragantino.isChecked() && Borussia.isChecked() && Pato.isChecked() && Barcelona.isChecked()){
            answer.setText("Errado! Preste atenção.");
        }
        if(SaoPaulo.isChecked() && Santos.isChecked() && Flamengo.isChecked() && Bragantino.isChecked() && Borussia.isChecked() && Pato.isChecked()) {
            answer.setText("Errado! Preste atenção.");
        }
        if(SaoPaulo.isChecked() && Santos.isChecked() && Flamengo.isChecked() && Bragantino.isChecked() && Borussia.isChecked()) {
            answer.setText("Errado! Preste atenção.");
        }
        if(SaoPaulo.isChecked() && Santos.isChecked() && Flamengo.isChecked() && Bragantino.isChecked() && !Borussia.isChecked() && !Pato.isChecked() && !Barcelona.isChecked() && !Liverpool.isChecked()){
            answer.setText("Parabéns! Você acertou todos.");
        }
        else{
            answer.setText("Errado! Preste atenção.");
        }
        SaoPaulo.setChecked(false);
        Santos.setChecked(false);
        Flamengo.setChecked(false);
        Bragantino.setChecked(false);
        Borussia.setChecked(false);
        Pato.setChecked(false);
        Barcelona.setChecked(false);
        Liverpool.setChecked(false);
    }
}
