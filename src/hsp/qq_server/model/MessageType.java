/**
 * 定义包的种类
 */
package hsp.qq_server.model;

public class MessageType {

	public final static String message_succeed = "1";// 表明是登陆成功
	public final static String message_login_fail = "2";// 表明登录失败
	public final static String message_comm_mes = "3";// 普通信息包
	public final static String message_get_onLineFriend = "4";// 要求在线好友的包
	public final static String message_ret_onLineFriend = "5";// 返回在线好友的包
}
