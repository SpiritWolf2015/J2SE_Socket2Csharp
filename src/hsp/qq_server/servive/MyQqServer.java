
package hsp.qq_server.servive;

import hsp.qq_server.model.Message;
import hsp.qq_server.model.MessageType;
import hsp.qq_server.model.User;

import java.net.*;
import java.io.*;

import socket_2_csharp.util.StringConverter2ByteArray;

/**
 * 这是qq服务器，它在监听，等待某个qq客户端，来连接
 */
public class MyQqServer {

	public MyQqServer() {

		try {
			// 在9999监听
			System.out.println("JAVA服务器，在9999监听");
			ServerSocket ss = new ServerSocket(9999);
			String encode = "UTF-8";
			
			while (true) {
				// 阻塞,等待连接
				Socket s = ss.accept();				
				
				BufferedReader br = new  BufferedReader(new InputStreamReader(s.getInputStream(), encode));
				String rec = "";
				// 接受C#发来的字符串数据
				String responseLine = ""; 
				while ((responseLine = br.readLine()) != null) {
					//用readLine接收数据是，会自动抛弃换行符，如果为了保持数据的格式，需要在这里加上一个换行标识符
					rec += responseLine + "\n";
				}    
				// 接收服务器的数据完毕
				System.out.printf("服务器接收到的字符串： %s", rec);
				
				// 按协议，用 | 分割出有效数据
				// split函数的参数为正则表达式，而不是一个简单的字符串，所以对一些特殊字符要加转义字符
				// 比如这里 | 就得在前面加\\，这样才能得到正确的结果。
				String[] data = rec.split("\\|");	// split("|"); // 没报错，但是效果不对 			
				User u = new User();
				u.setUserId(data[0].trim());
				u.setPasswd(data[1].trim());				
				System.out.println("服务器接收到用户id:" + u.getUserId() + "  , 密码:"
						+ u.getPasswd());
				
				Message m = new Message();
				// 输出流
				OutputStream outputStream = null;
//				PrintWriter printWriter = null;
				
				if ("123456".equals(u.getPasswd())) {					
					// 返回一个成功登陆的信息报
					m.setMesType(MessageType.message_succeed);
					// 输出流发送成功登陆字符串					
					outputStream = s.getOutputStream();
					BufferedOutputStream bos = new BufferedOutputStream(outputStream);
//					printWriter = new PrintWriter(outputStream);					
//					printWriter.write(m.getMesType());//C#客户端收不到
					
					// 把字符串转为字节数组
					byte[] buf = StringConverter2ByteArray.getBytes(m.getMesType(), "UTF-8");	
					bos.write(buf, 0, buf.length);
					bos.flush();	
					System.out.println("成功登陆");

					// 这里就单开一个线程，让该线程与该客户端保持通讯.
//					SerConClientThread scct = new SerConClientThread(s);
//					ManageClientThread.addClientThread(u.getUserId(), scct);
					// 启动与该客户端通信的线程.
//					scct.start();

					// 并通知其它在线用户.
//					scct.notifyOther(u.getUserId());
				} else {
					System.out.println("登录失败");
					// 登录失败
					m.setMesType(MessageType.message_login_fail);
//					oos.writeObject(m);
//					 关闭Socket
					s.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();		
		} finally {

		}

	}

}
