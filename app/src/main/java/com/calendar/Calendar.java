package com.calendar;
import android.database.Cursor;

import java.util.Arrays;

public class Calendar {

	private Event[] events;
	private long calendarID;
	private String owner;
	
	public Calendar()
	{
		calendarID = -1;
		events = new Event[100000];
		Arrays.fill(events, null);
	}

	/*Constructor that takes a cursor filled with event information*/
	public Calendar(Cursor c)
	{
		this.calendarID = c.getLong(c.getColumnIndexOrThrow("_calendarID"));
		this.owner = c.getString(c.getColumnIndexOrThrow("owner"));
	}
	public boolean addEvent(Event newEvent)
	{
		for(int i = 0; i < 100000; ++i)
		{
			if(events[i] == null)
			{
				events[i] = newEvent;
				return true;
			}
		}
		return false;
	}
	public Event getEvent(long eventID)
	{
		for(int i = 0; i < 100000; ++i)
		{
			if(events[i].getEventID() == eventID)
			{
				return events[i];
			}
		}
		return new Event(-1);
	}

	public Event getEvent(int day, int month, int year)
	{
		for(int i = 0; i < 100000; ++i)
		{
			if(events[i] != null)
			{
				if(events[i].getDay() == day && events[i].getMonth() == month && events[i].getYear() == year)
				{
					return events[i];
				}
			}
		}
		return new Event(-1);
	}
	/**
	 * @return the calendarID
	 */
	public long getCalendarID() {
		return calendarID;
	}

	/**
	 * @param calendarID the calendarID to set
	 */
	public void setCalendarID(long calendarID) {
		this.calendarID = calendarID;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Calendar [calendarID=" + calendarID + ", owner=" + owner + "]";
	}
}
