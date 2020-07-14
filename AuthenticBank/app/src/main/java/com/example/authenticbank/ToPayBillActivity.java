package com.example.authenticbank;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import maes.tech.intentanim.CustomIntent;

public class ToPayBillActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_pay_bill);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.cardOrBalanceContainer, new BalanceFragment());
        fragmentTransaction.commit();
    }

    public void txvBackToPayBill(View view) {
        finish();
        Intent backToPay = new Intent(ToPayBillActivity.this, ToPayActivity.class);
        startActivity(backToPay);
        CustomIntent.customType(ToPayBillActivity.this, "right-to-left");
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent backToPay = new Intent(ToPayBillActivity.this, ToPayActivity.class);
        startActivity(backToPay);
        CustomIntent.customType(ToPayBillActivity.this, "right-to-left");
    }
}