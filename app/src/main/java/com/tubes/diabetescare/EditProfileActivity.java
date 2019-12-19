package com.tubes.diabetescare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
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

import java.util.HashMap;
import java.util.Objects;

public class EditProfileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Uri ImageUri;
    ImageView photoProfile;
    //public static final int CAMERA_REQUEST = 9999;
    //public static final int IMAGE_PICK_CODE = 1000;
    private EditText viewNama, viewTanggal, viewHp, viewEmail, viewTempatLahir, viewPass, viewAlamat
            ,viewRePass, viewNewPass;
    private String hslEditNama, hslEditTgl, hslEditHp, hslEditEmail, hslEditTempatL, hslEditJk,
            hslEditAgama, hslEditAlamat, hslEditPass, hslRePass, hslNewPass;
    private String nama, hp, tl, tmptL, jk, pass, agm, almt;
    private RadioGroup viewJenisKelamin;
    private RadioButton rbjenisKelamin;
    private Spinner agama;
    private FirebaseUser firebaseuser;
    private DatabaseReference database;
    FirebaseAuth firebaseAuth;
    Button btnsave;
    StorageReference storage;
//    public static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //Create the Spinner
        final Spinner agama = (Spinner) findViewById(R.id.isi_agama_spinner);

        if (agama != null) {
            agama.setOnItemSelectedListener(this);
        }

        // Create ArrayAdapter using the string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.agama_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        if (agama != null) {
            agama.setAdapter(adapter);
        }
        //photo upload
        photoProfile = (ImageView) findViewById(R.id.editProfilePhoto);

        //Read Data
        //get Email bisa dari  current user atau database tp ini pake yang current user
        firebaseAuth = FirebaseAuth.getInstance();
        viewEmail = findViewById(R.id.isi_email);
        viewNama = findViewById(R.id.isi_namaLengkap);
        viewPass = findViewById(R.id.isi_password);
        viewRePass = findViewById((R.id.isi_passwordUlang));
        viewNewPass = findViewById(R.id.isi_passwordBaru);
        viewAlamat = findViewById(R.id.isi_alamatLengkap);
        viewTempatLahir = findViewById(R.id.isi_tempatLahir);
        viewTanggal = findViewById(R.id.isi_tanggalLahir);
        viewHp = findViewById(R.id.isi_nohp);
        viewJenisKelamin = findViewById(R.id.edit_radioGroup);
        firebaseuser = firebaseAuth.getCurrentUser();
        assert firebaseuser != null;
        viewEmail.setText(firebaseuser.getEmail());
        assert agama != null;
        agama.getSelectedItem();

        // assert agamaUser != null;
        // agama = agamaUser.getSelectedItem().toString();

        database = FirebaseDatabase.getInstance().getReference().child(firebaseuser.getUid());
        //store foto ke firebase
        storage = FirebaseStorage.getInstance().getReference().child("User_Images");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nama = dataSnapshot.child("namaUser").getValue(String.class);
                hp = dataSnapshot.child("hpUser").getValue(String.class);
                pass = dataSnapshot.child("passUserr").getValue(String.class);
                almt = dataSnapshot.child("alamatUser").getValue(String.class);
                tmptL = dataSnapshot.child("tempatLahirUser").getValue(String.class);
                tl = dataSnapshot.child("tanggalLahirUser").getValue(String.class);
                jk = dataSnapshot.child("jenisKelaminUser").getValue(String.class);

                if (jk.equals("Pria")){
                    rbjenisKelamin = findViewById(R.id.edit_radio_pria);
                    rbjenisKelamin.setChecked(true);
                } else if(jk.equals("Wanita")){
                    rbjenisKelamin = findViewById(R.id.edit_radio_wanita);
                    rbjenisKelamin.setChecked(true);
                }
                viewJenisKelamin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        rbjenisKelamin = viewJenisKelamin.findViewById(checkedId);

                        switch (checkedId) {
                            case R.id.radio_pria:
                            case R.id.radio_wanita:
                                jk = rbjenisKelamin.getText().toString();
                                break;
                            default:
                        }
                    }
                });
                //set text di edit
                viewJenisKelamin.getCheckedRadioButtonId();
                viewNama.setText(nama);
                viewAlamat.setText(almt);
                viewHp.setText(hp);
                viewTanggal.setText(tl);
                viewTempatLahir.setText(tmptL);
                agama.getSelectedItem();

                //save atau update data
                btnsave = findViewById(R.id.btnSave);
                btnsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        hslEditEmail = viewEmail.getText().toString();
                        hslEditPass = viewPass.getText().toString();
                        hslRePass = viewRePass.getText().toString();
                        hslNewPass = viewNewPass.getText().toString();
                        hslEditNama = viewNama.getText().toString();
                        hslEditAlamat = viewAlamat.getText().toString();
                        hslEditTempatL = viewTempatLahir.getText().toString();
                        hslEditTgl = viewTanggal.getText().toString();
                        hslEditJk = rbjenisKelamin.getText().toString();
                        hslEditHp = viewHp.getText().toString();
                        jk = rbjenisKelamin.getText().toString();

                        assert agama != null;
                        hslEditAgama = agama.getSelectedItem().toString();

                        if(hslEditNama.isEmpty()){
                            viewNama.setError("Nama Masih Kosong");
                            viewNama.requestFocus();
                        } else if (hslEditPass.isEmpty()){
                            viewPass.setError("Password Sekarang Kosong");
                            viewPass.requestFocus();
                        } else if (!hslEditPass.equals(pass)){
                            viewPass.setError("Password Kamu Salah");
                            viewPass.requestFocus();
                        } else if (!hslNewPass.equals(hslRePass)){
                            viewRePass.setError("Password Tidak Cocok atau Kosong");
                            viewRePass.requestFocus();
                        } else if(hslEditAlamat.isEmpty()){
                            viewAlamat.setError("Alamat Masih Kosong");
                            viewAlamat.requestFocus();
                        } else if(hslEditTempatL.isEmpty()){
                            viewTempatLahir.setError("Tempat Lahir Masih Kosong");
                            viewTempatLahir.requestFocus();
                        } else if(hslEditHp.isEmpty()){
                            viewHp.setError("No.Hp Masih Kosong ");
                            viewHp.requestFocus();
                        } else if (!hslNewPass.isEmpty() && hslNewPass.equals(hslRePass)){
                              firebaseuser.updatePassword(hslNewPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                  @Override
                                  public void onComplete(@NonNull Task<Void> task) {
                                      if (task.isSuccessful()){
                                          User user = new User(hslEditNama, hslEditEmail, hslNewPass, hslEditAlamat, hslEditTempatL, hslEditTgl, hslEditJk, hslEditHp, hslEditAgama);
                                          database.setValue(user);
                                          Toast.makeText(EditProfileActivity.this,"Data berhasil di update", Toast.LENGTH_SHORT).show();
                                          finish();
                                      } else if(!task.isSuccessful()){
                                          Toast.makeText(EditProfileActivity.this,"Gagal", Toast.LENGTH_SHORT).show();
                                      }
                                  }
                              });
                        } else if (hslNewPass.isEmpty() && hslEditPass.equals(pass)){
                            User user = new User(hslEditNama, hslEditEmail, hslEditPass, hslEditAlamat, hslEditTempatL, hslEditTgl, hslEditJk, hslEditHp, hslEditAgama);
                            database.setValue(user);
                            Toast.makeText(EditProfileActivity.this,"Data berhasil di update", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        //end Read Data


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void showDatePickerEdit(View view) {
        DialogFragment newFragment = new DatePickerEdit();
        newFragment.show(getSupportFragmentManager(), getString(R.string.datepicker));
    }

    public void processDatePickerResult(int year, int month, int dayOfMonth) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(dayOfMonth);
        String year_string = Integer.toString(year);
        String dateMessage = (day_string +
                "/" + month_string + "/" + year_string); //Indonesia Style Format

        EditText dateText = findViewById(R.id.isi_tanggalLahir);
        dateText.setText(dateMessage);
    }

//    public void btnCamera(View view) {
//        Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(openCamera, CAMERA_REQUEST);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAMERA_REQUEST) {
//            assert data != null;
//            Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
//            photoProfile.setImageBitmap((bitmap));
//        }

        if (requestCode == 12 && resultCode == RESULT_OK && data!=null) {
            // Set image to ImageView
            //assert data != null;
            ImageUri = data.getData();
            photoProfile.setImageURI(ImageUri);

            //store foto
            final StorageReference myRef = storage.child(firebaseuser.getUid() + ".jpg");
            myRef.putFile(ImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Gambar Berhasil di Upload",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Gambar Gagal di Upload",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void btnGallery(View view) {
        Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //openGallery.setType("image/*");
        startActivityForResult(openGallery, 12);

//        // Check runtime permission
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
//                // Permission not granted, request it.
//                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
//                // Show popup for runtime permission
//                requestPermissions(permissions, PERMISSION_CODE);
//            } else {
//                // Permission already granted
//                pickImageFromGallery();
//            }
//        } else {
//            // System OS is less then Marshmallow
//            pickImageFromGallery();
//        }
    }

//    private void pickImageFromGallery() {
//        // Intent to Pick an Image
//        Intent openGallery = new Intent(Intent.ACTION_PICK);
//        openGallery.setType("image/*");
//        startActivityForResult(openGallery, IMAGE_PICK_CODE);
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSION_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission was Granted
//                pickImageFromGallery();
//            } else {
//                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}
