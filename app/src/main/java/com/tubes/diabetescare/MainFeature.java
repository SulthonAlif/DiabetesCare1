package com.tubes.diabetescare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainFeature extends AppCompatActivity {
    DatabaseReference database;
    private ArrayList<BasisPengetahuan> basisPengetahuans;
    private RadioGroup p1,p2,p3,p4,p5,p6,p7,p8;
    public TextView cek;
    String nilai1,nilai2,nilai3,nilai4,nilai5,nilai6,nilai7,nilai8;
    public String hasil;
    public Double r1,r2,r3,r4,r5,r6,r7,r8;
    public Double s1,s2,s3,s4,s5,s6,s7,s8;
    public Double t1,t2,t3,t4,t5,t6,t7,t8;
    public Double hasilRendah,hasilSedang,hasilTinggi;
    public Double pRendah,pSedang,pTinggi,pKasusRendah,pKasusSedang,pKasusTinggi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feature);



        //Declare Pertanyaan
        p1 = findViewById(R.id.radioGroup1);
        p2 = findViewById(R.id.radioGroup2);
        p3 = findViewById(R.id.radioGroup3);
        p4 = findViewById(R.id.radioGroup4);
        p5 = findViewById(R.id.radioGroup5);
        p6 = findViewById(R.id.radioGroup6);
        p7 = findViewById(R.id.radioGroup7);
        p8 = findViewById(R.id.radioGroup8);

        //Read Rule DB + Cek Risiko
        database = FirebaseDatabase.getInstance().getReference();

        //Get Selected Radio All of Questions
        p1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId1 = group.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId1);
                System.out.println(radioButton.getText().toString());
                if(selectedId1 == R.id.radioJawab1_1){
                    nilai1 = "ya";
                } else if(selectedId1 == R.id.radioJawab2_1) {
                    nilai1 = "tidak";
                }
                System.out.println(selectedId1);
                System.out.println(nilai1);
            }
        });
        p2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId2 = group.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId2);
                System.out.println(radioButton.getText().toString());
                if(selectedId2 == R.id.radioJawab1_2){
                    nilai2 = "ya";
                } else if(selectedId2 == R.id.radioJawab2_2){
                    nilai2 = "tidak";
                }
                System.out.println(selectedId2);
                System.out.println(nilai2);
            }
        });
        p3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = group.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                System.out.println(radioButton.getText().toString());
                if(selectedId == R.id.radioJawab1_3){
                    nilai3 = "ya";
                } else if(selectedId == R.id.radioJawab2_3){
                    nilai3 = "tidak";
                }
                System.out.println(selectedId);
                System.out.println(nilai3);
            }
        });
        p4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = group.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                System.out.println(radioButton.getText().toString());
                if(selectedId == R.id.radioJawab1_4){
                    nilai4 = "rendah";
                } else if(selectedId == R.id.radioJawab2_4){
                    nilai4 = "sedang";
                } else if(selectedId == R.id.radioJawab3_4){
                    nilai4 = "tinggi";
                }
                System.out.println(selectedId);
                System.out.println(nilai4);
            }
        });
        p5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = group.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                System.out.println(radioButton.getText().toString());
                if(selectedId == R.id.radioJawab1_5){
                    nilai5 = "rendah";
                } else if(selectedId == R.id.radioJawab2_5){
                    nilai5 = "sedang";
                } else if(selectedId == R.id.radioJawab3_5){
                    nilai5 = "tinggi";
                }
                System.out.println(selectedId);
                System.out.println(nilai5);
            }
        });
        p6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = group.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                System.out.println(radioButton.getText().toString());
                if(selectedId == R.id.radioJawab1_6){
                    nilai6 = "rendah";
                } else if(selectedId == R.id.radioJawab2_6){
                    nilai6 = "sedang";
                } else if(selectedId == R.id.radioJawab3_6){
                    nilai6 = "tinggi";
                }
                System.out.println(selectedId);
                System.out.println(nilai6);
            }
        });
        p7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = group.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                System.out.println(radioButton.getText().toString());
                if(selectedId == R.id.radioJawab1_7){
                    nilai7 = "normal";
                } else if(selectedId == R.id.radioJawab2_7){
                    nilai7 = "lebih";
                }
                System.out.println(selectedId);
                System.out.println(nilai7);
            }
        });
        p8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = group.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);
                System.out.println(radioButton.getText().toString());
                if(selectedId == R.id.radioJawab1_8){
                    nilai8 = "<45";
                } else if(selectedId == R.id.radioJawab2_8){
                    nilai8 = ">=45";
                }
                System.out.println(selectedId);
                System.out.println(nilai8);
            }
        });

        Button mShowDialog = (Button) findViewById(R.id.btn_cekResiko);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainFeature.this);
                final Query query = database.child("basisPengetahuan");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        basisPengetahuans = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            BasisPengetahuan basisPengetahuan = ds.getValue(BasisPengetahuan.class);
                            basisPengetahuans.add(basisPengetahuan);
                        }
                        int index = 0;
                        for (int i =0; i < basisPengetahuans.size(); i++){
                            if (basisPengetahuans.get(index).getRiwayatOrtu().equals(nilai1)&&
                                    basisPengetahuans.get(index).getRiwayatGDR().equals(nilai2)&&
                                    basisPengetahuans.get(index).getRiwayatHiper().equals(nilai3)&&
                                    basisPengetahuans.get(index).getPolaMakan().equals(nilai4)&&
                                    basisPengetahuans.get(index).getAktFisik().equals(nilai5)&&
                                    basisPengetahuans.get(index).getIntenOrg().equals(nilai6)&&
                                    basisPengetahuans.get(index).getImt().equals(nilai7)&&
                                    basisPengetahuans.get(index).getUsia().equals(nilai8)){
                                System.out.println(basisPengetahuans.get(index).getRisiko());
                                hasil = basisPengetahuans.get(index).getRisiko();
                                cek.setText(hasil);
                            }
                            else {
                                //query ambil data rumus tiap probabilitas
                                final Query query2 = database.child("rumus/rendah");
                                final Query query3 = database.child("rumus/sedang");
                                final Query query4 = database.child("rumus/tinggi");
                                query2.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for (DataSnapshot rm : dataSnapshot.getChildren()){
                                            r1 = new Double((dataSnapshot.child("riwayatOrtu/"+nilai1).getValue()).toString());
                                            r2 = new Double((dataSnapshot.child("riwayatGDR/"+nilai2).getValue()).toString());
                                            r3 = new Double((dataSnapshot.child("riwayatHiper/"+nilai3).getValue()).toString());
                                            r4 = new Double((dataSnapshot.child("polaMakan/"+nilai4).getValue()).toString());
                                            r5 = new Double((dataSnapshot.child("aktFisik/"+nilai5).getValue()).toString());
                                            r6 = new Double((dataSnapshot.child("intenOrg/"+nilai6).getValue()).toString());
                                            r7 = new Double((dataSnapshot.child("imt/"+nilai7).getValue()).toString());
                                            r8 = new Double((dataSnapshot.child("usia/"+nilai8).getValue()).toString());
//                                            System.out.println("hasil riwayat Ortu "+r1);
//                                            System.out.println("hasil riwayat GDR "+r2);
//                                            System.out.println("hasil riwayat Hiper "+r3);
//                                            System.out.println("hasil pola Makan "+r4);
//                                            System.out.println("hasil akt Fisik "+r5);
//                                            System.out.println("hasil inten Org "+r6);
//                                            System.out.println("hasil imt "+r7);
//                                            System.out.println("hasil usia "+r8);
                                            hasilRendah = r1*r2*r3*r4*r5*r6*r7*r8;
                                            pKasusRendah = hasilRendah*20.0/70.0;
                                        }
                                        System.out.println("tes hasil dibawah hasil rendah"+hasilRendah);
                                        System.out.println("tes hasil dibawah p kasus rendah"+pKasusRendah);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                                query3.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for (DataSnapshot rm2 : dataSnapshot.getChildren()){
                                            s1 = new Double((dataSnapshot.child("riwayatOrtu/"+nilai1).getValue()).toString());
                                            s2 = new Double((dataSnapshot.child("riwayatGDR/"+nilai2).getValue()).toString());
                                            s3 = new Double((dataSnapshot.child("riwayatHiper/"+nilai3).getValue()).toString());
                                            s4 = new Double((dataSnapshot.child("polaMakan/"+nilai4).getValue()).toString());
                                            s5 = new Double((dataSnapshot.child("aktFisik/"+nilai5).getValue()).toString());
                                            s6 = new Double((dataSnapshot.child("intenOrg/"+nilai6).getValue()).toString());
                                            s7 = new Double((dataSnapshot.child("imt/"+nilai7).getValue()).toString());
                                            s8 = new Double((dataSnapshot.child("usia/"+nilai8).getValue()).toString());
//                                            System.out.println("hasil riwayat Ortu "+s1);
//                                            System.out.println("hasil riwayat GDR "+s2);
//                                            System.out.println("hasil riwayat Hiper "+s3);
//                                            System.out.println("hasil pola Makan "+s4);
//                                            System.out.println("hasil akt Fisik "+s5);
//                                            System.out.println("hasil inten Org "+s6);
//                                            System.out.println("hasil imt "+s7);
//                                            System.out.println("hasil usia "+s8);
                                            hasilSedang = s1*s2*s3*s4*s5*s6*s7*s8;
                                            pKasusSedang = hasilSedang*30.0/70.0;
                                        }
                                        System.out.println("tes hasil dibawah fornya sedang"+hasilSedang);
                                        System.out.println("tes hasil dibawah fornya psedang"+pKasusSedang);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                                query4.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for (DataSnapshot rm3 : dataSnapshot.getChildren()){
                                            t1 = new Double((dataSnapshot.child("riwayatOrtu/"+nilai1).getValue()).toString());
                                            t2 = new Double((dataSnapshot.child("riwayatGDR/"+nilai2).getValue()).toString());
                                            t3 = new Double((dataSnapshot.child("riwayatHiper/"+nilai3).getValue()).toString());
                                            t4 = new Double((dataSnapshot.child("polaMakan/"+nilai4).getValue()).toString());
                                            t5 = new Double((dataSnapshot.child("aktFisik/"+nilai5).getValue()).toString());
                                            t6 = new Double((dataSnapshot.child("intenOrg/"+nilai6).getValue()).toString());
                                            t7 = new Double((dataSnapshot.child("imt/"+nilai7).getValue()).toString());
                                            t8 = new Double((dataSnapshot.child("usia/"+nilai8).getValue()).toString());
//                                            System.out.println("hasil riwayat Ortu "+t1);
//                                            System.out.println("hasil riwayat GDR "+t2);
//                                            System.out.println("hasil riwayat Hiper "+t3);
//                                            System.out.println("hasil pola Makan "+t4);
//                                            System.out.println("hasil akt Fisik "+t5);
//                                            System.out.println("hasil inten Org "+t6);
//                                            System.out.println("hasil imt "+t7);
//                                            System.out.println("hasil usia "+t8);
                                            hasilTinggi = t1*t2*t3*t4*t5*t6*t7*t8;
                                            pKasusTinggi = hasilTinggi*20.0/70.0;
                                        }
                                        System.out.println("tes hasil dibawah fornya tinggi"+hasilTinggi);
                                        System.out.println("tes hasil dibawah fornya ptinggi"+pKasusTinggi);
                                        pRendah = pKasusRendah/(pKasusRendah+pKasusSedang+pKasusTinggi);
                                        pSedang = pKasusSedang/(pKasusRendah+pKasusSedang+pKasusTinggi);
                                        pTinggi = pKasusTinggi/(pKasusRendah+pKasusSedang+pKasusTinggi);
                                        System.out.println("ini hasil rumus kedua rendah "+pRendah);
                                        System.out.println("ini hasil rumus kedua sedang "+pSedang);
                                        System.out.println("ini hasil rumus kedua tinggi "+pTinggi);
                                        if (pRendah > pSedang && pRendah > pTinggi){
                                            hasil = "rendah";
                                            cek.setText(hasil);
                                            System.out.println(hasil);
                                        } else if(pSedang > pRendah && pSedang > pTinggi){
                                            hasil = "sedang";
                                            cek.setText(hasil);
                                            System.out.println(hasil);
                                        } else if(pTinggi > pRendah && pTinggi > pSedang){
                                            hasil = "tinggi";
                                            cek.setText(hasil);
                                            System.out.println(hasil);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            index++;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                System.out.println("clicked");
                View mView = getLayoutInflater().inflate(R.layout.hasil_cek_resiko_dialog, null);
                Button mToArtikel = (Button) mView.findViewById(R.id.btn_kurangiResiko);
                cek = mView.findViewById(R.id.hasilResiko);
                mToArtikel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent toArtikel = new Intent(getApplicationContext(), ArticleFeature.class);
                        startActivity(toArtikel);
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

    }
}
