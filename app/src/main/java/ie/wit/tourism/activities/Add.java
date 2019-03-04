package ie.wit.tourism.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import ie.wit.tourism.R;
import ie.wit.tourism.models.Attraction;




public class Add extends Base {

    private String 		attractionName, attractionLocation;
    private double       attractionPrice, ratingValue;
    private EditText name, location, price;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        name = findViewById(R.id.addLocationBX);
        location =  findViewById(R.id.addLocationBX);
        price =  findViewById(R.id.editPriceET);
        ratingBar =  findViewById(R.id.addRatingBar);
    }

    public void addAttraction(View v) {

        attractionName = name.getText().toString();
        attractionLocation = location.getText().toString();
        try {
            attractionPrice = Double.parseDouble(price.getText().toString());
        } catch (NumberFormatException e) {
            attractionPrice = 0.0;
        }
        ratingValue = ratingBar.getRating();

        if ((attractionName.length() > 0) && (attractionLocation.length() > 0)
                && (price.length() > 0)) {
            Attraction c = new Attraction(attractionName, attractionLocation, ratingValue,
                    attractionPrice, false);

            app.attractionList.add(c);
            startActivity(new Intent(this, Home.class));
        } else
            Toast.makeText(
                    this,
                    "You must Enter Something for "
                            + "\'Name\', \'location\' and \'Price\'",
                    Toast.LENGTH_SHORT).show();
    }
}

