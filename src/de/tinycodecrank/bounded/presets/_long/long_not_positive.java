package de.tinycodecrank.bounded.presets._long;

import de.tinycodecrank.bounded.BoundedLong;
import de.tinycodecrank.bounded.BoundsException;

/**
 * Guarantees to contain a long that is <= 0.
 * 
 * @author tinycodecrank
 */
public final class long_not_positive implements BoundedLong
{
	private final long value;
	
	private long_not_positive(long value)
	{
		this.value = value;
	}
	
	public long value()
	{
		return value;
	}
	
	private static boolean validate(long value)
	{
		return value <= 0;
	}
	
	private static BoundsException createError(long value)
	{
		return new BoundsException("Expected value to be <= 0, but it was %s!", value);
	}
	
	private static final BoundedLongBuilder<long_not_positive> builder = new BoundedLongBuilder<>(
		long_not_positive::validate,
		long_not_positive::new,
		long_not_positive::createError);
	
	public static BoundedLongBuilder<long_not_positive> builder()
	{
		return builder;
	}
}