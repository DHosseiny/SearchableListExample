package ir.parsdroidgroup.searchlistexample.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.izigzag.messenger.ui.adapter.BaseSearchAdapter;
import com.izigzag.messenger.ui.adapter.OnItemClickListener;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import ir.parsdroidgroup.searchlistexample.R;
import ir.parsdroidgroup.searchlistexample.model.Contact;

/**
 * Created by Davud(hosseinyDavid@gmail.com). SearchableListExample project.
 */

public class ContactsAdapter extends BaseSearchAdapter<Contact, ContactsAdapter.ViewHolder> {


    private final OnItemClickListener<Contact> itemClickListener;

    public ContactsAdapter(List<Contact> items, OnItemClickListener<Contact> itemClickListener) {
        super(items);
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Contact contact = getFilteredItems().get(position);

        holder.itemView.setOnClickListener((view) -> itemClickListener.onItemClick(contact));

        holder.name.setText(contact.getSearchArgument());

        holder.phoneNumber.setText(contact.getMsisdn());
    }

    @Override
    public int getItemCount() {
        return getFilteredItems().size();
    }

    @Override
    public void notifyItemChanged(@NotNull Contact item, @Nullable Object payload) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        final TextView phoneNumber;

        ViewHolder(View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(R.id.phone_number);
        }
    }
}
