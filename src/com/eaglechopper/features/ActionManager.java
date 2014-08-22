package com.eaglechopper.features;

import android.content.pm.PackageManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.eaglechopper.ambientsoundscape.AppCloseTimer;
import com.eaglechopper.ambientsoundscape.MainActivity;
import com.eaglechopper.communication.NewsFeeder;
import com.eaglechopper.player.AmbientManager;

public class ActionManager
{
	public static MainActivity main;
	private static ActionManager actionManager;
	public static TelephonyManager telephony;
	public static PhoneStatus phoneStatus;
	public static NewsFeeder news;
	public static YourPreferences prefs;
	public static AppCloseTimer timer;
	
	public static AmbientManager ambMngr;
	
	
	private ActionManager(MainActivity main)
	{
		ActionManager.main = main;
		initAmbientManager();
		initFeatures();
	}
	public static ActionManager getInstance(MainActivity main)
	{
		if(actionManager != null)
			return actionManager;
		else
		{
			actionManager = new ActionManager(main);
			return actionManager;
		}
	}
	private void initAmbientManager()
	{
		ambMngr = new AmbientManager(main);
	}
	private void initFeatures()
	{
		PackageManager pm = main.getPackageManager();
		boolean hasTelephony=pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY);
		//Setup Telephony manager if device has it
		if(hasTelephony)
		{
			phoneStatus = new PhoneStatus(ambMngr);
			telephony = (TelephonyManager)main.getApplicationContext().getSystemService("phone");
			telephony.listen(phoneStatus.getPhoneState(), PhoneStateListener.LISTEN_CALL_STATE);
		}
		//Setup prefs
		prefs = new YourPreferences(main);
		news = new NewsFeeder(main, prefs);
		timer = new AppCloseTimer(main);
	}
	//Public methods
	public AmbientManager getAmbientManager()
	{
		return ambMngr;
	}
	
}
