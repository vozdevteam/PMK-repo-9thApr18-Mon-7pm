package com.example.hp.pmk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Donation extends Fragment {

    TextView view;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View donation = inflater.inflate(R.layout.fragment_donation, container, false);

        view = (TextView) donation.findViewById(R.id.donation);
        return donation;
    }


}
