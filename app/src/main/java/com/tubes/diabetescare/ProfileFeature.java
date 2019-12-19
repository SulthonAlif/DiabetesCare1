package com.tubes.diabetescare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ProfileFeature extends AppCompatActivity {
    private TextView viewNama, viewTanggal, viewHp, viewJk, viewEmail, viewAlamat;
    private FirebaseUser firebaseuser;
    private DatabaseReference database;
    ImageView fotoProfil;
    FirebaseAuth firebaseAuth;
    StorageReference storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_feature);

        //Read Data
        //get Email bisa dari  current user atau database tp ini pake yang current user
        firebaseAuth = FirebaseAuth.getInstance();
        viewEmail = findViewById(R.id.email_fill_text);
        viewNama = findViewById(R.id.profile_name);
        viewTanggal = findViewById(R.id.tanggalLahir_fill_text);
        viewHp = findViewById(R.id.nohp_fill_text);
        viewJk = findViewById(R.id.jk_fill_text);
        viewAlamat = findViewById(R.id.agama_fill_text);
        firebaseuser = firebaseAuth.getCurrentUser();

        //storage foto
        fotoProfil = findViewById(R.id.photoProfile);
        storage = FirebaseStorage.getInstance().getReference().child("User_Images").child(firebaseuser.getUid()+".jpg");
        try {
            final File file = File.createTempFile("image","jpg");
            storage.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    fotoProfil.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ProfileFeature.this,"Image failed to load",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert firebaseuser != null;
        viewEmail.setText(firebaseuser.getEmail());

        database = FirebaseDatabase.getInstance().getReference().child(firebaseuser.getUid());
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nama = dataSnapshot.child("namaUser").getValue(String.class);
                String hp = dataSnapshot.child("hpUser").getValue(String.class);
                String tl = dataSnapshot.child("tanggalLahirUser").getValue(String.class);
                String jk = dataSnapshot.child("jenisKelaminUser").getValue(String.class);
                String almt = dataSnapshot.child("alamatUser").getValue(String.class);
                viewNama.setText(nama);
                viewHp.setText(hp);
                viewTanggal.setText(tl);
                viewJk.setText(jk);
                viewAlamat.setText(almt);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //end Read Data

        Button mEditDialog = (Button) findViewById(R.id.btn_edit);
        mEditDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toEditProfile = new Intent(getApplicationContext(), EditProfileActivity.class);
                startActivity(toEditProfile);
                finish();
            }
        });

    }
//    public void btn_editProfile(View view) {
//        Dialog settingsDialog = new Dialog(this);
//        Objects.requireNonNull(settingsDialog.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
//        settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.edit_profile_dialog, null));
//        settingsDialog.show();
//    }
}
