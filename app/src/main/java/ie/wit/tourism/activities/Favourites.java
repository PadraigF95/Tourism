package ie.wit.tourism.activities;

import android.os.Bundle;
import android.widget.TextView;

import ie.wit.tourism.R;

public class Favourites extends Base {


    private TextView emptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites);

        emptyList = findViewById(R.id.emptyList);
    }
}