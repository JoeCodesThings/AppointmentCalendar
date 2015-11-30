package com.tools;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public final class DisplayToast implements Runnable
{
	private final String mText;
	private final Context mCtx;
	private final String mExtraInfo;
	private final boolean mShowPrefix;
	public static volatile Handler toastHandler = new Handler();

	public DisplayToast(String text, String extraInfo, Context ctx, boolean showPrefix)
	{
		this.mText = text;
		this.mCtx = ctx;
		this.mExtraInfo = extraInfo;
		this.mShowPrefix = showPrefix;
	}

	public void run()
	{
		Toast toast;
		int duration;
		String toastMsg;

		if (mShowPrefix)
		{
			toastMsg = "Appointment Calendar" + ": ";
		} else
		{
			toastMsg = "";
		}

		if (mExtraInfo == null)
		{
			toastMsg = toastMsg + mText;
			duration = Toast.LENGTH_SHORT;
		} else
		{
			toastMsg = toastMsg + mText + "\n" + mExtraInfo;
			duration = Toast.LENGTH_LONG;
		}

		toast = Toast.makeText(mCtx, toastMsg, duration);
		toast.show();
	}
}
