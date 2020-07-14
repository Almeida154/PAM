package com.example.authenticbank;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import maes.tech.intentanim.CustomIntent;

public class SettingsActivity extends AppCompatActivity {
    BottomNavigationView btnv;
    public static TextView txvPasswordSettings, txvEmailSettings;
    TextView txvId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btnv = findViewById(R.id.bottom_navigation);
        txvId = findViewById(R.id.txvId);
        btnv.setSelectedItemId(R.id.itSettings);
        btnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.itHome:
                        finish();
                        Intent toHome = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(toHome);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.itWallet:
                        finish();
                        Intent toWallet = new Intent(getApplicationContext(), WalletActivity.class);
                        startActivity(toWallet);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.itToPay:
                        finish();
                        Intent toToPay = new Intent(getApplicationContext(), ToPayActivity.class);
                        startActivity(toToPay);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.itSettings:
                        return true;
                }
                return false;
            }
        });
        txvEmailSettings = findViewById(R.id.txvEmailSettings);
        txvPasswordSettings = findViewById(R.id.txvPasswordSettings);
        txvEmailSettings.setText(RegisterActivity.users.get(LoginActivity.userPos).getEmail());
        txvPasswordSettings.setText(RegisterActivity.users.get(LoginActivity.userPos).getPassword());
        txvId.setText("Your id: " + RegisterActivity.users.get(LoginActivity.userPos).getId());
    }

    public void txvCancelSaveMoney(View view) {
        if(!RegisterActivity.users.get(LoginActivity.userPos).isSaveMoneyStatus()){
            DialogError saveMoneyCancel = new DialogError("Error!", "You haven't money saved.");
            saveMoneyCancel.show(getSupportFragmentManager(), "saveMoneyCancel");
        }else{
            DialogConfirm creditCardCancel = new DialogConfirm("Confirm", "Are you sure?", "saveMoney");
            creditCardCancel.show(getSupportFragmentManager(), "creditCardCancel");
        }
    }

    public void txvCancelMyCard(View view) {
        if(!RegisterActivity.users.get(LoginActivity.userPos).isCardStatus()){
            DialogError creditCardCancel = new DialogError("Error", "You haven't credit card.");
            creditCardCancel.show(getSupportFragmentManager(), "creditCardCancel");
        }else{
            if(RegisterActivity.users.get(LoginActivity.userPos).getCardInvoice() > 0){
                DialogError invoiceError = new DialogError("Error", "Pay your invoice first.");
                invoiceError.show(getSupportFragmentManager(), "invoiceError");
            }else{
                DialogConfirm saveMoneyCancel = new DialogConfirm("Confirm", "Are you sure?", "creditCard");
                saveMoneyCancel.show(getSupportFragmentManager(), "saveMoneyCancel");
            }
        }
    }

    public void txvChangeEmail(View view) {
        DialogExchanger exchanger = new DialogExchanger("Change your email", "email");
        exchanger.show(getSupportFragmentManager(), "exchanger");
    }

    public void txvChangePassword(View view) {
        DialogExchanger exchanger = new DialogExchanger("Change your password", "password");
        exchanger.show(getSupportFragmentManager(), "exchanger");
    }

    public void txvLogOut(View view) {
        new AlertDialog.Builder(this, R.style.DialogStyle)
                .setIcon(R.drawable.ic_saida)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        CustomIntent.customType(SettingsActivity.this, "right-to-left");
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this, R.style.DialogStyle)
                .setIcon(R.drawable.ic_saida)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        CustomIntent.customType(SettingsActivity.this, "right-to-left");
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}