package com.example.spinnerradiobuttonandcheckbox;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup raG1, raG2;
    Spinner spnQ1;
    RadioButton rbQ2opcAlanKey, rbQ2opcAlanFerreira, rbQ2opcTonyStark;
    RadioButton rbQ3opcPython, rbQ3opcJavaScript, rbQ3opcCpp;
    CheckBox cbQ4opcHTML, cbQ4opcKotlin, cbQ4opcJSON;
    TextView res1, res2, res3, res4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Quest 1
        spnQ1 = findViewById(R.id.spnQ1);

        //Quest 2
        rbQ2opcAlanKey = findViewById(R.id.rbQ2opcAlanKey);
        rbQ2opcAlanFerreira = findViewById(R.id.rbQ2opcAlanFerreira);
        rbQ2opcTonyStark = findViewById(R.id.rbQ2opcTonyStark);

        //Quest 3
        rbQ3opcPython = findViewById(R.id.rbQ3opcPython);
        rbQ3opcJavaScript = findViewById(R.id.rbQ3opcJavaScript);
        rbQ3opcCpp = findViewById(R.id.rbQ3opcCpp);

        //Quest 4
        cbQ4opcHTML = findViewById(R.id.cbQ4opcHTML);
        cbQ4opcKotlin = findViewById(R.id.cbQ4opcKotlin);
        cbQ4opcJSON = findViewById(R.id.cbQ4opcJSON);

        //Feedback
        res1 = findViewById(R.id.r1);
        res2 = findViewById(R.id.r2);
        res3 = findViewById(R.id.r3);
        res4 = findViewById(R.id.r4);

        //Clean
        raG1 = findViewById(R.id.raG1);
        raG2 = findViewById(R.id.raG2);
    }

    public void btnCorrigir(View view) {
        int sum = 0;
        boolean resp = true;
        String spinnerQuest1 = spnQ1.getSelectedItem().toString();

        //Quest 1
        if(spinnerQuest1.equals("CMS(Content Management System)")){
            sum += 3;
            res1.setText("Acertou!");
            res1.setTextColor(Color.GREEN);
        }else if(spinnerQuest1.equals("Escolha")){
            Toast.makeText(this, "Questão 1 não respondida", Toast.LENGTH_SHORT).show();
            resp = false;
        }else{
            res1.setText("Errou!");
            res1.setTextColor(Color.RED);
        }

        //Quest2
        if(rbQ2opcAlanKey.isChecked()){
            sum += 3;
            res2.setText("Acertou!");
            res2.setTextColor(Color.GREEN);
        }else if(rbQ2opcAlanFerreira.isChecked()){
            res2.setText("Errou!");
            res2.setTextColor(Color.RED);
        }else if(rbQ2opcTonyStark.isChecked()){
            res2.setText("Errou!");
            res2.setTextColor(Color.RED);
        }else if(!rbQ2opcTonyStark.isChecked() && !rbQ2opcAlanFerreira.isChecked() && !rbQ2opcAlanKey.isChecked()){
            Toast.makeText(this, "Questão 2 não respondida", Toast.LENGTH_SHORT).show();
            resp = false;
        }

        //Quest3
        if(rbQ3opcJavaScript.isChecked()){
            sum += 2;
            res3.setText("Acertou!");
            res3.setTextColor(Color.GREEN);
        }else if(rbQ3opcPython.isChecked()){
            res3.setText("Errou!");
            res3.setTextColor(Color.RED);
        }else if(rbQ3opcCpp.isChecked()){
            res3.setText("Errou!");
            res3.setTextColor(Color.RED);
        }else if(!rbQ3opcCpp.isChecked() && !rbQ3opcPython.isChecked() && !rbQ3opcJavaScript.isChecked()){
            Toast.makeText(this, "Questão 3 não respondida", Toast.LENGTH_SHORT).show();
            resp = false;
        }

        //Quest4
        if(cbQ4opcJSON.isChecked() && cbQ4opcHTML.isChecked() && !cbQ4opcKotlin.isChecked()) {
            sum += 2;
            res4.setText("Acertou!");
            res4.setTextColor(Color.GREEN);
        }else if(!cbQ4opcKotlin.isChecked() && !cbQ4opcHTML.isChecked() && !cbQ4opcJSON.isChecked()){
            Toast.makeText(this, "Questão 4 não respondida", Toast.LENGTH_SHORT).show();
            resp = false;
        }else{
            res4.setText("Errou!");
            res4.setTextColor(Color.RED);
        }

        if(resp == true){
            if(sum > 9) Toast.makeText(this, "Caraca, gabaritou! Pts: " + sum, Toast.LENGTH_LONG).show();
            if(sum > 7 && sum < 10) Toast.makeText(this, "Muito bom! Pts: " + sum, Toast.LENGTH_LONG).show();
            if(sum > 2 && sum < 8) Toast.makeText(this, "Boa! Pts: " + sum, Toast.LENGTH_LONG).show();
            if(sum == 0) Toast.makeText(this, "ERROR: Programador não encontrado! Pts: " + sum, Toast.LENGTH_LONG).show();
        }

    }

    public void btnLimpar(View view) {
        Clean();
        cbQ4opcHTML.setChecked(false);
        cbQ4opcKotlin.setChecked(false);
        cbQ4opcJSON.setChecked(false);
        res1.setText("3 pts");
        res1.setTextColor(Color.BLACK);
        res2.setText("3 pts");
        res2.setTextColor(Color.BLACK);
        res3.setText("2 pts");
        res3.setTextColor(Color.BLACK);
        res4.setText("2 pts");
        res4.setTextColor(Color.BLACK);
    }

    public void Clean(){
        raG1.clearCheck();
        raG2.clearCheck();
    }
}
