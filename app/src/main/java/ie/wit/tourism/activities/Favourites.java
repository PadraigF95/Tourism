package ie.wit.tourism.activities;

import android.os.Bundle;
import android.widget.TextView;

import ie.wit.tourism.R;
import ie.wit.tourism.fragments.AttractionFragment;
import ie.wit.tourism.fragments.SearchFragment;

public class Favourites extends Base {


    private TextView emptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites);

        emptyList = findViewById(R.id.emptyList);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if(app.attractionList.isEmpty())
            emptyList.setText(getString(R.string.emptyMessageLbl));
        else
            emptyList.setText("");

        attractionFragment = AttractionFragment.newInstance(); //get a new Fragment instance
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, attractionFragment)
                .commit(); // add it to the current activity
    }

}

