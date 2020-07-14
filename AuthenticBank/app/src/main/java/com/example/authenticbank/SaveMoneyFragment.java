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

public class SaveMoneyFragment extends Fragment {
    TextView txvSaveMoneyFrag;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_save_money, container, false);
        txvSaveMoneyFrag = view.findViewById(R.id.txvSaveMoneyFrag);
        txvSaveMoneyFrag.setText("Save money: " + toBrazilianCoin(RegisterActivity.users.get(LoginActivity.userPos).getSaveMoney()));
        return view;
    }
}