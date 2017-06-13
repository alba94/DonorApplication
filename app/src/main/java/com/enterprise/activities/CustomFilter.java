package com.enterprise.activities;

import android.widget.Filter;

import com.enterprise.adapter.DonorAdapter;
import com.enterprise.responses.Donors;

import java.util.ArrayList;

/**
 * Created by albal on 07/06/2017.
 */

public class CustomFilter extends Filter {

    private DonorAdapter adapter;
    private ArrayList<Donors> filterList;

    public CustomFilter(ArrayList<Donors> filterList, DonorAdapter adapter) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if (constraint != null && constraint.length() > 0) {

            constraint = constraint.toString().toUpperCase();

            ArrayList<Donors> filteredDonors = new ArrayList<>();
            for (int i = 0; i < filterList.size(); i++) {

                if (filterList.get(i).getQuestion().toUpperCase().contains(constraint) || filterList.get(i).getCity().toUpperCase().contains(constraint) || filterList.get(i).getBloodType().toUpperCase().contains(constraint)) {

                    filteredDonors.add(filterList.get(i));
                }
            }
            results.count = filteredDonors.size();
            results.values = filteredDonors;
        } else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.quest = (ArrayList<Donors>) results.values;
        adapter.notifyDataSetChanged();
    }
}