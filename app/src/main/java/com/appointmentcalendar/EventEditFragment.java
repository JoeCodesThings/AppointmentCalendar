package com.appointmentcalendar;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.calendar.Event;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class EventEditFragment extends Fragment implements View.OnClickListener {

    private EventEditFragmentListener activityCallback;
    private ArrayList<Event> adapter;
    private static final String TAG = "EVENT_EDIT_FRAG_TAG";

    public interface EventEditFragmentListener {
        void eventEdit_addEvent(Event event);
        void eventEdit_editEvent(ArrayList<Event> event);
    }

    public void eventEdit_addEvent(Event event)
    {
        activityCallback.eventEdit_addEvent(event);
    }
    public void eventEdit_editEvent(ArrayList<Event> event)
    {
        activityCallback.eventEdit_editEvent(event);
    }

    public static EventEditFragment newInstance (ArrayList<Event> eventList){
        EventEditFragment mf = new EventEditFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(TAG, eventList);
        mf.setArguments(bundle);
        return mf;
    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.event_edit_fragment,container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
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
        }
        else
        {
            makeText(getActivity(), "FAILED TO ATTACH FRAGMENT", LENGTH_SHORT).show();
        }
        try
        {
            activityCallback = (EventEditFragmentListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement ToolbarListener");
        }
    }
}