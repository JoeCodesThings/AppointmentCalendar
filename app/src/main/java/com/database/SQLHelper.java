package com.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLHelper extends SQLiteOpenHelper
{
	protected static final String DATABASE_NAME = "AC";
	private final static int DATABASE_VERSION = 1;

	public static final String TABLE_CALENDAR = "calendar";
	public static final String TABLE_EVENT = "event";

	/*Calendar table*/
	public static final String COLUMN_CALENDARID = "_calendarid";
	public static final String COLUMN_OWNER = "owner";
	public final static String[] CALENDAR_COLUMNS = {COLUMN_CALENDARID, COLUMN_OWNER};
	
	/*Event table*/
	public static final String COLUMN_EVENTID = "_eventid";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_LOCATION = "location";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_STARTTIME = "start_time";
	public static final String COLUMN_ENDTIME = "end_time";
	public static final String COLUMN_DURATION = "duration";
	public final static String[] EVENT_COLUMNS = {	COLUMN_EVENTID, COLUMN_CALENDARID, COLUMN_OWNER, 
													COLUMN_TITLE, COLUMN_LOCATION, COLUMN_DATE, 
													COLUMN_STARTTIME, COLUMN_ENDTIME,COLUMN_DURATION};

	// Database creation sql statements
	private final String CREATE_CALENDAR_TABLE = "create table " + TABLE_CALENDAR + "(" + CALENDAR_COLUMNS[0]
			+ " integer primary key autoincrement, " + CALENDAR_COLUMNS[1] + " text not null);";

	private final String CREATE_EVENT_TABLE = "create table " + TABLE_EVENT + "(" + EVENT_COLUMNS[0]
			+ " integer primary key autoincrement, " + EVENT_COLUMNS[1] + " integer, " + EVENT_COLUMNS[2] + " text not null, "
			+ EVENT_COLUMNS[3] + " text, " + EVENT_COLUMNS[4] + " text, " + EVENT_COLUMNS[5] + " text, " + EVENT_COLUMNS[6] + " text, "
			+ EVENT_COLUMNS[7] + " text, " + EVENT_COLUMNS[8] + " text);";

	public SQLHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database)
	{
		database.execSQL(CREATE_CALENDAR_TABLE);
		database.execSQL(CREATE_EVENT_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Log.w(SQLHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion
				+ ", which will destroy all old data");

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALENDAR);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
		onCreate(db);
	}

	public String getCalendarTable()
	{
		return TABLE_CALENDAR;
	}
	public String getEventTable()
	{
		return TABLE_EVENT;
	}
}