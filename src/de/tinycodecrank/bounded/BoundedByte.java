package de.tinycodecrank.bounded;

import de.tinycodecrank.functions.byte_.ByteFunction;
import de.tinycodecrank.functions.byte_.BytePredicate;
import de.tinycodecrank.monads.opt.Opt;

//@formatter:off
/**
 * Implementations of this interface should be final immutable classes with a
 * single BoundedByteBuilder instance used to create instances of the
 * Implementation.
 * </br></br>
 * <b>Example Implementation:</b>
 * 
 * <blockquote>
 * <pre>
 * final class BoundedByteLess128 implements BoundedByte
 * {
 *    private final byte value;
 *    
 *    private BoundedByteLess128(byte value)
 *    {
 *       this.value = value;
 *    }
 *    
 *    public byte value()
 *    {
 *       return value;
 *    }
 *    
 *    public static final BoundedByteBuilder byteBuilder =
 *       new BoundedByteBuilder(
 *          value -> value < 128,
 *          BoundedByteLess128::new,
 *          value -> new BoundsException(
 *             "Expected value to be < 128 but was %s instead",
 *             value));
 * }
 * </pre>
 * </blockquote>
 * 
 * @author tinycodecrank
 */
//@formatter:on
public interface BoundedByte
{
	/**
	 * @return The contained value.
	 */
	byte value();
	
	/**
	 * @author tinycodecrank
	 *
	 * @param <B>
	 *            The Bounding Type
	 * @param validator
	 *            Predicate restricting values.
	 * @param constructor
	 *            The Constructor to be used to create the bounded Type.
	 * @param exceptionBuilder
	 *            Function to use to build a BoundsException when supplied an
	 *            invalid value.
	 */
	public static record BoundedByteBuilder<B extends BoundedByte> (
		BytePredicate validator,
		ByteFunction<B> constructor,
		ByteFunction<BoundsException> exceptionBuilder)
	{
		/**
		 * @param value
		 *            The value to create a bounded type from.
		 * @return A bounded version of the supplied value.
		 * @throws BoundsException
		 *             Throws a BoundsException if an invalid value was supplied.
		 */
		public B create(byte value) throws BoundsException
		{
			if (validator.test(value))
				return constructor.apply(value);
			throw exceptionBuilder.apply(value);
		}
		
		/**
		 * @param value
		 *            The value to create a bounded type from.
		 * @return Either an {@link Opt} of bounded value or {@link Opt#empty()} if an
		 *         invalid value was supplied.
		 */
		public Opt<B> createOpt(byte value)
		{
			if (validator.test(value))
				return Opt.of(constructor.apply(value));
			return Opt.empty();
		}
	}
}