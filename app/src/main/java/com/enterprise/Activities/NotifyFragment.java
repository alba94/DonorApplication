package com.enterprise.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.enterprise.dagger.Injector;
import com.enterprise.requests.AreaNotifyRequest;
import com.enterprise.services.DonationService;
import com.enterprise.services.LoginService;

import javax.inject.Inject;


public class NotifyFragment extends Fragment {

    Spinner city_sp;
    Spinner blood_sp;
    Button msg;
    AreaNotifyRequest request;

    @Inject
    LoginService loginService;

    @Inject
    DonationService donationService;

    private boolean initializedView=true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootview = inflater.inflate(R.layout.fragment_notify,container,false);
        request = new AreaNotifyRequest();
        city_sp = (Spinner) rootview.findViewById(R.id.spinner_city);
        blood_sp = (Spinner) rootview.findViewById(R.id.spinner_blood);

        msg = (Button) rootview.findViewById(R.id.button_notify);

        Injector.applicationComponent().inject(this);

        //Spinner 1
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.country_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city_sp.setAdapter(adapter);
        city_sp.setSelection(0,false);
        city_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (initializedView) {
                    initializedView = false;
                } else {
                    request.setCityName(city_sp.getSelectedItem().toString());
                    Toast.makeText(getActivity(), request.getCityName(), Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Spinner 2
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),R.array.blood_array,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blood_sp.setAdapter(adapter1);
        blood_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (initializedView) {
                    initializedView = false;
                } else {
                    request.setBloodType(blood_sp.getSelectedItem().toString());
                    Toast.makeText(getActivity(), request.getBloodType(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        msg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Message sent", Toast.LENGTH_LONG).show();

                if (donationService.notifyArea(request))
                {
                    Toast.makeText(getActivity(), "Message sent", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "A problem occurred,try again!", Toast.LENGTH_LONG).show();
                }
            }
        });

        return rootview;
    }
}