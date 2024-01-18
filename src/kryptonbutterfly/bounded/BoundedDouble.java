package kryptonbutterfly.bounded;

import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;

import kryptonbutterfly.monads.opt.Opt;

//@formatter:off
/**
 * Implementations of this interface should be final immutable classes with a
 * single BoundedDoubleBuilder instance used to create instances of the
 * Implementation.
 * </br></br>
 * <b>Example Implementation:</b>
 * 
 * <blockquote>
 * <pre>
 * final class BoundedDoubleIs3 implements BoundedDouble
 * {
 *    private final byte value;
 *    
 *    private BoundedDoubleIs3(double value)
 *    {
 *       this.value = value;
 *    }
 *    
 *    public double value()
 *    {
 *       return value;
 *    }
 *    
 *    public static final BoundedDoubleBuilder doubleBuilder =
 *       new BoundedDoubleBuilder(
 *          value -> value == 3.0,
 *          BoundedDoubleIs3::new,
 *          value -> new BoundsException(
 *             "Excepted value to be 3.0 but was %s instead",
 *             value));
 * }
 * </pre>
 * </blockquote>
 * @author kryptonbutterfly
 */
//@formatter:on
public interface BoundedDouble
{
	/**
	 * @return THe contained value.
	 */
	double value();
	
	/**
	 * @author kryptonbutterfly
	 *
	 * @param <B>
	 *            The Bounding Type
	 * @param validator
	 *            Predicate restricting values.
	 * @param constructor
	 *            THe Constructor to be used to create the bounded Type.
	 * @param exceptionBuilder
	 *            Function to use to build a BoundsException when supplied an
	 *            invalid value.
	 */
	public static record BoundedDoubleBuilder<B extends BoundedDouble>(
		DoublePredicate validator,
		DoubleFunction<B> constructor,
		DoubleFunction<BoundsException> exceptionBuilder)
	{
		/**
		 * @param value
		 *            The value to create a bounded type from.
		 * @return A bounded version of the supplied value.
		 * @throws BoundsException
		 *             Throws a BoundsException if an invalid value was supplied.
		 */
		public B create(double value) throws BoundsException
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
		public Opt<B> createOpt(double value)
		{
			if (validator.test(value))
				return Opt.of(constructor.apply(value));
			return Opt.empty();
		}
	}
}