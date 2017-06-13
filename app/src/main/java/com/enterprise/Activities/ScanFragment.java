package com.enterprise.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScanFragment extends Fragment  {

    @Bind(R.id.button_scan)
    Button scanButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ButterKnife.bind(this, container);
        View rootview = inflater.inflate(R.layout.fragment_scan,container,false);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final IntentIntegrator scanIntegrator = new IntentIntegrator(getActivity());
                scanIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                scanIntegrator.setPrompt("Scan");
                scanIntegrator.setCameraId(0);
                scanIntegrator.initiateScan();
            }
        });

        return rootview;
    }


}

