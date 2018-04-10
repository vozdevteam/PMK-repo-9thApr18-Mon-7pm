package com.example.hp.pmk;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Registration extends AppCompatActivity {

    private EditText ed_name, ed_ward, ed_address, ed_pin, ed_mobile1, ed_age, ed_occupation, ed_memberid;
    Button submit;
    Spinner s1, s2, s3, t1, t2, t3, t4, t5;
    RadioButton radioButton, status;
    RadioGroup radioGroup, statusgroup;
    Task<Void> sav;

    private static final int STORAGE_PERMISSION_CODE = 123;
    ImageView image;
    String userChoosenTask;
    private int REQUEST_CAMERA = 0;
    private int SELECT_FILE = 1;
    private int PICK_IMAGE_REQUEST = 1;
    StorageReference mStorage;
    ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        submit = (Button) findViewById(R.id.submit);
        ed_name = (EditText) findViewById(R.id.name);
        ed_ward = (EditText) findViewById(R.id.ward);
        ed_address = (EditText) findViewById(R.id.address);
        ed_pin = (EditText) findViewById(R.id.pin);
        ed_mobile1 = (EditText) findViewById(R.id.mobile1);
        ed_age = (EditText) findViewById(R.id.age);
        ed_occupation = (EditText) findViewById(R.id.occupation);
        ed_memberid = (EditText) findViewById(R.id.memberid);

        radioGroup = (RadioGroup) findViewById(R.id.rg1);
        statusgroup = (RadioGroup) findViewById(R.id.r1);

        image = (ImageView) findViewById(R.id.img);
        mStorage = FirebaseStorage.getInstance().getReference();

        mProgress = new ProgressDialog(this);


//            district spinner
        s1 = (Spinner) findViewById(R.id.district);
        s2 = (Spinner) findViewById(R.id.subdistrict);
        s3 = (Spinner) findViewById(R.id.constituency);

//                     team spinner

        t1 = (Spinner) findViewById(R.id.teamname);
        t2 = (Spinner) findViewById(R.id.primary);
        t3 = (Spinner) findViewById(R.id.primary1);
        t4 = (Spinner) findViewById(R.id.secondary);
        t5 = (Spinner) findViewById(R.id.secondary1);


        image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                selectImage();
            }

        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

                mProgress = new ProgressDialog(Registration.this);
                mProgress.setMessage("Registering....");
                mProgress.setIndeterminate(true);
                mProgress.show();
                mProgress.setCancelable(false);

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);

                int statusId = statusgroup.getCheckedRadioButtonId();
                status = (RadioButton) findViewById(statusId);


                String name = ed_name.getText().toString().trim();
                String ward = ed_ward.getText().toString().trim();
                String address = ed_address.getText().toString().trim();
                String pin = ed_pin.getText().toString().trim();
                String mbl1 = ed_mobile1.getText().toString().trim();
                String dob = ed_age.getText().toString().trim();
                String occ = ed_occupation.getText().toString().trim();
                String memid = ed_memberid.getText().toString().trim();
                String dis = s1.getSelectedItem().toString();
                String sub = s2.getSelectedItem().toString();
                String con = s3.getSelectedItem().toString();
                String team = t1.getSelectedItem().toString();
                String pri1 = t2.getSelectedItem().toString();
                String pri2 = t3.getSelectedItem().toString();
                String sec1 = t4.getSelectedItem().toString();
                String sec2 = t5.getSelectedItem().toString();
                String gender = radioButton.getText().toString();
                String marital = status.getText().toString();


                if (name.isEmpty()){
                    Toast.makeText(Registration.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }else if (ward.isEmpty()){
                    Toast.makeText(Registration.this, "Enter Ward", Toast.LENGTH_SHORT).show();
                }else if(address.isEmpty()){
                    Toast.makeText(Registration.this, "Enter Address", Toast.LENGTH_SHORT).show();
                }else if (pin.isEmpty()){
                    Toast.makeText(Registration.this, "Enter Pincode", Toast.LENGTH_SHORT).show();
                }else if (mbl1.isEmpty()){
                    Toast.makeText(Registration.this, "Enter Mobile", Toast.LENGTH_SHORT).show();
                }else if (dob.isEmpty()){
                    Toast.makeText(Registration.this, "Enter Date of Birth", Toast.LENGTH_SHORT).show();
                }else if (occ.isEmpty()){
                    Toast.makeText(Registration.this, "Enter Occupation", Toast.LENGTH_SHORT).show();
                }else if (memid.isEmpty()){
                    Toast.makeText(Registration.this, "Enter MemberID", Toast.LENGTH_SHORT).show();
                } else {

                    Artist artist = new Artist(name,ward,address,gender,marital,occ,mbl1,dob,dis,sub,memid,pin,pri1,pri2,sec1,sec2,team,con);

                    sav = FirebaseDatabase.getInstance().getReference().child("Members").child(user.getUid()).setValue(artist);

                    mProgress.dismiss();

                    Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Registration.this,MainActivity.class));

                    finish();

                }


            }
        });

        // first spinner

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("மாவட்டங்கள்");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<String> name = new ArrayList<String>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                    String areaName = areaSnapshot.getValue(String.class);
                    name.add(areaName);
                }


                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, name);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s1.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        // second spinner

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String sp1 = String.valueOf(s1.getSelectedItem());

                if (sp1.contentEquals("சென்னை")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("சென்னை");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> chennai = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            chennai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(chennai);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }


                if (sp1.contentEquals("கோயம்புத்தூர்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("கோயம்புத்தூர்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> kovai = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            kovai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(kovai);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }


                if (sp1.contentEquals("சிவகங்கை")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("சிவகங்கை");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> siva = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            siva.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(siva);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("சேலம்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("சேலம்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> salem = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            salem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(salem);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("தஞ்சாவூர்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("தஞ்சாவூர்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> than = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            than.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(than);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("தர்மபுரி")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("தர்மபுரி");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> dharmapuri = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            dharmapuri.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(dharmapuri);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("திண்டுக்கல்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("திண்டுக்கல்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> dgl = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            dgl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(dgl);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("திருச்சிராப்பள்ளி")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("திருச்சிராப்பள்ளி");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> trichy = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            trichy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(trichy);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("திருநெல்வேலி")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("திருநெல்வேலி");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> veli = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            veli.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(veli);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("திருப்பூர்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("திருப்பூர்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> tpr = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            tpr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(tpr);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("திருவண்ணாமலை")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("திருவண்ணாமலை");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> thiru = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            thiru.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(thiru);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("அரியலூர்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("அரியலூர்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> ariyalur = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            ariyalur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(ariyalur);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("திருவள்ளூர்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("திருவள்ளூர்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> vallur = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            vallur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(vallur);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("திருவாரூர்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("திருவாரூர்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> tiruvarur = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            tiruvarur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(tiruvarur);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("தூத்துக்குடி")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("தூத்துக்குடி");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> kodi = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            kodi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(kodi);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("தேனி")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("தேனி");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> theni = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            theni.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(theni);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("நாகப்பட்டினம்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("நாகப்பட்டினம்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> nagai = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            nagai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(nagai);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("நாமக்கல்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("நாமக்கல்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> namakal = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            namakal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(namakal);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("நீலகிரி")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("நீலகிரி");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> nilagiri = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            nilagiri.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(nilagiri);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("புதுக்கோட்டை")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("புதுக்கோட்டை");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> pudhu = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            pudhu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(pudhu);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("பெரம்பலூர்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("பெரம்பலூர்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> perambalur = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            perambalur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(perambalur);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("மதுரை")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("மதுரை");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> madu = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            madu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(madu);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("இராமநாதபுரம்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("இராமநாதபுரம்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> rama = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            rama.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(rama);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("விருதுநகர்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("விருதுநகர்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> viruthu = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            viruthu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(viruthu);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("விழுப்புரம்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("விழுப்புரம்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> vilupuram = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            vilupuram.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(vilupuram);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("வேலூர்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("வேலூர்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> vell = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            vell.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(vell);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("ஈரோடு")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("ஈரோடு");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> erode = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            erode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(erode);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("கடலூர்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("கடலூர்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> kadalur = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            kadalur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(kadalur);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("கரூர்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("கரூர்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> karur = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            karur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(karur);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("கன்னியாகுமரி")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("கன்னியாகுமரி");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> kumari = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            kumari.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(kumari);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp1.contentEquals("கிருஷ்ணகிரி")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("கிருஷ்ணகிரி");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> giri = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            giri.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(giri);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

                if (sp1.contentEquals("காஞ்சிபுரம்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("காஞ்சிபுரம்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> kanchi1 = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            kanchi1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s2.setAdapter(kanchi1);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //  third spinner


        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String sp2 = String.valueOf(s2.getSelectedItem());

                if (sp2.contentEquals("தென்சென்னை (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("தென்சென்னை (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> one = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            one.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(one);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("தென்சென்னை (தெ)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("தென்சென்னை (தெ)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> one = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            one.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(one);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("தென்சென்னை (மே)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("தென்சென்னை (மே)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> one = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            one.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(one);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("மத்திய சென்னை (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("மத்திய சென்னை (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> one = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            one.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(one);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("மத்திய சென்னை (மே)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("மத்திய சென்னை (மே)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> one = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            one.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(one);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("வடசென்னை (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("வடசென்னை (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> one = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            one.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(one);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("வடசென்னை (மே)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("வடசென்னை (மே)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> one = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            one.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(one);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("வடசென்னை (வ)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("வடசென்னை (வ)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> one = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            one.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(one);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

                if (sp2.contentEquals("கோவை (வ)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("கோவை (வ)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> one = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            one.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(one);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("சிவகங்கை(ம)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("சிவகங்கை(ம)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> sivagangai = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            sivagangai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(sivagangai);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("சேலம் (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("சேலம் (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> one = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            one.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(one);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("தஞ்சை (வ)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("தஞ்சை (வ)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> tanjore = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            tanjore.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(tanjore);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("தருமபுரி (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("தருமபுரி (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> dharma = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            dharma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(dharma);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("திண்டுக்கல் (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("திண்டுக்கல் (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> dindugul = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            dindugul.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(dindugul);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("திருச்சி (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("திருச்சி (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> tri = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            tri.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(tri);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("திருநெல்வேலி (மாநகர்)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("திருநெல்வேலி (மாநகர்)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> alwa = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            alwa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(alwa);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("திருப்பூர் (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("திருப்பூர் (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> tiruppur = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            tiruppur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(tiruppur);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("திருவண்ணாமலை (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("திருவண்ணாமலை (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> tv = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            tv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(tv);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("அரியலூர் (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("அரியலூர் (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> ari = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            ari.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(ari);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("திருவள்ளூர் (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("திருவள்ளூர் (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> tvallur = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            tvallur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(tvallur);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("திருவாரூர் (வ)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("திருவாரூர் (வ)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> varur = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            varur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(varur);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("தூத்துக்குடி (வ)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("தூத்துக்குடி (வ)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> tuti = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            tuti.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(tuti);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("தேனி (வ)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("தேனி (வ)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> the = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            the.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(the);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("நாகை (மே)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("நாகை (மே)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> naga = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            naga.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(naga);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("நாமக்கல் (ம)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("நாமக்கல் (ம)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> nama = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            nama.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(nama);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("நீலகிரி(ம)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("நீலகிரி(ம)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> nil = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            nil.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(nil);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("புதுக்கோட்டை (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("புதுக்கோட்டை (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> pkottai = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            pkottai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(pkottai);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("பெரம்பலூர்(ம)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("பெரம்பலூர்(ம)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> pera = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            pera.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(pera);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("மதுரை மாநகர்")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("மதுரை மாநகர்");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> madurai = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            madurai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(madurai);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("விருதுநகர் (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("விருதுநகர் (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> viruthunagar = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            viruthunagar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(viruthunagar);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

                if (sp2.contentEquals("விழுப்புரம் (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("விழுப்புரம் (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> villupuram = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            villupuram.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(villupuram);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("வேலூர் (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("வேலூர் (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> vellore1 = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            vellore1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(vellore1);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("ஈரோடு (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("ஈரோடு (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> erode = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            erode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(erode);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("கடலூர் (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("கடலூர் (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> cuddalore = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            cuddalore.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(cuddalore);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("கரூர் (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("கரூர் (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> karur1 = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            karur1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(karur1);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("கன்னியாகுமரி (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("கன்னியாகுமரி (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> kanyakumari = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            kanyakumari.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(kanyakumari);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("காஞ்சிபுரம் (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("காஞ்சிபுரம் (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> kanorth = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            kanorth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(kanorth);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("காஞ்சிபுரம் (தெ)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("காஞ்சிபுரம் (தெ)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> kasouth = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            kasouth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(kasouth);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("காஞ்சிபுரம் (ம)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("காஞ்சிபுரம் (ம)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> kacen = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            kacen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(kacen);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("காஞ்சிபுரம் (மே)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("காஞ்சிபுரம் (மே)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> kawest = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            kawest.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(kawest);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("காஞ்சிபுரம் (வ)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("காஞ்சிபுரம் (வ)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> kaeast = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            kaeast.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(kaeast);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (sp2.contentEquals("கிருஷ்ணகிரி (கி)")) {

                    final FirebaseDatabase sub = FirebaseDatabase.getInstance();
                    DatabaseReference su = sub.getReference("கிருஷ்ணகிரி (கி)");


                    su.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> krishnagiri = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            krishnagiri.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s3.setAdapter(krishnagiri);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // Team name spinner


        FirebaseDatabase team = FirebaseDatabase.getInstance();
        DatabaseReference reff = database.getReference("Team Name");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<String> name = new ArrayList<String>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                    String areaName = areaSnapshot.getValue(String.class);
                    name.add(areaName);
                }


                ArrayAdapter<String> teamAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, name);
                teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                t1.setAdapter(teamAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        primary spinner

        FirebaseDatabase level = FirebaseDatabase.getInstance();
        DatabaseReference base = database.getReference("Responsibility Level");

        base.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<String> name = new ArrayList<String>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                    String areaName = areaSnapshot.getValue(String.class);
                    name.add(areaName);
                }


                ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, name);
                levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                t2.setAdapter(levelAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        secondary spinner

        FirebaseDatabase secondary = FirebaseDatabase.getInstance();
        DatabaseReference basesec = database.getReference("Responsibility Level");

        basesec.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<String> name = new ArrayList<String>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                    String areaName = areaSnapshot.getValue(String.class);
                    name.add(areaName);
                }


                ArrayAdapter<String> secAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, name);
                secAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                t4.setAdapter(secAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        t2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String te1 = String.valueOf(t2.getSelectedItem());

                if (te1.contentEquals("மாநிலம்")) {

                    final FirebaseDatabase tea = FirebaseDatabase.getInstance();
                    DatabaseReference coffee = tea.getReference("மாநிலம்");


                    coffee.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> state = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            t3.setAdapter(state);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (te1.contentEquals("ஒருங்கிணைந்த மாவட்டம்")) {

                    final FirebaseDatabase tea = FirebaseDatabase.getInstance();
                    DatabaseReference coffee = tea.getReference("ஒருங்கிணைந்த மாவட்டம்");


                    coffee.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> district = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            t3.setAdapter(district);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (te1.contentEquals("மாவட்ட பிரிவு")) {

                    final FirebaseDatabase tea = FirebaseDatabase.getInstance();
                    DatabaseReference coffee = tea.getReference("மாவட்ட பிரிவு");


                    coffee.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> subdis = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            subdis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            t3.setAdapter(subdis);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

                if (te1.contentEquals("தொகுதி")) {

                    final FirebaseDatabase tea = FirebaseDatabase.getInstance();
                    DatabaseReference coffee = tea.getReference("தொகுதி");

                    coffee.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> ccc = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            ccc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            t3.setAdapter(ccc);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (te1.contentEquals("வட்டம்")) {

                    final FirebaseDatabase tea = FirebaseDatabase.getInstance();
                    DatabaseReference coffee = tea.getReference("வட்டம்");


                    coffee.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> tal = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            tal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            t3.setAdapter(tal);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (te1.contentEquals("ஆர்வளர் ")) {

                    final FirebaseDatabase tea = FirebaseDatabase.getInstance();
                    DatabaseReference coffee = tea.getReference("ஆர்வளர்");


                    coffee.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> arr = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            t3.setAdapter(arr);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (te1.contentEquals("அடிப்படை உறுப்பினர் ")) {

                    final FirebaseDatabase tea = FirebaseDatabase.getInstance();
                    DatabaseReference coffee = tea.getReference("அடிப்படை உறுப்பினர்");


                    coffee.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> basi = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            basi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            t3.setAdapter(basi);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        t4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String te2 = String.valueOf(t4.getSelectedItem());

                if (te2.contentEquals("மாநிலம்")) {

                    final FirebaseDatabase tea = FirebaseDatabase.getInstance();
                    DatabaseReference coffee = tea.getReference("மாநிலம்");


                    coffee.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> state = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            t5.setAdapter(state);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (te2.contentEquals("ஒருங்கிணைந்த மாவட்டம்")) {

                    final FirebaseDatabase tea = FirebaseDatabase.getInstance();
                    DatabaseReference coffee = tea.getReference("ஒருங்கிணைந்த மாவட்டம்");


                    coffee.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> district = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            district.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            t5.setAdapter(district);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (te2.contentEquals("மாவட்ட பிரிவு")) {

                    final FirebaseDatabase tea = FirebaseDatabase.getInstance();
                    DatabaseReference coffee = tea.getReference("மாவட்ட பிரிவு");


                    coffee.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> subdis = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            subdis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            t5.setAdapter(subdis);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

                if (te2.contentEquals("தொகுதி")) {

                    final FirebaseDatabase tea = FirebaseDatabase.getInstance();
                    DatabaseReference coffee = tea.getReference("தொகுதி");

                    coffee.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> ccc = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            ccc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            t5.setAdapter(ccc);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (te2.contentEquals("வட்டம்")) {

                    final FirebaseDatabase tea = FirebaseDatabase.getInstance();
                    DatabaseReference coffee = tea.getReference("வட்டம்");


                    coffee.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> tal = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            tal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            t5.setAdapter(tal);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (te2.contentEquals("ஆர்வளர் ")) {

                    final FirebaseDatabase tea = FirebaseDatabase.getInstance();
                    DatabaseReference coffee = tea.getReference("ஆர்வளர்");


                    coffee.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> arr = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            t5.setAdapter(arr);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (te2.contentEquals("அடிப்படை உறுப்பினர் ")) {

                    final FirebaseDatabase tea = FirebaseDatabase.getInstance();
                    DatabaseReference coffee = tea.getReference("அடிப்படை உறுப்பினர்");


                    coffee.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            final List<String> area = new ArrayList<String>();

                            for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {

                                String areaName1 = areaSnapshot.getValue(String.class);
                                area.add(areaName1);
                            }


                            ArrayAdapter<String> basi = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, area);
                            basi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            t5.setAdapter(basi);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

//    private void load() {
//
//
//        mProgress = new ProgressDialog(Registration.this);
//        mProgress.setMessage("Registering....");
//        mProgress.setIndeterminate(true);
//        mProgress.show();
//        mProgress.setCancelable(false);
//
//
//
//        if (ed_name.equals("")) {
//            Toast.makeText(Registration.this, "Enter Name", Toast.LENGTH_SHORT).show();
//        } else if (ed_ward.equals("")) {
//            Toast.makeText(Registration.this, "Enter Ward", Toast.LENGTH_SHORT).show();
//        } else if (ed_address.equals("")) {
//            Toast.makeText(Registration.this, "Enter Address", Toast.LENGTH_SHORT).show();
//        } else if (ed_pin.equals("")) {
//            Toast.makeText(Registration.this, "Enter PinNumber", Toast.LENGTH_SHORT).show();
//        } else if (ed_mobile1.equals("")) {
//            Toast.makeText(Registration.this, "Enter Mobile Number1", Toast.LENGTH_SHORT).show();
//        } else if (ed_age.equals("")) {
//            Toast.makeText(Registration.this, "Enter DOB", Toast.LENGTH_SHORT).show();
//        } else if (ed_occupation.equals("")) {
//            Toast.makeText(Registration.this, "Enter Occupation", Toast.LENGTH_SHORT).show();
//        } else if (ed_memberid.equals("")) {
//            Toast.makeText(Registration.this, "Enter Member ID", Toast.LENGTH_SHORT).show();
//        }
//
//
//
//        int selectedId = radioGroup.getCheckedRadioButtonId();
//        radioButton = (RadioButton) findViewById(selectedId);
//
//        int statusId = statusgroup.getCheckedRadioButtonId();
//        status = (RadioButton) findViewById(statusId);
//
//
//        String name = ed_name.getText().toString();
//        String ward = ed_ward.getText().toString();
//        String address = ed_address.getText().toString();
//        String pin = ed_pin.getText().toString();
//        String mbl1 = ed_mobile1.getText().toString();
//        String dob = ed_age.getText().toString();
//        String occ = ed_occupation.getText().toString();
//        String memid = ed_memberid.getText().toString();
//        String dis = s1.getSelectedItem().toString();
//        String sub = s2.getSelectedItem().toString();
//        String con = s3.getSelectedItem().toString();
//        String team = t1.getSelectedItem().toString();
//        String pri1 = t2.getSelectedItem().toString();
//        String pri2 = t3.getSelectedItem().toString();
//        String sec1 = t4.getSelectedItem().toString();
//        String sec2 = t5.getSelectedItem().toString();
//        String gender = radioButton.getText().toString();
//        String marital = status.getText().toString();
//
//        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
//
//
//        if (user!=null) {
//
//         Artist artist = new Artist(name,ward,address,gender,marital,occ,mbl1,dob,dis,sub,memid,pin,pri1,pri2,sec1,sec2,team,con);
//
//         sav = FirebaseDatabase.getInstance().getReference().child("Members").child(user.getUid()).setValue(artist);
//
//            mProgress.dismiss();
//
//            Toast.makeText(this, "Registration Success", Toast.LENGTH_SHORT).show();
//
//            startActivity(new Intent(Registration.this,MainActivity.class));
//
//            finish();
//
//        }
//    }


    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Registration.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(Registration.this);
                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (requestCode == STORAGE_PERMISSION_CODE) {

                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        if (userChoosenTask.equals("Take Photo"))
                            cameraIntent();
                        else if (userChoosenTask.equals("Choose from Library"))
                            galleryIntent();
                    } else {
//code for deny
                    }
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                onSelectFromGalleryResult(data);
            } else if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);

                mProgress.setMessage("Uploading Image....");
                mProgress.show();

                Uri uri = data.getData();

                StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());

                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        mProgress.dismiss();

                        Toast.makeText(Registration.this, "Uploading Finished....", Toast.LENGTH_LONG).show();

                    }
                });

            }

        }
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;

        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        image.setImageBitmap(bm);
        System.out.println("img   " + bm);

    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        image.setImageBitmap(thumbnail);
        System.out.println("img  " + destination);
    }


}

