package com.example.contactlist.Interfaces;
import android.view.View;

public interface RecyclerViewOnClickListener {
    void onClickListener(View v, String number, String item, Integer position);
}