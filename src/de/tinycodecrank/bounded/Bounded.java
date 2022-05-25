package de.tinycodecrank.bounded;

import java.util.function.Function;
import java.util.function.Predicate;

import de.tinycodecrank.monads.Opt;

//@formatter:off
/**
 * Implementations of this interface should be final immutable classes with a
 * single BoundedBuilder instance used to create instances of the
 * Implementation.
 * </br></br>
 * <b>Example Implementation:</b>
 * 
 * <blockquote>
 * <pre>
 * final class BoundedHelloWorld implements Bounded<String>
 * {
 *    private final String value;
 *    
 *    private BoundedHelloWorld(String value)
 *    {
 *       this.value = value;
 *    }
 *    
 *    public String value()
 *    {
 *       return value;
 *    }
 *    
 *    public static final BoundedBuilder<String, BoundedHelloWorld> helloWorldBuilder =
 *       new BoundedBuilder<>(
 *          "Hello, World!"::equals,
 *          BoundedHello::new,
 *          value -> new BoundsException(
 *             "Expected \"Hello, World!\" but got %s instead",
 *             value));
 * }
 * </pre>
 * </blockquote>
 * 
 * @author tinycodecrank
 *
 * @param <O>
 *            The Type of the bounded value.
 */
//@formatter:on
public interface Bounded<O>
{
	/**
	 * @return The contained value.
	 */
	O value();
	
	/**
	 * @author tinycodecrank
	 *
	 * @param <O>
	 *            The Type of the bounded value.
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
	public static record BoundedBuilder<O, B extends Bounded<O>> (
		Predicate<O> validator,
		Function<O, B> constructor,
		Function<O, BoundsException> exceptionBuilder)
	{
		/**
		 * @param value
		 *            The value to create a bounded type from.
		 * @return A bounded version of the supplied value.
		 * @throws BoundsException
		 *             Throws a BoundsException if an invalid value was supplied.
		 */
		public B create(O value) throws BoundsException
		{
			if (validator.test(value))
				return constructor.apply(value);
			throw exceptionBuilder.apply(value);
		}
		
		/**
		 * @param value
		 *            The value to create a bounded type from.
		 * @return Either an {@link Opt} of the bounded value or {@link Opt#empty()} if
		 *         an invalid value was supplied.
		 */
		public Opt<B> createOpt(O value)
		{
			if (validator.test(value))
				return Opt.of(constructor.apply(value));
			return Opt.empty();
		}
	}
}