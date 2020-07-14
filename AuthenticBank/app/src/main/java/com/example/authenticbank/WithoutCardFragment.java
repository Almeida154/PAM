package com.example.authenticbank;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class WithoutCardFragment extends Fragment {
    Button btnCreateCard;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_without_card, container, false);
        btnCreateCard = view.findViewById(R.id.btnCreateCard);
        btnCreateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Intent toCreateCard = new Intent(getActivity(), AddCardActivity.class);
                startActivity(toCreateCard);
            }
        });

        return view;
    }

}