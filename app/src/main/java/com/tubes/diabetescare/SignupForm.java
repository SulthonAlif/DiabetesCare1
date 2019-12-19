package com.tubes.diabetescare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignupForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //variabel create account auth
    public EditText emailUser, passUser;
    Button btnDaftar;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    //variabel create account database
    private EditText namaUserr, alamatUserr, tempatLahirUserr, tanggalLahirUserr, hpUserr, rePassUser;
    private RadioGroup jKel;
    private RadioButton jenisKelaminUser;
    private Spinner agamaUser;
    private DatabaseReference database;
    private FirebaseUser firebaseuser;
    String emailUs, passUs, rePassUs, namaUs, alamatUs, tempatLUs, tanggalLUs, jenisKelaminUs, hpUs,
            agamaUs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);

        //Create the Spinner
        final Spinner agamaUser = (Spinner) findViewById(R.id.signup_agama_spinner);

        if (agamaUser != null) {
            agamaUser.setOnItemSelectedListener(this);
        }

        // Create ArrayAdapter using the string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.agama_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        if (agamaUser != null) {
            agamaUser.setAdapter(adapter);
        }

        // Create Account Auth
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        emailUser = findViewById(R.id.signup_email);
        passUser = findViewById(R.id.signup_pw);
        rePassUser = findViewById((R.id.signup_repw));
        // Create Account Database
        namaUserr = findViewById(R.id.signup_fullname);
        alamatUserr = findViewById(R.id.signup_address);
        tempatLahirUserr = findViewById(R.id.signup_tempat_lahir);
        tanggalLahirUserr = findViewById(R.id.signup_lahir);
        jKel = findViewById(R.id.radioGroup);
        hpUserr = findViewById(R.id.signup_nohp);
        jenisKelaminUser = findViewById(jKel.getCheckedRadioButtonId());

        jKel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                jenisKelaminUser = jKel.findViewById(checkedId);

                switch (checkedId) {
                    case R.id.radio_pria:
                    case R.id.radio_wanita:
                        jenisKelaminUs = jenisKelaminUser.getText().toString();
                        break;
                    default:
                }
            }
        });

        //onClick Button
        btnDaftar = findViewById(R.id.btn_daftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() { //Register new User
            @Override
            public void onClick(View view) {
                emailUs = emailUser.getText().toString();
                passUs = passUser.getText().toString();
                rePassUs = rePassUser.getText().toString();
                namaUs = namaUserr.getText().toString();
                alamatUs = alamatUserr.getText().toString();
                tempatLUs = tempatLahirUserr.getText().toString();
                tanggalLUs = tanggalLahirUserr.getText().toString();
                jenisKelaminUs = jenisKelaminUser.getText().toString();
                hpUs = hpUserr.getText().toString();
                assert agamaUser != null;
                agamaUs = agamaUser.getSelectedItem().toString();

                if (namaUs.isEmpty()){
                    namaUserr.setError("Masukkan Nama Lengkap Kamu");
                    namaUserr.requestFocus();
                } else if (emailUs.isEmpty()) {
                    emailUser.setError("Masukkan Email Kamu");
                    emailUser.requestFocus();
                } else if (passUs.isEmpty()) {
                    passUser.setError("Masukkan Password Kamu");
                    passUser.requestFocus();
                } else if (!rePassUs.equals(passUs)) {
                    rePassUser.setError("Password Kamu Tidak Cocok");
                    rePassUser.requestFocus();
                } else if (alamatUs.isEmpty()){
                    alamatUserr.setError("Masukkan Tempat Lahir Kamu");
                    alamatUserr.requestFocus();
                }else if (tempatLUs.isEmpty()){
                    tempatLahirUserr.setError("Masukkan Tempat Lahir Kamu");
                    tempatLahirUserr.requestFocus();
                } else if (tanggalLUs.isEmpty()){
                    tanggalLahirUserr.setError("Masukkan Tanggal Lahir Kamu");
                    tanggalLahirUserr.requestFocus();
                } else if (jenisKelaminUs.isEmpty()){
                    jenisKelaminUser.setError("Pilih Jenis Kelamin Kamu");
                    jenisKelaminUser.requestFocus();
                } else if (hpUs.isEmpty()){
                    hpUserr.setError("Masukkan Nomor Handphone Kamu");
                    hpUserr.requestFocus();
                }  else {
                    firebaseAuth.createUserWithEmailAndPassword(emailUs, passUs).addOnCompleteListener(SignupForm.this,
                            new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignupForm.this.getApplicationContext(),
                                        "SignUp unsuccessful: " + Objects.requireNonNull(task.getException()).getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                firebaseuser = firebaseAuth.getCurrentUser();
                                submitData(new User(namaUs, emailUs, passUs, alamatUs, tempatLUs, tanggalLUs, jenisKelaminUs, hpUs, agamaUs));
                                startActivity(new Intent(SignupForm.this, MainActivity.class));
                            }
                        }
                    });
                }
            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(SignupForm.this, "User logged in ", Toast.LENGTH_SHORT).show();
                    Intent toMainAct = new Intent(SignupForm.this, MainActivity.class);
                    startActivity(toMainAct);
                }
            }
        };
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerSignup();
        newFragment.show(getSupportFragmentManager(), getString(R.string.datepicker));
    }

    public void processDatePickerResult(int year, int month, int dayOfMonth) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(dayOfMonth);
        String year_string = Integer.toString(year);
        String dateMessage = (day_string +
                "/" + month_string + "/" + year_string); //Indonesia Style Format

        TextInputEditText dateText = findViewById(R.id.signup_lahir);
        dateText.setText(dateMessage);
    }


    public void submitData(User user) {
        database.child(firebaseuser.getUid()).setValue(user).addOnSuccessListener(this, new OnSuccessListener<Void>() {

              @Override
              public void onSuccess(Void aVoid) {
                  emailUser.setText("");
                  passUser.setText("");
                  namaUserr.setText("");
                  alamatUserr.setText("");
                  tempatLahirUserr.setText("");
                  tanggalLahirUserr.setText("");
                  jenisKelaminUser.setText("");
                  hpUserr.setText("");
                  assert agamaUser != null;
                  agamaUs = agamaUser.getSelectedItem().toString();
                  Snackbar.make(findViewById(R.id.btn_daftar), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
