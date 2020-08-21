package com.hssa.ezybiz.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Utility for reflection-related tasks.
 *
 */
public final class ReflectionUtil {

	/**
	 * Unsupported constructor.
	 */
	private ReflectionUtil() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Gets the generic parameters from a certain instance
	 *
	 * @param <T>
	 *            Class of the instance.
	 * @return Parameterized types for that instance, in case of exist.
	 */
	public static <T> Type[] getParameterizedTypes(T instance) {
		return ((ParameterizedType) instance.getClass().getGenericSuperclass()).getActualTypeArguments();
	}
}
