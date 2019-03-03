package ie.wit.tourism.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import ie.wit.tourism.R;
import ie.wit.tourism.models.Attraction;

public class AttractionsListAdapter extends ArrayAdapter<Attraction>
{
    private Context context;
    private View.OnClickListener deleteListener;
    public List<Attraction> attractionList;

    public AttractionsListAdapter(Context context, View.OnClickListener deleteListener, List<Attraction> attractionList)
    {
        super(context, R.layout.attractionrow, attractionList);

        this.context = context;
        this.deleteListener = deleteListener;
        this.attractionList = attractionList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AttractionsItem item = new AttractionsItem(context, parent,
                deleteListener, attractionList.get(position));
        return item.view;
    }

    @Override
    public int getCount() {
        return attractionList.size();
    }

    @Override
    public Attraction getItem(int position) {
        return attractionList.get(position);
    }
}

