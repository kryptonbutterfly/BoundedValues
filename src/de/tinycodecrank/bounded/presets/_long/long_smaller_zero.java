package de.tinycodecrank.bounded.presets._long;

import de.tinycodecrank.bounded.BoundedLong;
import de.tinycodecrank.bounded.BoundsException;

/**
 * Guarantees to contain a long < 0.
 * 
 * @author tinycodecrank
 */
public final class long_smaller_zero implements BoundedLong
{
	private final long value;
	
	private long_smaller_zero(long value)
	{
		this.value = value;
	}
	
	public long value()
	{
		return value;
	}
	
	private static boolean validate(long value)
	{
		return value < 0;
	}
	
	private static BoundsException createError(long value)
	{
		return new BoundsException("Expected value to be < 0, but it was %s!", value);
	}
	
	private static final BoundedLongBuilder<long_smaller_zero> builder = new BoundedLongBuilder<>(
		long_smaller_zero::validate,
		long_smaller_zero::new,
		long_smaller_zero::createError);
	
	public static BoundedLongBuilder<long_smaller_zero> builder()
	{
		return builder;
	}
}