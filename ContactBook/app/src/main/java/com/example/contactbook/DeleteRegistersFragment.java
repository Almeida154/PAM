package com.example.contactbook;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteRegistersFragment extends Fragment {
    private Button btnDelete;
    private EditText edtEmailDelete, edtEmailDeleteConfirm;
    private ContactDAO contactDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_delete_registers, container, false);
        edtEmailDelete = view.findViewById(R.id.edtEmailDelete);
        edtEmailDeleteConfirm = view.findViewById(R.id.edtEmailDeleteConfirm);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toValidateField(edtEmailDelete);
                toValidateField(edtEmailDeleteConfirm);
                if(toValidateField(edtEmailDelete) && toValidateField(edtEmailDeleteConfirm)){
                    if(edtEmailDelete.getText().toString().equals(edtEmailDeleteConfirm.getText().toString())){
                        contactDAO = new ContactDAO(getActivity());
                        int totRows = contactDAO.delete(edtEmailDeleteConfirm.getText().toString());
                        if(totRows > 0){
                            Toast.makeText(getActivity(), "Deleted...", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(), "Not found...", Toast.LENGTH_SHORT).show();
                        }
                        clean();
                    }else{
                        edtEmailDeleteConfirm.setText("");
                        edtEmailDeleteConfirm.setError("Different email!");
                    }
                }
            }
        });
        return view;
    }

    private boolean toValidateField(EditText edtField){
        if(!TextUtils.isEmpty(edtField.getText().toString())) return true;
        else {
            edtField.setError("Insert this field");
            edtField.requestFocus();
            return false;
        }
    }

    private void clean(){
        edtEmailDelete.setText("");
        edtEmailDeleteConfirm.setText("");
    }

}