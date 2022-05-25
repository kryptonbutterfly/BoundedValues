package de.tinycodecrank.bounded;

import java.util.function.IntFunction;
import java.util.function.IntPredicate;

import de.tinycodecrank.monads.Opt;
import de.tinycodecrank.monads.OptInt;

//formatter:off
/**
 * Implementations of this interface should be final immutable classes with a
 * singe BoundedIntBuilder instance used to create instances of the
 * Implementation. </br>
 * </br>
 * <b>Example Implementation:</b>
 * 
 * <blockquote>
 * 
 * <pre>
 * final class BoundedIntNot0 implements BoundedInt
 * {
 * 	private final int value;
 * 	
 * 	private BoundedIntNot0(int value)
 * 	{
 * 		this.value = value;
 * 	}
 * 	
 * 	public int value()
 * 	{
 * 		return value;
 * 	}
 * 	
 * 	public static final BoundedIntBuilder intBuilder = new BoundedIntBuilder(
 * 		value -> value != 0,
 * 		BoundedIntNot0::new,
 * 		value -> new BoundsException(
 * 			"Expected value to be not 0 but was %d instead",
 * 			value));
 * }
 * </pre>
 * 
 * </blockquote>
 * 
 * @author tinycodecrank
 */
// formatter:on
public interface BoundedInt
{
	/**
	 * @return The contained value.
	 */
	int value();
	
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
	public static record BoundedIntBuilder<B extends BoundedInt> (
		IntPredicate validator,
		IntFunction<B> constructor,
		IntFunction<BoundsException> exceptionBuilder)
	{
		/**
		 * @param value
		 *            The value to create a bounded type from.
		 * @return A bounded version of the supplied value.
		 * @throws BoundsException
		 *             Throws a BoundsException if an invalid value was supplied.
		 */
		public B create(int value) throws BoundsException
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
		public Opt<B> createOpt(int value)
		{
			return OptInt.of(value)
				.filter(validator)
				.map(constructor);
		}
	}
}