package ie.wit.tourism.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;


import ie.wit.tourism.R;
import ie.wit.tourism.models.Attraction;

public class Home extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Information", Snackbar.LENGTH_LONG)
                        .setAction("More Info...", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        }).show();
            }
        });

    }

    public void add(View v) {
        startActivity(new Intent(this, Add.class));
    }


    public void search(View v) {
        startActivity(new Intent(this, Search.class));
    }

    public void favourites(View v) {
        startActivity(new Intent(this, Favourites.class));
    }

}