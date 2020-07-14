package com.example.authenticbank;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
import maes.tech.intentanim.CustomIntent;

public class AddCardActivity extends AppCompatActivity {
    public static TextView txvCardN1, txvCardN2, txvCardN3, txvCardN4;
    TextView txvCardHolder, txvCardHolderDetail, txvCardNumber, txvCvvCard;
    RadioButton rbOpc1, rbOpc2, rbOpc3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        txvCardN1 = findViewById(R.id.txvCardN1);
        txvCardN2 = findViewById(R.id.txvCardN2);
        txvCardN3 = findViewById(R.id.txvCardN3);
        txvCardN4 = findViewById(R.id.txvCardN4);
        txvCardHolder = findViewById(R.id.txvCardHolder);
        txvCardHolderDetail = findViewById(R.id.txvCardHolderDetail);
        txvCardNumber = findViewById(R.id.txvCardNumber);
        txvCvvCard = findViewById(R.id.txvCvvCard);
        rbOpc1 = findViewById(R.id.rbOpc1);
        rbOpc2 = findViewById(R.id.rbOpc2);
        rbOpc3 = findViewById(R.id.rbOpc3);
        txvCardHolder.setText(RegisterActivity.users.get(LoginActivity.userPos).getFullName());
        setCardNumber();
        setDetails();
    }

    public void setDetails(){
        txvCardHolderDetail.setText(txvCardHolder.getText());
        txvCardNumber.setText(txvCardN1.getText() + " " + txvCardN2.getText() + " " +txvCardN3.getText() + " " + txvCardN4.getText());
        txvCvvCard.setText("" + randNumber(100, 999));
    }

    public void setCardNumber(){
        int numberCard[] = new int[4];
        for(int i = 0; i < numberCard.length; i++) numberCard[i] = randNumber(1000, 9999);
        txvCardN1.setText("" + numberCard[0]); txvCardN2.setText("" + numberCard[1]);
        txvCardN3.setText("" + numberCard[2]); txvCardN4.setText("" + numberCard[3]);
    }

    public int randNumber(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public void btnAddCard(View view) {
        if(!rbOpc1.isChecked() && !rbOpc2.isChecked() && !rbOpc3.isChecked()) Toast.makeText(this, "Select a limit to continue", Toast.LENGTH_SHORT).show();
        else{
            if(!RegisterActivity.users.get(LoginActivity.userPos).isCardStatus()){
                if(rbOpc1.isChecked()) setCardDetails(1200, rbOpc1);
                else if(rbOpc2.isChecked()) setCardDetails(1500, rbOpc2);
                else if(rbOpc3.isChecked()) setCardDetails(2000, rbOpc3);
            }else{
                DialogError alreadyCreated = new DialogError("Error!", "Card already created.");
                alreadyCreated.show(getSupportFragmentManager(), "alreadyCreated");
            }
        }
    }

    public void setCardDetails(int limit, RadioButton rb){
        RegisterActivity.users.get(LoginActivity.userPos).setCardStatus(true);
        RegisterActivity.users.get(LoginActivity.userPos).setCardLimit(limit);
        DialogSuccessful successful = new DialogSuccessful("Successful", "Card added successfully.");
        successful.show(getSupportFragmentManager(), "successful");
        if(rb.isChecked()) rb.setChecked(false);
    }

    public void ibProfileAc(View view) {
        Intent toProfile = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(toProfile);
        CustomIntent.customType(AddCardActivity.this, "up-to-bottom");
    }

    public void txvBackToHome(View view) {
        finish();
        Intent backToHome = new Intent(AddCardActivity.this, HomeActivity.class);
        startActivity(backToHome);
        CustomIntent.customType(AddCardActivity.this, "right-to-left");
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent backToHome = new Intent(AddCardActivity.this, HomeActivity.class);
        startActivity(backToHome);
        CustomIntent.customType(AddCardActivity.this, "right-to-left");
    }
}