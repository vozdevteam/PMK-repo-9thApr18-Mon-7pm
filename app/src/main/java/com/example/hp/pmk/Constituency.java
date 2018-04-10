package com.example.hp.pmk;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Constituency extends Fragment {

    RecyclerView recycle;
    ArrayList<Member> mMemberList=new ArrayList<>();
    MemberAdapter memberAdapter;
    LinearLayoutManager manager;

    TextView chennai, kovai, sivagakai, salem, thangavur, tharmapuri, thindugal, thiruchy, thirunelveli,
            thirupur, thiruvanamalai, ariyalur, thiruvallur, thiruvarur, thuthukodi, theni, nagapatinam, namakal, nilagiri, phudhukottai, perambalur,
            madurai, ramanathapuram, viruthunagar, vilupuram, vellore, erode, cudalore, karur, kaniyakumari, kanchipuram, krishnagiri;


    TextView subchennai1, subchennai2, subchennai3, subchennai4, subchennai5, subchennai6, subchennai7, subchennai8;
    TextView subkovai, subsivagangai, subsalem, subthangavur, subtharmapuri, subdindugul,subthiruchy, subthirunelveli, subthirupur, subthiruvanamalai,
            subariyalur, subthiruvallur, subthiruvarur, subthuthukodi, subtheni, subnagapatinam, subnamakal, subnilagiri, subphudhukottai,
            subperambalur, submadurai, subramanathapuram, subviruthunagar, subvilupuram, subvellore, suberode, subcudalore, subkarur, subkaniyakumari,
            subkanchipuram1, subkanchipuram2, subkanchipuram3, subkanchipuram4, subkanchipuram5, subkrishnagiri;

    DatabaseReference ref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View con = inflater.inflate(R.layout.fragment_constituency, container, false);

        //        district id:
        chennai = (TextView) con.findViewById(R.id.chennai);
        kovai = (TextView) con.findViewById(R.id.kovai);
        sivagakai = (TextView) con.findViewById(R.id.sivagakai);
        salem = (TextView) con.findViewById(R.id.salem);
        thangavur = (TextView) con.findViewById(R.id.thangavur);
        tharmapuri = (TextView) con.findViewById(R.id.tharmapuri);
        thindugal = (TextView) con.findViewById(R.id.thindugal);
        thiruchy = (TextView) con.findViewById(R.id.thiruchy);
        thirunelveli = (TextView) con.findViewById(R.id.thirunelveli);
        thirupur = (TextView) con.findViewById(R.id.thirupur);
        thiruvanamalai = (TextView) con.findViewById(R.id.thiruvanamalai);
        ariyalur = (TextView) con.findViewById(R.id.ariyalur);
        thiruvallur = (TextView) con.findViewById(R.id.thiruvallur);
        thiruvarur = (TextView) con.findViewById(R.id.thiruvarur);
        thuthukodi = (TextView) con.findViewById(R.id.thuthukodi);
        theni = (TextView) con.findViewById(R.id.theni);
        nagapatinam = (TextView) con.findViewById(R.id.nagapatinam);
        namakal = (TextView) con.findViewById(R.id.namakal);
        nilagiri = (TextView) con.findViewById(R.id.nilagiri);
        phudhukottai = (TextView) con.findViewById(R.id.phudhukottai);
        perambalur = (TextView) con.findViewById(R.id.perambalur);
        madurai = (TextView) con.findViewById(R.id.madurai);
        ramanathapuram = (TextView) con.findViewById(R.id.ramanathapuram);
        viruthunagar = (TextView) con.findViewById(R.id.viruthunagar);
        vilupuram = (TextView) con.findViewById(R.id.vilupuram);
        vellore = (TextView) con.findViewById(R.id.vellore);
        erode = (TextView) con.findViewById(R.id.erode);
        cudalore = (TextView) con.findViewById(R.id.cudalore);
        karur = (TextView) con.findViewById(R.id.karur);
        kaniyakumari = (TextView) con.findViewById(R.id.kaniyakumari);
        krishnagiri = (TextView) con.findViewById(R.id.krishnagiri);
        kanchipuram = (TextView) con.findViewById(R.id.kanchipuram);


//        subdistrict id:


        subchennai1 = (TextView) con.findViewById(R.id.northche1);
        subchennai2 = (TextView) con.findViewById(R.id.northche2);
        subchennai3 = (TextView) con.findViewById(R.id.northche3);
        subchennai4 = (TextView) con.findViewById(R.id.northche4);
        subchennai5 = (TextView) con.findViewById(R.id.northche5);
        subchennai6 = (TextView) con.findViewById(R.id.northche6);
        subchennai7 = (TextView) con.findViewById(R.id.northche7);
        subchennai8 = (TextView) con.findViewById(R.id.northche8);

        subkovai = (TextView) con.findViewById(R.id.subkovai1);
        subsivagangai = (TextView) con.findViewById(R.id.subsivagangai1);
        subsalem = (TextView) con.findViewById(R.id.subsalem1);
        subthangavur = (TextView) con.findViewById(R.id.subthangavur1);
        subtharmapuri = (TextView) con.findViewById(R.id.subtharmapuri1);
        subdindugul = (TextView) con.findViewById(R.id.subdindugul);
        subthiruchy = (TextView) con.findViewById(R.id.subthiruchy1);
        subthirunelveli = (TextView) con.findViewById(R.id.subthirunelveli1);
        subthirupur = (TextView) con.findViewById(R.id.subthirupur1);
        subthiruvanamalai = (TextView) con.findViewById(R.id.subthiruvanamalai1);
        subariyalur = (TextView) con.findViewById(R.id.subariyalur1);
        subthiruvallur = (TextView) con.findViewById(R.id.subthiruvallur1);
        subthiruvarur = (TextView) con.findViewById(R.id.subthiruvarur1);
        subthuthukodi = (TextView) con.findViewById(R.id.subthuthukodi1);
        subtheni = (TextView) con.findViewById(R.id.subtheni1);
        subnagapatinam = (TextView) con.findViewById(R.id.subnagapatinam1);
        subnamakal = (TextView) con.findViewById(R.id.subnamakal1);
        subnilagiri = (TextView) con.findViewById(R.id.subnilagiri1);
        subphudhukottai = (TextView) con.findViewById(R.id.subphudhukottai1);
        subperambalur = (TextView) con.findViewById(R.id.subperambalur1);
        submadurai = (TextView) con.findViewById(R.id.submadurai1);
        subramanathapuram = (TextView) con.findViewById(R.id.subramanathapuram1);
        subviruthunagar = (TextView) con.findViewById(R.id.subviruthunagar1);
        subvilupuram = (TextView) con.findViewById(R.id.subvilupuram1);
        subvellore = (TextView) con.findViewById(R.id.subvellore1);
        suberode = (TextView) con.findViewById(R.id.suberode1);
        subcudalore = (TextView) con.findViewById(R.id.subcudalore1);
        subkarur = (TextView) con.findViewById(R.id.subkarur1);
        subkaniyakumari = (TextView) con.findViewById(R.id.subkaniyakumari1);
        subkrishnagiri = (TextView) con.findViewById(R.id.subkrishnagiri1);
        subkanchipuram1 = (TextView) con.findViewById(R.id.subkanchipuram1);
        subkanchipuram2 = (TextView) con.findViewById(R.id.subkanchipuram2);
        subkanchipuram3 = (TextView) con.findViewById(R.id.subkanchipuram3);
        subkanchipuram4 = (TextView) con.findViewById(R.id.subkanchipuram4);
        subkanchipuram5 = (TextView) con.findViewById(R.id.subkanchipuram5);


//        Recyclerview for Members in Subdistrict

        recycle = (RecyclerView)con.findViewById(R.id.recyclerview);

        manager = new LinearLayoutManager(getActivity());
        recycle.setLayoutManager(manager);
        recycle.setHasFixedSize(true);
        memberAdapter = new MemberAdapter(Constituency.this,mMemberList);
        recycle.setAdapter(memberAdapter);




        subchennai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subchennai1.setEnabled(false);
                subchennai1.setEnabled(true);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("தென்சென்னை (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });

        subchennai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subchennai2.setEnabled(false);
                subchennai2.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref2 = database.getReference();

                ref2.child("Members").orderByChild("subdistrict").equalTo("தென்சென்னை (தெ)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });

        subchennai3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subchennai3.setEnabled(false);
                subchennai3.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref3 = database.getReference();

                ref3.child("Members").orderByChild("subdistrict").equalTo("தென்சென்னை (மே)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });

        subchennai4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subchennai4.setEnabled(false);
                subchennai4.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("மத்திய சென்னை (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });

        subchennai5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subchennai5.setEnabled(false);
                subchennai5.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("மத்திய சென்னை (மே)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });

        subchennai6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subchennai6.setEnabled(false);
                subchennai6.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("வடசென்னை (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });

        subchennai7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subchennai7.setEnabled(false);
                subchennai7.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("வடசென்னை (மே)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });

        subchennai8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subchennai8.setEnabled(false);
                subchennai8.setEnabled(true);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("வடசென்னை (வ)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subkovai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subkovai.setEnabled(false);
                subkovai.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("கோவை (வ)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subsivagangai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subsivagangai.setEnabled(false);
                subsivagangai.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("சிவகங்கை(ம)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });

        subsalem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subsalem.setEnabled(false);
                subsalem.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("சேலம் (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subthangavur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subthangavur.setEnabled(false);
                subthangavur.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("தஞ்சை (வ)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });

        subtharmapuri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subtharmapuri.setEnabled(false);
                subtharmapuri.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("தருமபுரி (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subdindugul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subtharmapuri.setEnabled(false);
                subtharmapuri.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("திண்டுக்கல் (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subthiruchy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subthiruchy.setEnabled(false);
                subthiruchy.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("திருச்சி (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subthirunelveli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subthirunelveli.setEnabled(false);
                subthirunelveli.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("திருநெல்வேலி (மாநகர்)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subthirupur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subthirupur.setEnabled(false);
                subthirupur.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("திருப்பூர் (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });

        subthiruvanamalai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subthiruvanamalai.setEnabled(false);
                subthiruvanamalai.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("திருவண்ணாமலை (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subariyalur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subariyalur.setEnabled(false);
                subariyalur.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("அரியலூர் (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subthiruvallur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subthiruvallur.setEnabled(false);
                subthiruvallur.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("திருவள்ளூர் (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subthiruvarur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subthiruvarur.setEnabled(false);
                subthiruvarur.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("திருவாரூர் (வ)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subthuthukodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subthuthukodi.setEnabled(false);
                subthuthukodi.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("தூத்துக்குடி (வ)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });

        subtheni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subtheni.setEnabled(false);
                subtheni.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("தேனி (வ)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subnagapatinam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subnagapatinam.setEnabled(false);
                subnagapatinam.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("நாகை (மே)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subnamakal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subnamakal.setEnabled(false);
                subnamakal.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("நாமக்கல் (ம)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subnilagiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subnilagiri.setEnabled(false);
                subnilagiri.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("நீலகிரி(ம)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subphudhukottai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subphudhukottai.setEnabled(false);
                subphudhukottai.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("புதுக்கோட்டை (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subperambalur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subperambalur.setEnabled(false);
                subperambalur.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("பெரம்பலூர்(ம)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        submadurai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submadurai.setEnabled(false);
                submadurai.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("மதுரை மாநகர்").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subramanathapuram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subramanathapuram.setEnabled(false);
                subramanathapuram.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("இராமநாதபுரம் (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subviruthunagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subviruthunagar.setEnabled(false);
                subviruthunagar.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("விருதுநகர் (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subvilupuram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subvilupuram.setEnabled(false);
                subvilupuram.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("விழுப்புரம் (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subvellore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subvellore.setEnabled(false);
                subvellore.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("வேலூர் (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        suberode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                suberode.setEnabled(false);
                suberode.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("ஈரோடு (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subcudalore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subcudalore.setEnabled(false);
                subcudalore.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("கடலூர் (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subkarur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subkarur.setEnabled(false);
                subkarur.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("கரூர் (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subkaniyakumari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subkaniyakumari.setEnabled(false);
                subkaniyakumari.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("கன்னியாகுமரி (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subkanchipuram1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subkanchipuram1.setEnabled(false);
                subkanchipuram1.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("காஞ்சிபுரம் (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });

        subkanchipuram2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subkanchipuram2.setEnabled(false);
                subkanchipuram2.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("காஞ்சிபுரம் (தெ)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });

        subkanchipuram3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subkanchipuram3.setEnabled(false);
                subkanchipuram3.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("காஞ்சிபுரம் (ம)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });

        subkanchipuram4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subkanchipuram4.setEnabled(false);
                subkanchipuram4.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("காஞ்சிபுரம் (மே)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subkanchipuram5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subkanchipuram5.setEnabled(false);
                subkanchipuram5.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("காஞ்சிபுரம் (வ)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        subkrishnagiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subkrishnagiri.setEnabled(false);
                subkrishnagiri.setEnabled(true);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference ref1 = database.getReference();

                ref1.child("Members").orderByChild("subdistrict").equalTo("கிருஷ்ணகிரி (கி)").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mMemberList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Member value = snapshot.getValue(Member.class);
                            mMemberList.add(value);
                        }
                        memberAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });


        //        // district spinner:

        ref = FirebaseDatabase.getInstance().getReference("மாவட்டங்கள்");

        ref.child("dis1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d1 = dataSnapshot.getValue(String.class);
                chennai.setText(d1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d2 = dataSnapshot.getValue(String.class);
                ariyalur.setText(d2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d3 = dataSnapshot.getValue(String.class);
                ramanathapuram.setText(d3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d4 = dataSnapshot.getValue(String.class);
                erode.setText(d4);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d5 = dataSnapshot.getValue(String.class);
                cudalore.setText(d5);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis6").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d6 = dataSnapshot.getValue(String.class);
                karur.setText(d6);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis7").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d7 = dataSnapshot.getValue(String.class);
                kaniyakumari.setText(d7);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis8").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d8 = dataSnapshot.getValue(String.class);
                kanchipuram.setText(d8);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis9").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d9 = dataSnapshot.getValue(String.class);
                krishnagiri.setText(d9);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis10").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d10 = dataSnapshot.getValue(String.class);
                kovai.setText(d10);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis11").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d11 = dataSnapshot.getValue(String.class);
                sivagakai.setText(d11);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis12").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d12 = dataSnapshot.getValue(String.class);
                salem.setText(d12);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis13").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d13 = dataSnapshot.getValue(String.class);
                thangavur.setText(d13);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis14").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d14 = dataSnapshot.getValue(String.class);
                tharmapuri.setText(d14);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis15").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d15 = dataSnapshot.getValue(String.class);
                thindugal.setText(d15);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ref.child("dis16").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d16 = dataSnapshot.getValue(String.class);
                thiruchy.setText(d16);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ref.child("dis17").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d17 = dataSnapshot.getValue(String.class);
                thirunelveli.setText(d17);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ref.child("dis18").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d18 = dataSnapshot.getValue(String.class);
                thirupur.setText(d18);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ref.child("dis19").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d19 = dataSnapshot.getValue(String.class);
                thiruvanamalai.setText(d19);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ref.child("dis20").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d20 = dataSnapshot.getValue(String.class);
                thiruvallur.setText(d20);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ref.child("dis21").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d21 = dataSnapshot.getValue(String.class);
                thiruvarur.setText(d21);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis22").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d22 = dataSnapshot.getValue(String.class);
                thuthukodi.setText(d22);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis23").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d23 = dataSnapshot.getValue(String.class);
                theni.setText(d23);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis24").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d24 = dataSnapshot.getValue(String.class);
                nagapatinam.setText(d24);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis25").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d25 = dataSnapshot.getValue(String.class);
                namakal.setText(d25);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis26").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d26 = dataSnapshot.getValue(String.class);
                nilagiri.setText(d26);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis27").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d21 = dataSnapshot.getValue(String.class);
                phudhukottai.setText(d21);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis28").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d28 = dataSnapshot.getValue(String.class);
                perambalur.setText(d28);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ref.child("dis29").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d29 = dataSnapshot.getValue(String.class);
                madurai.setText(d29);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis30").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d30 = dataSnapshot.getValue(String.class);
                viruthunagar.setText(d30);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis31").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d31 = dataSnapshot.getValue(String.class);
                vilupuram.setText(d31);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child("dis32").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String d32 = dataSnapshot.getValue(String.class);
                vellore.setText(d32);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //subdistrict-chennai

        chennai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ref = FirebaseDatabase.getInstance().getReference("சென்னை");

                ref.child("che1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String ncc1 = dataSnapshot.getValue(String.class);
                        subchennai1.setText(ncc1);

                        subchennai1.setVisibility(View.VISIBLE);
                        subchennai2.setVisibility(View.VISIBLE);
                        subchennai3.setVisibility(View.VISIBLE);
                        subchennai4.setVisibility(View.VISIBLE);
                        subchennai5.setVisibility(View.VISIBLE);
                        subchennai6.setVisibility(View.VISIBLE);
                        subchennai7.setVisibility(View.VISIBLE);
                        subchennai8.setVisibility(View.VISIBLE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                ref.child("che2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String ncc2 = dataSnapshot.getValue(String.class);
                        subchennai2.setText(ncc2);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                ref.child("che3").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String ncc3 = dataSnapshot.getValue(String.class);
                        subchennai3.setText(ncc3);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                ref.child("che4").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String ncc4 = dataSnapshot.getValue(String.class);
                        subchennai4.setText(ncc4);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                ref.child("che5").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String ncc5 = dataSnapshot.getValue(String.class);
                        subchennai5.setText(ncc5);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                ref.child("che6").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String ncc6 = dataSnapshot.getValue(String.class);
                        subchennai6.setText(ncc6);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                ref.child("che7").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String ncc7 = dataSnapshot.getValue(String.class);
                        subchennai7.setText(ncc7);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                ref.child("che8").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String ncc8 = dataSnapshot.getValue(String.class);
                        subchennai8.setText(ncc8);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });
//subdrict-kovai

        kovai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("கோயம்புத்தூர்");


                ref.child("covai").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String ko1 = dataSnapshot.getValue(String.class);
                        subkovai.setText(ko1);


                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.VISIBLE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


        //subdrict-sivagangai

        sivagakai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("சிவகங்கை");


                ref.child("siva").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String ko1 = dataSnapshot.getValue(String.class);
                        subsivagangai.setText(ko1);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.VISIBLE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        //subdrict-salem

        salem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("சேலம்");


                ref.child("salem").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String selam1 = dataSnapshot.getValue(String.class);
                        subsalem.setText(selam1);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.VISIBLE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        //subdrict-thangavur

        thangavur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("தஞ்சாவூர்");


                ref.child("tanjore").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String than1 = dataSnapshot.getValue(String.class);
                        subthangavur.setText(than1);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.VISIBLE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


        //subdrict-tharmapuri:

        tharmapuri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("தர்மபுரி");


                ref.child("dhar").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String tharmap = dataSnapshot.getValue(String.class);
                        subtharmapuri.setText(tharmap);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.VISIBLE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


        thindugal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("திண்டுக்கல்");


                ref.child("dgl").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String dgl = dataSnapshot.getValue(String.class);
                        subtharmapuri.setText(dgl);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.VISIBLE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });



        //subdrict-thiruchy:

        thiruchy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("திருச்சிராப்பள்ளி");


                ref.child("tri").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String thiruch = dataSnapshot.getValue(String.class);
                        subthiruchy.setText(thiruch);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.VISIBLE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-thirunelveli:

        thirunelveli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("திருநெல்வேலி");


                ref.child("nellai").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String thirunelvel = dataSnapshot.getValue(String.class);
                        subthirunelveli.setText(thirunelvel);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.VISIBLE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-thirupur:

        thirupur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("திருப்பூர்");


                ref.child("tpr").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String thirupurr = dataSnapshot.getValue(String.class);
                        subthirupur.setText(thirupurr);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.VISIBLE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-thiruvannamlai:

        thiruvanamalai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("திருவண்ணாமலை");


                ref.child("tv").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String thiruvannamal = dataSnapshot.getValue(String.class);
                        subthiruvanamalai.setText(thiruvannamal);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.VISIBLE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-ariyalore:

        ariyalur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("அரியலூர்");


                ref.child("ariyalur").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String ariyalo = dataSnapshot.getValue(String.class);
                        subariyalur.setText(ariyalo);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.VISIBLE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-thiruvallur:

        thiruvallur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("திருவள்ளூர்");


                ref.child("thiru").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String thiruvallu = dataSnapshot.getValue(String.class);
                        subthiruvallur.setText(thiruvallu);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.VISIBLE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-thiruvarur:

        thiruvarur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("திருவாரூர்");


                ref.child("thiruvarur").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String thiruvar = dataSnapshot.getValue(String.class);
                        subthiruvarur.setText(thiruvar);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.VISIBLE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-thuthukodi:

        thuthukodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("தூத்துக்குடி");


                ref.child("tuti").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String thuthtu = dataSnapshot.getValue(String.class);
                        subthuthukodi.setText(thuthtu);


                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.VISIBLE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-theni:

        theni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("தேனி");


                ref.child("theni").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String theniw = dataSnapshot.getValue(String.class);
                        subtheni.setText(theniw);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.VISIBLE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-nagapatinam:

        nagapatinam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("நாகப்பட்டினம்");


                ref.child("nagai").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String nagap = dataSnapshot.getValue(String.class);
                        subnagapatinam.setText(nagap);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.VISIBLE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-namakal:

        namakal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("நாமக்கல்");


                ref.child("namak").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String namaka = dataSnapshot.getValue(String.class);
                        subnamakal.setText(namaka);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.VISIBLE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-nilagiri:

        nilagiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("நீலகிரி");


                ref.child("nilgiri").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String nilag = dataSnapshot.getValue(String.class);
                        subnilagiri.setText(nilag);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.VISIBLE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-phuthukottai:

        phudhukottai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("புதுக்கோட்டை");


                ref.child("pudhu").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String phudhu = dataSnapshot.getValue(String.class);
                        subphudhukottai.setText(phudhu);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.VISIBLE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-peramballur:

        perambalur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("பெரம்பலூர்");


                ref.child("peram").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String peramb = dataSnapshot.getValue(String.class);
                        subperambalur.setText(peramb);


                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.VISIBLE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-madurai:

        madurai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("மதுரை");


                ref.child("madurai").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String madurai = dataSnapshot.getValue(String.class);
                        submadurai.setText(madurai);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.VISIBLE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-ramanathapuram:

        ramanathapuram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("இராமநாதபுரம்");


                ref.child("ramnad").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String ramana = dataSnapshot.getValue(String.class);
                        subramanathapuram.setText(ramana);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.VISIBLE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

//subdrict-viruthunagar:

        viruthunagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("விருதுநகர்");


                ref.child("viruthu").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String virudhu = dataSnapshot.getValue(String.class);
                        subviruthunagar.setText(virudhu);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.VISIBLE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-vilupuram:

        vilupuram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("விழுப்புரம்");


                ref.child("villu").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String vilupura = dataSnapshot.getValue(String.class);
                        subvilupuram.setText(vilupura);
                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.VISIBLE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-vellore:

        vellore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("வேலூர்");


                ref.child("vellore").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String vellor = dataSnapshot.getValue(String.class);
                        subvellore.setText(vellor);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.VISIBLE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-erode:

        erode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("ஈரோடு");


                ref.child("erode").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String erod = dataSnapshot.getValue(String.class);
                        suberode.setText(erod);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.VISIBLE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-cadalore:

        cudalore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("கடலூர்");


                ref.child("cuddalore").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String cadalo = dataSnapshot.getValue(String.class);
                        subcudalore.setText(cadalo);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.VISIBLE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

//subdrict-karur:

        karur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("கரூர்");


                ref.child("karur").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String karau = dataSnapshot.getValue(String.class);
                        subkarur.setText(karau);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.VISIBLE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

//sub-kanchipuram:

        kanchipuram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ref = FirebaseDatabase.getInstance().getReference("காஞ்சிபுரம்");

                ref.child("kanchi1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String kan1 = dataSnapshot.getValue(String.class);
                        subkanchipuram1.setText(kan1);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.VISIBLE);
                        subkanchipuram2.setVisibility(View.VISIBLE);
                        subkanchipuram3.setVisibility(View.VISIBLE);
                        subkanchipuram4.setVisibility(View.VISIBLE);
                        subkanchipuram5.setVisibility(View.VISIBLE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                ref.child("kanchi2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String kan2 = dataSnapshot.getValue(String.class);
                        subkanchipuram2.setText(kan2);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                ref.child("kanchi3").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String kan3 = dataSnapshot.getValue(String.class);
                        subkanchipuram3.setText(kan3);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                ref.child("kanchi4").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String kan4 = dataSnapshot.getValue(String.class);
                        subkanchipuram4.setText(kan4);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                ref.child("kanchi5").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String kan5 = dataSnapshot.getValue(String.class);
                        subkanchipuram5.setText(kan5);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-kanniyakumari:

        kaniyakumari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("கன்னியாகுமரி");


                ref.child("kumari").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String kanniyaku = dataSnapshot.getValue(String.class);
                        subkaniyakumari.setText(kanniyaku);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.VISIBLE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


//subdrict-krishnagiri:

        krishnagiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference("கிருஷ்ணகிரி");


                ref.child("krishnagiri").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String krishnagir = dataSnapshot.getValue(String.class);
                        subkrishnagiri.setText(krishnagir);

                        subchennai1.setVisibility(View.GONE);
                        subchennai2.setVisibility(View.GONE);
                        subchennai3.setVisibility(View.GONE);
                        subchennai4.setVisibility(View.GONE);
                        subchennai5.setVisibility(View.GONE);
                        subchennai6.setVisibility(View.GONE);
                        subchennai7.setVisibility(View.GONE);
                        subchennai8.setVisibility(View.GONE);
                        subkovai.setVisibility(View.GONE);
                        subsivagangai.setVisibility(View.GONE);
                        subsalem.setVisibility(View.GONE);
                        subthangavur.setVisibility(View.GONE);
                        subtharmapuri.setVisibility(View.GONE);
                        subdindugul.setVisibility(View.GONE);
                        subthiruchy.setVisibility(View.GONE);
                        subthirunelveli.setVisibility(View.GONE);
                        subthirupur.setVisibility(View.GONE);
                        subthiruvanamalai.setVisibility(View.GONE);
                        subariyalur.setVisibility(View.GONE);
                        subthiruvallur.setVisibility(View.GONE);
                        subthiruvarur.setVisibility(View.GONE);
                        subthuthukodi.setVisibility(View.GONE);
                        subtheni.setVisibility(View.GONE);
                        subnagapatinam.setVisibility(View.GONE);
                        subnamakal.setVisibility(View.GONE);
                        subnilagiri.setVisibility(View.GONE);
                        subphudhukottai.setVisibility(View.GONE);
                        subperambalur.setVisibility(View.GONE);
                        submadurai.setVisibility(View.GONE);
                        subramanathapuram.setVisibility(View.GONE);
                        subviruthunagar.setVisibility(View.GONE);
                        subvilupuram.setVisibility(View.GONE);
                        subvellore.setVisibility(View.GONE);
                        suberode.setVisibility(View.GONE);
                        subcudalore.setVisibility(View.GONE);
                        subkarur.setVisibility(View.GONE);
                        subkaniyakumari.setVisibility(View.GONE);
                        subkanchipuram1.setVisibility(View.GONE);
                        subkanchipuram2.setVisibility(View.GONE);
                        subkanchipuram3.setVisibility(View.GONE);
                        subkanchipuram4.setVisibility(View.GONE);
                        subkanchipuram5.setVisibility(View.GONE);
                        subkrishnagiri.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


        return con;
    }


}
