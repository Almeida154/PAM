package com.example.healthmind;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import static com.example.healthmind.MyAppointmentsActivity.item;
import static com.example.healthmind.MyAppointmentsActivity.listAppointments;
import static com.example.healthmind.MyAppointmentsActivity.listPatients;


public class ListViewFragment extends Fragment {
    ArrayAdapter<String> arrayAdapterFinal;
    ListView lvInformation;
    TextView txvListTitle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        lvInformation = view.findViewById(R.id.lvInformation);
        txvListTitle = view.findViewById(R.id.txvListTitle);
        if(item == 1){
            txvListTitle.setText("Patients");
            if(listPatients != null && !listPatients.isEmpty()){
                this.arrayAdapterFinal = new ArrayAdapter<String>(getActivity(), R.layout.itens_list_view, listPatients);
                lvInformation.setAdapter(arrayAdapterFinal);
            }
        } else if(item == 2){
            txvListTitle.setText("Appointments");
            if(listAppointments != null && !listAppointments.isEmpty()){
                this.arrayAdapterFinal = new ArrayAdapter<String>(getActivity(), R.layout.itens_list_view, listAppointments);
                lvInformation.setAdapter(arrayAdapterFinal);
            }
        }
        return view;
    }
}