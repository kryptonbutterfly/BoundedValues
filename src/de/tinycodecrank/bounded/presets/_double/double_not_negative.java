package de.tinycodecrank.bounded.presets._double;

import de.tinycodecrank.bounded.BoundedDouble;
import de.tinycodecrank.bounded.BoundsException;

/**
 * Guarantees to contain a double that is >= 0.
 * 
 * @author tinycodecrank
 */
public final class double_not_negative implements BoundedDouble
{
	private final double value;
	
	private double_not_negative(double value)
	{
		this.value = value;
	}
	
	public double value()
	{
		return value;
	}
	
	private static boolean validate(double value)
	{
		return value >= 0;
	}
	
	private static BoundsException createError(double value)
	{
		return new BoundsException("Expected value to be >= 0, but it was %s!", value);
	}
	
	private static final BoundedDoubleBuilder<double_not_negative> builder = new BoundedDoubleBuilder<>(
		double_not_negative::validate,
		double_not_negative::new,
		double_not_negative::createError);
	
	public static BoundedDoubleBuilder<double_not_negative> builder()
	{
		return builder;
	}
}