package com.appstone.parttimejobproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText phoneNo;
    private EditText signUpEmail;
    private EditText signUpPassword;
    private EditText signUpCnfrmPassword;
    private Button btnSignUp;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstName = findViewById(R.id.et_firstName);
        lastName = findViewById(R.id.et_lastName);
        phoneNo = findViewById(R.id.et_phoneNo);
        signUpEmail = findViewById(R.id.et_signUpEmail);
        signUpPassword = findViewById(R.id.et_signUpPassword);
        signUpCnfrmPassword = findViewById(R.id.et_cnfrmPassword);
        auth = FirebaseAuth.getInstance();

        btnSignUp = findViewById(R.id.btn_signUp);

////        if (auth.getCurrentUser() != null){
////            startActivity(new Intent(SignUp.this,RegisterPage.class));
////            finish();
//
//        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String txt_firstName = firstName.getText().toString();
                    String txt_LastName = lastName.getText().toString();
                    String txt_PhoneNo = phoneNo.getText().toString();
                    String txt_email = signUpEmail.getText().toString();
                    String txt_password = signUpPassword.getText().toString();
                    String txt_cnfrmPassword = signUpCnfrmPassword.getText().toString();
                    if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password) || TextUtils.isEmpty(txt_firstName)
                     || TextUtils.isEmpty(txt_LastName) || TextUtils.isEmpty(txt_PhoneNo)
                     || TextUtils.isEmpty(txt_cnfrmPassword)){
                        Toast.makeText(SignUp.this, "fill all the blanks",Toast.LENGTH_LONG).show();
                    }else if(txt_password.length() < 6) {
                        signUpPassword.setError("password must be greater than 6");
                    }

//                    } else if (txt_password != txt_cnfrmPassword){
//                       signUpCnfrmPassword.setError("password didn't match");
//
//                    }
                    else{
                        signUpUser(txt_email,txt_password);
                    }


            }
        });
    }

    private void signUpUser(String email, String password){
//        if (signUpPassword != signUpCnfrmPassword){
//            Toast.makeText(SignUp.this,"password is not same",Toast.LENGTH_LONG).show();
//        }else {

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUp.this, "successfully registered", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SignUp.this,RegisterPage.class));
                    } else {
                        Toast.makeText(SignUp.this, "registration failed", Toast.LENGTH_LONG).show();
                    }

                }
            });


//        }
    }

    public void backToLogInPage(View view){
        startActivity(new Intent(SignUp.this,LoginPage.class));
    }


}