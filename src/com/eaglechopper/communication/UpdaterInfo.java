package com.eaglechopper.communication;



//Contains all the information about app updates
public class UpdaterInfo
{
	public static final int YEAR = 2014;
	public static final int MONTH = 5;
	public static final int DAY = 16;
	
	
	public static String getLastUpdate()
	{
		return DAY + "/" + MONTH + "/" + YEAR;
	}
}
