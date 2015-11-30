package com.calendar;
import android.database.Cursor;

public class Event {
	private long eventID;
	private long calendarID;
	private String owner;
	private String title;
	private String location;
	private String date;
	private String startTime;
	private String endTime;
	private String duration;
	
	public Event()
	{
		eventID = -1;
		calendarID = -1;
	}
	
	/*Constructor that takes a cursor filled with event information*/
	public Event(Cursor c)
	{
		this.eventID = c.getLong(c.getColumnIndexOrThrow("_eventID"));
		this.calendarID = c.getLong(c.getColumnIndexOrThrow("_calendarID"));
		this.owner = c.getString(c.getColumnIndexOrThrow("owner"));
		this.title = c.getString(c.getColumnIndexOrThrow("title"));
		this.location = c.getString(c.getColumnIndex("location"));
		this.date = c.getString(c.getColumnIndexOrThrow("date"));
		this.startTime = c.getString(c.getColumnIndexOrThrow("startTime"));
		this.endTime = c.getString(c.getColumnIndexOrThrow("endTime"));
		this.duration = c.getString(c.getColumnIndexOrThrow("duration"));
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

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the eventID
	 */
	public long getEventID() {
		return eventID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Event [eventID=" + eventID + ", calendarID=" + calendarID
				+ ", owner=" + owner + ", title=" + title + ", location="
				+ location + ", date=" + date + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", duration=" + duration + "]";
	}
}
