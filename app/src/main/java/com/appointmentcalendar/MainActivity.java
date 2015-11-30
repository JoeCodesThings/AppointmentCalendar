package com.appointmentcalendar;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;

import com.calendar.Calendar;
import com.calendar.CalendarAdapter;
import com.calendar.Event;
import com.database.DatabaseAdapter;

public class MainActivity extends ActionBarActivity {
    CalendarView calendar;
    DatabaseAdapter dbAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //sets the main layout of the activity
        setContentView(R.layout.activity_main);

        //initializes the calendarview
        initializeCalendar();

        dbAdapter = new DatabaseAdapter(getApplicationContext());
        initializeDatabase();
    }

    public void initializeCalendar() {
        calendar = (CalendarView) findViewById(R.id.calendar);
        // sets whether to show the week number.
        calendar.setShowWeekNumber(false);
        // sets the first day of week according to Calendar.
        // here we set Monday as the first day of the Calendar
        calendar.setFirstDayOfWeek(2);
        //The background color for the selected week.
        //calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));
        //sets the color for the dates of an unfocused month.
        //calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));
        //sets the color for the separator line between weeks.
        //calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));
        //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
        //calendar.setSelectedDateVerticalBar(R.color.darkgreen);
        //sets the listener to be notified upon selected date change.

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Cursor eventsCursor = dbAdapter.getEvents(day,month,year);
                if(eventsCursor == null) {
                    Toast.makeText(getApplicationContext(), "NO EVENTS", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), eventsCursor.getColumnIndexOrThrow("title"), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void initializeDatabase(){
        dbAdapter.open();

        CalendarAdapter calAdapter = new CalendarAdapter();
        Calendar ourCalendar = new Calendar();
        ourCalendar.setCalendarID(0);
        ourCalendar.setOwner("Mark");

        Event today = new Event(1);
        today.setDay(29);
        today.setMonth(11);
        today.setYear(2015);
        today.setCalendarID(0);
        today.setOwner("Mark");
        today.setDuration("1 hour");
        today.setLocation("My House");
        today.setStartTime("1pm");
        today.setEndTime("2pm");
        today.setTitle("WOOHOO");

        ourCalendar.addEvent(today);
        calAdapter.setCalendar(ourCalendar);

        dbAdapter.addCalendar(ourCalendar);
        dbAdapter.addEvent(today);
    }
}

