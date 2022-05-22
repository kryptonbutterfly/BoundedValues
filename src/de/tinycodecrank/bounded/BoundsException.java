package de.tinycodecrank.bounded;

public final class BoundsException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public BoundsException(Throwable throwable, String msg, Object... args)
	{
		super(msg.formatted(args), throwable);
	}
	
	public BoundsException(Throwable throwable)
	{
		super(throwable);
	}
	
	public BoundsException(String msg, Object... args)
	{
		this(msg.formatted(args));
	}
	
	public BoundsException(String msg)
	{
		super(msg);
	}
}