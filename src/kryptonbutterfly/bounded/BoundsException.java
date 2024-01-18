package kryptonbutterfly.bounded;

public final class BoundsException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param cause
	 *            The original cause of this exception
	 * @param msg
	 *            The formatter string of the message of the exception
	 * @param args
	 *            formatter arguments to be applied to the formatter string
	 */
	public BoundsException(Throwable cause, String msg, Object... args)
	{
		super(msg.formatted(args), cause);
	}
	
	/**
	 * @param cause
	 *            The original cause of this exception
	 */
	public BoundsException(Throwable cause)
	{
		super(cause);
	}
	
	/**
	 * @param msg
	 *            The formatter string of the message of the exception
	 * @param args
	 *            formatter arguments to be applied to the formatter string
	 */
	public BoundsException(String msg, Object... args)
	{
		this(msg.formatted(args));
	}
	
	/**
	 * @param msg
	 *            The message of the exception
	 */
	public BoundsException(String msg)
	{
		super(msg);
	}
}