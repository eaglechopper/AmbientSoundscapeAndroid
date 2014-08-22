package com.eaglechopper.features;

import java.util.Calendar;

import android.content.Context;
import android.content.SharedPreferences;

import com.eaglechopper.ambientsoundscape.MainActivity;
import com.eaglechopper.communication.UpdaterInfo;

public class YourPreferences
{
	//Shared prefs string
	private static final String sharedPrefsString = "com.eaglechopper.AmbientSoundScape";
	//Strings for preferences
	private static final String PREFS_FIRST_INSTALL = "com.eaglechopper.welcome";
	//Last use of app details, using calender
	private static final String PREFS_DAY_LAST = "com.eaglechopper.lday";
	private static final String PREFS_MONTH_LAST = "com.eaglechopper.lmonth";
	private static final String PREFS_YEAR_LAST = "com.eaglechopper.lyear";
	//finish app on low battery
	//private static final String PREFS_CUT_ONLOW = "com.eaglechopper.cutonlow";
	

	private SharedPreferences yourPrefs;
	
	public YourPreferences(MainActivity main)
	{
		yourPrefs = main.getSharedPreferences(sharedPrefsString, Context.MODE_PRIVATE);
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
		putDate(month);
		putYear(year);
		putDay(day);
	}
	public SharedPreferences getSharedPreferences()
	{
		return yourPrefs;
	}
	//first install
	public boolean getFirstTimeMessage()
	{
		return yourPrefs.getBoolean(PREFS_FIRST_INSTALL, true);
	}
	public int getYear()
	{
		return yourPrefs.getInt(PREFS_YEAR_LAST, 2014);
	}
	public int getMonth()
	{
		return yourPrefs.getInt(PREFS_MONTH_LAST,0);
	}
	public int getDay()
	{
		return yourPrefs.getInt(PREFS_DAY_LAST, 1);
	}
	public void putFirstTimeMessage(boolean b)
	{
		SharedPreferences.Editor edit = yourPrefs.edit();
		edit.putBoolean(PREFS_FIRST_INSTALL, b);
		edit.commit();
	}
	public void putDate(int month)
	{
		SharedPreferences.Editor edit = yourPrefs.edit();
		edit.putInt(PREFS_MONTH_LAST, month);
		edit.commit();
	}
	public void putYear(int year)
	{
		SharedPreferences.Editor edit = yourPrefs.edit();
		edit.putInt(PREFS_YEAR_LAST, year);
		edit.commit();
	}
	public void putDay(int day)
	{
		SharedPreferences.Editor edit = yourPrefs.edit();
		edit.putInt(PREFS_DAY_LAST, day);
		edit.commit();
	}
	//
	public int getDaysSinceUpdate()
	{
		int daysUpdateYear;
		int dayLastYear;
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, UpdaterInfo.YEAR);
		cal.set(Calendar.MONTH, UpdaterInfo.MONTH - 1);
		cal.set(Calendar.DATE, UpdaterInfo.DAY);
		
		daysUpdateYear = cal.get(Calendar.DAY_OF_YEAR);
		
		cal.set(Calendar.YEAR, getYear());
		cal.set(Calendar.MONTH, getMonth());
		cal.set(Calendar.DATE, getDay());
		
		dayLastYear = cal.get(Calendar.DAY_OF_YEAR);
		return dayLastYear - daysUpdateYear;
	}
}
