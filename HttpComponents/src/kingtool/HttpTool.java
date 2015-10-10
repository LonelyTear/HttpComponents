package kingtool;



import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
 
public class HttpTool {
public static void main(String[] args) throws Exception {
		System.out.println(new StringBuffer().toString().length());
		for(int i = 0  ; i < 1; i++){
			new Thread(){
				public void run(){
					Timestamp start = new Timestamp(    new Date().getTime()  );
					try {
//						String reader = read("C:/Users/King/Desktop/connectUTF8.xml");
						String reader = read("C:/Users/King/Desktop/connectANSI.xml");
//						String url = "http://88.100.110.19:9081/sinoiacav442/caserver";//浙江商业
//						String url = "http://88.100.110.12:9080/sinoiacav442/caserver";//安徽商业
//						String url = "http://88.100.110.19:9081/sinoiaciv550/commserver";//浙江交强
//						String url = "http://88.100.110.12:9080/sinoiacav440/caserver";//苏
//						String url = "http://88.100.114.3:7007/sinoiaciv550/commserver";//宁波
//						String url = "http://88.100.110.5:7001/sinoiacav440/caserver";//山东
//						String url = "http://88.100.10.23:9086/sinoiaciv43/commserver";//73 青岛 交强
//						String url = "http://88.100.10.23:9087/sinoiacav60/caserver";//73 青岛 商业
//						String url = "http://88.100.110.10:7003/sinoiaciv553/commserver";//51 四川 交强
//						String url = "http://88.100.10.23:9084/sinoiaciv43/commserver";//41 河南 交强
//						String url = "http://88.100.10.23:9082/sinoiaciv43/commserver";//42 湖北交强
//						String url = "http://88.100.10.26:9082/sinoiaciv43/commserver";//44 广东交强
//						String url = "http://88.100.10.26:9082/sinoiaciv43/commserver";//44 广东交强
//						String url = "http://88.100.114.1:7005/sinoiaciv553/commserver";//44 广东交强
						String url = "http://192.168.153.105/circauto/servlet/insurance/interface"; //11 北京
//						String url = "http://192.168.154.180/circauto/servlet/insurance/interface"; //31 上海
//						String url = "http://88.100.114.1:7005/sinoiaciv553/commserver"; //74深圳
						
//						String url = "http://88.100.10.12:9089/sinosynpub/commserver"; //数据发布子系统
						sendRequestData(reader,url,1000l);
						} catch (Exception e) {
							e.printStackTrace();
						}
						Timestamp end = new Timestamp(    new Date().getTime()  );
						System.out.println(end.getTime()-start.getTime()+"ms");
				}
			}.start();
		}
	}

	private static Logger logger = Logger.getLogger(HttpTool.class);
	
	private static final int DEFAULT_CONNECTION_TIMEOUT=5*1000;//连接超时时间5秒
	private static final int DEFAULT_READ_TIMEOUT=5*1000;//读取超时时间5秒
	//**************************************************************************
	
	
	public static String sendRequestData(String requestData, String urlStr, Long timeOut) throws IOException{
		int index=1;
		String ret=null;
		IOException ex=null;
		final String uuid= ""+Math.random();//UUID.randomUUID().toString();
		do{
			try {				
				ret=sendRequestData(requestData, urlStr, timeOut,index,uuid);
			} catch (IOException e) {
				ex=e;
			}
			if(ret != null && ret.length()>0){
				ex = null;
				break;
			}
			index++;
		}while(index<=5);
		
		if(ex !=null){
			 throw  ex;
		}
		
		return ret;
	}
	
	
	
	
	/**
	 * TODO 方法sendRequestData的简要说明 <br><pre>
	 * 往urlStr中发送requestData数据，超时时间为timeOut(毫秒)
	 * 返回该url的返回数据
	 * 方法sendRequestData的详细说明 <br>
	 * 编写者：shengyao@isoftstone.com
	 * 创建时间：Mar 21, 2014 10:35:52 AM </pre>
	 * @param 参数类型 参数名 说明
	 * @return String 说明
	 * @throws IOException 
	 * @throws IOException 
	 * @throws 异常类型 说明
	 */
	//**************************************************************************
	private static String sendRequestData(String requestData, String urlStr, Long timeOut,int index,String uuid) throws IOException{
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
			requestData=new String(requestData.getBytes("GBK"), "GBK");
			logger.info("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>sendPltmXml>>>>>>["+new Date()+"]"+">>>>>>\n"+requestData);
			url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("SOAPAction", "\"\"");
			conn.setRequestProperty("Accept", "application/soap+xml, application/dime, multipart/related, text/*");
			conn.setRequestProperty("content-type", "text/xml;charset=GBK");
			conn.setRequestProperty("User-Agent", "Axis/1.4");
			conn.setRequestProperty("Cache-Control", "no-cache");
			conn.setUseCaches(false); //忽略缓存
			conn.setDoOutput(true); //使用 URL 连接进行输入
			conn.setDoInput(true); //使用 URL 连接进行输入
			conn.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT);
			conn.setReadTimeout(DEFAULT_READ_TIMEOUT);
			conn.connect();
			byteOut = new ByteArrayOutputStream();
			byteOut.write(requestData.getBytes("GBK"));
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
			logger.info("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>receivePltmXml>>>>>>["+new Date()+"]"+">>>>>>\n"+sb.toString());
		} catch (UnsupportedEncodingException e) {
			logger.error(String.format("UUID %s 本机IP: %s 第 %d 请求, 请求地址：%s 请求xml数据：%s   Exception: %s ",uuid,ip,index,urlStr,requestData,e.getMessage()));
			throw e;
		} catch (MalformedURLException e) {
			logger.error(String.format("UUID %s 本机IP: %s 第 %d 请求, 请求地址：%s 请求xml数据：%s   Exception: %s ", uuid,ip,index,urlStr,requestData,e.getMessage()));
			throw e;
		} catch (IOException e) {
			logger.error(String.format("UUID %s 本机IP: %s 第 %d 请求, 请求地址：%s 请求xml数据：%s   Exception: %s ", uuid,ip,index,urlStr,requestData,e.getMessage()));
			throw e;
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
				logger.error("关闭平台链接出错!"+e.getMessage());
			}
			
		}
		return sb.toString();
	}
	
	
	
	static String read(String filePath) throws Exception{
		File f = new File(filePath);
		InputStream is = new FileInputStream(f);
		StringBuffer sb = new StringBuffer();
		try {
			FileReader fs = new FileReader(f);
			
//			BufferedReader br = new BufferedReader(new FileReader(f));//默认
			BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is,"gbk"));//@@换成以GBK读取
			String text;
			while ((text = br.readLine()) != null) {
				sb.append(text).append("\n");
			}
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}
	
	
}
