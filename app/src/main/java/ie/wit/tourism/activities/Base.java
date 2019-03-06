package ie.wit.tourism.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import ie.wit.tourism.R;
import ie.wit.tourism.main.NewRossTourism;

public class Base extends AppCompatActivity {

    public NewRossTourism app;
    public Bundle activityInfo;
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (NewRossTourism) getApplication();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void menuHome(MenuItem m) {
        startActivity(new Intent(this, Home.class));
    }


    public void menuInfo(MenuItem m)
    {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.appAbout))
                .setMessage(getString(R.string.appDesc)
                        + "\n\n"
                        + getString(R.string.appMoreInfo))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // we could put some code here too
                    }
                })
                .show();
    }



    public void menuRegister(MenuItem m)
    {
        startActivity(new Intent(this, Register.class));

    }
    public void menuConverter(MenuItem m)
    {
        startActivity(new Intent(this, Currency_Converter.class));
    }
    public void menuLogin(MenuItem m)
    {
        startActivity(new Intent(this, Login.class));

    }

    public void menuLogout(MenuItem m)
    {
        startActivity(new Intent(this, Logout.class));

    }
}
