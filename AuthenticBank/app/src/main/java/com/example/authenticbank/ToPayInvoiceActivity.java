package com.example.authenticbank;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import maes.tech.intentanim.CustomIntent;
import static com.example.authenticbank.HomeActivity.toBrazilianCoin;

public class ToPayInvoiceActivity extends AppCompatActivity {
    CheckBox cbTermPayInvoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_pay_invoice);
        cbTermPayInvoice = findViewById(R.id.cbTermPayInvoice);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.cardOrBalanceContainer, new BalanceFragment());
        fragmentTransaction.commit();
    }

    public void btnToPayInvoice(View view) {
        if(cbTermPayInvoice.isChecked()){
            if(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance() >= RegisterActivity.users.get(LoginActivity.userPos).getCardInvoice()){
                RegisterActivity.users.get(LoginActivity.userPos).setBalance(
                        RegisterActivity.users.get(LoginActivity.userPos).getBalance() - RegisterActivity.users.get(LoginActivity.userPos).getCardInvoice()
                );
                if(RegisterActivity.users.get(LoginActivity.userPos).getCardInvoice() > 0){
                    RegisterActivity.users.get(LoginActivity.userPos).setCardInvoice(0);
                    BalanceFragment.txvBalancePay.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance()));
                    DialogSuccessful successful = new DialogSuccessful("Successful", "Invoice paid successfully.");
                    successful.show(getSupportFragmentManager(), "successful");
                    cbTermPayInvoice.setChecked(false);
                }else{
                    DialogError pay0 = new DialogError("Error", "you can't pay R$ 0,00.");
                    pay0.show(getSupportFragmentManager(), "pay0");
                    cbTermPayInvoice.setChecked(false);
                }
            }else{
                DialogError doNotHave = new DialogError("Alert", "You do not have that available amount.");
                doNotHave.show(getSupportFragmentManager(), "doNotHave");
                cbTermPayInvoice.setChecked(false);
            }
        }else Toast.makeText(this, "You need to accept the term", Toast.LENGTH_SHORT).show();
    }

    public void txvBackToPayInvoice(View view) {
        finish();
        Intent backToPay = new Intent(ToPayInvoiceActivity.this, ToPayActivity.class);
        startActivity(backToPay);
        CustomIntent.customType(ToPayInvoiceActivity.this, "right-to-left");
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent backToPay = new Intent(ToPayInvoiceActivity.this, ToPayActivity.class);
        startActivity(backToPay);
        CustomIntent.customType(ToPayInvoiceActivity.this, "right-to-left");
    }
}