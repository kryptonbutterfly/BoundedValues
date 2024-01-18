package kryptonbutterfly.bounded.presets._bool;

import kryptonbutterfly.bounded.BoundedBoolean;
import kryptonbutterfly.bounded.BoundsException;

/**
 * Guarantees to be false.
 * 
 * @author kryptonbutterfly
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