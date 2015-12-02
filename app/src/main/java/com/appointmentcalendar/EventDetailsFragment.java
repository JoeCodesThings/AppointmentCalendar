package com.appointmentcalendar;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class EventDetailsFragment extends ListFragment implements AdapterView.OnItemClickListener {

    EventDetailsFragmentListener activityCallback;
    private ArrayList<DataSetObserver> observers;

    public interface EventDetailsFragmentListener {
        void onListItemClick(ListView l, View view, int position, long id);
    }

    String[] numbers_text = new String[] { "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine", "ten", "eleven",
            "twelve", "thirteen", "fourteen", "fifteen" };

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //makeText(getActivity(), "CLICKED: " + position, LENGTH_SHORT).show();
        activityCallback.onListItemClick(l, v, position, id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        Bundle bundle = getActivity().getIntent().getExtras();
        if(bundle != null)
        {
            numbers_text = bundle.getStringArray("EVENT_LIST");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1, numbers_text);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Intent i = getActivity().getIntent();
        observers = (ArrayList<DataSetObserver>) i.getSerializableExtra("complexObject");
        try
        {
            activityCallback = (EventDetailsFragmentListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement ToolbarListener");
        }
    }
}