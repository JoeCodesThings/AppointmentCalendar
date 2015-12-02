package com.appointmentcalendar;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.calendar.Calendar;
import com.calendar.CalendarAdapter;
import com.calendar.Event;
import com.database.DatabaseAdapter;
import java.io.Serializable;
import java.util.ArrayList;

import static android.widget.Toast.*;

public class MainActivity extends ActionBarActivity implements Serializable, CalendarFragment.CalendarFragmentListener, EventFragment.EventFragmentListener, EventDetailsFragment.EventDetailsFragmentListener {

    CalendarView calendarFragmentView;
    DatabaseAdapter dbAdapter;
    CalendarAdapter calAdapter;
    LinearLayout calendarFragContainer;
    LinearLayout eventFragContainer;

    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //sets the main layout of the activity
        setContentView(R.layout.activity_main);

        calendarFragmentView = (CalendarView)findViewById(R.id.calendar);

        calendarFragContainer = (LinearLayout)findViewById(R.id.FragmentContainer1);
        calendarFragContainer.setId(R.id.FragmentContainer1);

        eventFragContainer = (LinearLayout)findViewById(R.id.FragmentContainer2);
        eventFragContainer.setId(R.id.FragmentContainer2);

        calAdapter = new CalendarAdapter();
        dbAdapter = new DatabaseAdapter(getApplicationContext());
        setTestData();
    }

    public void onListItemClick(ListView listView, View view, int position,long id)
    {
        makeText(getApplicationContext(), "CLICKED: " + position, LENGTH_SHORT).show();
    }


    public void onSelectedDayChange(CalendarView view, int year, int month, int day)
    {
        Calendar cal = calAdapter.getCalendar(0);
        ArrayList<Event> dailyEvents = cal.getEvents(day, month + 1, year);

        if (dailyEvents.size() == 0)
        {
            makeText(getApplicationContext(), "NO EVENTS FOUND", LENGTH_SHORT).show();
        }
        else
        {
            makeText(getApplicationContext(), dailyEvents.size() + "EVENTS FOUND", LENGTH_SHORT).show();
            setSecondFragment(dailyEvents);
        }
    }

    private void setSecondFragment(ArrayList<Event> dailyList) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        Fragment frag = EventFragment.newInstance(dailyList);
        if (fm.findFragmentByTag("EVENT_FRAG_TAG") == null)
        {
            ft.add(eventFragContainer.getId(), frag, "EVENT_FRAG_TAG").commit();
        }
        else
        {
            ft.addToBackStack("EVENT_FRAG_TAG");
            ft.replace(eventFragContainer.getId(), frag, "EVENT_FRAG_TAG").commit();
        }

        fm.executePendingTransactions();
    }

    public void setTestData(){
        dbAdapter.open();

        Calendar ourCalendar = new Calendar();
        ourCalendar.setCalendarID(0);
        ourCalendar.setOwner("Mark");
        Event today;

        for(int i = 1; i < 30; ++i)
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

