package com.example.authenticbank;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import maes.tech.intentanim.CustomIntent;
import static com.example.authenticbank.HomeActivity.toBrazilianCoin;

public class ProfileActivity extends AppCompatActivity {
    TextView txvUserName, txvUserEmail, txvName, txvEmail, txvPassword, txvCurrentBalace, txvCreditCard, txvSaveMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        txvUserName = findViewById(R.id.txvUserName);
        txvUserEmail = findViewById(R.id.txvUserEmail);
        txvName = findViewById(R.id.txvName);
        txvEmail = findViewById(R.id.txvEmail);
        txvPassword = findViewById(R.id.txvPassword);
        txvCurrentBalace = findViewById(R.id.txvCurrentBalance);
        txvCreditCard = findViewById(R.id.txvCreditCard);
        txvSaveMoney = findViewById(R.id.txvSaveMoney);
        setAll();
    }

    public void setAll(){
        txvUserName.setText(RegisterActivity.users.get(LoginActivity.userPos).getFullName());
        txvUserEmail.setText(RegisterActivity.users.get(LoginActivity.userPos).getEmail());
        txvName.setText(RegisterActivity.users.get(LoginActivity.userPos).getFullName());
        txvEmail.setText(RegisterActivity.users.get(LoginActivity.userPos).getEmail());
        txvPassword.setText(RegisterActivity.users.get(LoginActivity.userPos).getPassword());
        txvCurrentBalace.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance()));
        txvCreditCard.setText(verifyCreditCard());
        txvSaveMoney.setText(verifySaveMoney());
    }

    public String verifyCreditCard(){
        if(RegisterActivity.users.get(LoginActivity.userPos).isCardStatus()){
            String n1 = AddCardActivity.txvCardN1.getText().toString(), n2 = AddCardActivity.txvCardN2.getText().toString(),
            n3 = AddCardActivity.txvCardN3.getText().toString(), n4 = AddCardActivity.txvCardN4.getText().toString();
            return n1 + "  " + n2 + "  " + n3 + "  " + n4;
        }
        else return "Doesn't have";
    }

    public String verifySaveMoney(){
        if(RegisterActivity.users.get(LoginActivity.userPos).isSaveMoneyStatus()){
            return toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getSaveMoney());
        }
        else return "Not applied";
    }


    public void txvBack(View view) {
        finish();
        CustomIntent.customType(ProfileActivity.this, "bottom-to-up");
    }

    @Override
    public void onBackPressed() {
        finish();
        CustomIntent.customType(ProfileActivity.this, "bottom-to-up");
    }
}