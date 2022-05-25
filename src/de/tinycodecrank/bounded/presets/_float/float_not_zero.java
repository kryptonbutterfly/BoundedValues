package de.tinycodecrank.bounded.presets._float;

import de.tinycodecrank.bounded.BoundedFloat;
import de.tinycodecrank.bounded.BoundsException;

/**
 * Guarantees to contain a float that is != 0.
 * 
 * @author tinycodecrank
 */
public final class float_not_zero implements BoundedFloat
{
	private final float value;
	
	private float_not_zero(float value)
	{
		this.value = value;
	}
	
	public float value()
	{
		return value;
	}
	
	private static boolean validate(float value)
	{
		return value != 0;
	}
	
	private static BoundsException createError(float value)
	{
		return new BoundsException("Expected value to be != 0.");
	}
	
	private static final BoundedFloatBuilder<float_not_zero> builder = new BoundedFloatBuilder<>(
		float_not_zero::validate,
		float_not_zero::new,
		float_not_zero::createError);
	
	public static BoundedFloatBuilder<float_not_zero> builder()
	{
		return builder;
	}
}