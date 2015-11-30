package com.appointmentcalendar;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;
import android.widget.CalendarView;

import com.calendar.Event;
import com.database.DatabaseAdapter;

public class CalendarFragment extends Fragment implements CalendarView.OnDateChangeListener {

    CalendarFragmentListener activityCallback;

    public interface CalendarFragmentListener {
        void onSelectedDayChange(CalendarView view, int year, int month, int day);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.calendar_fragment,container, false);
        CalendarView calendar = (CalendarView) view.findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try
        {
            activityCallback = (CalendarFragmentListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement ToolbarListener");
        }
    }

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
        activityCallback.onSelectedDayChange(view,year,month,day);
    }
}