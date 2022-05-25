package de.tinycodecrank.bounded.presets._short;

import de.tinycodecrank.bounded.BoundedShort;
import de.tinycodecrank.bounded.BoundsException;

/**
 * Guarantees to contain a long that is != 0.
 * 
 * @author tinycodecrank
 */
public final class short_not_zero implements BoundedShort
{
	private final short value;
	
	private short_not_zero(short value)
	{
		this.value = value;
	}
	
	public short value()
	{
		return value;
	}
	
	private static boolean validate(short value)
	{
		return value != 0;
	}
	
	private static BoundsException createError(short value)
	{
		return new BoundsException("Expected value to be != 0");
	}
	
	private static final BoundedShortBuilder<short_not_zero> builder = new BoundedShortBuilder<>(
		short_not_zero::validate,
		short_not_zero::new,
		short_not_zero::createError);
	
	public static BoundedShortBuilder<short_not_zero> builder()
	{
		return builder;
	}
}