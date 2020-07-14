package com.example.authenticbank;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import maes.tech.intentanim.CustomIntent;
import static com.example.authenticbank.HomeActivity.toBrazilianCoin;

public class ToPayFriendActivity extends AppCompatActivity {
    CheckBox cbTermPayFriend;
    EditText edtHowMuchToFriend, edtFriendId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_pay_friend);
        cbTermPayFriend = findViewById(R.id.cbTermPayFriend);
        edtHowMuchToFriend = findViewById(R.id.edtHowMuchToFriend);
        edtFriendId = findViewById(R.id.edtFriendId);
        verifyFrag(RegisterActivity.users.get(LoginActivity.userPos).isCardStatus());
    }

    public void btnToPay(View view) {
        if(!RegisterActivity.users.get(LoginActivity.userPos).isCardStatus()) balanceMethod();
        else{
            if(PaymentMethod.isBalanceMethod()) balanceMethod();
            else cardMethod();
        }
    }

    public void balanceMethod(){
        if((edtHowMuchToFriend.getText().toString()).length() == 0) {
            DialogError allFields = new DialogError("Error", "Put some amount to continue.");
            allFields.show(getSupportFragmentManager(), "allFields");
        }else{
            if((edtFriendId.getText().toString()).length() == 7) {
                if(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance() >= Double.parseDouble(edtHowMuchToFriend.getText().toString())){
                    RegisterActivity.users.get(LoginActivity.userPos).setBalance(
                            RegisterActivity.users.get(LoginActivity.userPos).getBalance() - Double.parseDouble(edtHowMuchToFriend.getText().toString())
                    );
                    if(Double.parseDouble(edtHowMuchToFriend.getText().toString()) > 0){
                        if(cbTermPayFriend.isChecked()){
                            BalanceFragment.txvBalancePay.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance()));
                            DialogSuccessful successful = new DialogSuccessful("Successful", "Payment successfully.\n" +
                                    "\nPayment: R$" + edtHowMuchToFriend.getText() + "\nFor id: " + edtFriendId.getText());
                            successful.show(getSupportFragmentManager(), "successful");
                            clean();
                        }else Toast.makeText(this, "You need to accept the term", Toast.LENGTH_SHORT).show();
                    }else{
                        DialogError pay0 = new DialogError("Error", "you can't pay R$ 0,00.");
                        pay0.show(getSupportFragmentManager(), "pay0");
                        clean();
                    }
                }else{
                    DialogError doNotHave = new DialogError("Error", "You do not have that available amount.");
                    doNotHave.show(getSupportFragmentManager(), "doNotHave");
                    clean();
                }
            }else{
                DialogError example = new DialogError("Alert", "7 numbers must be inserted.\nExample: 3567042");
                example.show(getSupportFragmentManager(), "example");
                clean();
            }
        }
    }

    public void cardMethod(){
        if((edtHowMuchToFriend.getText().toString()).length() == 0) {
            DialogError allFields = new DialogError("Error", "Put some amount to continue.");
            allFields.show(getSupportFragmentManager(), "allFields");
        }else{
            if((edtFriendId.getText().toString()).length() == 7) {
                if((RegisterActivity.users.get(LoginActivity.userPos).getCardInvoice() + Double.parseDouble(edtHowMuchToFriend.getText().toString())) <= RegisterActivity.users.get(LoginActivity.userPos).getCardLimit()){
                    RegisterActivity.users.get(LoginActivity.userPos).setCardInvoice(
                            RegisterActivity.users.get(LoginActivity.userPos).getCardInvoice() + Double.parseDouble(edtHowMuchToFriend.getText().toString())
                    );
                    if(Double.parseDouble(edtHowMuchToFriend.getText().toString()) > 0){
                        if(cbTermPayFriend.isChecked()){
                            CardFragment.txvCardPay.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getCardInvoice()));
                            DialogSuccessful successful = new DialogSuccessful("Successful", "Payment successfully.\n" +
                                    "\nPayment: R$" + edtHowMuchToFriend.getText() + "\nFor id: " + edtFriendId.getText());
                            successful.show(getSupportFragmentManager(), "successful");
                            clean();
                        }else Toast.makeText(this, "You need to accept the term", Toast.LENGTH_SHORT).show();
                    }else{
                        DialogError pay0 = new DialogError("Error", "you can't pay R$ 0,00.");
                        pay0.show(getSupportFragmentManager(), "pay0");
                        clean();
                    }
                }else{
                    DialogError payInvoice = new DialogError("Error", "Pay your invoice. Insufficient threshold.");
                    payInvoice.show(getSupportFragmentManager(), "payInvoice");
                    clean();
                }
            }else{
                DialogError example = new DialogError("Alert", "7 numbers must be inserted.\nExample: 3567042");
                example.show(getSupportFragmentManager(), "example");
                clean();
            }
        }
    }

    public void clean(){
        edtHowMuchToFriend.setText("");
        edtFriendId.setText("");
        cbTermPayFriend.setChecked(false);
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

    public void txvBackToPayFriend(View view) {
        finish();
        Intent backToPay = new Intent(ToPayFriendActivity.this, ToPayActivity.class);
        startActivity(backToPay);
        CustomIntent.customType(ToPayFriendActivity.this, "right-to-left");
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent backToPay = new Intent(ToPayFriendActivity.this, ToPayActivity.class);
        startActivity(backToPay);
        CustomIntent.customType(ToPayFriendActivity.this, "right-to-left");
    }
}