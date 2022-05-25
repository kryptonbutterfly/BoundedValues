package de.tinycodecrank.bounded.presets._bool;

import de.tinycodecrank.bounded.BoundedBoolean;
import de.tinycodecrank.bounded.BoundsException;

/**
 * Guarantees to be false.
 * 
 * @author tinycodecrank
 */
public final class False implements BoundedBoolean
{
	private False(boolean _value)
	{}
	
	public final boolean value()
	{
		return false;
	}
	
	private static boolean validate(boolean value)
	{
		return !value;
	}
	
	private static BoundsException createError(boolean _value)
	{
		return new BoundsException("Expected the value to be false.");
	}
	
	private static final BoundedBooleanBuilder<False> builder = new BoundedBooleanBuilder<>(
		False::validate,
		False::new,
		False::createError);
	
	public static BoundedBooleanBuilder<False> builder()
	{
		return builder;
	}
}