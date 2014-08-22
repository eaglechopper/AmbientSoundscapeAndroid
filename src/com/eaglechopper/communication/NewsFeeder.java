package com.eaglechopper.communication;


import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.support.v4.app.NotificationCompat;
import au.com.eaglechopper.ambientsoundscape.R.drawable;

import com.eaglechopper.ambientsoundscape.MainActivity;
import com.eaglechopper.features.YourPreferences;

public class NewsFeeder implements OnMultiChoiceClickListener
{
	private Context main;
	private YourPreferences prefs;
	//private final int lastUpdateInDaysOfYear = 39;
	
	public NewsFeeder(MainActivity main, YourPreferences prefs)
	{
		this.main = main;
		this.prefs = prefs;
		
		
		if(prefs.getFirstTimeMessage())
			firstTimeMessage();
		//testNotification();
	}
	public void stringToNotify(String message)
	{
		//TODO
		NotificationCompat.Builder noteBuild = new NotificationCompat.Builder(main);
		noteBuild.setAutoCancel(true);
		//noteBuild.set
		noteBuild.setContentTitle("Ambient Soundscape");
		noteBuild.setContentText(message);
		noteBuild.setSmallIcon(drawable.melbourne);
		//noteBuild.setOngoing(true);
		//noteBuild.set
		
		Notification note = noteBuild.build();
		NotificationManager mNote =
		    (NotificationManager) main.getSystemService(Context.NOTIFICATION_SERVICE);
		mNote.notify(2202202, note);
		//mNote.*/
	}
	public void firstTimeMessage()
	{
		AlertDialog.Builder build = new AlertDialog.Builder(main);
		
		

		//build.setTitle("Relax its just a briefing");
		build.setTitle("Beta Initiative");
		//build.setMessage("This is some random message I am using to show an example");
		//Calendar cal = Calendar.getInstance();
		int daysLastUpdate = prefs.getDaysSinceUpdate();
		
		String message = "Ambient Soundscape buffering...\n" +
				"Reporting (" + daysLastUpdate + ") days since last update\n\n" +
				"Welcome to AmbientSoundscape BETA initiative\n" +
				"I understand there might be some bugs in the program this is my first app release and I will " +
				"try to get them fixed. Once the program is stable I will be adding more sounds. Click information" +
				" icon for my gmail if you have any issues. Thanks for downloading";
				
		
		
		build.setMessage(message);
		build.setCancelable(false);
		build.setPositiveButton("OK", new OnClickListener()
		{
			@Override
			public void onClick(DialogInterface d, int which)
			{
				// TODO Auto-generated method stub
				AlertDialog alertDialog = (AlertDialog) d;
				alertDialog.dismiss();
				prefs.putFirstTimeMessage(false);
				//setUp();
			}
		});
		

		
		AlertDialog alert = build.create();
		alert.show();
		
		/*Dialog d = new Dialog(this);
		d.setTitle("Welcome to this app its awesome");
		d.setContentView(findViewById(R.id.dialogBox));
		d.show();*/
	}
	public void setUp()
	{
		AlertDialog.Builder build = new AlertDialog.Builder(main);
		/*
		LayoutInflater inflater = LayoutInflater.from(main);
		View viewMenu = inflater.inflate(R.layout.select_dialog_singlechoice, null);
		build.setView(viewMenu);
		build.setTitle("Attention");
		build.setMessage("Welcome");*/
		
		
		
		build.setTitle("App Setup");
		//build.setMessage("This is some random message I am using to show an example");
		String[] strings = {"Background Image"};
		boolean[] booleans = {true};
		build.setMultiChoiceItems(strings, booleans, this);
		build.setCancelable(false);
		build.setPositiveButton("OK", new OnClickListener()
		{
			@Override
			public void onClick(DialogInterface d, int which)
			{
				// TODO Auto-generated method stub
				AlertDialog alertDialog = (AlertDialog) d;
				alertDialog.dismiss();
					
				
			}
		});
		

		
		AlertDialog alert = build.create();
		alert.show();
		
		/*Dialog d = new Dialog(this);
		d.setTitle("Welcome to this app its awesome");
		d.setContentView(findViewById(R.id.dialogBox));
		d.show();*/
	}
	public void sendAlertDialog(AlertDialog alert)
	{
		//main.showDialog(alert);
	}
	@Override
	public void onClick(DialogInterface arg0, int num, boolean val) {
		// TODO Auto-generated method stub
		
		
	}
}
