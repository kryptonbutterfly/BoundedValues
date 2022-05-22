package de.tinycodecrank.bounded;

import de.tinycodecrank.functions.byte_.ByteFunction;
import de.tinycodecrank.functions.byte_.BytePredicate;
import de.tinycodecrank.monads.Opt;

public interface BoundedByte
{
	byte value();
	
	public static record BoundedByteBuilder<B extends BoundedByte> (
		BytePredicate validator,
		ByteFunction<B> constructor,
		ByteFunction<BoundsException> exceptionBuilder)
	{
		public B create(byte value) throws BoundsException
		{
			if (validator.test(value))
				return constructor.apply(value);
			throw exceptionBuilder.apply(value);
		}
		
		public Opt<B> createOpt(byte value)
		{
			if (validator.test(value))
				return Opt.of(constructor.apply(value));
			return Opt.empty();
		}
	}
}