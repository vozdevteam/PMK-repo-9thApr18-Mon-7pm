package com.example.hp.pmk;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class News extends Fragment {

    TextView view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View news = inflater.inflate(R.layout.fragment_news, container, false);

        view = (TextView) news.findViewById(R.id.cons);



        return news;
    }


}
