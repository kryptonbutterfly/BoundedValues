package de.tinycodecrank.bounded;

import de.tinycodecrank.functions.short_.ShortFunction;
import de.tinycodecrank.functions.short_.ShortPredicate;
import de.tinycodecrank.monads.Opt;

public interface BoundedShort
{
	short value();
	
	public static record BoundedShortBuilder<B extends BoundedShort> (
		ShortPredicate validator,
		ShortFunction<B> constructor,
		ShortFunction<BoundsException> exceptionBuilder)
	{
		public B create(short value) throws BoundsException
		{
			if (validator.test(value))
				return constructor.apply(value);
			throw exceptionBuilder.apply(value);
		}
		
		public Opt<B> createOpt(short value)
		{
			if (validator.test(value))
				return Opt.of(constructor.apply(value));
			return Opt.empty();
		}
	}
}