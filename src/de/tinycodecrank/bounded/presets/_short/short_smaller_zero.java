package de.tinycodecrank.bounded.presets._short;

import de.tinycodecrank.bounded.BoundedShort;
import de.tinycodecrank.bounded.BoundsException;

/**
 * Guarantees to contain a short that is < 0.
 * 
 * @author tinycodecrank
 */
public final class short_smaller_zero implements BoundedShort
{
	private final short value;
	
	private short_smaller_zero(short value)
	{
		this.value = value;
	}
	
	public short value()
	{
		return value;
	}
	
	private static boolean validate(short value)
	{
		return value < 0;
	}
	
	private static BoundsException createError(short value)
	{
		return new BoundsException("Expected value to be < 0 but it was %s!", value);
	}
	
	private static final BoundedShortBuilder<short_smaller_zero> builder = new BoundedShortBuilder<>(
		short_smaller_zero::validate,
		short_smaller_zero::new,
		short_smaller_zero::createError);
	
	public static BoundedShortBuilder<short_smaller_zero> builder()
	{
		return builder;
	}
}