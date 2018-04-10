package com.example.hp.pmk;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hp on 26-Mar-18.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private ArrayList<Product> mProduct;

    public ProductAdapter(Myaccount myaccount, ArrayList<Product> list) {

        this.mProduct = list;
    }

    public ProductAdapter(MainActivity mainActivity, ArrayList<Product> mProductList) {

    }

    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.MyViewHolder holder, int position) {

       Product product = mProduct.get(position);
        holder.name.setText(product.getName());
        holder.ward.setText(product.getWard());
        holder.address.setText(product.getAddress());
        holder.pin.setText(product.getPincode());
        holder.mbl.setText(product.getMobile1());
        holder.dob.setText(product.getDob());
        holder.occpy.setText(product.getOccupation());
        holder.memid.setText(product.getMemberid());
        holder.dist.setText(product.getDistrict());
        holder.subdist.setText(product.getSubdistrict());
        holder.con.setText(product.getConstituency());
        holder.gen.setText(product.getGender());
        holder.marststus.setText(product.getMaritalstatus());
        holder.team.setText(product.getTeam());
        holder.priname.setText(product.getPrimaryname());
        holder.prilevel.setText(product.getPrimarylevel());
        holder.secname.setText(product.getSecondaryname());
        holder.seclevel.setText(product.getSecondarylevel());

    }

    @Override
    public int getItemCount() {
        return mProduct.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name,ward,address,pin,mbl,dob,occpy,memid,dist,subdist,con,gen,marststus,team,priname,prilevel,secname,seclevel;


        public MyViewHolder(View itemView) {
            super(itemView);


            name = (TextView) itemView.findViewById(R.id.name1);
            ward = (TextView) itemView.findViewById(R.id.ward1);
            address = (TextView) itemView.findViewById(R.id.address1);
            pin = (TextView) itemView.findViewById(R.id.pin1);
            mbl = (TextView) itemView.findViewById(R.id.mobile1);
            dob = (TextView) itemView.findViewById(R.id.dob1);
            occpy = (TextView) itemView.findViewById(R.id.business1);
            memid = (TextView) itemView.findViewById(R.id.id1);
            dist = (TextView) itemView.findViewById(R.id.district1);
            subdist = (TextView) itemView.findViewById(R.id.sub1);
            con = (TextView) itemView.findViewById(R.id.constituency1);
            gen = (TextView) itemView.findViewById(R.id.gender1);
            marststus = (TextView) itemView.findViewById(R.id.marital1);
            team = (TextView) itemView.findViewById(R.id.team1);
            priname = (TextView) itemView.findViewById(R.id.primarylevel1);
            prilevel = (TextView) itemView.findViewById(R.id.primary1);
            secname = (TextView) itemView.findViewById(R.id.secondarylevel1);
            seclevel = (TextView) itemView.findViewById(R.id.secondary1);

        }
    }
}
