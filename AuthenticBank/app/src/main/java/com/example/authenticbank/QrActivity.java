package com.example.authenticbank;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import maes.tech.intentanim.CustomIntent;
import static com.example.authenticbank.HomeActivity.toBrazilianCoin;

public class QrActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.cardOrBalanceContainer, new BalanceFragment());
        fragmentTransaction.commit();
    }

    public void txvBackToPayQr(View view) {
        finish();
        Intent backToPay = new Intent(QrActivity.this, ToPayActivity.class);
        startActivity(backToPay);
        CustomIntent.customType(QrActivity.this, "right-to-left");
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent backToPay = new Intent(QrActivity.this, ToPayActivity.class);
        startActivity(backToPay);
        CustomIntent.customType(QrActivity.this, "right-to-left");
    }
}