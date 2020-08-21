package com.appstone.parttimejobproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    private EditText etUserId;
    private EditText etPassword;
    private CheckBox chkRememberMe;
    private Button btnLogIn;
    private Button btnSignUp;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        etUserId = findViewById(R.id.et_LogInUserId);
        etPassword = findViewById(R.id.et_LogInPassword);
        chkRememberMe = findViewById(R.id.chk_remember);
        auth = FirebaseAuth.getInstance();

    }

    public void onclickLogIn(View view){
        String txt_email = etUserId.getText().toString();
        String txt_password = etPassword.getText().toString();
        loginUser(txt_email,txt_password);
    }


    public void onClickSignUp(View view){
        startActivity(new Intent(LoginPage.this,SignUp.class));
    }

    private void loginUser(String email, String password){
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginPage.this, "login sucessfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(LoginPage.this,RegisterPage.class));
                finish();

            }
        });
    }
}