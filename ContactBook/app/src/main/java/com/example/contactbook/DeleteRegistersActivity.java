package com.example.contactbook;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static maes.tech.intentanim.CustomIntent.customType;

public class DeleteRegistersActivity extends AppCompatActivity {
    private Connection db;
    private TextView txvTitleDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_registers);
        txvTitleDelete = findViewById(R.id.txvTitleDelete);
        db = new Connection(this);
        viewData();
    }

    private void viewData(){
        Cursor cursor = db.viewData();
        if(cursor.getCount() == 0) replaceFrag(true);
        else {
            replaceFrag(false);
            txvTitleDelete.setVisibility(View.VISIBLE);
        }
    }

    private void replaceFrag(boolean empty) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(!empty){
            fragmentTransaction.replace(R.id.flListViewDelete, new DeleteRegistersFragment());
            fragmentTransaction.commit();
        }else{
            fragmentTransaction.replace(R.id.flListViewDelete, new EmptyDBFragment());
            fragmentTransaction.commit();
        }
    }

    public void txvBack(View view) {
        Intent toHome = new Intent(DeleteRegistersActivity.this, HomeActivity.class);
        startActivity(toHome);
        finish();
        customType(DeleteRegistersActivity.this, "right-to-left");
    }

    @Override
    public void onBackPressed() {
        Intent toHome = new Intent(DeleteRegistersActivity.this, HomeActivity.class);
        startActivity(toHome);
        finish();
        customType(DeleteRegistersActivity.this, "right-to-left");
    }

}