package socket_2_csharp.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * 字符串与字节数组转换
 * @author gzc
 *
 */
public final class StringConverter2ByteArrayUtil {
	
	//==============编码=====================
	
	/**
	 * 将字符数组(按指定的编码)转为字节数组(编码)
	 * @param chars
	 * @param charsetName 指定的字符串编码，如"UTF-8"
	 * @return 字节数组
	 */
	public static byte[] getBytes(final char[] chars, final String charsetName) {
		Charset cs = Charset.forName(charsetName);	//"UTF-8"
		CharBuffer cb = CharBuffer.allocate(chars.length);
		cb.put(chars);
		cb.flip();
		ByteBuffer bb = cs.encode(cb);
		return bb.array();
	}	
	/**
	 * 将字符串(按指定的编码)转为字节数组(编码)
	 * @param chars
	 * @param charsetName 指定的字符串编码，如"UTF-8"
	 * @return 字节数组
	 */
	public static byte[] getBytes(final String str, final String charsetName) {
		return getBytes(str.toCharArray(), charsetName);
	}
	
	//==============解码=====================

	/**
	 * 将字节数组转为字符数组(按指定的字符串编码解码)
	 * @param bytes
	 * @param charsetName 指定的字符串编码，如"UTF-8"
	 * @return 字符数组
	 */
	public static char[] getChars(byte[] bytes, final String charsetName) {
		Charset cs = Charset.forName(charsetName);	//"GB2312"
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb = cs.decode(bb);

		return cb.array();
	}	
	/**
	 * 将字节数组转为字符串(按指定的字符串编码解码)
	 * @param bytes
	 * @param charsetName 指定的字符串编码，如"UTF-8"
	 * @return 字符串
	 */
	public static String getString(byte[] bytes, final String charsetName){
		return new String(getChars(bytes, charsetName));
	}
	
		
}
