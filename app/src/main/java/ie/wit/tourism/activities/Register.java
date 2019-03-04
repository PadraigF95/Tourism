package ie.wit.tourism.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ie.wit.tourism.R;

public class Register extends Base {

    private EditText registerEmail, registerPassword, registerUsername;
    private Button btnregister;
    private FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        setUIViews();

        firebaseauth = FirebaseAuth.getInstance();
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {

                    String Email = registerEmail.getText().toString().trim();
                    String Password = registerPassword.getText().toString().trim();


                    firebaseauth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, Login.class));
                            } else {
                                Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }

            }
        });
    }


    private void setUIViews() {
        registerEmail = (EditText) findViewById(R.id.Email);
        registerPassword = (EditText) findViewById(R.id.Password);
        registerUsername = (EditText) findViewById(R.id.Username);
        btnregister = (Button) findViewById(R.id.button);
    }

    private boolean validate() {
        boolean result = false;

        String regname = registerUsername.getText().toString();
        String regpassword = registerPassword.getText().toString();
        String regEmail = registerEmail.getText().toString();

        if (regname.isEmpty() || regpassword.isEmpty() || regEmail.isEmpty()) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }

}