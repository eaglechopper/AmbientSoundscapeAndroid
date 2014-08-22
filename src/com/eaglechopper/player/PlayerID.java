package com.eaglechopper.player;

public enum PlayerID
{
	PLAYER_1(0), PLAYER_2(1), PLAYER_3(2), PLAYER_4(3);
	
	int id;
	
	PlayerID(int id)
	{
		this.id = id;
	}
	public int getID()
	{
		return id;
	}
	static boolean validIDs()
	{
		PlayerID[] ids = PlayerID.values();
		//Loop through each id, must be sequal to the position
		for(int i=0; i<ids.length; i++)
		{
			if(ids[i].getID() != i)
				return false;
		}
		return true;
	}
	static boolean hasDuplicates()
	{
		PlayerID[] ids = PlayerID.values();
		for(int i=0; i < ids.length; i ++)
		{
			for(int j=0; j < ids.length; j ++)
			{
				if(i != j)
					if(ids[i].getID() == ids[j].getID())
						return false;
			}
		}
		return true;
	}
}
