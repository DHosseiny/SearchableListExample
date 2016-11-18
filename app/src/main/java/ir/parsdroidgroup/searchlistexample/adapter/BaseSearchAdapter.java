package ir.parsdroidgroup.searchlistexample.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ir.parsdroidgroup.searchlistexample.SearchListener;


/**
 * Created by Davud(hosseinyDavid@gmail.com). SearchableListExample project.
 */

public abstract class BaseSearchAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements SearchListener {

    //all of item given to adapter added to this list
    private final List<SearchableModel> allItems;

    //when search happened appropriate items remains in this list and others clears
    final List<SearchableModel> filteredItems;

    BaseSearchAdapter(List<? extends SearchableModel> items) {
        this.allItems = new ArrayList<>();
        this.allItems.addAll(items);
        this.filteredItems = new ArrayList<>();
        this.filteredItems.addAll(items);
    }

    public abstract void onBindViewHolder(VH holder, int position);

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    /**
     * {@link #toString()} that used here for classes like {@link Integer}, {@link Long}
     * and many other classes works normally
     *
     * @param keyword to search.
     */
    @Override
    public void onSearch(String keyword) {

        filteredItems.clear();
        for (SearchableModel item : allItems) {
            String itemString = (item.getSearchArgument()).toString().toLowerCase(Locale.getDefault());

            if (itemString.contains(keyword.toLowerCase(Locale.getDefault()))) {

                filteredItems.add(item);
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public void onDismiss() {

        filteredItems.clear();
        filteredItems.addAll(allItems);
        notifyDataSetChanged();
    }
}