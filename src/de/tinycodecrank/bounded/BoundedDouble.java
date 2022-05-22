package de.tinycodecrank.bounded;

import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;

import de.tinycodecrank.monads.Opt;

public interface BoundedDouble
{
	double value();
	
	public static record BoundedDoubleBuilder<B extends BoundedDouble> (
		DoublePredicate validator,
		DoubleFunction<B> constructor,
		DoubleFunction<BoundsException> exceptionBuilder)
	{
		public B create(double value) throws BoundsException
		{
			if (validator.test(value))
				return constructor.apply(value);
			throw exceptionBuilder.apply(value);
		}
		
		public Opt<B> createOpt(double value)
		{
			if (validator.test(value))
				return Opt.of(constructor.apply(value));
			return Opt.empty();
		}
	}
}