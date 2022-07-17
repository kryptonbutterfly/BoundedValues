package de.tinycodecrank.bounded;

import de.tinycodecrank.functions.float_.FloatFunction;
import de.tinycodecrank.functions.float_.FloatPredicate;
import de.tinycodecrank.monads.opt.Opt;

//@formatter:off
/**
 * Implementations of this interface should be final immutable classes with a
 * single BoundedFloatBuilder instance used to create instances of the
 * Implementation.
 * </br></br>
 * <b>Example Implementation:</b>
 * 
 * <blockquote>
 * <pre>
 * final class BoundedFloatIs5 implements BoundedFloat
 * {
 *    private final float value;
 *    
 *    private BoundedFloatIs5(float value)
 *    {
 *       this.value = value;
 *    }
 *    
 *    public float value()
 *    {
 *       return value;
 *    }
 *    
 *    public static final BoundedFloatBuilder floatBuilder =
 *       new BoundedFloatBuilder(
 *       value -> value = 5F,
 *       BoundedFloatIs5::new,
 *       value -> new BoundsException(
 *          "Expected value to be 5 but was %s instead",
 *          value));
 * }
 * </pre>
 * </blockquote>
 * @author tinycodecrank
 */
//@formatter:on
public interface BoundedFloat
{
	/**
	 * @return The contained value.
	 */
	float value();
	
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
	public static record BoundedFloatBuilder<B extends BoundedFloat> (
		FloatPredicate validator,
		FloatFunction<B> constructor,
		FloatFunction<BoundsException> exceptionBuilder)
	{
		/**
		 * @param value
		 *            The value to create a bounded type from.
		 * @return A bounded version of the supplied value.
		 * @throws BoundsException
		 *             Throws a BoundsException if an invalid value was supplied.
		 */
		public B create(float value) throws BoundsException
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
		public Opt<B> createOpt(float value)
		{
			if (validator.test(value))
				return Opt.of(constructor.apply(value));
			return Opt.empty();
		}
	}
}