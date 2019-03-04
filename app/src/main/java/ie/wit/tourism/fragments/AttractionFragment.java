package ie.wit.tourism.fragments;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import ie.wit.tourism.R;
import ie.wit.tourism.adapters.AttractionsFilter;
import ie.wit.tourism.adapters.AttractionsListAdapter;
import ie.wit.tourism.activities.Base;
import ie.wit.tourism.activities.Edit;
import ie.wit.tourism.activities.Favourites;
import ie.wit.tourism.models.Attraction;

public class AttractionFragment  extends ListFragment implements View.OnClickListener,
        AbsListView.MultiChoiceModeListener
{
    public Base activity;
    public static AttractionsListAdapter listAdapter;
    public ListView listView;
    public AttractionsFilter attractionFilter;

    public AttractionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Bundle activityInfo = new Bundle(); // Creates a new Bundle object
        activityInfo.putString("attractionId", (String) v.getTag());
        Intent goEdit = new Intent(getActivity(), Edit.class); // Creates a new Intent
        /* Add the bundle to the intent here */
        goEdit.putExtras(activityInfo);
        getActivity().startActivity(goEdit); // Launch the Intent
    }

    public static AttractionFragment newInstance() {
        AttractionFragment fragment = new AttractionFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.activity = (Base) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        listAdapter = new AttractionsListAdapter(activity, this, activity.app.attractionList);
        attractionFilter = new AttractionsFilter(activity.app.attractionList,"all",listAdapter);

        if (getActivity() instanceof Favourites) {
            attractionFilter.setFilter("favourites"); // Set the filter text field from 'all' to 'favourites'
            attractionFilter.filter(null); // Filter the data, but don't use any prefix
            listAdapter.notifyDataSetChanged(); // Update the adapter
        }
        setListAdapter (listAdapter);
        setRandomAttraction();
        checkEmptyList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        listView = v.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);

        return v;
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onClick(View view)
    {
        if (view.getTag() instanceof Attraction)
        {
            onAttractionDelete ((Attraction) view.getTag());
        }
    }

    public void onAttractionDelete(final Attraction attraction)
    {
        String stringName = attraction.attractionName;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Are you sure you want to Delete the \'Attraction\' " + stringName + "?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                activity.app.attractionList.remove(attraction); // remove from our list
                listAdapter.attractionList.remove(attraction); // update adapters data
                setRandomAttraction();
                listAdapter.notifyDataSetChanged(); // refresh adapter
                checkEmptyList();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /* ************ MultiChoiceModeListener methods (begin) *********** */
    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu)
    {
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.delete_list, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.menu_item_delete_attraction:
                deleteAttractions(actionMode);
                return true;
            default:
                return false;
        }
    }

    public void deleteAttractions(ActionMode actionMode)
    {
        for (int i = listAdapter.getCount() -1 ; i >= 0; i--)
        {
            if (listView.isItemChecked(i))
            {
                activity.app.attractionList.remove(listAdapter.getItem(i));
                if (activity instanceof Favourites)
                    listAdapter.attractionList.remove(listAdapter.getItem(i));
            }
        }
        setRandomAttraction();
        listAdapter.notifyDataSetChanged(); // refresh adapter
        checkEmptyList();

        actionMode.finish();
    }

    public void setRandomAttraction() {

        ArrayList<Attraction> attractionList = new ArrayList<>();

        for(Attraction c : activity.app.attractionList)
            if (c.favourite)
                attractionList.add(c);

        if (activity instanceof Favourites)
            if( !attractionList.isEmpty()) {
                Attraction randomAttraction= attractionList.get(new Random()
                        .nextInt(attractionList.size()));

                ((TextView) getActivity().findViewById(R.id.favouriteAttractionName)).setText(randomAttraction.attractionName);
                ((TextView) getActivity().findViewById(R.id.favouriteAttractionLocation)).setText(randomAttraction.location);
                ((TextView) getActivity().findViewById(R.id.favouriteAttractionPrice)).setText("€ " + randomAttraction.price);
                ((TextView) getActivity().findViewById(R.id.favouriteAttractionRating)).setText(randomAttraction.rating + " *");
            }
            else {
                ((TextView) getActivity().findViewById(R.id.favouriteAttractionName)).setText("N/A");
                ((TextView) getActivity().findViewById(R.id.favouriteAttractionLocation)).setText("N/A");
                ((TextView) getActivity().findViewById(R.id.favouriteAttractionPrice)).setText("N/A");
                ((TextView) getActivity().findViewById(R.id.favouriteAttractionRating)).setText("N/A");
            }
    }

    public void checkEmptyList()
    {
        TextView recentList = getActivity().findViewById(R.id.emptyList);

        if(activity.app.attractionList.isEmpty())
            recentList.setText(getString(R.string.emptyMessageLbl));
        else
            recentList.setText("");
    }
    @Override
    public void onDestroyActionMode(ActionMode actionMode)
    {}

    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked)
    {}
    /* ************ MultiChoiceModeListener methods (end) *********** */
}