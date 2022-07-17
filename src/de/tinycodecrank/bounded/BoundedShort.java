package de.tinycodecrank.bounded;

import de.tinycodecrank.functions.short_.ShortFunction;
import de.tinycodecrank.functions.short_.ShortPredicate;
import de.tinycodecrank.monads.opt.Opt;

//@formatter:off
/**
 * Implementations of this interface should be final immutable classes with a
 * single BoundedShortBuilder instance used to create instances of the
 * Implementation.
 * </br></br>
 * <b>Example Implementation:</b>
 * 
 * <blockquote>
 * <pre>
 * final class BoundedShortNot0 implements BoundedShort
 * {
 *    private final short value;
 *    
 *    private BoundedShortNot0(short value)
 *    {
 *       this.value = value;
 *    }
 *    
 *    public short value()
 *    {
 *       return value;
 *    }
 *    
 *    public static final BoundedShortBuilder shortBuilder =
 *       new BoundedShortBuilder(
 *          value -> value != 0,
 *          BoundedShortNot0::new,
 *          value -> new BoundsException(
 *             "Expected value not to be 0 but was %d",
 *             value));
 * }
 * </pre>
 * </blockquote>
 * @author tinycodecrank
 */
//@formatter:on
public interface BoundedShort
{
	/**
	 * @return The contained value
	 */
	short value();
	
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
	public static record BoundedShortBuilder<B extends BoundedShort> (
		ShortPredicate validator,
		ShortFunction<B> constructor,
		ShortFunction<BoundsException> exceptionBuilder)
	{
		/**
		 * @param value
		 *            The value to create a bounded type from.
		 * @return A bounded version of the supplied value.
		 * @throws BoundsException
		 *             Throws a BoundsException if an invalid value was supplied.
		 */
		public B create(short value) throws BoundsException
		{
			if (validator.test(value))
				return constructor.apply(value);
			throw exceptionBuilder.apply(value);
		}
		
		/**
		 * @param value
		 *            The value to create a bounded type from.
		 * @return Either an {@link Opt} of bounded value or {@link Opt#empty() if an
		 *         invalid value was supplied.}
		 */
		public Opt<B> createOpt(short value)
		{
			if (validator.test(value))
				return Opt.of(constructor.apply(value));
			return Opt.empty();
		}
	}
}