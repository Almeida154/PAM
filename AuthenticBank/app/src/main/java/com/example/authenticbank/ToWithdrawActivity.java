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

public class ToWithdrawActivity extends AppCompatActivity {
    TextView txvAvailableBalanceWithdraw;
    EditText edtWithdraw;
    CheckBox cbTermWithdraw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_withdraw);
        txvAvailableBalanceWithdraw = findViewById(R.id.txvAvailableBalanceWithdraw);
        edtWithdraw = findViewById(R.id.edtWithdraw);
        cbTermWithdraw = findViewById(R.id.cbTermWithdraw);
        txvAvailableBalanceWithdraw.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance()));
    }

    public void btnToWithdraw(View view) {
        if(edtWithdraw.length() == 0){
            DialogError someAmount = new DialogError("Error!", "Put some amount to continue.");
            someAmount.show(getSupportFragmentManager(), "someAmount");
            cbTermWithdraw.setChecked(false);
        }else{
            if(!cbTermWithdraw.isChecked()) Toast.makeText(this, "You need to accept the term", Toast.LENGTH_SHORT).show();
            else{
                if((Double.parseDouble(edtWithdraw.getText().toString()) > RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance())) {
                    DialogError doNotHave = new DialogError("Error!", "You do not have that available amount.");
                    doNotHave.show(getSupportFragmentManager(), "doNotHave");
                    edtWithdraw.setText("");
                    cbTermWithdraw.setChecked(false);
                }
                else{
                    Toast.makeText(this, "Balance updated successfully", Toast.LENGTH_SHORT).show();
                    RegisterActivity.users.get(LoginActivity.userPos).setBalance(RegisterActivity.users.get(LoginActivity.userPos).getBalance() - Double.parseDouble(edtWithdraw.getText().toString()));
                    txvAvailableBalanceWithdraw.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance()));
                    edtWithdraw.setText("");
                    cbTermWithdraw.setChecked(false);
                }
            }
        }
    }

    public void ibProfileToWithdraw(View view) {
        Intent toProfile = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(toProfile);
        CustomIntent.customType(ToWithdrawActivity.this, "up-to-bottom");
    }

    public void txvBackToWalletWithdraw(View view) {
        finish();
        Intent backToWallet = new Intent(ToWithdrawActivity.this, WalletActivity.class);
        startActivity(backToWallet);
        CustomIntent.customType(ToWithdrawActivity.this, "right-to-left");
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent backToWallet = new Intent(ToWithdrawActivity.this, WalletActivity.class);
        startActivity(backToWallet);
        CustomIntent.customType(ToWithdrawActivity.this, "right-to-left");
    }
}