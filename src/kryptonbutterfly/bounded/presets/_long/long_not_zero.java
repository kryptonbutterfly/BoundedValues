package kryptonbutterfly.bounded.presets._long;

import kryptonbutterfly.bounded.BoundedLong;
import kryptonbutterfly.bounded.BoundsException;

/**
 * Guarantees to contain a long that is != 0.
 * 
 * @author kryptonbutterfly
 */
public final class long_not_zero implements BoundedLong
{
	private final long value;
	
	private long_not_zero(long value)
	{
		this.value = value;
	}
	
	public long value()
	{
		return value;
	}
	
	private static boolean validate(long value)
	{
		return value != 0;
	}
	
	private static BoundsException createError(long value)
	{
		return new BoundsException("Expected value to be != 0");
	}
	
	private static final BoundedLongBuilder<long_not_zero> builder = new BoundedLongBuilder<>(
		long_not_zero::validate,
		long_not_zero::new,
		long_not_zero::createError);
	
	public static BoundedLongBuilder<long_not_zero> builder()
	{
		return builder;
	}
}