package com.eaglechopper.errorhandle;

import android.util.Log;

public class AppLog
{
	public static final String TAG_DEFAULT = "tag_default";
	public static final String TAG_NULL = "tag_null";
	public static final String TAG_APPLOG_REPORT = "tag_app-report";
	
	
	private static int warn = 0;
	private static int error = 0;
	private static int info = 0;
	private static int debug = 0;
	/*Log.v = Verbos
	 *  Log.d = Debug
	 *  
	 * Log.i = Info
	 * Log.e = Error
	 * Log.w = Warn
	 * 
	 * Verbos shoud be never compiled into app
	 * Debug are striped at run time
	 */
	public static String TAG_TIME = "tag_time";
	
	private AppLog()
	{
		
	}
	public static void warn(String tag, String msg)
	{
		Log.w(tag, msg);
		++warn;
	}
	public static void info(String tag, String msg)
	{
		Log.i(tag, msg);
		++info;
	}
	public static void debug(String tag, String msg)
	{
		Log.d(tag, msg);
		++debug;
	}
	public static void error(String tag, String msg)
	{
		Log.e(tag, msg);
		++error;
	}
	public static void appLogReport()
	{
		Log.i(TAG_APPLOG_REPORT, "Info Log: " + info);
		Log.i(TAG_APPLOG_REPORT, "Warn Log: " + warn);
		Log.i(TAG_APPLOG_REPORT, "Error Log: " + error);
		Log.i(TAG_APPLOG_REPORT, "Debug Log: " + debug);
	}
	
}
