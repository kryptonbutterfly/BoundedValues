package de.tinycodecrank.bounded;

import de.tinycodecrank.functions.float_.FloatFunction;
import de.tinycodecrank.functions.float_.FloatPredicate;
import de.tinycodecrank.monads.Opt;

public interface BoundedFloat
{
	float value();
	
	public static record BoundedFloatBuilder<B extends BoundedFloat> (
		FloatPredicate validator,
		FloatFunction<B> constructor,
		FloatFunction<BoundsException> exceptionBuilder)
	{
		public B create(float value) throws BoundsException
		{
			if (validator.test(value))
				return constructor.apply(value);
			throw exceptionBuilder.apply(value);
		}
		
		public Opt<B> createOpt(float value)
		{
			if (validator.test(value))
				return Opt.of(constructor.apply(value));
			return Opt.empty();
		}
	}
}