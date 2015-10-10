package debug.http;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class HttpRequestSimulation {
	public static  final String POST = "POST";
	public static final String GET = "GET";
	
	public static void main(String[] args) throws Exception {
		String ret = sendRequestData(
				HttpRequestSimulation.POST ,
				"id=2",
//				"http://www.baidu.com"
				"http://127.0.0.1:8080/SpringMVC/student/selectStudent.do"
				);
		
		String retUTF8 =new String(ret.getBytes("GBK"), "UTF-8");
		System.out.println(retUTF8);
	}
	
	
	private static String sendRequestData(String requestMethod , String requestData, String urlStr) throws IOException{
		URL url = null;
		HttpURLConnection conn = null;
		ByteArrayOutputStream byteOut = null;
		BufferedReader readInfo = null;
		StringBuffer sb=new StringBuffer();
		OutputStream out = null;
		InetAddress addr = InetAddress.getLocalHost();
		String ip=addr.getHostAddress().toString();//获得本机IP
		try {
			//@@这里必须改成这样,linux和windows系统默认读取编码不一样,必须强制
//			requestData=new String(requestData.getBytes("GBK"), "GBK");
			System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sendPltmXml>>>>>>["+new Date()+"]"+">>>>>>\n"+requestData);
			url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("SOAPAction", "\"\"");
			conn.setRequestProperty("Accept", "application/soap+xml, application/dime, multipart/related, text/*");
			conn.setRequestProperty("content-type", "application/x-www-form-urlencoded"); // text/xml;charset=GBK 用这个Content-Type请求本项目会取不到对应的Bean属性
			conn.setRequestProperty("User-Agent", "Axis/1.4");
			conn.setRequestProperty("Cache-Control", "no-cache");
			conn.setUseCaches(false); //忽略缓存
			conn.setDoOutput(true); //使用 URL 连接进行输入
			conn.setDoInput(true); //使用 URL 连接进行输入
			conn.setConnectTimeout(500*1000);//500秒
			conn.setReadTimeout(500*1000);//500秒
			conn.connect();
			byteOut = new ByteArrayOutputStream();
			byteOut.write(requestData.getBytes());
			byte[] buf = byteOut.toByteArray();
			out = conn.getOutputStream();
			out.write(buf);
			out.flush();
			if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {//正确返回 
				readInfo = new BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"GBK"));//@@换成以GBK读取
				String line = null;
				while ((line = readInfo.readLine()) != null) {
					sb.append(line);
				}
			} else {//没有正确返回
				throw new IOException("行业接口服务出现问题,返回编码：" + conn.getResponseCode());//波波
			}
			System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>receivePltmXml>>>>>>["+new Date()+"]"+">>>>>>\n"+sb.toString());
		} catch  (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if (readInfo != null) {
					readInfo.close();
				}
				if (byteOut != null) {
					byteOut.close();
				}
				if (out != null) {
					out.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			}catch(Exception e){
				System.out.println("关闭平台链接出错!"+e.getMessage());
			}
			
		}
		return sb.toString();
	}
}
