package kryptonbutterfly.bounded;

import kryptonbutterfly.functions.char_.CharFunction;
import kryptonbutterfly.functions.char_.CharPredicate;
import kryptonbutterfly.monads.opt.Opt;

//@formatter:off
/**
 * Implementations of this interface should be final immutable class with a
 * single BoundedCharBuilder instance used to create instances of the
 * Implementation.
 * </br></br>
 * <b>Example Implementation:</b>
 * 
 * <blockquotes>
 * <pre>
 * final class BoundedCharA implements BoundedChar
 * {
 *    private final char value;
 *    
 *    private BoundedCharA(char value)
 *    {
 *       this.value = value;
 *    }
 *    
 *    public char value()
 *    {
 *       return value;
 *    }
 *    
 *    public static final BoundedCharBuilder charBuilder =
 *       new BoundedCharBuilder(
 *          value -> value == 'A',
 *          BoundedCharA::new,
 *          value -> new BoundsException(
 *             "Expected value to be 'A' but was %s instead",
 *             value));
 * }
 * </pre>
 * </blockquote>
 * @author kryptonbutterfly
 */
//@formatter:on
public interface BoundedChar
{
	/**
	 * @return The contained value.
	 */
	char value();
	
	/**
	 * @author kryptonbutterfly
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
	public static record BoundedCharBuilder<B extends BoundedChar>(
		CharPredicate validator,
		CharFunction<B> constructor,
		CharFunction<BoundsException> exceptionBuilder)
	{
		/**
		 * @param value
		 *            The value to create a bounded type from.
		 * @return A bounded version of the supplied value.
		 * @throws BoundsException
		 *             Throws a BoundsException if an invalid value was supplied.
		 */
		public B create(char value) throws BoundsException
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
		public Opt<B> createOpt(char value)
		{
			if (validator.test(value))
				return Opt.of(constructor.apply(value));
			return Opt.empty();
		}
	}
}