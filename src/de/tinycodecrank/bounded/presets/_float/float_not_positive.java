package de.tinycodecrank.bounded.presets._float;

import de.tinycodecrank.bounded.BoundedFloat;
import de.tinycodecrank.bounded.BoundsException;

/**
 * Guarantees to contain a float that is <= 0.
 * 
 * @author tinycodecrank
 */
public final class float_not_positive implements BoundedFloat
{
	private final float value;
	
	private float_not_positive(float value)
	{
		this.value = value;
	}
	
	public float value()
	{
		return value;
	}
	
	private static boolean validate(float value)
	{
		return value <= 0;
	}
	
	private static BoundsException createError(float value)
	{
		return new BoundsException("Expected value to be <= 0, but it was %s!", value);
	}
	
	private static final BoundedFloatBuilder<float_not_positive> builder = new BoundedFloatBuilder<>(
		float_not_positive::validate,
		float_not_positive::new,
		float_not_positive::createError);
	
	public static BoundedFloatBuilder<float_not_positive> builder()
	{
		return builder;
	}
}