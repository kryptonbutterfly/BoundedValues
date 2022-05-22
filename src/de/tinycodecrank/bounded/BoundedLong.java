package de.tinycodecrank.bounded;

import java.util.function.LongFunction;
import java.util.function.LongPredicate;

import de.tinycodecrank.monads.Opt;

public interface BoundedLong
{
	long value();
	
	public static record BoundedLongBuilder<B extends BoundedLong> (
		LongPredicate validator,
		LongFunction<B> constructor,
		LongFunction<BoundsException> exceptionBuilder)
	{
		public B create(long value) throws BoundsException
		{
			if (validator.test(value))
				return constructor.apply(value);
			throw exceptionBuilder.apply(value);
		}
		
		public Opt<B> createOpt(long value)
		{
			if (validator.test(value))
				return Opt.of(constructor.apply(value));
			return Opt.empty();
		}
	}
}