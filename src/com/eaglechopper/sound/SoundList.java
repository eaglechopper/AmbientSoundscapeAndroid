package com.eaglechopper.sound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import android.content.Context;


import au.com.eaglechopper.ambientsoundscape.R;


public class SoundList
{
	public static final String TRANSPORT = "Transport";
	public static final String APPLIANCE = "Applicance";
	public static final String ELEMENTS = "Element";
	public static final String LOCATION = "Locations";
	public static final String NOISE = "Noise";
	public static Sound myFav1;
	public static Sound myFav2;
	public static int numSounds;
	private Sound[] sounds;
	
	
	//private static SoundList list;
	private final int offset = 300;
	
	public SoundList(Context context)
	{
		ArrayList<Sound> sl = new ArrayList<Sound>();
		Sound fav1 = new Sound("Thunder light", R.raw.thunder_light,ELEMENTS, context, offset);
		Sound fav2 = new Sound("Train 1", R.raw.train_1,TRANSPORT,context, 0);
		myFav1 = fav1;
		myFav2 = fav2;
		
		sl.add(fav1);
		sl.add(fav2);
		sl.add(new Sound("Bus short", R.raw.bus2,TRANSPORT, context,1000));
		sl.add(new Sound("Rain plastic roof", R.raw.rain_plastic_roof,ELEMENTS, context, offset));
		sl.add(new Sound("Restaurant Korean", R.raw.restaurant_ultradust,LOCATION, context, offset));
		sl.add(new Sound("Rain umbrella", R.raw.rain_umbrellka_bmccoy2,ELEMENTS, context, offset));
		sl.add(new Sound("Shower", R.raw.shower2,ELEMENTS, context, offset));
		sl.add(new Sound("Bus long", R.raw.bus,TRANSPORT, context,0));
	
		
		numSounds = sl.size();
		sounds = new Sound[numSounds];
		for(int i=0; i<sl.size();i++)
		{
			sounds[i] = sl.get(i);
		}
		Collections.sort(sl);
	}
	public Sound[] getSounds()
	{	
		return sounds;
	}
	public void sort()
	{
		Arrays.sort(sounds);
	}
	

}
