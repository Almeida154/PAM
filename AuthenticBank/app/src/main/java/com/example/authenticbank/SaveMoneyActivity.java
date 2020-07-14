package com.example.authenticbank;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import maes.tech.intentanim.CustomIntent;
import static com.example.authenticbank.HomeActivity.toBrazilianCoin;

public class SaveMoneyActivity extends AppCompatActivity {
    CheckBox cbTermSaveMoney;
    EditText edtHowMuch;
    TextView txvBalanceSaveMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_money);
        cbTermSaveMoney = findViewById(R.id.cbTermSaveMoney);
        edtHowMuch = findViewById(R.id.edtHowMuch);
        txvBalanceSaveMoney = findViewById(R.id.txvBalanceSaveMoney);
        cbTermSaveMoney.setChecked(false);
        txvBalanceSaveMoney.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance()));

    }

    public void btnToApplySaveMoney(View view) {
        if(edtHowMuch.length() == 0){
            DialogError someAmount = new DialogError("Error!", "Put some amount to continue.");
            someAmount.show(getSupportFragmentManager(), "someAmount");
            cbTermSaveMoney.setChecked(false);
        }else{
            if(cbTermSaveMoney.isChecked()){
                if(Double.parseDouble(edtHowMuch.getText().toString()) <= RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance()){
                    if((RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance() - Double.parseDouble(edtHowMuch.getText().toString())) >= 10){
                        if(Double.parseDouble(edtHowMuch.getText().toString()) > 0){
                            RegisterActivity.users.get(LoginActivity.userPos).setSaveMoneyStatus(true);
                            RegisterActivity.users.get(LoginActivity.userPos).setSaveMoney(Double.parseDouble(edtHowMuch.getText().toString()));
                            DialogSuccessful successful = new DialogSuccessful("Successful", "Amount saved successfully.");
                            successful.show(getSupportFragmentManager(), "successful");
                            txvBalanceSaveMoney.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance()));
                            edtHowMuch.setText("");
                            cbTermSaveMoney.setChecked(false);
                        }else{
                            DialogError save0 = new DialogError("Error!", "You can not save R$ 0,00.");
                            save0.show(getSupportFragmentManager(), "save0");
                            edtHowMuch.setText("");
                            cbTermSaveMoney.setChecked(false);
                        }
                    }else{
                        DialogError free10 = new DialogError("Error!", "It is not possible, you need to have R$ 10,00 free.");
                        free10.show(getSupportFragmentManager(), "free10");
                        edtHowMuch.setText("");
                        cbTermSaveMoney.setChecked(false);
                    }
                }else{
                    DialogError doNotHave = new DialogError("Error!", "You do not have that available amount.");
                    doNotHave.show(getSupportFragmentManager(), "doNotHave");
                    edtHowMuch.setText("");
                    cbTermSaveMoney.setChecked(false);
                }
            }else Toast.makeText(this, "You need to accept the term", Toast.LENGTH_SHORT).show();
        }
    }

    public void ibProfileSm(View view) {
        Intent toProfile = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(toProfile);
        CustomIntent.customType(SaveMoneyActivity.this, "up-to-bottom");
    }

    public void txvBackToHome(View view) {
        finish();
        Intent backToHome = new Intent(SaveMoneyActivity.this, HomeActivity.class);
        startActivity(backToHome);
        CustomIntent.customType(SaveMoneyActivity.this, "right-to-left");
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent backToHome = new Intent(SaveMoneyActivity.this, HomeActivity.class);
        startActivity(backToHome);
        CustomIntent.customType(SaveMoneyActivity.this, "right-to-left");
    }
}