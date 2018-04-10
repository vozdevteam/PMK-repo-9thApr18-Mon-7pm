package com.example.hp.pmk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Map extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View map = inflater.inflate(R.layout.fragment_map, container, false);

        Intent i= new Intent(android.content.Intent.ACTION_VIEW);
        i.setData(Uri.parse("http://maps.google.com"));
        startActivity(i);

        return map;
    }


}
