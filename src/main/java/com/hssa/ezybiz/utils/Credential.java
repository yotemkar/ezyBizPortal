package com.hssa.ezybiz.utils;

import java.security.MessageDigest;

import org.apache.commons.codec.digest.UnixCrypt;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/* ------------------------------------------------------------ */
/**
 * Credentials. The Credential class represents an abstract mechanism for
 * checking authentication credentials. A credential instance either represents
 * a secret, or some data that could only be derived from knowing the secret.
 * <p>
 * Often a Credential is related to a Password via a one way algorithm, so while a Password itself is a Credential, a UnixCrypt or MD5 digest of a a password is only a credential that can be checked
 * against the password.
 * <p>
 * This class includes an implementation for unix Crypt an MD5 digest.
 * 
 * @see Password
 * @author Greg Wilkins (gregw)
 */
public abstract class Credential {
	/* ------------------------------------------------------------ */
	/**
	 * Unix Crypt Credentials
	 */
	public static class Crypt extends Credential {
		public static final String	__TYPE	= "CRYPT:";

		public static String crypt(final String user, final String pw) {
			return "CRYPT:" + UnixCrypt.crypt(pw, user);
		}

		private final String	_cooked;

		Crypt(final String cooked) {
			this._cooked = cooked.startsWith(Crypt.__TYPE) ? cooked.substring(__TYPE.length()) : cooked;

		}

		@Override
		public boolean check(final Object credentials) {
			if (!(credentials instanceof String) && !(credentials instanceof Password)) {
				LOG.warn("Can't check " + credentials.getClass() + " against CRYPT");
			}

			final String passwd = credentials.toString();

			return this._cooked.equals(UnixCrypt.crypt(passwd, this._cooked));
		}
	}

	/* ------------------------------------------------------------ */
	/**
	 * MD5 Credentials
	 */
	public static class MD5 extends Credential {
		public static final String		__TYPE		= "MD5:";
		public static final Object		__MD5LOCK	= new Object();
		private static MessageDigest	md;

		/* ------------------------------------------------------------ */
		public static String digest(final String password) {
			try {
				byte[] digest;
				synchronized (__MD5LOCK) {
					if (md == null) {
						try {
							md = MessageDigest.getInstance("MD5");
						}
						catch (final Exception e) {
							LOG.warn(e);
							return null;
						}
					}

					md.reset();
					md.update(password.getBytes(StringUtil.__ISO_8859_1));
					digest = md.digest();
				}

				return __TYPE + TypeUtil.toString(digest, 16);
			}
			catch (final Exception e) {
				LOG.warn(e);
				return null;
			}
		}

		private final byte[]	_digest;

		/* ------------------------------------------------------------ */
		MD5(String digest) { // NOPMD by Channel-01 on 2/2/10 10:59 AM
			digest = digest.startsWith(__TYPE) ? digest.substring(__TYPE.length()) : digest;
			this._digest = TypeUtil.parseBytes(digest, 16);
		}

		/* ------------------------------------------------------------ */
		@Override
		public boolean check(final Object credentials) {
			try {
				byte[] digest = null;

				if ((credentials instanceof Password) || (credentials instanceof String)) {
					synchronized (__MD5LOCK) {
						if (md == null) {
							md = MessageDigest.getInstance("MD5");
						}
						md.reset();
						md.update(credentials.toString().getBytes(StringUtil.__ISO_8859_1));
						digest = md.digest();
					}
					if ((digest == null) || (digest.length != this._digest.length)) {
						return false;
					}
					for (int i = 0; i < digest.length; i++) {
						if (digest[i] != this._digest[i]) {
							return false;
						}
					}
					return true;
				}
				else if (credentials instanceof MD5) {
					final MD5 md5 = (MD5) credentials;
					if (this._digest.length != md5._digest.length) {
						return false;
					}
					for (int i = 0; i < this._digest.length; i++) {
						if (this._digest[i] != md5._digest[i]) {
							return false;
						}
					}
					return true;
				}
				else if (credentials instanceof Credential) {
					// Allow credential to attempt check - i.e. this'll work
					// for DigestAuthenticator$Digest credentials
					return ((Credential) credentials).check(this);
				}
				else {
					LOG.warn("Can't check " + credentials.getClass() + " against MD5");
					return false;
				}
			}
			catch (final Exception e) {
				LOG.warn(e);
				return false;
			}
		}

		/* ------------------------------------------------------------ */
		public byte[] getDigest() {
			return this._digest;
		}
	}

	/* ------------------------------------------------------------ */
	/**
	 * SHA Credentials
	 */
	public static class SHA1 extends Credential {
		public static final String		__TYPE		= "SHA1:";
		public static final Object		__SHA1LOCK	= new Object();
		private static MessageDigest	sha;

		/* ------------------------------------------------------------ */
		public static String digest(final String user, final String password) {
			try {
				byte[] digest;
				synchronized (__SHA1LOCK) {
					if (sha == null) {
						try {
							sha = MessageDigest.getInstance("SHA-1");
						}
						catch (final Exception e) {
							LOG.warn(e);
							return null;
						}
					}

					sha.reset();
					sha.update(password.getBytes(StringUtil.__ISO_8859_1));
					digest = sha.digest();
				}

				return __TYPE + UnixCrypt.crypt(TypeUtil.toString(digest, 16), user);
			}
			catch (final Exception e) {
				LOG.warn(e);
				return null;
			}
		}

		private final String	_cooked;

		/* ------------------------------------------------------------ */
		SHA1(final String cooked) {
			this._cooked = cooked.startsWith(__TYPE) ? cooked.substring(__TYPE.length()) : cooked;

		}

		/* ------------------------------------------------------------ */
		@Override
		public boolean check(final Object credentials) {
			try {
				byte[] digest = null;

				if ((credentials instanceof Password) || (credentials instanceof String)) {
					synchronized (__SHA1LOCK) {
						if (sha == null) {
							sha = MessageDigest.getInstance("SHA-1");
						}
						sha.reset();
						sha.update(credentials.toString().getBytes(StringUtil.__ISO_8859_1));
						digest = sha.digest();
					}

					return this._cooked.equals(UnixCrypt.crypt(TypeUtil.toString(digest, 16), this._cooked));
				}
				else if (credentials instanceof SHA1) {
					final SHA1 sha1 = (SHA1) credentials;
					return this._cooked.equals(sha1._cooked);
				}
				else if (credentials instanceof Credential) {
					// Allow credential to attempt check - i.e. this'll work
					// for DigestAuthenticator$Digest credentials
					return ((Credential) credentials).check(this);
				}
				else {
					LOG.warn("Can't check " + credentials.getClass() + " against SHA1");
					return false;
				}
			}
			catch (final Exception e) {
				LOG.warn(e);
				return false;
			}
		}
	}

	protected static final Log	LOG	= LogFactory.getLog(Credential.class);

	/* ------------------------------------------------------------ */
	/**
	 * Get a credential from a String. If the credential String starts with a
	 * known Credential type (eg "CRYPT:" or "MD5:" ) then a Credential of that
	 * type is returned. Else the credential is assumed to be a Password.
	 * 
	 * @param credential
	 *            String representation of the credential
	 * @return A Credential or Password instance.
	 */
	public static Credential getCredential(final String credential) {
		if (credential.startsWith(Crypt.__TYPE)) {
			return new Crypt(credential);
		}
		if (credential.startsWith(MD5.__TYPE)) {
			return new MD5(credential);
		}
		if (credential.startsWith(SHA1.__TYPE)) {
			return new SHA1(credential);
		}

		return new Password(credential);
	}

	/* ------------------------------------------------------------ */
	/**
	 * Check a credential
	 * 
	 * @param credentials
	 *            The credential to check against. This may either be another
	 *            Credential object, a Password object or a String which is
	 *            interpreted by this credential.
	 * @return True if the credentials indicated that the shared secret is known
	 *         to both this Credential and the passed credential.
	 */
	public abstract boolean check(Object credentials);
}

