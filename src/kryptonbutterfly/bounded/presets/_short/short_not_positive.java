package kryptonbutterfly.bounded.presets._short;

import kryptonbutterfly.bounded.BoundedShort;
import kryptonbutterfly.bounded.BoundsException;

/**
 * Guarantees to contain a short that is <= 0.
 * 
 * @author kryptonbutterfly
 */
public final class short_not_positive implements BoundedShort
{
	private final short value;
	
	private short_not_positive(short value)
	{
		this.value = value;
	}
	
	public short value()
	{
		return value;
	}
	
	private static boolean validate(short value)
	{
		return value <= 0;
	}
	
	private static BoundsException createError(short value)
	{
		return new BoundsException("Expected value to be <= 0 but it was %s!", value);
	}
	
	private static final BoundedShortBuilder<short_not_positive> builder = new BoundedShortBuilder<>(
		short_not_positive::validate,
		short_not_positive::new,
		short_not_positive::createError);
	
	public static BoundedShortBuilder<short_not_positive> builder()
	{
		return builder;
	}
}