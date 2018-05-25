package com.example.wei.first_program_assign;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button signIn;
    private Button signUp;
    private EditText userName;
    private EditText passWord;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        userName = findViewById(R.id.sign_in_username);
        if (userName.getText().toString().length() == 0)
            userName.setError("Email can't be empty!");


        passWord = findViewById(R.id.sign_in_password);
        if (passWord.getText().toString().length() == 0)
            passWord.setError("Password can't be empty!");

        signIn = findViewById(R.id.sign_in_butt);
        signUp = findViewById(R.id.sign_up_butt);


    }

    public void signIn(View view) {

        mAuth.signInWithEmailAndPassword(userName.getText().toString(), passWord.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, Welcome.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Email or Password is not correct!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    public void signUp(View view) {
        startActivity(new Intent(MainActivity.this, Sign_Up.class));
    }




}
