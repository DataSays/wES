package org.datasays.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {
	private static final String Algorithm = "DESede";// DES,DESede,Blowfish
	private static final String Algorithm2 = "SHA1";// SHA1,SHA-256,MD5
	private static final Logger LOG = LoggerFactory.getLogger(CryptoUtil.class);

	static {
		//Security.addProvider(new SunJCE());
	}

	private static byte[] getKey() throws Exception {
// KeyGenerator keygen = KeyGenerator.getInstance(Algorithm);
// SecretKey deskey = keygen.generateKey();
// deskey.getEncoded();
		byte[] key = hex2byte("4F9D3497E379AE6E54E004DF15A8DFC27CCD97AE756B9B9D");
		LOG.debug("generate Key:" + byte2hex(key));
		return key;
	}

	public static String encode(String input) {
		try {
			LOG.debug("intput string:" + input);
			SecretKey deskey = new SecretKeySpec(getKey(), Algorithm);
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			byte[] cipherByte = c1.doFinal(input.getBytes("utf-8"));
			LOG.debug("ouput string:" + byte2hex(cipherByte));
			return byte2hex(cipherByte);
		} catch (Exception e) {
			LOG.error("CryptoUtil.encode error!", e);
			return null;
		}
	}

	public static String decode(String input) {
		try {
			LOG.debug("intput string:" + input);
			SecretKey deskey;
			deskey = new SecretKeySpec(getKey(), Algorithm);
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			byte[] clearByte = c1.doFinal(input.getBytes("utf-8"));
			LOG.debug("ouput string:" + byte2hex(clearByte));
			return byte2hex(clearByte);
		} catch (Exception e) {
			LOG.error("CryptoUtil.decode error!", e);
			return null;
		}
	}

	public static String encrypt(String strSrc) {
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance(Algorithm2);
			md.update(bt);
			strDes = byte2hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Invalid algorithm.", e);
			return null;
		}
		return strDes;
	}

	private static String byte2hex(byte bytes[]) {
		StringBuffer retString = new StringBuffer();
		for (int i = 0; i < bytes.length; ++i) {
			retString.append(Integer.toHexString(0x0100 + (bytes[i] & 0x00FF)).substring(1).toUpperCase());
		}
		return retString.toString();
	}

	private static byte[] hex2byte(String hex) {
		byte[] bts = new byte[hex.length() / 2];
		for (int i = 0; i < bts.length; i++) {
			bts[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bts;
	}

	/**
	 * 将一个数字转换成0-9a-zA-z的62进制
	 */
	public static String encodeLongToString(long i) {
		if (i == 0) {
			return "0";
		} else if (i < 0) {
			throw new IllegalArgumentException();
		}
		StringBuffer buffer = new StringBuffer();
		while (i > 0) {
			buffer.insert(0, encodeIntToChar((int) (i % 62)));
			i /= 62;
		}
		return buffer.toString();
	}

	/**
	 * 将0-9a-zA-z的62进制转换成一个数字
	 */
	public static long decodeStringToLong(String s) {
		long i = 0;
		for (int index = 0; index < s.length(); index++) {
			if (index > 0) {
				i *= 62;
			}
			i += decodeCharToInt(s.charAt(index));
		}
		return i;
	}

	/**
	 * 将数字作为顺序转换成单位的0-9a-zA-z<br>
	 * 0 -> '0'<br>
	 * 9 -> '9'<br>
	 * 10 -> 'a'<br>
	 * 35 -> 'z'<br>
	 * 36 -> 'A'<br>
	 * 61 -> 'Z'
	 */
	private static char encodeIntToChar(int i) {
		if (i >= 0 && i <= 9) {
			return (char) (i + 48);
		} else if (i >= 10 && i <= 35) {
			return (char) (i + 87);
		} else if (i >= 36 && i <= 61) {
			return (char) (i + 29);
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 将单位的0-9a-zA-z按顺序转换成数字<br>
	 * '0' -> 0<br>
	 * '9' -> 9<br>
	 * 'a' -> 10<br>
	 * 'z' -> 35<br>
	 * 'A' -> 36<br>
	 * 'Z' -> 61
	 */
	private static int decodeCharToInt(char c) {
		if (c >= '0' && c <= '9') {
			return c - 48;
		} else if (c >= 'a' && c <= 'z') {
			return c - 87;
		} else if (c >= 'A' && c <= 'Z') {
			return c - 29;
		} else {
			throw new IllegalArgumentException();
		}
	}
}
