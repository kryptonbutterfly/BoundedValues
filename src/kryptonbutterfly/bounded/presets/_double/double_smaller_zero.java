package kryptonbutterfly.bounded.presets._double;

import kryptonbutterfly.bounded.BoundedDouble;
import kryptonbutterfly.bounded.BoundsException;

/**
 * Guarantees to contain a double that is < 0.
 * 
 * @author kryptonbutterfly
 */
public final class double_smaller_zero implements BoundedDouble
{
	private final double value;
	
	private double_smaller_zero(double value)
	{
		this.value = value;
	}
	
	public double value()
	{
		return value;
	}
	
	private static boolean validate(double value)
	{
		return value < 0;
	}
	
	private static BoundsException createError(double value)
	{
		return new BoundsException("Expected value to be < 0, but it was %s!", value);
	}
	
	private static final BoundedDoubleBuilder<double_smaller_zero> builder = new BoundedDoubleBuilder<>(
		double_smaller_zero::validate,
		double_smaller_zero::new,
		double_smaller_zero::createError);
	
	public static BoundedDoubleBuilder<double_smaller_zero> builder()
	{
		return builder;
	}
}