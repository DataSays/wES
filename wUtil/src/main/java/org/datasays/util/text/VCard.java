package org.datasays.util.text;

import java.io.ByteArrayOutputStream;

public class VCard {
	public String name;
	public String telephone;
	public String address;

	public VCard() {
	}

	public VCard(String name, String telephone) {
		super();
		this.name = name;
		this.telephone = telephone;
	}

	public String toText() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("BEGIN:VCARD\n");
		sb.append("VERSION:3.0\n");
		if (name != null && name.trim().length() > 0) {
			sb.append("FN:" + name + "\n");
			sb.append("N:" + name + ";;;\n");
		}
		if (telephone != null && telephone.trim().length() > 0) {
			sb.append("TEL;TYPE=CELL:" + telephone + "\n");
		}
		if (address != null && address.trim().length() > 0) {
			sb.append("ADR;TYPE=HOME:;;" + address + ";;;;\n");
		}
		sb.append("END:VCARD\n");
		return sb.toString();
	}

	/**
	 * 编码 UTF8 quoted-printable
	 *
	 * @param str 需要编码的字符串
	 * @return 编码后的字符串
	 * @throws Exception
	 */
	public static String qpEncodeingUTF8(String str) throws Exception {
		if (str != null) {
			char[] encode = str.toCharArray();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < encode.length; i++) {
				if ((encode[i] >= '!') && (encode[i] <= '~')
						&& (encode[i] != '=') && (encode[i] != '\n')) {
					sb.append(encode[i]);
				} else if (encode[i] == '=') {
					sb.append("=3D");
				} else if (encode[i] == '\n') {
					sb.append("\n");
				} else {
					StringBuffer sbother = new StringBuffer();
					sbother.append(encode[i]);
					String ss = sbother.toString();
					byte[] buf = null;
					buf = ss.getBytes("utf-8");
					// UTF-8: buf.length == 3
					// GBK: buf.length == 2
					if (buf.length == 3) {
						for (int j = 0; j < buf.length; j++) {
							String s16 = String.valueOf(Integer.toHexString(buf[j]));
							char c16_6;
							char c16_7;
							if (s16.charAt(6) >= 97 && s16.charAt(6) <= 122) {
								c16_6 = (char) (s16.charAt(6) - 32);
							} else {
								c16_6 = s16.charAt(6);
							}
							if (s16.charAt(7) >= 97 && s16.charAt(7) <= 122) {
								c16_7 = (char) (s16.charAt(7) - 32);
							} else {
								c16_7 = s16.charAt(7);
							}
							sb.append("=" + c16_6 + c16_7);
						}
					}
				}
			}
			str = sb.toString();
		}
		return str;
	}

	/**
	 * 解码 UTF8 quoted-printable
	 *
	 * @param str 需要解码的字符串
	 * @return 解码后的字符串
	 * @throws Exception
	 */
	public static String qpDecodingUTF8(String str) throws Exception {
		if (str != null) {
			StringBuffer sb = new StringBuffer(str);
			for (int i = 0; i < sb.length(); i++) {
				if (sb.charAt(i) == '\n' && sb.charAt(i - 1) == '=') {
					sb.deleteCharAt(i - 1);
				}
			}
			str = sb.toString();
			byte[] bytes = str.getBytes("US-ASCII");
			for (int i = 0; i < bytes.length; i++) {
				byte b = bytes[i];
				if (b != 95) {
					bytes[i] = b;
				} else {
					bytes[i] = 32;
				}
			}
			if (bytes != null) {
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				for (int i = 0; i < bytes.length; i++) {
					int b = bytes[i];
					if (b == '=') {
						try {
							int u = Character.digit((char) bytes[++i], 16);
							int l = Character.digit((char) bytes[++i], 16);
							if (u == -1 || l == -1) {
								continue;
							}
							buffer.write((char) ((u << 4) + l));
						} catch (ArrayIndexOutOfBoundsException e) {
							e.printStackTrace();
						}
					} else {
						buffer.write(b);
					}
				}
				str = new String(buffer.toByteArray(), "UTF-8");
			}
		}
		return str;
	}

}
