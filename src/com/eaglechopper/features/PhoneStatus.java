package com.eaglechopper.features;

import com.eaglechopper.player.AmbientManager;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneStatus
{
	private AmbientManager player;
	private PhoneState phoneState;
	
	public PhoneStatus(AmbientManager player)
	{
		this.player = player;
		phoneState = new PhoneState();
	}
	public PhoneState getPhoneState()
	{
		return phoneState;	
	}
	class PhoneState extends PhoneStateListener
	{
		@Override
		public void onCallStateChanged(int state, String incomingNumber)
		{
			// TODO Auto-generated method stub
			super.onCallStateChanged(state, incomingNumber);
			switch(state)
			{
			case TelephonyManager.CALL_STATE_RINGING:
				player.pauseAllPlayers();
				break;
			case TelephonyManager.CALL_STATE_IDLE:
				player.resumeAllPlayers();
				
			}
		}
		
	}
	
	
	
}
