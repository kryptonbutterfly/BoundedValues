package de.tinycodecrank.bounded;

import java.util.function.LongFunction;
import java.util.function.LongPredicate;

import de.tinycodecrank.monads.Opt;

//@formatter:off
/**
 * Implementations of this interface should be final immutable classes with a
 * single BoundedLongBuillder instance used to create instances of the
 * Implementation.
 * </br></br>
 * <b>Example Implementation:</b>
 * 
 * <blockquote>
 * <pre>
 * final class BoundedLongNot0 implements BoundedLong
 * {
 *    private final long value;
 *    
 *    private BoundedLongNot0(long value)
 *    {
 *       this.value = value;
 *    }
 *    
 *    public long value()
 *    {
 *       return value;
 *    }
 *    
 *    public static final BoundedLongBuilder longBuilder =
 *       new BoundedLongBuilder(
 *          value -> value != 0,
 *          BoundedLongNot0::new,
 *          value -> new BoundsException(
 *             "Excepted value to not be 0 but was %d instead",
 *             value));
 * }
 * </pre>
 * </blockquote>
 * @author tinycodecrank
 */
//@formatter:on
public interface BoundedLong
{
	/**
	 * @return The contained value.
	 */
	long value();
	
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
	 *            Function to use to build a BoundException when supplied an invalid
	 *            value.
	 */
	public static record BoundedLongBuilder<B extends BoundedLong> (
		LongPredicate validator,
		LongFunction<B> constructor,
		LongFunction<BoundsException> exceptionBuilder)
	{
		/**
		 * @param value
		 *            The value to create a bounded type from.
		 * @return A bounded version of the supplied value.
		 * @throws BoundsException
		 *             Throws a BoundsException if an invalid value was supplied.
		 */
		public B create(long value) throws BoundsException
		{
			if (validator.test(value))
				return constructor.apply(value);
			throw exceptionBuilder.apply(value);
		}
		
		/**
		 * @param value
		 *            The value to create a bounded type from.
		 * @return Eitehr an {@link Opt} of bounded value or {@link Opt#empty()} if an
		 *         invalid value was supplied.
		 */
		public Opt<B> createOpt(long value)
		{
			if (validator.test(value))
				return Opt.of(constructor.apply(value));
			return Opt.empty();
		}
	}
}