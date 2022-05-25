package de.tinycodecrank.bounded.presets._byte;

import de.tinycodecrank.bounded.BoundedByte;
import de.tinycodecrank.bounded.BoundsException;

/**
 * Guarantees to contain a byte that is != 0.
 * 
 * @author tinycodecrank
 */
public final class byte_not_zero implements BoundedByte
{
	private final byte value;
	
	private byte_not_zero(byte value)
	{
		this.value = value;
	}
	
	public byte value()
	{
		return value;
	}
	
	private static boolean validate(byte value)
	{
		return value != 0;
	}
	
	private static BoundsException createError(byte value)
	{
		return new BoundsException("Expected value to be != 0");
	}
	
	private static final BoundedByteBuilder<byte_not_zero> builder = new BoundedByteBuilder<>(
		byte_not_zero::validate,
		byte_not_zero::new,
		byte_not_zero::createError);
	
	public static BoundedByteBuilder<byte_not_zero> builder()
	{
		return builder;
	}
}