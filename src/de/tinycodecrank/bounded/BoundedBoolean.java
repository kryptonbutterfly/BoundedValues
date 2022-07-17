package de.tinycodecrank.bounded;

import de.tinycodecrank.functions.bool_.BoolFunction;
import de.tinycodecrank.functions.bool_.BoolUnaryOperator;
import de.tinycodecrank.monads.opt.Opt;

//@formatter:off
/**
 * <div style="color:#faa">
 * 	If you think you need this specific interface reevaluate your design decisions, because more likely than not you don't!
 * </div>
 * </br>
 * 
 * Implementations of this interface should be final immutable classes with a
 * single BoundedBooleanBuilder instance used to create instances of the
 * Implementation.
 * </br></br>
 * <b>Example Implementation:</br>
 * 
 * <blockquote>
 * <pre>
 * final class BoundedTrue implements BoundedBoolean
 * {
 *    private final boolean value;
 *    
 *    private BoundedTrue(boolean value)
 *    {
 *       this.value = value;
 *    }
 *    
 *    public boolean value()
 *    {
 *       return value;
 *    }
 *    
 *    public static final BoundedBooleanBuilder trueBuilder =
 *       new BoundedBooleanBuilder(
 *          value -> value,
 *          BoundedTrue::new,
 *          value -> new BoundsException(
 *             "Expected \"true\" but got %s instead",
 *             value));
 * }
 * </pre>
 * </blockquote>
 * 
 * @author tinycodecrank
 */
//@formatter:on
public interface BoundedBoolean
{
	/**
	 * @return The contained value.
	 */
	boolean value();
	
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
	public static record BoundedBooleanBuilder<B extends BoundedBoolean> (
		BoolUnaryOperator validator,
		BoolFunction<B> constructor,
		BoolFunction<BoundsException> exceptionBuilder)
	{
		/**
		 * @param value
		 *            The value to create a bounded type from.
		 * @return A bounded version of the supplied value.
		 * @throws BoundsException
		 *             Throws a BoundsException if an invalid value was supplied.
		 */
		public B create(boolean value) throws BoundsException
		{
			if (validator.apply(value))
				return constructor.apply(value);
			throw exceptionBuilder.apply(value);
		}
		
		/**
		 * @param value
		 *            The value to create a bounded type from.
		 * @return Either an {@link Opt} of the bounded value or {@link Opt#empty()} if
		 *         an invalid value was supplied.
		 */
		public Opt<B> createOpt(boolean value)
		{
			if (validator.apply(value))
				return Opt.of(constructor.apply(value));
			return Opt.empty();
		}
	}
}