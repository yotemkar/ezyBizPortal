package com.hssa.ezybiz.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class TypeUtil {
	private static final Log						LOG				= LogFactory.getLog(TypeUtil.class);

	/* ------------------------------------------------------------ */
	private static final HashMap<Object, Object>	NAME_2_CLASS	= new HashMap<Object, Object>();
	static {
		NAME_2_CLASS.put("boolean", java.lang.Boolean.TYPE);
		NAME_2_CLASS.put("byte", java.lang.Byte.TYPE);
		NAME_2_CLASS.put("char", java.lang.Character.TYPE);
		NAME_2_CLASS.put("double", java.lang.Double.TYPE);
		NAME_2_CLASS.put("float", java.lang.Float.TYPE);
		NAME_2_CLASS.put("int", java.lang.Integer.TYPE);
		NAME_2_CLASS.put("long", java.lang.Long.TYPE);
		NAME_2_CLASS.put("short", java.lang.Short.TYPE);
		NAME_2_CLASS.put("void", java.lang.Void.TYPE);

		NAME_2_CLASS.put("java.lang.Boolean.TYPE", java.lang.Boolean.TYPE);
		NAME_2_CLASS.put("java.lang.Byte.TYPE", java.lang.Byte.TYPE);
		NAME_2_CLASS.put("java.lang.Character.TYPE", java.lang.Character.TYPE);
		NAME_2_CLASS.put("java.lang.Double.TYPE", java.lang.Double.TYPE);
		NAME_2_CLASS.put("java.lang.Float.TYPE", java.lang.Float.TYPE);
		NAME_2_CLASS.put("java.lang.Integer.TYPE", java.lang.Integer.TYPE);
		NAME_2_CLASS.put("java.lang.Long.TYPE", java.lang.Long.TYPE);
		NAME_2_CLASS.put("java.lang.Short.TYPE", java.lang.Short.TYPE);
		NAME_2_CLASS.put("java.lang.Void.TYPE", java.lang.Void.TYPE);

		NAME_2_CLASS.put("java.lang.Boolean", java.lang.Boolean.class);
		NAME_2_CLASS.put("java.lang.Byte", java.lang.Byte.class);
		NAME_2_CLASS.put("java.lang.Character", java.lang.Character.class);
		NAME_2_CLASS.put("java.lang.Double", java.lang.Double.class);
		NAME_2_CLASS.put("java.lang.Float", java.lang.Float.class);
		NAME_2_CLASS.put("java.lang.Integer", java.lang.Integer.class);
		NAME_2_CLASS.put("java.lang.Long", java.lang.Long.class);
		NAME_2_CLASS.put("java.lang.Short", java.lang.Short.class);

		NAME_2_CLASS.put("Boolean", java.lang.Boolean.class);
		NAME_2_CLASS.put("Byte", java.lang.Byte.class);
		NAME_2_CLASS.put("Character", java.lang.Character.class);
		NAME_2_CLASS.put("Double", java.lang.Double.class);
		NAME_2_CLASS.put("Float", java.lang.Float.class);
		NAME_2_CLASS.put("Integer", java.lang.Integer.class);
		NAME_2_CLASS.put("Long", java.lang.Long.class);
		NAME_2_CLASS.put("Short", java.lang.Short.class);

		NAME_2_CLASS.put(null, java.lang.Void.TYPE);
		NAME_2_CLASS.put("string", java.lang.String.class);
		NAME_2_CLASS.put("String", java.lang.String.class);
		NAME_2_CLASS.put("java.lang.String", java.lang.String.class);
	}

	/* ------------------------------------------------------------ */
	private static final HashMap<Object, Object>	CLASS_2_NAME	= new HashMap<Object, Object>();
	static {
		CLASS_2_NAME.put(java.lang.Boolean.TYPE, "boolean");
		CLASS_2_NAME.put(java.lang.Byte.TYPE, "byte");
		CLASS_2_NAME.put(java.lang.Character.TYPE, "char");
		CLASS_2_NAME.put(java.lang.Double.TYPE, "double");
		CLASS_2_NAME.put(java.lang.Float.TYPE, "float");
		CLASS_2_NAME.put(java.lang.Integer.TYPE, "int");
		CLASS_2_NAME.put(java.lang.Long.TYPE, "long");
		CLASS_2_NAME.put(java.lang.Short.TYPE, "short");
		CLASS_2_NAME.put(java.lang.Void.TYPE, "void");

		CLASS_2_NAME.put(java.lang.Boolean.class, "java.lang.Boolean");
		CLASS_2_NAME.put(java.lang.Byte.class, "java.lang.Byte");
		CLASS_2_NAME.put(java.lang.Character.class, "java.lang.Character");
		CLASS_2_NAME.put(java.lang.Double.class, "java.lang.Double");
		CLASS_2_NAME.put(java.lang.Float.class, "java.lang.Float");
		CLASS_2_NAME.put(java.lang.Integer.class, "java.lang.Integer");
		CLASS_2_NAME.put(java.lang.Long.class, "java.lang.Long");
		CLASS_2_NAME.put(java.lang.Short.class, "java.lang.Short");

		CLASS_2_NAME.put(null, "void");
		NAME_2_CLASS.put(java.lang.String.class, "java.lang.String");
	}

	/* ------------------------------------------------------------ */
	private static final HashMap<Object, Object>	CLASS_2_VALUE	= new HashMap<Object, Object>();
	static {
		try {
			final Class[] s = { java.lang.String.class };

			CLASS_2_VALUE.put(java.lang.Boolean.TYPE, java.lang.Boolean.class.getMethod("valueOf", s));
			CLASS_2_VALUE.put(java.lang.Byte.TYPE, java.lang.Byte.class.getMethod("valueOf", s));
			CLASS_2_VALUE.put(java.lang.Double.TYPE, java.lang.Double.class.getMethod("valueOf", s));
			CLASS_2_VALUE.put(java.lang.Float.TYPE, java.lang.Float.class.getMethod("valueOf", s));
			CLASS_2_VALUE.put(java.lang.Integer.TYPE, java.lang.Integer.class.getMethod("valueOf", s));
			CLASS_2_VALUE.put(java.lang.Long.TYPE, java.lang.Long.class.getMethod("valueOf", s));
			CLASS_2_VALUE.put(java.lang.Short.TYPE, java.lang.Short.class.getMethod("valueOf", s));

			CLASS_2_VALUE.put(java.lang.Boolean.class, java.lang.Boolean.class.getMethod("valueOf", s));
			CLASS_2_VALUE.put(java.lang.Byte.class, java.lang.Byte.class.getMethod("valueOf", s));
			CLASS_2_VALUE.put(java.lang.Double.class, java.lang.Double.class.getMethod("valueOf", s));
			CLASS_2_VALUE.put(java.lang.Float.class, java.lang.Float.class.getMethod("valueOf", s));
			CLASS_2_VALUE.put(java.lang.Integer.class, java.lang.Integer.class.getMethod("valueOf", s));
			CLASS_2_VALUE.put(java.lang.Long.class, java.lang.Long.class.getMethod("valueOf", s));
			CLASS_2_VALUE.put(java.lang.Short.class, java.lang.Short.class.getMethod("valueOf", s));
		}
		catch (final Exception e) {
			//LOG.warn(LogSupport.EXCEPTION, e);
		}
	}

	/* ------------------------------------------------------------ */
	private static Class[]							stringArg		= { java.lang.String.class };

	/* ------------------------------------------------------------ */
	private static int								intCacheSize	= Integer.getInteger("org.mortbay.util.TypeUtil.IntegerCacheSize", 600).intValue();
	private static Integer[]						integerCache	= new Integer[intCacheSize];
	private static String[]							integerStrCache	= new String[intCacheSize];
	private static Integer							minusOne		= Integer.valueOf(-1);

	/* ------------------------------------------------------------ */
	/**
	 * @param b
	 *            An ASCII encoded character 0-9 a-f A-F
	 * @return The byte value of the character 0-16.
	 */
	public static byte convertHexDigit(final byte b) {
		if ((b >= '0') && (b <= '9')) {
			return (byte) (b - '0');
		}
		if ((b >= 'a') && (b <= 'f')) {
			return (byte) (b - 'a' + 10);
		}
		if ((b >= 'A') && (b <= 'F')) {
			return (byte) (b - 'A' + 10);
		}
		return 0;
	}

	public static void dump(final Class c) {

		LOG.error("Dump: " + c);

		dump(c.getClassLoader());
	}

	public static void dump(ClassLoader cl) { // NOPMD by Channel-01 on 2/2/10 10:38 AM

		LOG.error("Dump Loaders:");

		while (cl != null) {

			LOG.error("  loader " + cl);
			cl = cl.getParent();
		}
	}

	/* ------------------------------------------------------------ */
	public static byte[] fromHexString(final String s) {
		if (s.length() % 2 != 0) {
			throw new IllegalArgumentException(s);
		}
		final byte[] array = new byte[s.length() / 2];
		for (int i = 0; i < array.length; i++) {
			final int b = Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
			array[i] = (byte) (0xff & b);
		}
		return array;
	}

	/* ------------------------------------------------------------ */
	/**
	 * Class from a canonical name for a type.
	 * 
	 * @param name
	 *            A class or type name.
	 * @return A class , which may be a primitive TYPE field..
	 */
	public static Class fromName(final String name) {
		return (Class) NAME_2_CLASS.get(name);
	}

	/* ------------------------------------------------------------ */
	/**
	 * Convert int to Integer using cache.
	 */
	public static Integer newInteger(final int i) {
		if ((i >= 0) && (i < intCacheSize)) {
			if (integerCache[i] == null) {
				integerCache[i] = Integer.valueOf(i);
			}
			return integerCache[i];
		}
		else if (i == -1) {
			return minusOne;
		}
		return Integer.valueOf(i);
	}

	/* ------------------------------------------------------------ */
	public static byte[] parseBytes(final String s, final int base) {
		final byte[] bytes = new byte[s.length() / 2];
		for (int i = 0; i < s.length(); i += 2) {
			bytes[i / 2] = (byte) TypeUtil.parseInt(s, i, 2, base);
		}
		return bytes;
	}

	/* ------------------------------------------------------------ */
	/**
	 * Parse an int from a substring. Negative numbers are not handled.
	 * 
	 * @param s
	 *            String
	 * @param offset
	 *            Offset within string
	 * @param length
	 *            Length of integer or -1 for remainder of string
	 * @param base
	 *            base of the integer
	 * @exception NumberFormatException
	 */
	public static int parseInt(final String s, final int offset, int length, final int base) throws NumberFormatException { // NOPMD by Channel-01 on 2/2/10 10:38 AM
		int value = 0;

		if (length < 0) {
			length = s.length() - offset;
		}

		for (int i = 0; i < length; i++) {
			final char c = s.charAt(offset + i);

			int digit = c - '0';
			if ((digit < 0) || (digit >= base) || (digit >= 10)) {
				digit = 10 + c - 'A';
				if ((digit < 10) || (digit >= base)) {
					digit = 10 + c - 'a';
				}
			}
			if ((digit < 0) || (digit >= base)) {
				throw new NumberFormatException(s.substring(offset, offset + length));
			}
			value = value * base + digit;
		}
		return value;
	}

	/* ------------------------------------------------------------ */
	public static char toHexChar(final int b) {
		return (char) (b < 10 ? ('0' + b) : ('A' + b - 10));
	}

	/* ------------------------------------------------------------ */
	public static String toHexString(final byte[] b) {
		final StringBuffer buf = new StringBuffer();
		for (final byte element : b) {
			final int bi = 0xff & element;
			int c = '0' + (bi / 16) % 16;
			if (c > '9') {
				c = 'A' + (c - '0' - 10);
			}
			buf.append((char) c);
			c = '0' + bi % 16;
			if (c > '9') {
				c = 'a' + (c - '0' - 10);
			}
			buf.append((char) c);
		}
		return buf.toString();
	}

	/* ------------------------------------------------------------ */
	public static String toHexString(final byte[] b, final int offset, final int length) {
		final StringBuffer buf = new StringBuffer();
		for (int i = offset; i < offset + length; i++) {
			final int bi = 0xff & b[i];
			int c = '0' + (bi / 16) % 16;
			if (c > '9') {
				c = 'A' + (c - '0' - 10);
			}
			buf.append((char) c);
			c = '0' + bi % 16;
			if (c > '9') {
				c = 'a' + (c - '0' - 10);
			}
			buf.append((char) c);
		}
		return buf.toString();
	}

	/* ------------------------------------------------------------ */
	/**
	 * Canonical name for a type.
	 * 
	 * @param type
	 *            A class , which may be a primitive TYPE field.
	 * @return Canonical name.
	 */
	public static String toName(final Class type) {
		return (String) CLASS_2_NAME.get(type);
	}

	/* ------------------------------------------------------------ */
	public static String toString(final byte[] bytes, final int base) {
		final StringBuffer buf = new StringBuffer();
		for (final byte b : bytes) {
			final int bi = 0xff & b;
			int c = '0' + (bi / base) % base;
			if (c > '9') {
				c = 'a' + (c - '0' - 10);
			}
			buf.append((char) c);
			c = '0' + bi % base;
			if (c > '9') {
				c = 'a' + (c - '0' - 10);
			}
			buf.append((char) c);
		}
		return buf.toString();
	}

	/* ------------------------------------------------------------ */
	/**
	 * Convert int to String using cache.
	 */
	public static String toString(final int i) {
		if ((i >= 0) && (i < intCacheSize)) {
			if (integerStrCache[i] == null) {
				integerStrCache[i] = Integer.toString(i);
			}
			return integerStrCache[i];
		}
		else if (i == -1) {
			return "-1";
		}
		return Integer.toString(i);
	}

	/* ------------------------------------------------------------ */
	/**
	 * Convert String value to instance.
	 * 
	 * @param type
	 *            The class of the instance, which may be a primitive TYPE
	 *            field.
	 * @param value
	 *            The value as a string.
	 * @return The value as an Object.
	 */
	public static Object valueOf(final Class type, final String value) {
		try {
			if (type.equals(java.lang.String.class)) {
				return value;
			}

			final Method m = (Method) CLASS_2_VALUE.get(type);
			if (m != null) {
				return m.invoke(null, new Object[] { value });
			}

			if (type.equals(java.lang.Character.TYPE) || type.equals(java.lang.Character.class)) {
				return new Character(value.charAt(0));
			}

			final Constructor c = type.getConstructor(stringArg);
			return c.newInstance(new Object[] { value });
		}
		catch (final NoSuchMethodException e) {
			//LogSupport.ignore(LOG, e);
		}
		catch (final IllegalAccessException e) {
		//	LogSupport.ignore(LOG, e);
		}
		catch (final InstantiationException e) {
			//LogSupport.ignore(LOG, e);
		}
		catch (final InvocationTargetException e) {
			if (e.getTargetException() instanceof Error) {
				throw (Error) (e.getTargetException());
			}
			//LogSupport.ignore(LOG, e);
		}
		return null;
	}

	/* ------------------------------------------------------------ */
	/**
	 * Convert String value to instance.
	 * 
	 * @param type
	 *            classname or type (eg int)
	 * @param value
	 *            The value as a string.
	 * @return The value as an Object.
	 */
	public static Object valueOf(final String type, final String value) {
		return valueOf(fromName(type), value);
	}

}

