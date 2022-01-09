package com.random.mycovidtracker;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> implements Filterable {

    Context context;
    ArrayList<Items> list;
    ArrayList<Items> listFull;

    public CustomAdapter (Context context, ArrayList<Items> list) {
        this.context = context;
        this.list = list;
        listFull = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        Items items = list.get(position);

        String loc = items.getLoc();
        String cases = items.getCases();

        holder.loc.setText(loc);
        holder.cases.setText(cases);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return listFilter;
    }

    private Filter listFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Items> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listFull);
            } else {
                String filterPattern = constraint.toString().trim().toLowerCase();

                for (Items items: listFull) {
                    if (items.getLoc().toLowerCase().trim().contains(filterPattern)) {
                        filteredList.add(items);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll( (ArrayList) results.values);

            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView loc;
        TextView cases;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            loc = itemView.findViewById(R.id.loc);
            cases = itemView.findViewById(R.id.cases);
        }
    }
}
