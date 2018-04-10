package com.example.hp.pmk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class Signout extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View signout = inflater.inflate(R.layout.fragment_signout,container,false);


        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(getActivity(),PhoneLogin.class);
        startActivity(i);

        return signout;
    }

}
