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
import com.enterprise.responses.DonorData;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DonorFragment extends Fragment implements DonorAdapter.ItemClickCallback {

    @Bind(R.id.recycler_lists)
    RecyclerView recyclerView;

    @Bind(R.id.search_donor)
    SearchView search;


    public DonorAdapter adapter;
    ArrayList listData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.activity_question_lists, container, false);
        ButterKnife.bind(this, rootview);

        listData = (ArrayList) DonorData.getListData();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(true);
        adapter = new DonorAdapter(DonorData.getListData(), getActivity());
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
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        startActivity(intent);

    }

}
