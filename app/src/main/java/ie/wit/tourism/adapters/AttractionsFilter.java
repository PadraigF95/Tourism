package ie.wit.tourism.adapters;

import android.util.Log;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

import ie.wit.tourism.models.Attraction;

public class AttractionsFilter extends Filter {
    public List<Attraction> originalAttractionList;
    public String filterText;
    public AttractionsListAdapter adapter;

    public AttractionsFilter(List<Attraction> originalAttractionList, String filterText,
                        AttractionsListAdapter adapter) {
        super();
        this.originalAttractionList = originalAttractionList;
        this.filterText = filterText;
        this.adapter = adapter;
    }

    public void setFilter(String filterText) {
        this.filterText = filterText;
    }

    @Override
    protected FilterResults performFiltering(CharSequence prefix) {
        FilterResults results = new FilterResults();

        List<Attraction> newAttractions;
        String attractionName;

        if (prefix == null || prefix.length() == 0) {
            newAttractions = new ArrayList<>();
            if (filterText.equals("all")) {
                results.values = originalAttractionList;
                results.count = originalAttractionList.size();
            } else {
                if (filterText.equals("favourites")) {
                    for (Attraction c : originalAttractionList)
                        if (c.favourite)
                            newAttractions.add(c);
                }
                results.values = newAttractions;
                results.count = newAttractions.size();
            }
        } else {
            String prefixString = prefix.toString().toLowerCase();
            newAttractions = new ArrayList<>();

            for (Attraction c : originalAttractionList) {
                attractionName = c.attractionName.toLowerCase();
                if (attractionName.contains(prefixString)) {
                    if (filterText.equals("all")) {
                        newAttractions.add(c);
                    } else if (c.favourite) {
                        newAttractions.add(c);
                    }}}
            results.values = newAttractions;
            results.count = newAttractions.size();
        }
        return results;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void publishResults(CharSequence prefix, FilterResults results) {

        adapter.attractionList = (ArrayList<Attraction>) results.values;

        if (results.count >= 0)
            adapter.notifyDataSetChanged();
        else {
            adapter.notifyDataSetInvalidated();
            adapter.attractionList = originalAttractionList;
        }
        Log.v("newrosstourism", "publishResults : " + adapter.attractionList);
    }
}
