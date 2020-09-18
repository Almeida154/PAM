package com.example.contactbook;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ListViewFragment extends Fragment {
    private ListView lsvRegisters;
    private Connection db;
    private Cursor cursor;
    private ArrayAdapter adapter;
    private ArrayList<String> registers;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        lsvRegisters = view.findViewById(R.id.lsvRegisters);

        db = new Connection(getContext());
        cursor = db.viewData();
        registers = new ArrayList<>();

        while(cursor.moveToNext()){
            String contact = "Nome: " + cursor.getString(1) + "\n"
                            + "Email: " + cursor.getString(2) + "\n"
                            + "Telephone: " + cursor.getString(3) + "\n"
                            + "Point: " + cursor.getString(4) + "\n"
                            + "Message: " + cursor.getString(5);
            registers.add(contact);
        }

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.itens_list_view, registers);
        lsvRegisters.setAdapter(adapter);

        return view;
    }
}