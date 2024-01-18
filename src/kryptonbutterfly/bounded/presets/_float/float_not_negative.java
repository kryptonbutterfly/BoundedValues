package kryptonbutterfly.bounded.presets._float;

import kryptonbutterfly.bounded.BoundedFloat;
import kryptonbutterfly.bounded.BoundsException;

/**
 * Guarantees to contain a float that is >= 0.
 * 
 * @author kryptonbutterfly
 */
public final class float_not_negative implements BoundedFloat
{
	private final float value;
	
	private float_not_negative(float value)
	{
		this.value = value;
	}
	
	public float value()
	{
		return value;
	}
	
	private static boolean validate(float value)
	{
		return value >= 0;
	}
	
	private static BoundsException createError(float value)
	{
		return new BoundsException("Expected value to be >= 0, but it was %s!", value);
	}
	
	private static final BoundedFloatBuilder<float_not_negative> builder = new BoundedFloatBuilder<>(
		float_not_negative::validate,
		float_not_negative::new,
		float_not_negative::createError);
	
	public static BoundedFloatBuilder<float_not_negative> builder()
	{
		return builder;
	}
}