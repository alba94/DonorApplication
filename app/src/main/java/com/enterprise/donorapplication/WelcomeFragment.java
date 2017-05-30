package com.enterprise.donorapplication;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.enterprise.Session.SessionManager;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;


public class WelcomeFragment extends Fragment {

    private float[] data = {25.3f, 22.4f, 21.4f, 20.5f, 50.5f, 9.5f, 3.5f, 5.5f};
    private String[] words = {"A+", "A-", "AB+", "AB-", "B+", "B-", "0+", "0-"};
    PieChart pie;

    TextView tv_view;
    SessionManager session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_welcome, container, false);
        tv_view = (TextView) rootview.findViewById(R.id.username);

        session = new SessionManager(this.getActivity());
        String name = session.getUsername();
        tv_view.setText("Welcome " + name);


        pie = (PieChart) rootview.findViewById(R.id.pie_chart);
        pie.setRotationEnabled(false);
        pie.setHoleRadius(20f);
        pie.setTransparentCircleAlpha(0);
        pie.setCenterText("Donors");
        addDataSet(pie);

        pie.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int pos1 = e.toString().indexOf("(sum):");
                String sales = e.toString().substring(pos1 + 6);

                for (int i = 0; i < data.length; i++) {
                    if ((data[i] == Float.parseFloat(sales))) {
                        pos1 = i;
                        break;
                    }
                }
                String emplyee = words[pos1];
                Toast.makeText(getActivity(), "emp " + emplyee + " sales " + sales, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected() {

            }
        });


        return rootview;
    }

    private void addDataSet(PieChart pie) {
        ArrayList<PieEntry> y = new ArrayList<>();
        ArrayList<String> xi = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            y.add(new PieEntry(data[i], i));
        }

        for (int i = 0; i < words.length; i++) {
            xi.add(words[i]);

        }

        PieDataSet pieData = new PieDataSet(y, "EMp");
        pieData.setSliceSpace(2);
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

        pieData.setColors(colors);


        PieData pieD = new PieData(pieData);
        pie.setData(pieD);
        pie.invalidate();


    }

}
