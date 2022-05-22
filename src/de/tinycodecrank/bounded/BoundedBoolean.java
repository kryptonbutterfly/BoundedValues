package de.tinycodecrank.bounded;

import de.tinycodecrank.functions.bool_.BoolFunction;
import de.tinycodecrank.functions.bool_.BoolUnaryOperator;
import de.tinycodecrank.monads.Opt;

public interface BoundedBoolean
{
	boolean value();
	
	public static record BoundedBooleanBuilder<B extends BoundedBoolean> (
		BoolUnaryOperator validator,
		BoolFunction<B> constructor,
		BoolFunction<BoundsException> exceptionBuilder)
	{
		public B create(boolean value) throws BoundsException
		{
			if (validator.apply(value))
				return constructor.apply(value);
			throw exceptionBuilder.apply(value);
		}
		
		public Opt<B> createOpt(boolean value)
		{
			if (validator.apply(value))
				return Opt.of(constructor.apply(value));
			return Opt.empty();
		}
	}
}