package kryptonbutterfly.bounded.presets._long;

import kryptonbutterfly.bounded.BoundedLong;
import kryptonbutterfly.bounded.BoundsException;

/**
 * Guarantees to contain a long that is > 0.
 * 
 * @author kryptonbutterfly
 */
public final class long_greater_zero implements BoundedLong
{
	private final long value;
	
	private long_greater_zero(long value)
	{
		this.value = value;
	}
	
	public long value()
	{
		return value;
	}
	
	private static boolean validate(long value)
	{
		return value > 0;
	}
	
	private static BoundsException createError(long value)
	{
		return new BoundsException("Expected value to be > 0, but it was %s!", value);
	}
	
	private static final BoundedLongBuilder<long_greater_zero> builder = new BoundedLongBuilder<>(
		long_greater_zero::validate,
		long_greater_zero::new,
		long_greater_zero::createError);
	
	public static BoundedLongBuilder<long_greater_zero> builder()
	{
		return builder;
	}
}