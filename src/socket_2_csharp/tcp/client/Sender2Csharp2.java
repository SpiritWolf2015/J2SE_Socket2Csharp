package socket_2_csharp.tcp.client;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class Sender2Csharp2 {

	public static void main(String[] args) {
		// JAVA TCP客户端，向C#发请求
		Sender2 sender = new Sender2("127.0.0.1", 10000);
		sender.send();
	}

}

class Sender2 {

	private String ip;
	private int port;

	public Sender2(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void send() {			
		Socket socket = null;
		DataOutputStream dos = null;
		BufferedOutputStream bos = null;
		
		int j = 0;
		float num = 123.456F;
		
		for (int i = 0; i < 10; i++) {
			try {
				// TCP连接请求
				socket = new Socket(InetAddress.getByName(ip), port);
				if (socket.isConnected()) {
					j++;
					System.out.println("已建立第" + j + "个连接!");
					Thread.sleep(50);		
					
					// 把Floa数转成字节数组
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					dos = new DataOutputStream(baos);							
					dos.writeFloat(num + j);
					dos.flush();
					byte[] buf = baos.toByteArray();
					System.out.printf("字节数组的长度是%d\n", buf.length);
					
					// 往服务器发数据
					bos	= new BufferedOutputStream(socket.getOutputStream());
					bos.write(buf);
					bos.flush();

					dos.flush();
					dos.close();
					bos.close();
					socket.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
