package com.hssa.ezybiz.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;

import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.springframework.dao.DataAccessException;


public abstract class JCOEPasswordEncoder implements PasswordEncoder {

	/** The encoder. */
	private static JCOEPasswordEncoder encoder = null;

	/** The Constant LOGGER. */
	//private static final Logger LOGGER = Logger.getLogger(FrmJCOEPasswordEncoder.class);

	/**
	 * Gets the encoder.
	 * 
	 * @return the encoder
	 * @throws Exception
	 *             the exception
	 */
	public static JCOEPasswordEncoder getEncoder() throws Exception {

		String className = "com.lafargeholcim.customerportal.util.BlowfishEncoder";
		Class obj = null;

		Constructor constructor = null;
		try {
			if (encoder == null) {

				try {
					obj = Class.forName(className);
				} catch (ClassNotFoundException e) {
					//LOGGER.error("Class " + className + " not found : " + e);
				}

				try {
					if (obj != null) {
						constructor = obj.getConstructor(null);
					}
				} catch (SecurityException e) {
					//LOGGER.error("SecurityException for Class " + className + " : " + e);
				} catch (NoSuchMethodException e) {
				//	LOGGER.error("No Argument constructor not found for Class " + className + " : " + e);
				}
				try {
					if (constructor != null) {
						encoder = (JCOEPasswordEncoder) constructor.newInstance(null);
					}
				} catch (IllegalArgumentException e) {
					//LOGGER.error("IllegalArgumentException for Class " + className + " : " + e);
				} catch (InstantiationException e) {
					//LOGGER.error("InstantiationException for Class " + className + " : " + e);
				} catch (IllegalAccessException e) {
					//LOGGER.error("IllegalAccessException for Class " + className + " : " + e);
				} catch (InvocationTargetException e) {
					//LOGGER.error("InvocationTargetException for Class " + className + " : " + e);
				}
			}
		} catch (Exception ex) {

			//LOGGER.error("FIPassword Encoder Error :" + ex.getMessage());

		}
		return encoder;
	}

	/**
	 * Encode base64.
	 * 
	 * @param byteArr
	 *            the byte arr
	 * @return the string
	 */
	public abstract String encodeBase64(byte[] byteArr);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.acegisecurity.providers.encoding.PasswordEncoder#encodePassword(java
	 * .lang.String, java.lang.Object)
	 */
	@Override
	public abstract String encodePassword(String arg0, Object arg1) throws DataAccessException;

	/**
	 * Encode password.
	 * 
	 * @param password
	 *            the password
	 * @param key
	 *            the key
	 * @return the byte[]
	 */
	public abstract byte[] encodePassword(String password, SecretKey key);

	/**
	 * Gets the key.
	 * 
	 * @param algorithm
	 *            the algorithm
	 * @return the key
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 */
	public abstract SecretKey getKey(String algorithm) throws NoSuchAlgorithmException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.acegisecurity.providers.encoding.PasswordEncoder#isPasswordValid(
	 * java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public abstract boolean isPasswordValid(String arg0, String arg1, Object arg2) ;

}
