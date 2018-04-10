package com.example.hp.pmk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Search extends Fragment {

    TextView view;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View search = inflater.inflate(R.layout.fragment_search, container, false);

        view = (TextView) search.findViewById(R.id.search);



        return search;
    }


}
