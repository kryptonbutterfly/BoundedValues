package de.tinycodecrank.bounded.presets._int;

import de.tinycodecrank.bounded.BoundedInt;
import de.tinycodecrank.bounded.BoundsException;

/**
 * Guarantees to contain an int that is <= 0.
 * 
 * @author tinycodecrank
 */
public final class int_not_positive implements BoundedInt
{
	private final int value;
	
	private int_not_positive(int value)
	{
		this.value = value;
	}
	
	public final int value()
	{
		return value;
	}
	
	private static boolean validate(int value)
	{
		return value <= 0;
	}
	
	private static BoundsException createError(int value)
	{
		return new BoundsException("Expected the value to be <= 0 but it was %s!", value);
	}
	
	private static final BoundedIntBuilder<int_not_positive> builder = new BoundedIntBuilder<>(
		int_not_positive::validate,
		int_not_positive::new,
		int_not_positive::createError);
	
	public static BoundedIntBuilder<int_not_positive> builder()
	{
		return builder;
	}
}