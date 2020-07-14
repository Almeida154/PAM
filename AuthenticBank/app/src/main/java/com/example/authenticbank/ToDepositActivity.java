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

public class ToDepositActivity extends AppCompatActivity {
    TextView txvAvailableBalance;
    EditText edtDeposit;
    CheckBox cbTermDeposit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_deposit);
        txvAvailableBalance = findViewById(R.id.txvAvailableBalance);
        edtDeposit = findViewById(R.id.edtDeposit);
        cbTermDeposit = findViewById(R.id.cbTermDeposit);
        txvAvailableBalance.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance()));
    }

    public void btnToDeposit(View view) {
        if(edtDeposit.length() == 0){
            DialogError someAmount = new DialogError("Error!", "Put some amount to continue.");
            someAmount.show(getSupportFragmentManager(), "someAmount");
            cbTermDeposit.setChecked(false);
        }else{
            if(!cbTermDeposit.isChecked()) Toast.makeText(this, "You need to accept the term", Toast.LENGTH_SHORT).show();
            else{
                if((Double.parseDouble(edtDeposit.getText().toString()) + RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance()) > 1000000) {
                    DialogError limit = new DialogError("Error!", "Deposits greater than R$ 1.000.000,00 are not allowed.");
                    limit.show(getSupportFragmentManager(), "limit");
                    edtDeposit.setText("");
                    cbTermDeposit.setChecked(false);
                }
                else{
                    Toast.makeText(this, "Balance updated successfully", Toast.LENGTH_SHORT).show();
                    RegisterActivity.users.get(LoginActivity.userPos).setBalance(Double.parseDouble(edtDeposit.getText().toString()) +
                            RegisterActivity.users.get(LoginActivity.userPos).getBalance());
                    txvAvailableBalance.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance()));
                    edtDeposit.setText("");
                    cbTermDeposit.setChecked(false);
                }
            }
        }
    }

    public void ibProfileToDeposit(View view) {
        Intent toProfile = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(toProfile);
        CustomIntent.customType(ToDepositActivity.this, "up-to-bottom");
    }

    public void txvBackToWallet(View view) {
        finish();
        Intent backToWallet = new Intent(ToDepositActivity.this, WalletActivity.class);
        startActivity(backToWallet);
        CustomIntent.customType(ToDepositActivity.this, "right-to-left");
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent backToWallet = new Intent(ToDepositActivity.this, WalletActivity.class);
        startActivity(backToWallet);
        CustomIntent.customType(ToDepositActivity.this, "right-to-left");
    }
}