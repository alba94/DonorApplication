package com.enterprise.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.enterprise.activities.R;
import com.enterprise.responses.Donor;

import java.util.List;

/**
 * Created by albal on 04/06/2017.
 */

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.DonorHolder> implements Filterable {

    public List<Donor> quest, filterList;
    private DonorsFilter filter;
    private LayoutInflater inflater;
    private ItemClickCallback itemClickCallback;

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new DonorsFilter(filterList, this);
        }
        return filter;
    }

    public interface ItemClickCallback {
        void onItemClick(int p);

    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }


    public DonorAdapter(List<Donor> quest, Context c) {
        this.inflater = (LayoutInflater.from(c));
        this.quest = quest;
        this.filterList = quest;
    }

    @Override
    public DonorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.liste_items, parent, false);
        return new DonorHolder(view);
    }

    @Override
    public void onBindViewHolder(DonorHolder holder, int position) {
        Donor item = quest.get(position);
        holder.name.setText(item.getName());
        holder.city.setText(item.getCity().getName());
        holder.blood.setText(item.getBllodtype());

    }

    @Override
    public int getItemCount() {
        return quest.size();
    }

    class DonorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView city;
        TextView blood;
        View container;

        public DonorHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.hai);
            blood = (TextView) itemView.findViewById(R.id.grupi_gjakut);
            city = (TextView) itemView.findViewById(R.id.qyteti);
            container = itemView.findViewById(R.id.cont);
            container.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.cont)
                itemClickCallback.onItemClick(getAdapterPosition());
        }

    }
}
