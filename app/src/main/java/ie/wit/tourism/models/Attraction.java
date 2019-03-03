package ie.wit.tourism.models;

import java.io.Serializable;
import java.util.UUID;

public class Attraction implements Serializable
{
    public String attractionId;
    public String attractionName;
    public String location;
    public double rating;
    public double price;
    public boolean favourite;


    public Attraction() {}

    public Attraction(String name, String location, double rating, double price, boolean fav)
    {
        this.attractionId = UUID.randomUUID().toString();
        this.attractionName = name;
        this.location = location;
        this.rating = rating;
        this.price = price;
        this.favourite = fav;
    }

    @Override
    public String toString() {
        return attractionId + " " + attractionName + ", " + location + ", " + rating
                + ", " + price + ", fav =" + favourite;
    }
}

