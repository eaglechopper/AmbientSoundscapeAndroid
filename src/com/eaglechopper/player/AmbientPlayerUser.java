package com.eaglechopper.player;

import com.eaglechopper.sound.Sound;

public interface AmbientPlayerUser
{
	public AmbientPlayer stopPlayer(int id);
	public AmbientPlayer playSound(Sound sound);
}
