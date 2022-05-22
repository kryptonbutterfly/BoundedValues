package de.tinycodecrank.bounded;

import java.util.function.IntFunction;
import java.util.function.IntPredicate;

import de.tinycodecrank.monads.Opt;
import de.tinycodecrank.monads.OptInt;

public interface BoundedInt
{
	int value();
	
	public static record BoundedIntBuilder<B extends BoundedInt> (
		IntPredicate validator,
		IntFunction<B> constructor,
		IntFunction<BoundsException> exceptionBuilder)
	{
		public B create(int value) throws BoundsException
		{
			if (validator.test(value))
				return constructor.apply(value);
			throw exceptionBuilder.apply(value);
		}
		
		public Opt<B> createOpt(int value)
		{
			return OptInt.of(value)
				.filter(validator)
				.map(constructor);
		}
	}
}