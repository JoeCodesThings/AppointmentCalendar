package com.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.annotation.SuppressLint;

public class DateFormat
{
	final static String TIMEZONE = "PST";
	public static String miliToDate(Long miliseconds)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
		dateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
		Date d = new Date(miliseconds);
		return dateFormat.format(d);
	}

	public static String miliToDay(Long miliseconds)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
		dateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
		Date d = new Date(miliseconds);
		return dateFormat.format(d);
	}

	@SuppressLint("SimpleDateFormat")
	public static String timeSince(Long miliseconds)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM, yyyy hh:mm a");
		dateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
		Date d = new Date(miliseconds);
		return dateFormat.format(d);
	}
}