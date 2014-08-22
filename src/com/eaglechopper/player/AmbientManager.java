package com.eaglechopper.player;


import com.eaglechopper.sound.Sound;
import com.eaglechopper.sound.SoundList;
import com.eaglechopper.sound.SoundManager;

import android.content.Context;

public class AmbientManager implements AmbientPlayerUser
{
	private AmbientPlayer[] players;
	//private SoundList sounds;
	private SoundManager soundMngr;
	private Context parent;
	
	public AmbientManager(Context context)
	{
		//sounds = new SoundList(context);
		soundMngr = new SoundManager(context);
		parent = context;
		players = new AmbientPlayer[PlayerID.values().length];
		players[PlayerID.PLAYER_1.getID()] = new AmbientPlayer(PlayerID.PLAYER_1);
		players[PlayerID.PLAYER_2.getID()] = new AmbientPlayer(PlayerID.PLAYER_2);
		players[PlayerID.PLAYER_3.getID()] = new AmbientPlayer(PlayerID.PLAYER_3);
		players[PlayerID.PLAYER_4.getID()] = new AmbientPlayer(PlayerID.PLAYER_4);
	}
	@Override
	public AmbientPlayer playSound(Sound sound)
	{
		//If there is free player, find it and load sound, and play
		if(isPlayer())
		{
			AmbientPlayer player = getAvaliablePlayer();
			player.playSound(parent, sound);
			return player;
		}
		return null;
	}
	//Stops track with pos ID
	@Override
	public AmbientPlayer stopPlayer(int id)
	{
		players[id].threadStop();
		return players[id];
	}
	public void setVolume(int id, int progress)
	{
		players[id].setVolume(progress);
	}
	//Returns true is player is avaliable for use
	private boolean isPlayer() 
	{
		for(int i=0;i<players.length;i++)
		{
			if(players[i].isAvaliable())
				return true;
		}
		return false;
	}
	private AmbientPlayer getAvaliablePlayer()
	{
		for(int i=0;i<players.length;i++)
		{
			if(players[i].isAvaliable())
				return players[i];
		}
		return null;
	}
	public SoundList getSoundList()
	{
		return soundMngr.getSoundList();
	}
	public void releaseAllPlayers()
	{
		for(AmbientPlayer p : players)
		{
			p.release();
		}
	}
	public void resumeAllPlayers()
	{
		for(AmbientPlayer ap : players)
		{
			ap.resume();
		}
	}
	public void pauseAllPlayers()
	{
		for(AmbientPlayer ap : players)
		{
			ap.pause();
		}
	}
	public void sort()
	{
		soundMngr.sort();
	}
	public void stopAllThreads()
	{
		for(int i=0;i< players.length;i++)
		{
			players[i].threadStop();
		}
	}


}

	

