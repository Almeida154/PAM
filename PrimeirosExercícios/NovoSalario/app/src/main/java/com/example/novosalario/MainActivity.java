package com.example.novosalario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText SalarioAtual;
    RadioButton FortyPerCent, FortyFivePerCent, FiftyPerCent;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.answer);
        SalarioAtual = findViewById(R.id.edtSa);
        FortyPerCent = findViewById(R.id.rbForty);
        FortyFivePerCent = findViewById(R.id.rbFortyFive);
        FiftyPerCent = findViewById(R.id.rbFifty);
    }

    public void btnVerificar(View view) {
        double SalarioNow, SalarioAfter = 0, Percent;
        SalarioNow = Double.parseDouble(SalarioAtual.getText().toString());
        result.setText("Novo salário:");
        if(!FortyPerCent.isChecked() && !FortyFivePerCent.isChecked() && !FiftyPerCent.isChecked()){
            Toast.makeText(getApplicationContext(), "Meu camarada, escolha uma opção de acréscimo!", Toast.LENGTH_LONG).show();
        }
        else if(FortyPerCent.isChecked()){
            Percent = (SalarioNow * 40) / 100;
            SalarioAfter = SalarioNow + Percent;
        }
        else if(FortyFivePerCent.isChecked()){
            Percent = (SalarioNow * 45) / 100;
            SalarioAfter = SalarioNow + Percent;
        }
        else if(FiftyPerCent.isChecked()){
            Percent = (SalarioNow * 50) / 100;
            SalarioAfter = SalarioNow + Percent;
        }
        result.append(" R$"+SalarioAfter);
    }
}
