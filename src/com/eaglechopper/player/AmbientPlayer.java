package com.eaglechopper.player;

import com.eaglechopper.ambientsoundscape.MainActivity;
import com.eaglechopper.sound.Sound;

import android.content.Context;
import android.media.MediaPlayer;

public class AmbientPlayer
{
	private MediaPlayer player;
	private MediaThread loopThread;
	private Thread thread;
	private PlayerID playerID;
	private int startTime;
	private int endTime;
	private boolean isAvaliable;
	private boolean playerInit = false;
	
	public AmbientPlayer(PlayerID playerID)
	{
		this.playerID = playerID;
		isAvaliable = true;
		player = new MediaPlayer();
	}
	public PlayerID getPlayerID()
	{
		return playerID;
		
	}
	public void playSound(Context context, Sound sound)
	{
		//Release the old player
		if(player!= null)
		{
			player.reset();
			player.release();
		}
		
		//Create the new player
		player = MediaPlayer.create(context, sound.getResId());
		playerInit = true;
		player.setVolume(0.5f, 0.5f);
		player.start();
		player.setLooping(true);
		loopThread = new MediaThread(this);
		startTime = sound.getStart();
		endTime = sound.getEnd();
		isAvaliable = false;
		thread = new Thread(loopThread);
		thread.start();
	}
	public AmbientPlayer threadStop()
	{
		if(loopThread != null)
			loopThread.stopThread();
		return this;
	}
	public void setVolume(int prog)
	{
		if(player!= null)
		{
			float vol = (float)prog / (float)MainActivity.maxSeek;
			player.setVolume(vol, vol);
		}
	}
	private void setIdle()
	{	
		player.stop();
		isAvaliable = true;
		
	}
	//Two user mehtods
	public boolean isAvaliable()
	{
		return isAvaliable;
	}
	//Media Thread class
	class MediaThread implements Runnable
	{
		private AmbientPlayer player;
		private boolean loopThread;
		
		public MediaThread(AmbientPlayer player)
		{
			this.player = player;
			loopThread = true;
		}
		@Override
		public void run()
		{
			while(loopThread)
			{
				try
				{
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					try
					{
						if(player.player.getCurrentPosition() >= player.endTime)
						{
							player.player.seekTo(startTime);
						}
					}
					catch(Exception e)
					{
						
					}	
			}
			player.setIdle();
		}
		public void stopThread()
		{
			loopThread = false;
		}
		
	}
	public void release()
	{
		player.reset();
		player.release();
	}
	public void resume()
	{
		if(!player.isPlaying() && playerInit)
		{
			player.start();
		}
			
	}
	public void pause()
	{
		if(player.isPlaying() && player != null)
			player.pause();
	}

	
	

}