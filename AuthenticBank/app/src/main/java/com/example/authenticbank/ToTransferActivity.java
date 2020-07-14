package com.example.authenticbank;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import maes.tech.intentanim.CustomIntent;

import static com.example.authenticbank.HomeActivity.toBrazilianCoin;

public class ToTransferActivity extends AppCompatActivity {
    EditText edtAgency, edtNumberAccount, edtHowMuchTransfer;
    CheckBox cbTermTransfer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_transfer);
        edtHowMuchTransfer = findViewById(R.id.edtHowMuchTransfer);
        edtNumberAccount = findViewById(R.id.edtNumberAccount);
        edtAgency = findViewById(R.id.edtAgency);
        cbTermTransfer = findViewById(R.id.cbTermTransfer);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.cardOrBalanceContainer, new BalanceFragment());
        fragmentTransaction.commit();
    }

    public void btnToTransfer(View view) {
        if((edtAgency.getText().toString()).length() == 4){
            if((edtNumberAccount.getText().toString()).length() == 10){
                if(Double.parseDouble(edtHowMuchTransfer.getText().toString()) > 0){
                    if(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance() >= Double.parseDouble(edtHowMuchTransfer.getText().toString())){
                        if(cbTermTransfer.isChecked()){
                            RegisterActivity.users.get(LoginActivity.userPos).setBalance(
                                    RegisterActivity.users.get(LoginActivity.userPos).getBalance() - Double.parseDouble(edtHowMuchTransfer.getText().toString())
                            );
                            BalanceFragment.txvBalancePay.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance()));
                            DialogSuccessful successful = new DialogSuccessful("Successful", "Successful transfer.\n" +
                                    "\nAccount number: " + edtNumberAccount.getText() + "\nAgency: " + edtAgency.getText() + "\nTransfer: R$ " + edtHowMuchTransfer.getText());
                            successful.show(getSupportFragmentManager(), "successful");
                            clean();
                        }else Toast.makeText(this, "You need to accept the term", Toast.LENGTH_SHORT).show();
                    }else{
                        DialogError doNotHave = new DialogError("Error", "You do not have that available amount.");
                        doNotHave.show(getSupportFragmentManager(), "doNotHave");
                        clean();
                    }
                }else{
                    DialogError pay0 = new DialogError("Error", "you can't pay R$ 0,00.");
                    pay0.show(getSupportFragmentManager(), "pay0");
                    clean();
                }
            }else{
                DialogError example = new DialogError("Alert", "10 numbers must be inserted.\nExample: 3567675332");
                example.show(getSupportFragmentManager(), "example");
                clean();
            }
        }else{
            DialogError example = new DialogError("Alert", "4 numbers must be inserted.\nExample: 3567");
            example.show(getSupportFragmentManager(), "example");
            clean();
        }
    }

    public void clean(){
        edtAgency.setText("");
        edtNumberAccount.setText("");
        edtHowMuchTransfer.setText("");
        cbTermTransfer.setChecked(false);
    }

    public void txvBackToPayTransfer(View view) {
        finish();
        Intent backToPay = new Intent(ToTransferActivity.this, ToPayActivity.class);
        startActivity(backToPay);
        CustomIntent.customType(ToTransferActivity.this, "right-to-left");
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent backToPay = new Intent(ToTransferActivity.this, ToPayActivity.class);
        startActivity(backToPay);
        CustomIntent.customType(ToTransferActivity.this, "right-to-left");
    }
}