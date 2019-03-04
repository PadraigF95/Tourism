package ie.wit.tourism.activities;

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
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



import ie.wit.tourism.R;


public class Login extends Base {
    private EditText Password, Username;
    private Button btnLogin;
    private FirebaseAuth firebaseauth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Password = (EditText) findViewById(R.id.loginPassword);
        Username = (EditText) findViewById(R.id.loginUsername);
        btnLogin = (Button) findViewById(R.id.loginButton);




        firebaseauth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseauth.getCurrentUser();

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                validate(Username.getText().toString(), Password.getText().toString());
            }
        });



        if (user != null) {
            finish();
            startActivity(new Intent(Login.this, Home.class));
        }
    }


    private void validate(String Username, String Password) {
        firebaseauth.signInWithEmailAndPassword(Username, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, Home.class));
                } else {
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}