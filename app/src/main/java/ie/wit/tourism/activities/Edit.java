package ie.wit.tourism.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import ie.wit.tourism.R;
import ie.wit.tourism.models.Attraction;

public class Edit extends Base {
    public Context context;
    public boolean isFavourite;
    public Attraction anAttraction;
    public ImageView editFavourite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        context = this;
        activityInfo = getIntent().getExtras();
        anAttraction = getAttractionObject(activityInfo.getString("coffeeId"));

        ((TextView)findViewById(R.id.editTitleTV)).setText(anAttraction.attractionName);

        ((EditText)findViewById(R.id.editNameET)).setText(anAttraction.attractionName);
        ((EditText)findViewById(R.id.editShopET)).setText(anAttraction.location);
        ((EditText)findViewById(R.id.editPriceET)).setText(""+anAttraction.price);
        ((RatingBar) findViewById(R.id.editRatingBar)).setRating((float)anAttraction.rating);

        editFavourite = findViewById(R.id.editFavourite);

        if (anAttraction.favourite == true) {
            editFavourite.setImageResource(R.drawable.favourites_72_on);
            isFavourite = true;
        } else {
            editFavourite.setImageResource(R.drawable.favourites_72);
            isFavourite = false;
        }
    }

    private Attraction getAttractionObject(String id) {

        for (Attraction c : app.attractionList)
            if (c.attractionId.equalsIgnoreCase(id))
                return c;

        return null;
    }

//    private int getCoffeeIndex(Coffee obj) {
//
//        for (Coffee c : coffeeList)
//            if (c.coffeeId == obj.coffeeId)
//                return coffeeList.indexOf(c);
//
//        return -1;
//    }

    public void saveAttraction(View v) {

        String attractionName = ((EditText) findViewById(R.id.editNameET)).getText().toString();
        String attractionLocation = ((EditText) findViewById(R.id.editShopET)).getText().toString();
        String attractionPriceStr = ((EditText) findViewById(R.id.editPriceET)).getText().toString();
        double ratingValue =((RatingBar) findViewById(R.id.editRatingBar)).getRating();
        double attractionPrice;

        try {
            attractionPrice = Double.parseDouble(attractionPriceStr);
        } catch (NumberFormatException e) {
            attractionPrice = 0.0;
        }

        if ((attractionName.length() > 0) && (attractionLocation.length() > 0) && (attractionPriceStr.length() > 0)) {
            anAttraction.attractionName = attractionName;
            anAttraction.location = attractionLocation;
            anAttraction.price = attractionPrice;
            anAttraction.rating = ratingValue;

            startActivity(new Intent(this, Home.class));

        } else
            Toast.makeText(this, "You must Enter Something for Name and Location",Toast.LENGTH_SHORT).show();
    }

    public void toggle(View view) {

        if (isFavourite) {
            anAttraction.favourite = false;
            Toast.makeText(this,"Removed From Favourites",Toast.LENGTH_SHORT).show();
            isFavourite = false;
            editFavourite.setImageResource(R.drawable.favourites_72);
        } else {
            anAttraction.favourite = true;
            Toast.makeText(this,"Added to Favourites !!",Toast.LENGTH_SHORT).show();
            isFavourite = true;
            editFavourite.setImageResource(R.drawable.favourites_72_on);
        }
    }
}
