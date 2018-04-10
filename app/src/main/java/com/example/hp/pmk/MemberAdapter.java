package com.example.hp.pmk;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hp on 07-Apr-18.
 */

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MyViewHolder> {

    private ArrayList<Member> mMember;

    public MemberAdapter(Constituency constituency, ArrayList<Member> mMember) {
        this.mMember = mMember;
    }
    public MemberAdapter(MainActivity mainActivity, ArrayList<Member> mMemberList) {

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.memberlayout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Member member = mMember.get(position);
        holder.name.setText(member.getName());
        holder.mbl.setText(member.getMobile1());
        holder.con.setText(member.getConstituency());
        holder.team.setText(member.getTeam());
        holder.priname.setText(member.getPrimaryname());


    }

    @Override
    public int getItemCount() {
        return mMember.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView name,mbl,con,team,priname;


        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.mem_name);
            mbl = (TextView) itemView.findViewById(R.id.mem_mobile);
            con = (TextView) itemView.findViewById(R.id.mem_constituency);
            team = (TextView) itemView.findViewById(R.id.mem_team);
            priname = (TextView) itemView.findViewById(R.id.mem_primaryrep);

    }
    }
}
