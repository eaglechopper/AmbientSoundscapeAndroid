package com.eaglechopper.errorhandle;

public class AmbientException extends Exception
{
	public static final int NULL_AMBIENT_MANAGER = 0;
	public static final int NULL_FEATURES = 1;
	
	private static final long serialVersionUID = -8472103628804042529L;
	private String message;
	private int exceptionFlag;
	
	public AmbientException(String string, int exceptionFlag)
	{
		message = string;
		this.exceptionFlag = exceptionFlag;
	}
	public String getMessage()
	{
		return message;
	}
	public int getFlag()
	{
		return exceptionFlag;
	}
	
}
