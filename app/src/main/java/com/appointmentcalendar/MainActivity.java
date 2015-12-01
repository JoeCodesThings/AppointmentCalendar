package com.appointmentcalendar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.calendar.Calendar;
import com.calendar.CalendarAdapter;
import com.calendar.Event;
import com.database.DatabaseAdapter;

import java.io.Serializable;
import java.util.ArrayList;

import static android.widget.Toast.*;

public class MainActivity extends ActionBarActivity implements Serializable, CalendarFragment.CalendarFragmentListener, EventFragment.EventFragmentListener {

    CalendarView calendarFragmentView;
    DatabaseAdapter dbAdapter;
    CalendarAdapter calAdapter = new CalendarAdapter();
    LinearLayout calendarFragContainer;
    LinearLayout eventFragContainer;
    ListView eventListView;
    ArrayList<Event> eventListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //sets the main layout of the activity
        setContentView(R.layout.activity_main);

        calendarFragmentView = (CalendarView)findViewById(R.id.calendar);
        calendarFragContainer = (LinearLayout)findViewById(R.id.FragmentContainer1);
        eventFragContainer = (LinearLayout)findViewById(R.id.FragmentContainer2);
        eventListView = (ListView)findViewById(R.id.eventListView);
        dbAdapter = new DatabaseAdapter(getApplicationContext());
        eventListAdapter = new ArrayList<Event>();

        initializeCalendarView();
        setTestData();
    }

    public void onListItemClick(ListView listView, View view, int position,long id)
    {
        makeText(getApplicationContext(), "CLICKED: " + position, LENGTH_SHORT).show();
    }


    public void onSelectedDayChange(CalendarView view, int year, int month, int day)
    {
        //getEvents(day,(month+1),year);
        Calendar cal = calAdapter.getCalendar(0);
        Event event;
        //c.moveToFirst();
        event = cal.getEvent(day, month+1, year);
        if (event.getEventID() == -1)
        {
            makeText(getApplicationContext(), "NO EVENTS FOUND", LENGTH_SHORT).show();
        }
        else
        {
            makeText(getApplicationContext(), "FOUND EVENT # " + event.getEventID(), LENGTH_SHORT).show();
            eventListAdapter.add(event);
            getIntent().putExtra("complexObject", eventListAdapter);
            FragmentManager fragmentManager = getFragmentManager();
            CalendarFragment f1 = new CalendarFragment();
            //f1.setArguments();
            //fragmentManager.beginTransaction().replace(R.id.FragmentContainer2, f1).commit();
        }
    }

    /*Sets the properties for our calendar*/
    private void initializeCalendarView()
    {
        calendarFragmentView.setSelectedWeekBackgroundColor(getResources().getColor(R.color.blue));
    }
    public void setTestData(){
        dbAdapter.open();

        Calendar ourCalendar = new Calendar();
        ourCalendar.setCalendarID(0);
        ourCalendar.setOwner("Mark");
        Event today;

        for(int i = 1; i < 32; ++i)
        {
            today = new Event(i);
            today.setDay(i);
            today.setMonth(12);
            today.setYear(2015);
            today.setCalendarID(0);
            today.setOwner("Mark");
            today.setDuration("1 hour");
            today.setLocation("My House");
            today.setStartTime("1pm");
            today.setEndTime("2pm");
            today.setTitle("WOOHOO");

            dbAdapter.addEvent(today);
            ourCalendar.addEvent(today);
        }

        calAdapter.setCalendar(ourCalendar);
        dbAdapter.addCalendar(ourCalendar);

        dbAdapter.refresh();
    }
}

