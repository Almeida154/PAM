package com.example.authenticbank;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerFragment extends Fragment {
    Spinner spn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spinner, container, false);
        spn = view.findViewById(R.id.spinnerOpc);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity().getBaseContext(), R.array.values, R.layout.color_spinner_selected
        );
        adapter.setDropDownViewResource(R.layout.color_spinner_dropdown);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        fragmentMethod("balance");
                        PaymentMethod.setBalanceMethod(true);
                        break;
                    case 1:
                        fragmentMethod("card");
                        PaymentMethod.setCardMethod(true);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                fragmentMethod("balance");
            }
        });
        return  view;
    }

    public void fragmentMethod(String cardORbalance){
        if(cardORbalance.equals("card")){
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.cardOrBalanceContainer, new CardFragment());
            fragmentTransaction.commit();
        }else{
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.cardOrBalanceContainer, new BalanceFragment());
            fragmentTransaction.commit();
        }
    }
}