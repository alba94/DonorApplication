package com.enterprise.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enterprise.activities.AddDonor;
import com.enterprise.activities.DetailActivity;
import com.enterprise.activities.R;
import com.enterprise.adapter.DonorAdapter;
import com.enterprise.responses.Donor;
import com.enterprise.services.DonorService;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DonorFragment extends Fragment implements DonorAdapter.ItemClickCallback {

    @Bind(R.id.recycler_lists)
    RecyclerView recyclerView;

    @Bind(R.id.search_donor)
    SearchView search;

    public DonorAdapter adapter;
    List<Donor> list;

    private final static String NameExtra = "NameExtra";
    private final static String BloodExtra = "BloodExtra";
    private final static String CityExtra = "CelExtra";
    private final static String BundleExtra = "BundleExtra";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.activity_question_lists, container, false);
        ButterKnife.bind(this, rootview);

        list = DonorService.getAllDonors();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(true);
        adapter = new DonorAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);
        adapter.setItemClickCallback(this);

        search = (SearchView) rootview.findViewById(R.id.search_donor);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                adapter.getFilter().filter(query);
                return false;

            }
        });


        FloatingActionButton fab = (FloatingActionButton) rootview.findViewById(R.id.floating_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddDonor.class);
                startActivity(intent);

            }
        });


        return rootview;
    }

    @Override

    public void onItemClick(int p) {

        Donor d = list.get(p);
        Intent intent = new Intent(getActivity(), DetailActivity.class);

        Bundle extras = new Bundle();
        extras.putString(NameExtra, d.getName());
        extras.putString(BloodExtra, d.getBllodtype());
        extras.putString(CityExtra, d.getCity().getName());


        intent.putExtra(BundleExtra, extras);
        startActivity(intent);

    }

}


