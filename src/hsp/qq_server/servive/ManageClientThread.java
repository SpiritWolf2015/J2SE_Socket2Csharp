package hsp.qq_server.servive;

import java.util.*;

public class ManageClientThread {

	public static HashMap<String, SerConClientThread> hm = new HashMap<String, SerConClientThread>();

	// ��hm������һ���ͻ���ͨѶ�߳�
	public static void addClientThread(String uid, SerConClientThread ct) {
		hm.put(uid, ct);
	}

	public static SerConClientThread getClientThread(String uid) {
		return (SerConClientThread) hm.get(uid);
	}

	// ���ص�ǰ���ߵ��˵����
	public static String getAllOnLineUserid() {
		// ʹ�õ��������
		Iterator<String> it = hm.keySet().iterator();
		String res = "";
		while (it.hasNext()) {
			res += it.next().toString() + " ";
		}
		return res;
	}
}