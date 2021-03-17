package com.example.contactlist.Adapter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.contactlist.Interfaces.RecyclerViewOnClickListener;
import com.example.contactlist.Model.Contact;
import com.example.contactlist.R;
import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> implements Filterable {

    // Properties

    private final List<Contact> contacts;
    private List<Contact> contactsCopy;
    private RecyclerViewOnClickListener recyclerViewOnClickListener;
    private Context context;

    // Constructor

    public ContactAdapter(List<Contact> contacts, RecyclerViewOnClickListener r, Context context) {
        this.contacts = contacts;
        this.contactsCopy = new ArrayList<>(contacts);
        this.recyclerViewOnClickListener = r;
        this.context = context;
    }

    // Methods

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvitem, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, final int position) {
        final Contact contact = contacts.get(position);
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return this.contacts.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Contact> filtered = new ArrayList<>();
            if(constraint == null || constraint.length() == 0) filtered.addAll(contactsCopy);
            else{
              String filterPattern = constraint.toString().toLowerCase().trim();
              for(Contact contact : contactsCopy){
                  if(contact.getNameContact().toLowerCase().contains(filterPattern)) filtered.add(contact);
              }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filtered;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            contacts.clear();
            contacts.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    // View Holder

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txvContactName;
        TextView txvNumberContact;
        TextView txvPhotoContact;
        ImageView ivDelete;
        ImageView ivUpdate;

        public ContactViewHolder(@NonNull final View itemView) {
            super(itemView);
            txvContactName = itemView.findViewById(R.id.txvNameContact);
            txvNumberContact = itemView.findViewById(R.id.txvNumberContact);
            txvPhotoContact = itemView.findViewById(R.id.txvPhotoContact);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            ivUpdate = itemView.findViewById(R.id.ivUpdate);

            itemView.setOnClickListener(this);
            ivDelete.setOnClickListener(this);
            ivUpdate.setOnClickListener(this);
        }

        public void bind(Contact contact) {
            txvPhotoContact.setText(String.valueOf(contact.getNameContact().charAt(0)).toLowerCase());
            txvPhotoContact.setBackground(bg(Color.parseColor("#6C6C6C"), txvPhotoContact));
            txvContactName.setText(contact.getNameContact());
            txvNumberContact.setText(contact.getNumberContact());
        }

        @Override
        public void onClick(View v) {
            if(v == itemView) recyclerViewOnClickListener.onClickListener(v, txvNumberContact.getText().toString(), "dialog", null);
            if(v == ivUpdate) recyclerViewOnClickListener.onClickListener(v, txvNumberContact.getText().toString(), "update", null);
            if(v == ivDelete) recyclerViewOnClickListener.onClickListener(v, txvNumberContact.getText().toString(), "delete", getAdapterPosition());
        }

    }

    // Photo Background Shape

    private static ShapeDrawable bg(@ColorInt int color, View view){
        ShapeDrawable shape = new ShapeDrawable(new OvalShape());
        shape.setIntrinsicHeight(view.getHeight());
        shape.setIntrinsicWidth(view.getWidth());
        shape.getPaint().setColor(color);
        return shape;
    }

    public void removeContact(int position){
        contacts.remove(position);
        contactsCopy.remove(position);
        notifyItemRemoved(position);
    }
}