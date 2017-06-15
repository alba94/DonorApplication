package com.enterprise.adapter;

import android.widget.Filter;

import com.enterprise.responses.Donor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by albal on 07/06/2017.
 */

public class DonorsFilter extends Filter {

    private DonorAdapter adapter;
    private List<Donor> filterList;

    public DonorsFilter(List<Donor> filterList, DonorAdapter adapter) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if (constraint != null && constraint.length() > 0) {

            constraint = constraint.toString().toUpperCase();

            List<Donor> filteredDonors = new ArrayList<>();
            for (int i = 0; i < filterList.size(); i++) {

                if (filterList.get(i).getName().toUpperCase().contains(constraint)) {
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
        adapter.quest = (List<Donor>) results.values;
        adapter.notifyDataSetChanged();
    }
}