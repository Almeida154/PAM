package com.example.authenticbank;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import static com.example.authenticbank.HomeActivity.toBrazilianCoin;

public class WithCardFragment extends Fragment {
    TextView txvCardInvoice, txvCardLimit;
    Button btnPaySomething, btnIncreaseLimit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_with_card, container, false);
        txvCardInvoice = view.findViewById(R.id.txvCardInvoice);
        txvCardLimit = view.findViewById(R.id.txvCardLimit);
        btnPaySomething = view.findViewById(R.id.btnPaySomething);
        btnIncreaseLimit = view.findViewById(R.id.btnIncreaseLimit);
        txvCardInvoice.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getCardInvoice()));
        txvCardLimit.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getCardLimit()));
        double percentInvoice = (RegisterActivity.users.get(LoginActivity.userPos).getCardInvoice() * 100) / RegisterActivity.users.get(LoginActivity.userPos).getCardLimit();
        if(percentInvoice < 51) txvCardInvoice.setTextColor(Color.rgb(19, 124, 61));
        else if(percentInvoice < 71) txvCardInvoice.setTextColor(Color.rgb(255, 127, 51));
        else txvCardInvoice.setTextColor(Color.rgb(255, 0, 0));
        btnPaySomething.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Intent toPay = new Intent(getActivity(), ToPayActivity.class);
                startActivity(toPay);
            }
        });
        btnIncreaseLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Intent increaseLimit = new Intent(getActivity(), IncreaseLimitActivity.class);
                startActivity(increaseLimit);
            }
        });
        return view;
    }
}