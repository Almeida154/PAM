package com.example.contactlist;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.contactlist.Adapter.ContactAdapter;
import com.example.contactlist.Database.ContactDAO;
import com.example.contactlist.Interfaces.RecyclerViewOnClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.ArrayList;
import static maes.tech.intentanim.CustomIntent.customType;

public class FragmentListDatabase extends Fragment implements RecyclerViewOnClickListener {

    private ContactDAO contactDAO;
    private RecyclerView rvContacts;
    private ContactAdapter contactAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_database, container, false);
        rvContacts = v.findViewById(R.id.rvContacts);
        rvContacts.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvContacts.setHasFixedSize(true);
        contactDAO = new ContactDAO(getActivity());
        contactAdapter = new ContactAdapter(new ArrayList<>(contactDAO.selectDB()), this, getActivity());
        rvContacts.setAdapter(contactAdapter);
        return v;
    }

    @Override
    public void onClickListener(View v, String number, String item, Integer position) {
        switch(item){
            case "dialog":
                this.dialogItem(number);
                break;
            case "delete":
                int pos = Integer.parseInt(position.toString());
                dialogDeleteItem(number, pos);
                break;
            case "update":
                Intent it = new Intent(getActivity(), UpdateActivity.class);
                it.putExtra("numberContact", number);
                startActivity(it);
                customType(getActivity(), "left-to-right");
                break;
        }
    }

    private void dialogDeleteItem(final String number, final int position){

        String name = "<h1><font>DELETE?</font></h1>";
        String message = "<big><font><b>" + contactDAO.selectDB(number).get(0).getNameContact() + "</b> will be deleted, are you sure?</font></big>";

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), R.style.CustomDialog2);
        builder.setTitle(Html.fromHtml(name));
        builder.setMessage(Html.fromHtml(message));
        builder.setIcon(R.drawable.ic_baseline_delete_24);
        builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_bg));

        builder.setPositiveButton("Yes, I'm sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                contactAdapter.removeContact(position);
                contactDAO.deleteDB(number);
                Intent refresh = new Intent(getActivity(), HomeActivity.class);
                startActivity(refresh);
                getActivity().overridePendingTransition(0, 0);
                getActivity().finish();
            }
        });
        builder.show();
    }

    private void dialogItem(String number){
        String data[] = new String[]{
                contactDAO.selectDB(number).get(0).getEmailContact(),
                contactDAO.selectDB(number).get(0).getLandlineContact(),
                contactDAO.selectDB(number).get(0).getNicknameContact()
        };

        for(int i = 0; i < 3; i++) data[i] = data[i] != null ? data[i] : "No registered";

        String finalRes = "<big><font color=\"#FFFFFF\">Email: </font>"
                + "<font color=\"#8E8E8E\">" + data[0] + "</font><br>"
                + "<font color=\"#FFFFFF\">Landline: </font>"
                + "<font color=\"#8E8E8E\">" + data[1] + "</font><br>"
                + "<font color=\"#FFFFFF\">Nickname: </font>"
                + "<font color=\"#8E8E8E\">" + data[2] + "</font></big>";

        String name = "<h1><font>"+ contactDAO.selectDB(number).get(0).getNameContact() +"</font></h1>";

        final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), R.style.CustomDialog);
        builder.setTitle(Html.fromHtml(name));
        builder.setMessage(Html.fromHtml(finalRes));
        builder.setIcon(R.drawable.ic_baseline_perm_contact_calendar_24);
        builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_bg));
        builder.setPositiveButton("Ok", null);
        builder.show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menutoolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem searchItem = menu.findItem(R.id.searchToolBar);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search some contact");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

}