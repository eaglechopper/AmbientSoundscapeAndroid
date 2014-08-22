package com.eaglechopper.sound;

import android.content.Context;

public class SoundManager
{
	private SoundList sounds;
	
	
	public SoundManager(Context parent)
	{
		sounds = new SoundList(parent);
	}

	public SoundList getSoundList()
	{
		return sounds;
	}

	public void sort()
	{
		sounds.sort();
	}
}
