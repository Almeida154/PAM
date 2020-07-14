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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import maes.tech.intentanim.CustomIntent;
import static com.example.authenticbank.HomeActivity.toBrazilianCoin;

public class ToPayActivity extends AppCompatActivity {
    BottomNavigationView btnv;
    TextView txvCurrentBalanceToPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_pay);
        btnv = findViewById(R.id.bottom_navigation);
        btnv.setSelectedItemId(R.id.itToPay);
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
        txvCurrentBalanceToPay = findViewById(R.id.txvCurrentBalanceToPay);
        txvCurrentBalanceToPay.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getAvailableBalance()));
    }

    public void linearTransfer(View view) {
        finish();
        Intent toTransfer = new Intent(ToPayActivity.this, ToTransferActivity.class);
        startActivity(toTransfer);
    }

    public void linearBill(View view) {
        finish();
        Intent topPayBill = new Intent(ToPayActivity.this, ToPayBillActivity.class);
        startActivity(topPayBill);
    }

    public void linearInvoice(View view) {
        if(RegisterActivity.users.get(LoginActivity.userPos).isCardStatus()){
            finish();
            Intent topPayInvoice = new Intent(ToPayActivity.this, ToPayInvoiceActivity.class);
            startActivity(topPayInvoice);
        }else{
            DialogError doNotHave = new DialogError("Error!", "You don't have a card yet.");
            doNotHave.show(getSupportFragmentManager(), "doNotHave");
        }
    }

    public void linearFriend(View view) {
        finish();
        Intent topPayFriend = new Intent(ToPayActivity.this, ToPayFriendActivity.class);
        startActivity(topPayFriend);
    }

    public void linearTopUp(View view) {
        finish();
        Intent topUp = new Intent(ToPayActivity.this, MobileTopUpActivity.class);
        startActivity(topUp);
    }

    public void linearQr(View view) {
        finish();
        Intent qrCode = new Intent(ToPayActivity.this, QrActivity.class);
        startActivity(qrCode);
    }

    public void ibProfileToPay(View view) {
        Intent toProfile = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(toProfile);
        CustomIntent.customType(ToPayActivity.this, "up-to-bottom");
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