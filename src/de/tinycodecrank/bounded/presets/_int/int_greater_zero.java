package de.tinycodecrank.bounded.presets._int;

import de.tinycodecrank.bounded.BoundedInt;
import de.tinycodecrank.bounded.BoundsException;

/**
 * Guarantees to contain an int that is != 0.
 * 
 * @author tinycodecrank
 */
public final class int_greater_zero implements BoundedInt
{
	private final int value;
	
	private int_greater_zero(int value)
	{
		this.value = value;
	}
	
	public int value()
	{
		return value;
	}
	
	private static boolean validate(int value)
	{
		return value > 0;
	}
	
	private static BoundsException createError(int value)
	{
		return new BoundsException("Expected value to be > 0, but was %s!", value);
	}
	
	private static final BoundedIntBuilder<int_greater_zero> builder = new BoundedIntBuilder<>(
		int_greater_zero::validate,
		int_greater_zero::new,
		int_greater_zero::createError);
	
	public static BoundedIntBuilder<int_greater_zero> builder()
	{
		return builder;
	}
}