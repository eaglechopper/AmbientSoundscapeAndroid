package com.eaglechopper.features;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import au.com.eaglechopper.ambientsoundscape.R;

import com.eaglechopper.ambientsoundscape.AppCloseTimer;
import com.eaglechopper.communication.UpdaterInfo;
import com.eaglechopper.sound.SoundList;

public class OptionsMenu
{
	public static void showAppInfo(Context context, AppCloseTimer timer)
	{
		PackageInfo pInfo = null;
		try 
		{
			pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} 
		catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		String version;
		if(pInfo == null)
			version = "N/A";
		else
			version = pInfo.versionName;
		
		AlertDialog.Builder build = new AlertDialog.Builder(context);
		build.setTitle("App/Dev Info");
		String data = timer.getTimerData();
		data += "\nSound Count: ";
		data += SoundList.numSounds + " (More to be added)\n\n";
		data += "Version:";
		data += version +  "\n";
		data += "Last Update: ";
		data += UpdaterInfo.getLastUpdate()+ "\n"+
				context.getString(R.string.infoText);
		build.setMessage(data);
		build.create().show();
	}

	public static void setUpTimer(Context context, OnTimeSetListener listen, final AppCloseTimer timer)
	{
		TimePickerDialog time = new TimePickerDialog(context,listen,0, 0,true);
		time.setTitle("Set time: hour/minute");
		
		time.setOnCancelListener(new OnCancelListener()
		{
			@Override
			public void onCancel(DialogInterface can)
			{
				timer.stopTimer();
			}
			
		});

			
		
		time.show();
		
	}
}
