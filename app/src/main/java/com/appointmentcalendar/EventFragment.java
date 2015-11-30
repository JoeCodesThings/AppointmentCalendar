package com.appointmentcalendar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

public class EventFragment extends Fragment implements AdapterView.OnItemClickListener {

    EventFragmentListener activityCallback;

    public interface EventFragmentListener {
        void onItemClick(AdapterView<?> parent, View view, int position, long id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.event_fragment,container, false);
        ExpandableListView eventList = (ExpandableListView) view.findViewById(R.id.eventListView);
        eventList.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try
        {
            activityCallback = (EventFragmentListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement ToolbarListener");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id)
    {
        activityCallback.onItemClick(parent, view, position, id);
    }

}