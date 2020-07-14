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
import static com.example.authenticbank.HomeActivity.toBrazilianCoin;

public class WalletActivity extends AppCompatActivity {
    TextView txvCurrentBalanceWallet;
    BottomNavigationView btnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        btnv = findViewById(R.id.bottom_navigation);
        btnv.setSelectedItemId(R.id.itWallet);
        txvCurrentBalanceWallet = findViewById(R.id.txvCurrentBalanceWallet);
        txvCurrentBalanceWallet.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance()));
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
                        return true;
                    case R.id.itToPay:
                        finish();
                        Intent toToPay = new Intent(getApplicationContext(), ToPayActivity.class);
                        startActivity(toToPay);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.itSettings:
                        finish();
                        Intent toSettings = new Intent(getApplicationContext(), SettingsActivity.class);
                        startActivity(toSettings);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    public void btnToDepositWallet(View view) {
        finish();
        Intent toDeposit = new Intent(WalletActivity.this, ToDepositActivity.class);
        startActivity(toDeposit);
    }

    public void btnToWithdrawWallet(View view) {
        finish();
        Intent ToWithdraw = new Intent(WalletActivity.this, ToWithdrawActivity.class);
        startActivity(ToWithdraw);
    }

    public void ibProfileWallet(View view) {
        Intent toProfile = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(toProfile);
        CustomIntent.customType(WalletActivity.this, "up-to-bottom");
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
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}