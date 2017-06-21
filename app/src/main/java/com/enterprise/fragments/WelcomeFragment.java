package com.enterprise.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.enterprise.activities.R;
import com.enterprise.responses.DonationsPerMonthResponse;
import com.enterprise.services.DonorService;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WelcomeFragment extends Fragment {

    private long[] data = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private String[] month = {"Janar", "Shkurt", "Mars", "Prill", "Maj", "Qershor", "Korrik", "Gusht", "Shtator", "Tetor", "Nentor", "Dhjetor"};

    @Bind(R.id.pie_chart)
    PieChart pie;


    private void init() {
        List<DonationsPerMonthResponse> lista = DonorService.getStatistics();
        for(DonationsPerMonthResponse donation:lista)
        {
            data[donation.getMounth()-1]=donation.getNumberOfDonors();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_welcome, container, false);

        ButterKnife.bind(this, rootview);
        init();

        pie.setRotationEnabled(true);
        pie.setHoleRadius(20f);
        pie.setTransparentCircleAlpha(0);
        pie.setCenterText("");
        addDataSet(pie);

        pie.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int pos1 = e.toString().indexOf("(sum):");
                int nrDhurimeve=(int)e.getY();

                String muaji = month[pos1];
                Toast.makeText(getActivity(), "Muaji " + muaji + " Nr dhurimeve " + nrDhurimeve, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected() {

            }
        });


        return rootview;
    }

    private void addDataSet(PieChart pie) {
        ArrayList<PieEntry> y = new ArrayList<>();
        PieEntry entry;
        for (int i = 0; i < data.length; i++) {
            entry=new PieEntry(data[i],month[i]);
            y.add(entry);
        }

        PieDataSet pieData = new PieDataSet(y, "Muaji");
        pieData.setSliceSpace(4);
        pieData.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.GRAY);
        colors.add(Color.MAGENTA);
        colors.add(Color.CYAN);
        colors.add(Color.GREEN);
        colors.add(Color.WHITE);
        colors.add(Color.BLACK);
        colors.add(Color.LTGRAY);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);

        pieData.setColors(colors);


        PieData pieD = new PieData(pieData);
        pie.setData(pieD);
        pie.invalidate();


    }

}
