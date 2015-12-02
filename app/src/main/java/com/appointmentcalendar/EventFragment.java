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

public class EventFragment extends ListFragment implements AdapterView.OnItemClickListener {

    private EventFragmentListener activityCallback;
    private ArrayList<Event> adapter;
    private static final String TAG = "EVENT_FRAG_TAG";
    private String[] row_text;

    public interface EventFragmentListener {
        void onListItemClick(ListView l, View view, int position, long id);
    }

    public static EventFragment newInstance(ArrayList<Event> eventList){
        EventFragment mf = new EventFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(TAG, eventList);
        mf.setArguments(bundle);
        return mf;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        activityCallback.onListItemClick(l, v, position, id);
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
            for (Event e : adapter) {
                temp.add(e.getTitle());
            }
            row_text = temp.toArray(new String[15]);
        }
        else
        {
            makeText(getActivity(), "FAILED TO ATTACH FRAGMENT", LENGTH_SHORT).show();
        }
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, row_text));

        try
        {
            activityCallback = (EventFragmentListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement ToolbarListener");
        }
    }
}