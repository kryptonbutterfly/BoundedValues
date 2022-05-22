package de.tinycodecrank.bounded;

import java.util.function.Function;
import java.util.function.Predicate;

import de.tinycodecrank.monads.Opt;

public interface Bounded<O>
{
	O value();
	
	public static record BoundedBuilder<O, B extends Bounded<O>> (
		Predicate<O> validator,
		Function<O, B> constructor,
		Function<O, BoundsException> exceptionBuilder)
	{
		public B create(O value) throws BoundsException
		{
			if (validator.test(value))
				return constructor.apply(value);
			throw exceptionBuilder.apply(value);
		}
		
		public Opt<B> createOpt(O value)
		{
			if (validator.test(value))
				return Opt.of(constructor.apply(value));
			return Opt.empty();
		}
	}
}