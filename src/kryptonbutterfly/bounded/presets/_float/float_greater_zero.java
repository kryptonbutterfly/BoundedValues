package kryptonbutterfly.bounded.presets._float;

import kryptonbutterfly.bounded.BoundedFloat;
import kryptonbutterfly.bounded.BoundsException;

/**
 * Guarantees to contain a float that is > 0.
 * 
 * @author kryptonbutterfly
 */
public final class float_greater_zero implements BoundedFloat
{
	private final float value;
	
	private float_greater_zero(float value)
	{
		this.value = value;
	}
	
	public float value()
	{
		return value;
	}
	
	private static boolean validate(float value)
	{
		return value > 0;
	}
	
	private static BoundsException createError(float value)
	{
		return new BoundsException("Expected value to be > 0, but it was %s!", value);
	}
	
	private static final BoundedFloatBuilder<float_greater_zero> builder = new BoundedFloatBuilder<>(
		float_greater_zero::validate,
		float_greater_zero::new,
		float_greater_zero::createError);
	
	public static BoundedFloatBuilder<float_greater_zero> builder()
	{
		return builder;
	}
}