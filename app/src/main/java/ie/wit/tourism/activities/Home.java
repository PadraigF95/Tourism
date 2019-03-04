package ie.wit.tourism.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;


import ie.wit.tourism.R;
import ie.wit.tourism.fragments.AttractionFragment;
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

    @Override
    protected void onResume() {
        super.onResume();

        attractionFragment = AttractionFragment.newInstance(); //get a new Fragment instance
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, attractionFragment)
                .commit(); // add it to the current activity
    }

    public void setupAttractions(){
        app.attractionList.add(new Attraction("Dunbrody Famine ship", "The Quay,New Ross",3.5,10.99,false));
        app.attractionList.add(new Attraction("The Kennedy Home Stead", "Dunganstown",3.5,8.99,true));
        app.attractionList.add(new Attraction("Kennedy Statue", "The Quay,New Ross",4.5,0.00,true));
    }
}

