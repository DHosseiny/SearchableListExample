package ir.parsdroidgroup.searchlistexample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ir.parsdroidgroup.searchlistexample.R;
import ir.parsdroidgroup.searchlistexample.model.Contact;

/**
 * Created by Davud(hosseinyDavid@gmail.com). SearchableListExample project.
 */

public class ContactsAdapter extends BaseSearchAdapter<ContactsAdapter.ViewHolder> {


    public ContactsAdapter(List<Contact> items) {
        super(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Contact contact = (Contact) filteredItems.get(position);

        holder.name.setText(contact.name);

        holder.phoneNumber.setText(contact.phoneNumber);
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        final TextView phoneNumber;

        ViewHolder(View itemView) {

            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            phoneNumber = (TextView) itemView.findViewById(R.id.phone_number);
        }
    }
}
