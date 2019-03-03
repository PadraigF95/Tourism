package ie.wit.tourism.ie.cm.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ie.wit.tourism.R;
import ie.wit.tourism.fragments.SearchFragment;

public class Search extends Base{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
    }

    @Override
    protected void onResume() {
        super.onResume();

        attractionFragment = SearchFragment.newInstance(); //get a new Fragment instance
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, attractionFragment)
                .commit(); // add it to the current activity
    }
}