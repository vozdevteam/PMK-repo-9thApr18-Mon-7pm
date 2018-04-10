package com.example.hp.pmk;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Myaccount extends Fragment {

    RecyclerView cycle;
    ArrayList<Product> mProductList=new ArrayList<>();
    ProductAdapter productAdapter;
    LinearLayoutManager manager;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myaccount = inflater.inflate(R.layout.fragment_myaccount,container,false);

        cycle = (RecyclerView) myaccount.findViewById(R.id.recyclerview);

        manager = new LinearLayoutManager(getActivity());
        cycle.setLayoutManager(manager);
        cycle.setHasFixedSize(true);
        productAdapter = new ProductAdapter(Myaccount.this, mProductList);
        cycle.setAdapter(productAdapter);


        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            FirebaseDatabase.getInstance().getReference().child("Members").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Product item = dataSnapshot.getValue(Product.class);
                    mProductList.add(item);

                    productAdapter.notifyDataSetChanged();



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }






        return  myaccount;

    }

}
