package com.hssa.ezybiz.utils;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Password extends Credential {

	public static final String __OBFUSCATE = "OBF:";

	/* ------------------------------------------------------------ */
	public static String deobfuscate(String s) { // NOPMD by Channel-01 on 2/2/10 11:02 AM
		if (s.startsWith(__OBFUSCATE)) {
			s = s.substring(4);
		}

		final byte[] b = new byte[s.length() / 2];
		int l = 0;
		for (int i = 0; i < s.length(); i += 4) {
			final String x = s.substring(i, i + 4);
			final int i0 = Integer.parseInt(x, 36);
			final int i1 = (i0 / 256);
			final int i2 = (i0 % 256);
			b[l++] = (byte) ((i1 + i2 - 254) / 2);
		}

		return new String(b, 0, l);
	}

	/* ------------------------------------------------------------ */
	/**
	 * Get a password. A password is obtained by trying
	 * <UL>
	 * <LI>Calling <Code>System.getProperty(realm,dft)</Code>
	 * <LI>Prompting for a password
	 * <LI>Using promptDft if nothing was entered.
	 * </UL>
	 * 
	 * @param realm
	 *            The realm name for the password, used as a SystemProperty name.
	 * @param dft
	 *            The default password.
	 * @param promptDft
	 *            The default to use if prompting for the password.
	 * @return Password
	 */
	public static Password getPassword(final String realm, final String dft, final String promptDft) {
		String passwd = System.getProperty(realm, dft);
		if ((passwd == null) || (passwd.length() == 0)) {
			try {
				final byte[] buf = new byte[512];
				final int len = System.in.read(buf);
				if (len > 0) {
					passwd = new String(buf, 0, len).trim();
				}
			} catch (final IOException e) {
				LOG.warn("IOException:", e);
			}
			if ((passwd == null) || (passwd.length() == 0)) {
				passwd = promptDft;
			}
		}
		return new Password(passwd);
	}

	/* ------------------------------------------------------------ */
	public static String obfuscate(final String s) {
		final StringBuffer buf = new StringBuffer();
		final byte[] b = s.getBytes();

		synchronized (buf) {
			buf.append(__OBFUSCATE);
			for (int i = 0; i < b.length; i++) {
				final byte b1 = b[i];
				final byte b2 = b[s.length() - (i + 1)];
				final int i1 = 127 + b1 + b2;
				final int i2 = 127 + b1 - b2;
				final int i0 = i1 * 256 + i2;
				final String x = Integer.toString(i0, 36);

				switch (x.length()) {
				case 1:
					buf.append('0');
				case 2:
					buf.append('0');
				case 3:
					buf.append('0');
				default:
					buf.append(x);
				}
			}
			return buf.toString();
		}
	}

	private String pwd;

	protected static final Log LOG = LogFactory.getLog(Password.class);

	/* ------------------------------------------------------------ */
	/**
	 * Constructor.
	 * 
	 * @param password
	 *            The String password.
	 */
	public Password(final String password) {
		this.pwd = password;

		// expand password
		while ((this.pwd != null) && this.pwd.startsWith(__OBFUSCATE)) {
			this.pwd = deobfuscate(this.pwd);
		}
	}

	/* ------------------------------------------------------------ */
	@Override
	public boolean check(final Object credentials) {

		if (this == credentials) {
			return true;
		}

		if (credentials instanceof Password) {
			return credentials.equals(this.pwd);
		}

		if (credentials instanceof String) {
			return credentials.equals(this.pwd);
		}

		if (credentials instanceof Credential) {
			return ((Credential) credentials).check(this.pwd);
		}

		return false;
	}

	/* ------------------------------------------------------------ */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}

		if (null == o) {
			return false;
		}

		if (o instanceof Password) {
			final Password p = (Password) o;
			return (p.pwd == this.pwd) || ((null != this.pwd) && this.pwd.equals(p.pwd));
		}

		if (o instanceof String) {
			// String pwd = (String) o;
			return this.pwd.equals(o);
		}

		return false;
	}

	/* ------------------------------------------------------------ */
	@Override
	public int hashCode() {
		return null == this.pwd ? super.hashCode() : this.pwd.hashCode();
	}

	/* ------------------------------------------------------------ */
	public String toStarString() {
		return "*****************************************************".substring(0, this.pwd.length());
	}

	/* ------------------------------------------------------------ */
	@Override
	public String toString() {
		return this.pwd;
	}
}
