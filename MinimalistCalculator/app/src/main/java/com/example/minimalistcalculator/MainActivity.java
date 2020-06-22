package com.example.minimalistcalculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    boolean firtsN = true;
    double firstValue = 0, secondValue = 0, n2;
    int value = 0;
    int i = 0, j = 0, cont = 0;
    String operation = "vazio";

    // Constante de Pi

    public void btnPi(View view) {
        cleaner();
        RecentScreen.txvRecent.setText("Pi");
        ResultScreen.txvAnswer.setText("3.14159265359");
    }

    //Constante de Euler

    public void btnEuler(View view) {
        cleaner();
        RecentScreen.txvRecent.setText("Euler");
        ResultScreen.txvAnswer.setText("2.71828182845");
    }

    // Teclado numérico

    public void btn0(View view) {
        value = 0;
        calcAnswer();
        presentation();
        i++;
    }

    public void btn1(View view) {
        value = 1;
        calcAnswer();
        presentation();
        i++;
    }

    public void btn2(View view) {
        value = 2;
        calcAnswer();
        presentation();
        i++;
    }

    public void btn3(View view) {
        value = 3;
        calcAnswer();
        presentation();
        i++;
    }

    public void btn4(View view) {
        value = 4;
        calcAnswer();
        presentation();
        i++;
    }

    public void btn5(View view) {
        value = 5;
        calcAnswer();
        presentation();
        i++;
    }

    public void btn6(View view) {
        value = 6;
        calcAnswer();
        presentation();
        i++;
    }

    public void btn7(View view) {
        value = 7;
        calcAnswer();
        presentation();
        i++;
    }

    public void btn8(View view) {
        value = 8;
        calcAnswer();
        presentation();
        i++;
    }

    public void btn9(View view) {
        value = 9;
        calcAnswer();
        presentation();
        i++;
    }

    // Teclado operatório

    public void btnAddition(View view) {
        cont++;
        if(cont > 1) Toast.makeText(this, "Apenas dois valores por calculo!", Toast.LENGTH_SHORT).show();
        else setOperation("+");
    }

    public void btnSubtraction(View view) {
        cont++;
        if(cont > 1) Toast.makeText(this, "Apenas dois valores por calculo!", Toast.LENGTH_SHORT).show();
        else setOperation("-");
    }

    public void btnMultiplication(View view) {
        cont++;
        if(cont > 1) Toast.makeText(this, "Apenas dois valores por calculo!", Toast.LENGTH_SHORT).show();
        else setOperation("*");
    }

    public void btnDivision(View view) {
        cont++;
        if(cont > 1) Toast.makeText(this, "Apenas dois valores por calculo!", Toast.LENGTH_SHORT).show();
        else setOperation("/");
    }

    public void btnElevated(View view) {
        cont++;
        if(cont > 1) Toast.makeText(this, "Apenas dois valores por calculo!", Toast.LENGTH_SHORT).show();
        else setOperation("xʸ");
    }

    public void btnClean(View view) {
        cleaner();
        ResultScreen.txvAnswer.setText("");
        RecentScreen.txvRecent.setText("");
    }

    // Acoplar números

    public double couple(double n1, double n2){
        double finalRes = (n1 * 10) + n2;
        return finalRes;
    }

    // Calculo das dezenas

    public void calcAnswer(){
        if(firtsN){
            if(i == 0) firstValue = value;
            else if(i != 0 && firtsN) {
                n2 = value;
                firstValue = couple(firstValue, n2);
                i = 0;
            }
        }else{
            if(j == 0) secondValue = value;
            else if(j != 0 && !firtsN) {
                n2 = value;
                secondValue = couple(secondValue, n2);
                j = 0;
            }
        }
    }

    // Resultado ( = )

    public void btnEqual(View view) {
        RecentScreen.txvRecent.setText("");
        double resultado = 0.0;
        if(!firtsN && !operation.equals("vazio")){
            switch(operation) {
                case "+":
                    resultado = firstValue + secondValue;
                    break;
                case "-":
                    resultado = firstValue - secondValue;
                    break;
                case "*":
                    resultado = firstValue * secondValue;
                    break;
                case "/":
                    resultado = firstValue / secondValue;
                    break;
                case "xʸ":
                    resultado = 1;
                    for (int i = 0; i < secondValue; i++) {
                        resultado *= firstValue;
                    }
                    break;
            }
            if(resultado % 1 == 0) {
                String resultadoInteiro = String.format("%.0f", resultado);
                ResultScreen.txvAnswer.setText(""+resultadoInteiro);
            }
            else ResultScreen.txvAnswer.setText(""+resultado);

        }else{
            Toast.makeText(this, "Você precisa fazer alguma operação!", Toast.LENGTH_SHORT).show();
        }
        cleaner();
    }

    // Limpador

    public void cleaner(){
        firstValue = 0;
        secondValue = 0;
        operation = "vazio";
        firtsN = true;
        i = 0;
        j = 0;
        cont = 0;
    }

    // Setar operação

    public void setOperation(String operation){
        firtsN = false;
        this.operation = operation;
        RecentScreen.txvRecent.setText(operation);
    }

    // Apresentação

    public void presentation(){
        String recentValues1 = String.format("%.0f", firstValue);
        String recentValues2 = String.format("%.0f", secondValue);
        if(firtsN) RecentScreen.txvRecent.setText(recentValues1);
        else {
            j++;
            RecentScreen.txvRecent.setText(recentValues2);
        }
        ResultScreen.txvAnswer.setText("");
    }
}