package de.tinycodecrank.bounded.presets._short;

import de.tinycodecrank.bounded.BoundedShort;
import de.tinycodecrank.bounded.BoundsException;

/**
 * Guarantees to contain a short that is > 0.
 * 
 * @author tinycodecrank
 */
public final class short_greater_zero implements BoundedShort
{
	private final short value;
	
	private short_greater_zero(short value)
	{
		this.value = value;
	}
	
	public short value()
	{
		return value;
	}
	
	private static boolean validate(short value)
	{
		return value > 0;
	}
	
	private static BoundsException createError(short value)
	{
		return new BoundsException("Expected value to be > 0 but was %s!", value);
	}
	
	private static final BoundedShortBuilder<short_greater_zero> builder = new BoundedShortBuilder<>(
		short_greater_zero::validate,
		short_greater_zero::new,
		short_greater_zero::createError);
	
	public static BoundedShortBuilder<short_greater_zero> builder()
	{
		return builder;
	}
}