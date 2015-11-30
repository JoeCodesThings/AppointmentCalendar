package com.appointmentcalendar;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.Toast;

import com.calendar.Calendar;
import com.calendar.CalendarAdapter;
import com.calendar.Event;
import com.database.DatabaseAdapter;

import static android.widget.Toast.*;

public class MainActivity extends ActionBarActivity implements CalendarFragment.CalendarFragmentListener, EventFragment.EventFragmentListener {

    DatabaseAdapter dbAdapter;
    CalendarAdapter calAdapter = new CalendarAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //sets the main layout of the activity
        setContentView(R.layout.activity_main);

        dbAdapter = new DatabaseAdapter(getApplicationContext());
        initializeCalendarAndDatabase();

    }

    public void onItemClick(AdapterView<?> parent, View view, int position,long id)
    {
        makeText(getApplicationContext(), "Item: " + position, LENGTH_SHORT).show();
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
        else {
            makeText(getApplicationContext(), event.getTitle(), LENGTH_SHORT).show();
        }
    }

    public void initializeCalendarAndDatabase(){
        dbAdapter.open();

        Calendar ourCalendar = new Calendar();
        ourCalendar.setCalendarID(0);
        ourCalendar.setOwner("Mark");
        Event today;

        for(int i = 1; i < 30; ++i)
        {
            today = new Event(i);
            today.setDay(i);
            today.setMonth(11);
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

