package com.eaglechopper.sound;

import java.util.Calendar;
import java.util.Locale;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;





public class Sound implements Comparable<Sound>
{
	private String name;
	private int resId;
	private int start;
	private int end;
	private String category;
	private boolean favourite = false;
	private static boolean showCategory = false;
	private static boolean sortByCat = false;
	
	public Sound(String name, int resId, String category, Context context, int offset)
	{
		this.name = name;
		this.resId = resId;
		this.category = category;
		start = 0;
		MediaPlayer m = MediaPlayer.create(context, resId);
		end = m.getDuration() - offset;
		m.release();
	}
	public Sound(String name, int resId, String category, int start, int end)
	{
		this.name = name.trim();
		this.resId = resId;
		this.start = start;
		this.end = end;
		this.category = category.trim();
	}
	public String getName() {
		
		return name;
	}
	public String getCategory()
	{
		return category;
	}
	public int getStart()
	{
		return start;
	}
	public int getEnd()
	{
		return end;
	}
	public static void toggleSortByCat(Context parent)
	{
		sortByCat = !sortByCat;
		if(sortByCat)
			Toast.makeText(parent, "Sorting by category", Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(parent, "Sorting by alphabet", Toast.LENGTH_SHORT).show();
			
	}
	public static void toggleShowCat(Context parent)
	{
		showCategory = !showCategory;
		if(showCategory)
			Toast.makeText(parent, "Showing sound categories", Toast.LENGTH_SHORT).show();
	}
	public String toString()
	{
		String retString = "";
		
		if(showCategory)
			retString += category + " - ";
		//if(favourite)
			//retString += "#";
		retString += name;
		
		return retString;
	}
	public int getResId()
	{
		return resId;
	}
	@Override
	public int compareTo(Sound another)
	{
		if(sortByCat)
		{
			int catSort = category.compareTo(another.getCategory());
			if(catSort == 0)
			{
				return name.compareTo(another.getName());
			}
			return catSort;
		}
		else
			return name.compareTo(another.getName());
		
	}
	public void toggleFavourite()
	{
		favourite = !favourite ;
	}
	
}
