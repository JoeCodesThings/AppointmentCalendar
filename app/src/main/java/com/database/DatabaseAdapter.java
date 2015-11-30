package com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.CalendarContract;
import android.util.Log;

import com.calendar.Calendar;
import com.calendar.Event;

public class DatabaseAdapter
{
	private static final String TAG = "AppointmentCalendar";
	
	private SQLiteDatabase database;
	private SQLHelper dbHelper;
	private Cursor calendarCursor;
	private Cursor eventCursor;

	private boolean isProcessing;

	public DatabaseAdapter(Context c)
	{
		dbHelper = new SQLHelper(c);
		open();
	}

	public void open()
	{
		database = dbHelper.getWritableDatabase();
		calendarCursor = database.query(SQLHelper.TABLE_CALENDAR, null, null, null, null, null, null);
		eventCursor = database.query(SQLHelper.TABLE_EVENT, null, null, null, null, null, null);
		this.isProcessing = false;
	}

	public void startProcessing()
	{
		this.isProcessing = true;
	}

	public boolean isProcessing()
	{
		return this.isProcessing;
	}
	public void stopProcessing()
	{
		this.isProcessing = false;
	}
	public void close()
	{
		dbHelper.close();
	}
	public void refresh()
	{
		close();
		open();
	}
	public Cursor getCalendarCursor()
	{
		return calendarCursor;
	}
	public Cursor getEventCursor()
	{
		return eventCursor;
	}
	public void addEvent(Event newEvent)
	{
		ContentValues values = new ContentValues();

		values.put(SQLHelper.EVENT_COLUMNS[0], newEvent.getEventID());
		values.put(SQLHelper.EVENT_COLUMNS[1], newEvent.getCalendarID());
		values.put(SQLHelper.EVENT_COLUMNS[2], newEvent.getOwner());
		values.put(SQLHelper.EVENT_COLUMNS[3], newEvent.getTitle());
		values.put(SQLHelper.EVENT_COLUMNS[4], newEvent.getLocation());
		values.put(SQLHelper.EVENT_COLUMNS[5], newEvent.getDate());
		values.put(SQLHelper.EVENT_COLUMNS[6], newEvent.getStartTime());
		values.put(SQLHelper.EVENT_COLUMNS[7], newEvent.getEndTime());
		values.put(SQLHelper.EVENT_COLUMNS[8], newEvent.getDuration());
		values.put(SQLHelper.EVENT_COLUMNS[9], newEvent.getDay());
		values.put(SQLHelper.EVENT_COLUMNS[10], newEvent.getMonth());
		values.put(SQLHelper.EVENT_COLUMNS[11], newEvent.getYear());

		database.insertWithOnConflict(dbHelper.getEventTable(), null, values, SQLiteDatabase.CONFLICT_REPLACE);
		Log.i(TAG, "Inserted Event : " + newEvent.getEventID() + " Into Database.");
	}

	public Cursor getEvents(int day, int month, int year)
	{
		Cursor events = database.query(SQLHelper.TABLE_EVENT,
				new String[]{SQLHelper.COLUMN_DAY,SQLHelper.COLUMN_MONTH,SQLHelper.COLUMN_YEAR},
				"day = ? AND month = ? AND year = ?",
				new String[]{String.valueOf(day),String.valueOf(month),String.valueOf(year)},null,null,null);
		if(events.moveToFirst())
			return events;
		else
			return null;
	}

	public Event deleteEvent(Event event)
	{
		long id = event.getEventID();
		database.delete(SQLHelper.TABLE_EVENT, SQLHelper.COLUMN_EVENTID + "=" + id, null);
		return event;
	}
	
	public void addCalendar(Calendar newCalendar)
	{
		ContentValues values = new ContentValues();

		values.put(SQLHelper.CALENDAR_COLUMNS[0], newCalendar.getCalendarID());
		values.put(SQLHelper.CALENDAR_COLUMNS[1], newCalendar.getOwner());

		database.insertWithOnConflict(dbHelper.getCalendarTable(), null, values, SQLiteDatabase.CONFLICT_REPLACE);
		Log.i(TAG, "Inserted Calendar : " + newCalendar.getCalendarID() + " Into Database.");
	}
	
	public Calendar deleteCalendar(Calendar calendar)
	{
		long id = calendar.getCalendarID();
		database.delete(SQLHelper.TABLE_CALENDAR, SQLHelper.CALENDAR_COLUMNS[0] + "=" + id, null);
		return calendar;
	}
}