package ie.wit.tourism.main;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ie.wit.tourism.models.Attraction;

public class NewRossTourism extends Application
{
    public List<Attraction> attractionList = new ArrayList<>();

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("newrosstourism", "New Ross Tourism App Started");
    }



}
