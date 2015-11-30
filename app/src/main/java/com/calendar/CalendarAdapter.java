package com.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appointmentcalendar.R;

    /**
     * A placeholder fragment containing a simple view.
     */
    public class CalendarAdapter extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "1";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static CalendarAdapter newInstance(int sectionNumber) {
        	CalendarAdapter fragment = new CalendarAdapter();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public CalendarAdapter() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.month_view, container, false);
            return rootView;
        }
    }
