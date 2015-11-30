package com.calendar;
import android.database.Cursor;

public class Calendar {

	private long calendarID;
	private String owner;
	
	public Calendar()
	{
		calendarID = -1;
	}
	
	/*Constructor that takes a cursor filled with event information*/
	public Calendar(Cursor c)
	{
		this.calendarID = c.getLong(c.getColumnIndexOrThrow("_calendarID"));
		this.owner = c.getString(c.getColumnIndexOrThrow("owner"));
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
