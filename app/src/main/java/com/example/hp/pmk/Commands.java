package com.example.hp.pmk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Commands extends Fragment {

    TextView view;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View commands = inflater.inflate(R.layout.fragment_commands, container, false);

        view = (TextView) commands.findViewById(R.id.commands);

        return commands;
    }


}
