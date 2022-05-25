package de.tinycodecrank.bounded.presets._bool;

import de.tinycodecrank.bounded.BoundedBoolean;
import de.tinycodecrank.bounded.BoundsException;

/**
 * Guarantees to be true.
 * 
 * @author tinycodecrank
 */
public final class True implements BoundedBoolean
{
	private True(boolean _value)
	{}
	
	public final boolean value()
	{
		return true;
	}
	
	private static boolean validate(boolean value)
	{
		return value;
	}
	
	private static BoundsException createError(boolean _value)
	{
		return new BoundsException("Expected the value to be true.");
	}
	
	private static final BoundedBooleanBuilder<True> builder = new BoundedBooleanBuilder<>(
		True::validate,
		True::new,
		True::createError);
	
	public static BoundedBooleanBuilder<True> builder()
	{
		return builder;
	}
}