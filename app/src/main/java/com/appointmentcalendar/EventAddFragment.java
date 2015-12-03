package com.appointmentcalendar;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.calendar.Event;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class EventAddFragment extends Fragment implements View.OnClickListener {

    private EventAddFragmentListener activityCallback;
    private ArrayList<Event> adapter;
    private Event event;
    private static final String TAG = "EVENT_EDIT_FRAG_TAG";
    EditText eventTitle;
    EditText eventOwner;
    EditText eventLocation;
    EditText eventStartTime;
    EditText eventEndTime;
    EditText eventDuration;
    EditText eventDay;
    EditText eventMonth;
    EditText eventYear;

    public interface EventAddFragmentListener {
        void eventAdd_addEvent(Event event);
    }

    public void eventAdd_addEvent(Event event)
    {
        activityCallback.eventAdd_addEvent(event);
    }


    public static EventAddFragment newInstance (ArrayList<Event> eventList){
        EventAddFragment mf = new EventAddFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(TAG, eventList);
        mf.setArguments(bundle);
        return mf;
    }

    @Override
    public void onClick(View v) {
        boolean correct = true;

        if(eventTitle.getText().length() == 0)
        {
            eventTitle.setBackgroundColor(android.graphics.Color.RED);
            correct = false;
        }
        if (eventOwner.getText().length() == 0) {
            eventOwner.setBackgroundColor(android.graphics.Color.RED);
            correct = false;
        }
        if (eventDay.getText().length() == 0) {
            eventDay.setBackgroundColor(android.graphics.Color.RED);
            correct = false;
        }
        if (eventMonth.getText().length() == 0) {
            eventMonth.setBackgroundColor(android.graphics.Color.RED);
            correct = false;
        }
        if (eventYear.getText().length() == 0) {
            eventYear.setBackgroundColor(android.graphics.Color.RED);
            correct = false;
        }

        if(correct)
        {
            event.setTitle(eventTitle.getText().toString());
            event.setOwner(eventOwner.getText().toString());
            event.setLocation(eventLocation.getText().toString());
            event.setStartTime(eventStartTime.getText().toString());
            event.setEndTime(eventEndTime.getText().toString());
            event.setDuration(eventDuration.getText().toString());
            event.setDay(Integer.parseInt(eventDay.getText().toString()));
            event.setMonth(Integer.parseInt(eventMonth.getText().toString()));
            event.setYear(Integer.parseInt(eventYear.getText().toString()));
            eventAdd_addEvent(event);
        }
        else
        {
            makeText(getActivity(), "FIX INVALID ENTRIES", LENGTH_SHORT).show();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.event_add_fragment,container, false);
        eventTitle = (EditText)view.findViewById(R.id.event_title);
        eventOwner = (EditText)view.findViewById(R.id.event_owner);
        eventLocation = (EditText)view.findViewById(R.id.event_location);
        eventStartTime = (EditText)view.findViewById(R.id.event_start_time);
        eventEndTime = (EditText)view.findViewById(R.id.event_end_time);
        eventDuration = (EditText)view.findViewById(R.id.event_duration);
        eventDay = (EditText)view.findViewById(R.id.event_day);
        eventMonth = (EditText)view.findViewById(R.id.event_month);
        eventYear = (EditText)view.findViewById(R.id.event_year);

        Button submitButton = (Button)view.findViewById(R.id.event_button);
        submitButton.setOnClickListener(this);
        return view;
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
            event = adapter.get(0);
        }
        else
        {
            event = new Event();
            makeText(getActivity(), "FAILED TO ATTACH FRAGMENT", LENGTH_SHORT).show();
        }

        try
        {
            activityCallback = (EventAddFragmentListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement ToolbarListener");
        }
    }
}