package com.eaglechopper.player;

import com.eaglechopper.sound.Sound;

import android.content.Context;
import android.media.MediaPlayer;

public interface MediaPlayerInterface
{
	public boolean isPlayerAvaliable();
	public MediaPlayer getAvaliablePlayer() throws NullPointerException;
	//Stops players if they are playing
	public void setPlayerIdle(int playerNum);
	public void setAllPlayersIdle();
	//Resets players
	public void resetPlayer(int playerNum);
	public void resetAllPlayers();
	//Returns player to null value
	//public void returnPlayerToNull(int playerNum);
	//public void returnAllPlayersToNull();
	
	public void setVolume(int playerNum, float vol);
	public boolean playSound(Sound sound, Context context);
	
	public void releaseAllPlayers();
}
