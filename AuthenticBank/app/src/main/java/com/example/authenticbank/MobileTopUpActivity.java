package com.example.authenticbank;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import maes.tech.intentanim.CustomIntent;
import static com.example.authenticbank.HomeActivity.toBrazilianCoin;

public class MobileTopUpActivity extends AppCompatActivity {
    EditText edtNumberphone, edtHowMuchTopUp;
    RadioButton rbOi, rbVivo, rbTim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_top_up);
        edtNumberphone = findViewById(R.id.edtNumberphone);
        edtHowMuchTopUp = findViewById(R.id.edtHowMuchTopUp);
        rbOi = findViewById(R.id.rbOi);
        rbVivo = findViewById(R.id.rbVivo);
        rbTim = findViewById(R.id.rbTim);
        verifyFrag(RegisterActivity.users.get(LoginActivity.userPos).isCardStatus());
    }

    public void btnTopUp(View view) {
        if(!RegisterActivity.users.get(LoginActivity.userPos).isCardStatus()) balanceMethod();
        else{
            if(PaymentMethod.isBalanceMethod()) balanceMethod();
            else cardMethod();
        }
    }

    public void balanceMethod(){
        if((edtHowMuchTopUp.getText().toString()).length() == 0) {
            DialogError allFields = new DialogError("Error", "Please, insert all fields.");
            allFields.show(getSupportFragmentManager(), "allFields");
        }
        else{
            if(!rbOi.isChecked() && !rbVivo.isChecked() && !rbTim.isChecked()) Toast.makeText(this, "Select a operator to continue", Toast.LENGTH_SHORT).show();
            else{
                if((edtNumberphone.getText().toString()).length() == 11) {
                    if(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance() >= Double.parseDouble(edtHowMuchTopUp.getText().toString())){
                        if(Double.parseDouble(edtHowMuchTopUp.getText().toString()) > 0){
                            RegisterActivity.users.get(LoginActivity.userPos).setBalance(
                                    RegisterActivity.users.get(LoginActivity.userPos).getBalance() - Double.parseDouble(edtHowMuchTopUp.getText().toString())
                            );
                            BalanceFragment.txvBalancePay.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance()));
                            DialogSuccessful successful = new DialogSuccessful("Successful", "Mobile top up successfully.\n" +
                                    "\nNumber phone: " + edtNumberphone.getText() + "\nOperator: " + getOperator() + "\nTop up: R$ " + edtHowMuchTopUp.getText());
                            successful.show(getSupportFragmentManager(), "successful");
                            clean();
                        }else{
                            DialogError pay0 = new DialogError("Error", "you can't top up R$ 0,00.");
                            pay0.show(getSupportFragmentManager(), "pay0");
                            clean();
                        }
                    }else{
                        DialogError doNotHave = new DialogError("Error", "You do not have that available amount.");
                        doNotHave.show(getSupportFragmentManager(), "doNotHave");
                        clean();
                    }
                }else{
                    DialogError example = new DialogError("Alert", "11 numbers must be inserted.\nExample: 11957648755");
                    example.show(getSupportFragmentManager(), "example");
                    clean();
                }
            }
        }
    }

    public void cardMethod(){
        if((edtHowMuchTopUp.getText().toString()).length() == 0) {
            DialogError allFields = new DialogError("Error", "Please, insert all fields.");
            allFields.show(getSupportFragmentManager(), "allFields");
        }else{
            if(!rbOi.isChecked() && !rbVivo.isChecked() && !rbTim.isChecked()) Toast.makeText(this, "Select a operator to continue", Toast.LENGTH_SHORT).show();
            else{
                if((edtNumberphone.getText().toString()).length() == 11) {
                    if((RegisterActivity.users.get(LoginActivity.userPos).getCardInvoice() + Double.parseDouble(edtHowMuchTopUp.getText().toString())) <= RegisterActivity.users.get(LoginActivity.userPos).getCardLimit()){
                        if(Double.parseDouble(edtHowMuchTopUp.getText().toString()) > 0){
                            RegisterActivity.users.get(LoginActivity.userPos).setCardInvoice(
                                    RegisterActivity.users.get(LoginActivity.userPos).getCardInvoice() + Double.parseDouble(edtHowMuchTopUp.getText().toString())
                            );
                            Toast.makeText(this, "Mobile top up successfully: " + edtNumberphone.getText(), Toast.LENGTH_LONG).show();
                            CardFragment.txvCardPay.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getCardInvoice()));
                            DialogSuccessful successful = new DialogSuccessful("Successful", "Mobile top up successfully.\n" +
                                    "\nNumber phone: " + edtNumberphone.getText() + "\nOperator: " + getOperator() + "\nTop up: R$ " + edtHowMuchTopUp.getText());
                            successful.show(getSupportFragmentManager(), "successful");
                            clean();
                        }else{
                            DialogError pay0 = new DialogError("Error", "you can't top up R$ 0,00.");
                            pay0.show(getSupportFragmentManager(), "pay0");
                            clean();
                        }
                    }else{
                        DialogError payInvoice = new DialogError("Error", "Pay your invoice. Insufficient threshold.");
                        payInvoice.show(getSupportFragmentManager(), "payInvoice");
                        clean();
                    }
                }else{
                    DialogError example = new DialogError("Alert", "11 numbers must be inserted.\nExample: 11957648755");
                    example.show(getSupportFragmentManager(), "example");
                    clean();
                }
            }
        }
    }

    public String getOperator(){
        String operator = null;
        if(rbVivo.isChecked()) operator = "Vivo";
        else if(rbTim.isChecked()) operator = "Tim";
        else if(rbOi.isChecked()) operator = "Oi";
        return operator;
    }

    public void clean(){
        edtHowMuchTopUp.setText("");
        edtNumberphone.setText("");
        rbOi.setChecked(false);
        rbTim.setChecked(false);
        rbVivo.setChecked(false);
    }

    public void verifyFrag(boolean status){
        if(status){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragContainer, new SpinnerFragment());
            fragmentTransaction.commit();
        }else{
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.cardOrBalanceContainer, new BalanceFragment());
            fragmentTransaction.commit();
        }
    }

    public void txvBackToPayQr(View view) {
        finish();
        Intent backToPay = new Intent(MobileTopUpActivity.this, ToPayActivity.class);
        startActivity(backToPay);
        CustomIntent.customType(MobileTopUpActivity.this, "right-to-left");
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent backToPay = new Intent(MobileTopUpActivity.this, ToPayActivity.class);
        startActivity(backToPay);
        CustomIntent.customType(MobileTopUpActivity.this, "right-to-left");
    }
}