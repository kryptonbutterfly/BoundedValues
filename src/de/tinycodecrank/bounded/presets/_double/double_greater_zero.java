package de.tinycodecrank.bounded.presets._double;

import de.tinycodecrank.bounded.BoundedDouble;
import de.tinycodecrank.bounded.BoundsException;

/**
 * Guarantees to contain a double that is >= 0.
 * 
 * @author tinycodecrank
 */
public class double_greater_zero implements BoundedDouble
{
	private final double value;
	
	private double_greater_zero(double value)
	{
		this.value = value;
	}
	
	public double value()
	{
		return value;
	}
	
	private static boolean validate(double value)
	{
		return value > 0;
	}
	
	private static BoundsException createError(double value)
	{
		return new BoundsException("Expected value to be > 0, but it was %s!", value);
	}
	
	private static final BoundedDoubleBuilder<double_greater_zero> builder = new BoundedDoubleBuilder<>(
		double_greater_zero::validate,
		double_greater_zero::new,
		double_greater_zero::createError);
	
	public static BoundedDoubleBuilder<double_greater_zero> builder()
	{
		return builder;
	}
}