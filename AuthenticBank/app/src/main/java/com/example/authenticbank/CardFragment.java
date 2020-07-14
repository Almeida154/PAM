package com.example.authenticbank;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import static com.example.authenticbank.HomeActivity.toBrazilianCoin;

public class CardFragment extends Fragment {
    public static TextView txvCardPay;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        txvCardPay = view.findViewById(R.id.txvCardPay);
        txvCardPay.setText(toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getCardInvoice()));
        return view;
    }
}