package kryptonbutterfly.bounded.presets._double;

import kryptonbutterfly.bounded.BoundedDouble;
import kryptonbutterfly.bounded.BoundsException;

/**
 * Guarantees to contain a double that is != 0.
 * 
 * @author kryptonbutterfly
 */
public final class double_not_zero implements BoundedDouble
{
	private final double value;
	
	private double_not_zero(double value)
	{
		this.value = value;
	}
	
	public double value()
	{
		return value;
	}
	
	private static boolean validate(double value)
	{
		return value != 0;
	}
	
	private static BoundsException createError(double value)
	{
		return new BoundsException("Expected value to be != 0");
	}
	
	private static final BoundedDoubleBuilder<double_not_zero> builder = new BoundedDoubleBuilder<>(
		double_not_zero::validate,
		double_not_zero::new,
		double_not_zero::createError);
	
	public static BoundedDoubleBuilder<double_not_zero> builder()
	{
		return builder;
	}
}