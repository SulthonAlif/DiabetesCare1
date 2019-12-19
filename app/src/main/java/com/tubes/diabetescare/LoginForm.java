package com.tubes.diabetescare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginForm extends AppCompatActivity {
    // Variabel login
    public EditText loginEmailUser, loginPassUser;
    Button btnLogin, btnSignup, btnFacebook, btnGoogle;
    TextInputLayout textInputLayout, textInputLayout2;
    MaterialTextView forgot_pw, orID, need_account_text;
    ImageView logo;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    Animation right_to_left, left_to_right, app_splash, alpha_to_appear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //authentication account
        firebaseAuth = FirebaseAuth.getInstance();
        loginEmailUser = findViewById(R.id.login_username);
        loginPassUser = findViewById(R.id.login_pw);
        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_sign_up);
        btnFacebook = findViewById(R.id.btn_login_facebook);
        btnGoogle = findViewById(R.id.btn_login_google);
        textInputLayout = findViewById(R.id.textInputLayout);
        textInputLayout2 = findViewById(R.id.textInputLayout2);
        logo = findViewById(R.id.logo);
        forgot_pw = findViewById(R.id.forgot_pw);
        orID = findViewById(R.id.orID);
        need_account_text = findViewById(R.id.need_account_text);

        // Load Animation
        right_to_left = AnimationUtils.loadAnimation(this, R.anim.right_to_left);
        left_to_right = AnimationUtils.loadAnimation(this, R.anim.left_to_right);
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        alpha_to_appear = AnimationUtils.loadAnimation(this, R.anim.alpha_to_appear);

        // Run Animation
        btnLogin.startAnimation(right_to_left);
        btnSignup.startAnimation(left_to_right);
        btnFacebook.startAnimation(left_to_right);
        btnGoogle.startAnimation(right_to_left);
        textInputLayout.startAnimation(left_to_right);
        textInputLayout2.startAnimation(right_to_left);
        logo.startAnimation(app_splash);
        forgot_pw.startAnimation(alpha_to_appear);
        orID.startAnimation(alpha_to_appear);
        need_account_text.startAnimation(alpha_to_appear);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(LoginForm.this, "Selamat Datang", Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(LoginForm.this, MainActivity.class);
                    startActivity(I);
                } else {
                    Toast.makeText(LoginForm.this, "Masuk untuk Melanjutkan", Toast.LENGTH_SHORT).show();
                }
            }
        };
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //login code
                String loginEmail = loginEmailUser.getText().toString();
                String loginPassword = loginPassUser.getText().toString();
                if (loginEmail.isEmpty()) {
                    loginEmailUser.setError("Masukkan Email Kamu");
                    loginEmailUser.requestFocus();
                } else if (loginPassword.isEmpty()) {
                    loginPassUser.setError("Masukkan Password Kamu");
                    loginPassUser.requestFocus();
                } else {
                    firebaseAuth.signInWithEmailAndPassword(loginEmail, loginPassword).addOnCompleteListener(LoginForm.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginForm.this, "Gagal Masuk", Toast.LENGTH_SHORT).show();
                            } else startActivity(new Intent(LoginForm.this, MainActivity.class));
                        }
                    });
                }
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public void btnSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), SignupForm.class));
    }

    //public void btnLogin(View view) {

    //code sebelumnya
    //Intent toMainActivity = new Intent(getApplicationContext(), MainActivity.class);
    //startActivity(toMainActivity);
    //}
}
