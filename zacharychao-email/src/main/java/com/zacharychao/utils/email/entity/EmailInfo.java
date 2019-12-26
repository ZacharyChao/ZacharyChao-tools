package com.zacharychao.utils.email.entity;

public class EmailInfo {
		private final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		private String smtpServer;
		// SMTP服务器地址
		private String port; // 端口
		private String fromUserName;
		// 登录SMTP服务器的用户名,发送人邮箱地址
		private String fromUserPassword;
		// 登录SMTP服务器的密码
		private String toUser; // 收件人
		private String subject; // 邮件主题
		private String content; // 邮件正文
		public EmailInfo() {
			 

		}

		public EmailInfo(String toUser, String subject, String content) {

		this.toUser = toUser;

		this.subject = subject;

		this.content = content;

//		this.smtpServer = Global.getConfig("smtpServer");
//
//		this.port = Global.getConfig("port");
//
//		this.fromUserName = Global.getConfig("fromUserName");
//
//		this.fromUserPassword = Global.getConfig("fromUserPassword");

		}
}
