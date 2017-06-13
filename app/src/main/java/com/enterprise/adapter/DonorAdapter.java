package com.enterprise.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.enterprise.activities.CustomFilter;
import com.enterprise.activities.R;
import com.enterprise.responses.Donors;

import java.util.ArrayList;

/**
 * Created by albal on 04/06/2017.
 */

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.QuizHolder> implements Filterable {

    public ArrayList<Donors> quest, filterList;
    private CustomFilter filter;
    private LayoutInflater inflater;
    private ItemClickCallback itemClickCallback;

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(filterList, this);
        }
        return filter;
    }

    public interface ItemClickCallback {
        void onItemClick(int p);

    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }


    public DonorAdapter(ArrayList<Donors> quest, Context c) {
        this.inflater = (LayoutInflater.from(c));
        this.quest = quest;
        this.filterList = quest;
    }

    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.liste_items, parent, false);
        return new QuizHolder(view);
    }

    @Override
    public void onBindViewHolder(QuizHolder holder, int position) {
        Donors item = quest.get(position);
        holder.title.setText(item.getQuestion());
        holder.city.setText(item.getCity());
        holder.blood.setText(item.getBloodType());

    }

    @Override
    public int getItemCount() {
        return quest.size();
    }

    class QuizHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView city;
        TextView blood;
        View container;

        public QuizHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.hai);
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
