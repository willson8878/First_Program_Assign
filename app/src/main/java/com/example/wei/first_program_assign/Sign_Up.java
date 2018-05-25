package com.example.wei.first_program_assign;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Sign_Up extends AppCompatActivity {

    private Button signUp;
    private EditText userName;
    private EditText passWord;
    private EditText re_passWord;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        mAuth = FirebaseAuth.getInstance();

        userName = findViewById(R.id.sign_up_email);
        if (userName.getText().toString().length() == 0)
            userName.setError("Email can't be empty!");


        passWord = findViewById(R.id.sign_up_pass);
        if (passWord.getText().toString().length() < 7)
            passWord.setError("Least 6 digits/letters for Password!");


        re_passWord = findViewById(R.id.sign_up_pass_re);
        if (re_passWord.getText().toString().length() == 0)
            re_passWord.setError("Password can't be empty!");
        if (!re_passWord.getText().toString().equals(passWord))
            re_passWord.setError("Passwords are not match!");

        signUp = findViewById(R.id.sign_up);
    }

    public void signUP(View view) {
        mAuth.createUserWithEmailAndPassword(userName.getText().toString(), passWord.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Sign_Up.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Sign_Up.this, MainActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                userName.setError("User with this email already exist.");
                                Toast.makeText(Sign_Up.this, "Registered Fail! \nUser with this email already exist.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Sign_Up.this, "Registered Fail", Toast.LENGTH_SHORT).show();
                            }

                        }

                        // ...
                    }
                });
    }


}
