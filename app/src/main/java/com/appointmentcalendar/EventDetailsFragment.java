package com.appointmentcalendar;
import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.calendar.Event;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class EventDetailsFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private ArrayList<Event> adapter;
    private static final String TAG = "EVENT_DETAILS_FRAG_TAG";
    private String[] row_text;

    public static EventDetailsFragment newInstance(ArrayList<Event> eventList){
        EventDetailsFragment mf = new EventDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(TAG, eventList);
        mf.setArguments(bundle);
        return mf;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {

    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        Bundle b = this.getArguments();
        if(b != null)
        {
            ArrayList<String> temp = new ArrayList<>();
            adapter = b.getParcelableArrayList(TAG);
            if(adapter.size() == 1){
                row_text = adapter.get(0).toStringArray();
            }
        }
        else
        {
            row_text = new String[]{"NULL"};
            makeText(getActivity(), "FAILED TO ATTACH FRAGMENT", LENGTH_SHORT).show();
        }
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, row_text));
    }
}