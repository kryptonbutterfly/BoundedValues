package kryptonbutterfly.bounded.presets._bool;

import kryptonbutterfly.bounded.BoundedBoolean;
import kryptonbutterfly.bounded.BoundsException;

/**
 * Guarantees to be true.
 * 
 * @author kryptonbutterfly
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