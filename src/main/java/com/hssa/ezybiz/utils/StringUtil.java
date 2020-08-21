package com.hssa.ezybiz.utils;

public class StringUtil {
	public static final String	__LINE_SEPARATOR	= System.getProperty("line.separator", "\n");

	public final static String	__ISO_8859_1;
	static {
		String iso = System.getProperty("ISO_8859_1");
		if (iso != null) {
			__ISO_8859_1 = iso;
		}
		else {
			try {
				new String(new byte[] { (byte) 20 }, "ISO-8859-1");
				iso = "ISO-8859-1";
			}
			catch (final java.io.UnsupportedEncodingException e) {
				iso = "ISO8859_1";
			}
			__ISO_8859_1 = iso;
		}
	}

	public final static String	__UTF_8				= "UTF-8";

}
