package ie.wit.tourism.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import ie.wit.tourism.R;


public class Logout extends Base {
    private FirebaseAuth firebaseAuth;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout);

        firebaseAuth = FirebaseAuth.getInstance();

        logout = (Button) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(Logout.this, Home.class));
            }
        });
    }

    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(Logout.this, Home.class));


    }
}
