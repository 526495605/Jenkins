package com.ykyy.server.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Sms {
	private static final String URL = "http://sms.1xinxi.cn/asmx/smsservice.aspx?";
	private static final String name = "泰凡";
	private static final String pwd = "58104EFEDD2B05C52DF0E551644D";
	private static final String type = "pt";
	private static final String sign = "爱智康";
	public static final String content = "欢迎使用爱智康养老云平台，短信验证码为：";


	/*
	public static boolean sendPost(String mobile, String content) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("name", name));
		nvps.add(new BasicNameValuePair("pwd", pwd));
		nvps.add(new BasicNameValuePair("type", type));
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("content", content));
		nvps.add(new BasicNameValuePair("sign",sign));

		return HttpUtil.post(URL, nvps);
	}
	
	public static boolean sendGet(String mobile, String content) {
		String url = URL;
		String sms = content;
		//String sms = String.format(template, content);
		try {
			sms = URLEncoder.encode(sms, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			Logging.error(e.getMessage());
			e.printStackTrace();
		}
		Logging.debug(mobile + " : " + sms);
		url += "?name=" + name + "&pwd=" + pwd + "&type=" + type + "&sign="+ sign + "&mobile=" + mobile + "&content=" + sms ;
		System.out.println(url);
		if (Switch.SENDSMS) {
			return HttpUtil.get(url);
		}
		return true;
	}
	
	public static boolean setMsg(String mobile, String content) {
		String url = URL;
		String sms = content;
		try {
			sms = URLEncoder.encode(sms, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			Logging.error(e.getMessage());
			e.printStackTrace();
		}
		
		Logging.debug(mobile + " : " + sms);
		url += "?name=" + name + "&pwd=" + pwd + "&type=" + type  + "&sign="+ sign + "&mobile=" + mobile + "&content=" + sms;
		if (Switch.SENDSMS) {
			return HttpUtil.get(url);
		}
		return true;
	}
	*/

	/**
	 * 手机号验证
	 *
	 * @param  str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 电话号码验证
	 *
	 * @param  str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		Pattern p1 = null,p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
		if(str.length() >9)
		{   m = p1.matcher(str);
			b = m.matches();
		}else{
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
	}



	public static void main(String[] args) throws Exception {

	//	Boolean success = Sms.sendPost("18310042308", "wsj test");
		//Boolean success = Sms.sendGet("18810549396", "5678");
	//	System.out.println(isMobile("13311292379"));

		//发送内容
		/*String content = "您好： 这里是，欢迎老人入住本院。老人ID为：。" +
				"请你通过如下地址：www.XXXXXX.com 下载家属端APP，" +
				"注册并绑定老人ID，体验我们的优质服务，并可随时随地查看老人在院信息。更多服务请详见家属端APP。";
		String mobile="13311292379";
		sendSMS(mobile,content);*/

	}


	public static void sendSMS( String mobile,String content) {
		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer(URL);

		// 向StringBuffer追加用户名
		sb.append("name="+name);

		// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
		sb.append("&pwd="+pwd);

		// 向StringBuffer追加手机号码
		sb.append("&mobile="+mobile);

		// 向StringBuffer追加消息内容转URL标准码
		sb.append("&content="+ URLEncoder.encode(content));

		//追加发送时间，可为空，为空为及时发送
		sb.append("&stime=");

		//加签名
		sb.append("&sign="+URLEncoder.encode(sign));

		//type为固定值pt  extno为扩展码，必须为数字 可为空
		sb.append("&type="+type+"&extno=");
		// 创建url对象

		try {
			URL url = new URL(sb.toString());

			// 打开url连接
			HttpURLConnection connection = null;
			connection = (HttpURLConnection) url.openConnection();
			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setRequestMethod("POST");
			// 发送
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			// 返回发送结果
			String inputline = in.readLine();
			// 返回结果为‘0，20140009090990,1，提交成功’ 发送成功   具体见说明文档
			System.out.println(inputline);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
