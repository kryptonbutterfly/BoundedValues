package de.tinycodecrank.bounded.presets._double;

import de.tinycodecrank.bounded.BoundedDouble;
import de.tinycodecrank.bounded.BoundsException;

/**
 * Guarantees to contain a double that is < 0.
 * 
 * @author tinycodecrank
 */
public final class double_not_positive implements BoundedDouble
{
	private final double value;
	
	private double_not_positive(double value)
	{
		this.value = value;
	}
	
	public double value()
	{
		return value;
	}
	
	private static boolean validate(double value)
	{
		return value <= 0;
	}
	
	private static BoundsException createError(double value)
	{
		return new BoundsException("Expected value to be <= 0, but it was %s!", value);
	}
	
	private static final BoundedDoubleBuilder<double_not_positive> builder = new BoundedDoubleBuilder<>(
		double_not_positive::validate,
		double_not_positive::new,
		double_not_positive::createError);
	
	public static BoundedDoubleBuilder<double_not_positive> builder()
	{
		return builder;
	}
}