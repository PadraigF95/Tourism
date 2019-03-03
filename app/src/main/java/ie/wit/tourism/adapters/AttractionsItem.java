package ie.wit.tourism.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import ie.wit.tourism.R;
import ie.wit.tourism.models.Attraction;

public class AttractionsItem {
    View view;

    public AttractionsItem(Context context, ViewGroup parent,
                      View.OnClickListener deleteListener, Attraction attraction)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.attractionrow, parent, false);
        view.setTag(attraction.attractionId);

        updateControls(attraction);

        ImageView imgDelete = view.findViewById(R.id.rowDeleteImg);
        imgDelete.setTag(attraction);
        imgDelete.setOnClickListener(deleteListener);
    }

    private void updateControls(Attraction attraction) {
        ((TextView) view.findViewById(R.id.rowAttractionName)).setText(attraction.attractionName);

        ((TextView) view.findViewById(R.id.rowAttractionLocation)).setText(attraction.location);
        ((TextView) view.findViewById(R.id.rowRating)).setText(attraction.rating + " *");
        ((TextView) view.findViewById(R.id.rowPrice)).setText("€" +
                new DecimalFormat("0.00").format(attraction.price));

        ImageView imgIcon = view.findViewById(R.id.rowFavouriteImg);

        if (attraction.favourite == true)
            imgIcon.setImageResource(R.drawable.favourites_72_on);
        else
            imgIcon.setImageResource(R.drawable.favourites_72);


    }
}
