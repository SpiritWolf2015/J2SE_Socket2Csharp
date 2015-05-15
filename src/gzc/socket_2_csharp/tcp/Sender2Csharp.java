package gzc.socket_2_csharp.tcp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class Sender2Csharp {

	public static void main(String[] args) {
		// JAVA TCP客户端，向C#发请求
		Sender sender = new Sender("127.0.0.1", 10000);
		sender.send();
	}

}

class Sender {

	private String ip;
	private int port;

	public Sender(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void send() {
		//ByteBuffer buf = null;		
		Socket socket = null;
//		DataOutputStream dos = null;
		BufferedOutputStream bos = null;
		
		int j = 0;
//		float num = 123.456F;
		
		for (int i = 0; i < 10; i++) {
			try {
				// TCP连接请求
				socket = new Socket(InetAddress.getByName(ip), port);
				if (socket.isConnected()) {
					j++;
					System.out.println("已建立第" + j + "个连接!");
					Thread.sleep(50);
					
					/*
					// 把Floa数转成字节数组
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					dos = new DataOutputStream(baos);							
					dos.writeFloat(num + j);
					dos.flush();
					byte[] buf = baos.toByteArray();
					System.out.printf("字节数组的长度是%d\n", buf.length);
					*/					
					
					String str = "乃木板46" + j;	
					byte[] buf = getBytes(str.toCharArray(), "UTF-8");
					// 
//					dos.write(buf);	
					System.out.printf("字节数组的长度是%d\n", buf.length);
					
					// 往服务器发数据
					bos	= new BufferedOutputStream(socket.getOutputStream());
					bos.write(buf);
					bos.flush();

//					dos.flush();
//					dos.close();
					bos.close();
					socket.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 将字符转为字节(编码)
	private byte[] getBytes(char[] chars, final String charsetName) {
		Charset cs = Charset.forName(charsetName);
		CharBuffer cb = CharBuffer.allocate(chars.length);
		cb.put(chars);
		cb.flip();
		ByteBuffer bb = cs.encode(cb);
		return bb.array();
	}

	// 将字节转为字符(解码)
	private char[] getChars(byte[] bytes) {
		Charset cs = Charset.forName("GB2312");
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb = cs.decode(bb);

		return cb.array();
	}

}
