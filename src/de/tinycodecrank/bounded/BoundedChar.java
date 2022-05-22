package de.tinycodecrank.bounded;

import de.tinycodecrank.functions.char_.CharFunction;
import de.tinycodecrank.functions.char_.CharPredicate;
import de.tinycodecrank.monads.Opt;

public interface BoundedChar
{
	char value();
	
	public static record BoundedCharBuilder<B extends BoundedChar> (
		CharPredicate validator,
		CharFunction<B> constructor,
		CharFunction<BoundsException> exceptionBuilder)
	{
		public B create(char value) throws BoundsException
		{
			if (validator.test(value))
				return constructor.apply(value);
			throw exceptionBuilder.apply(value);
		}
		
		public Opt<B> createOpt(char value)
		{
			if (validator.test(value))
				return Opt.of(constructor.apply(value));
			return Opt.empty();
		}
	}
}