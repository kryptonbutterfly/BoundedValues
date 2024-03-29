package kryptonbutterfly.bounded.presets._int;

import kryptonbutterfly.bounded.BoundedInt;
import kryptonbutterfly.bounded.BoundsException;

/**
 * Guarantees to contain an int that is >= 0.
 * 
 * @author kryptonbutterfly
 */
public final class int_not_negative implements BoundedInt
{
	private final int value;
	
	private int_not_negative(int value)
	{
		this.value = value;
	}
	
	public final int value()
	{
		return value;
	}
	
	private static boolean validate(int value)
	{
		return value >= 0;
	}
	
	private static BoundsException createError(int value)
	{
		return new BoundsException("Expected the value to be >= 0 but it was %s!", value);
	}
	
	private static final BoundedIntBuilder<int_not_negative> builder = new BoundedIntBuilder<>(
		int_not_negative::validate,
		int_not_negative::new,
		int_not_negative::createError);
	
	public static BoundedIntBuilder<int_not_negative> builder()
	{
		return builder;
	}
}