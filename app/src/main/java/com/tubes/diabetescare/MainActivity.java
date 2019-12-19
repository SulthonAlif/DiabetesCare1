package com.tubes.diabetescare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*
* Topik atau masalah adalaa penyakit diabetes melitus tipe 2 yang terjadi kepada banyak orang
* mulai dari kalangan muda sampai lanjut usia
*
* Mengapa aplikasi karena di jaman sekarang ini, setiap orang memiliki gadget masing-masing yang
* dibawa hampir setiap saat dan aplikasi kami memiliki fitur reminder yang mengharuskan berbasis android
*
* Siapa usernya: dokter dan masyarakat umum (remaja, orang tua, dll)
*
* Siapa clientnya: pihak rumah sakit, baik negeri maupun swasta ataupun kementrian kesehatan
*
* Apa inputnya = data pengguna (nama, ttl, no hp, kelamin, email, dll), data medis
* (riwayat diabetes ortu, riwayat gula darah tinggi, riwayat hipertensi, pola makan,
* aktivitas fisik sehari hari, intensitas olahraga, indeks massa tubuh, dan usia)
*
* Outputnya = profil pengguna dan hasil risiko diabetes melitus tipe 2 apakah termasuk rendah, sedang, atau tinggi
* */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView btnProfile;
    ImageView btnAbout;
    ImageView btnMain;
    ImageView btnKesehatan;
    ImageView btnArtikel;
    private FirebaseUser firebaseuser;
    private DatabaseReference database;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnProfile = (ImageView) findViewById(R.id.profile_menu);
        btnAbout = (ImageView) findViewById(R.id.about_menu);
        btnMain = (ImageView) findViewById(R.id.main_menu);
        btnKesehatan = (ImageView) findViewById(R.id.kesehatan_menu);
        btnArtikel = (ImageView) findViewById(R.id.artikel_menu);

        btnProfile.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnMain.setOnClickListener(this);
        btnKesehatan.setOnClickListener(this);
        btnArtikel.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseuser = firebaseAuth.getCurrentUser();

        database = FirebaseDatabase.getInstance().getReference().child(firebaseuser.getUid());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_account:
                AlertDialog.Builder dialogDelete = new AlertDialog.Builder(MainActivity.this);
                dialogDelete.setTitle("Apakah Anda Yakin?");
                dialogDelete.setMessage("Menghapus akun ini akan menyebabkan hilangnya data dan tidak bisa diakses kembali.");
                dialogDelete.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        firebaseuser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Intent intentDelete = new Intent(MainActivity.this, LoginForm.class);
                                    intentDelete.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intentDelete.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intentDelete);
                                    database.removeValue();
                                }
                                else {
                                    Toast.makeText(MainActivity.this,"Akun Gagal di Hapus",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialogDelete.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = dialogDelete.create();
                alertDialog.show();
                return true;
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intentLogout = new Intent(MainActivity.this, LoginForm.class);
                intentLogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentLogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentLogout);
                return true;
            default:
                //Do Nothing
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.profile_menu:
                Intent profileMenu = new Intent(getApplicationContext(), ProfileFeature.class);
                startActivity(profileMenu);
                break;
            case R.id.about_menu:
                Intent aboutMenu = new Intent(getApplicationContext(), AboutFeature.class);
                startActivity(aboutMenu);
                break;
            case R.id.main_menu:
                Intent mainMenu = new Intent(getApplicationContext(), MainFeature.class);
                startActivity(mainMenu);
                break;
            case R.id.kesehatan_menu:
                Intent remainderMenu = new Intent(getApplicationContext(), HealthFeature.class);
                startActivity(remainderMenu);
                break;
            case R.id.artikel_menu:
                Intent articleMenu = new Intent(getApplicationContext(), ArticleFeature.class);
                startActivity(articleMenu);
                break;
        }
    }
}
