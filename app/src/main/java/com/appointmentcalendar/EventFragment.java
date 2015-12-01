package com.appointmentcalendar;
import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EventFragment extends Fragment implements AdapterView.OnItemClickListener {

    EventFragmentListener activityCallback;
    ExpandableListView eventList;
    private ArrayList<DataSetObserver> observers;


    public interface EventFragmentListener {
        void onItemClick(AdapterView<?> parent, View view, int position, long id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_fragment,container, false);

        Intent i = getActivity().getIntent();
        observers = (ArrayList<DataSetObserver>) i.getSerializableExtra("complexObject");

        eventList = (ExpandableListView) view.findViewById(R.id.eventListView);
        eventList.setAdapter(new ExpandableListAdapter() {
            @Override
            public void registerDataSetObserver(DataSetObserver observer) {
                if(observers != null)
                {
                    observers.add(observer);
                }
            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {
                observers.remove(observer);
            }

            @Override
            public int getGroupCount() {
                return 0;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return 0;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return null;
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return null;
            }

            @Override
            public long getGroupId(int groupPosition) {
                return 0;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                return null;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                return null;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return false;
            }

            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public void onGroupExpanded(int groupPosition) {

            }

            @Override
            public void onGroupCollapsed(int groupPosition) {

            }

            @Override
            public long getCombinedChildId(long groupId, long childId) {
                return 0;
            }

            @Override
            public long getCombinedGroupId(long groupId) {
                return 0;
            }
        });
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