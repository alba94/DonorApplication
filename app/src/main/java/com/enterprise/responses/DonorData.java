package com.enterprise.responses;

import java.util.ArrayList;

/**
 * Created by albal on 04/06/2017.
 */

public class DonorData {

    private static final String[] titles = {"Alba Llubani", "Donaldo Lika", "Enkeleda Elezi", "Besnik Palluqi"};
    private static final String[] cities = {"Kruje", "Korce", "Tirane", "Elbasan"};
    private static final String[] blood = {"A+", "B+", "AB+", "0+"};

    public static ArrayList<Donors> getListData() {
        ArrayList<Donors> data = new ArrayList<>();

        for (int x = 0; x < 5; x++) {

            for (int i = 0; i < titles.length && i < cities.length && i < blood.length; i++) {
                Donors item = new Donors();
                item.setQuestion(titles[i]);
                item.setCity(cities[i]);
                item.setBloodType(blood[i]);
                data.add(item);
            }
        }
        return data;
    }

}
