package com.eaglechopper.ambientsoundscape;

import java.util.Timer;
import java.util.TimerTask;

import android.widget.Toast;

public class AppCloseTimer
{
	private Timer timer;
	private TimerTask timerTask;
	private MainActivity main;
	private boolean isTimerRunning;
	
	public AppCloseTimer(MainActivity main)
	{
		this.main = main;
		isTimerRunning = false;
	}
	public String getTimerData()
	{
		String isRun;
		if(isTimerRunning)
			isRun = "Running";
		else
			isRun = "Idle";
		return new String("Timer: " + isRun);
	}
	public void setUpTimer(int hour, int min)
	{ 
		int minMili = min * 60000;
		int hourMili = hour * 3600000;
		int when = minMili + hourMili;
		
		if(timerTask != null)
			timerTask.cancel();
		if(timer != null)
			cancelTimer();
		
		
		
		timerTask = new TimerTask()
		{
			@Override
			public void run() {
				// TODO Auto-generated method stub
				main.stringToNotify("Timer event was successful");
				main.closeThreads();
				main.finish();
				isTimerRunning = false;
				return;
				
			}
			
		};
		Timer timer = new Timer();
		timer.schedule(timerTask, when);
		isTimerRunning = true;
		String toast = "Timer set for: ";
		if(hour == 1)
			toast += "1 hour ";
		else
			toast += hour + " hours ";
		if(min == 1)
			toast += "1 minute";
		else
			toast += min + " minutes";
		
		Toast.makeText(main, toast, Toast.LENGTH_SHORT).show();
		
		
	}
	public void cancelTimer()
	{
		timer.cancel();
	}
	public void stopTimer()
	{
		if(timer != null)
			timer.cancel();
		timer = new Timer();
		timerTask = new TimerTask()
		{
			@Override
			public void run()
			{
				isTimerRunning = false;
				
			}
			
		};
	}
	

}
