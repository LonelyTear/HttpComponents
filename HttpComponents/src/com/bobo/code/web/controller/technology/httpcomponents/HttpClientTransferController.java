package com.bobo.code.web.controller.technology.httpcomponents;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
/**
 * 用main直接调用该controller
 * 这里的总流程为:
 * 1.Client 把请求obj1 -->str1 
 * 2.Server把从Client接受到的请求的str1 -->obj2
 * 3.Server对转换完成后的obj2进行加工处理,分析完毕后,再obj3-->str3
 * 4.Client 把Server返回的str3-->obj4
 * @author King
 *
 */
@Controller
@RequestMapping(value = HttpClientTransferController.DIR)
public class HttpClientTransferController {
	protected static final String DIR = "httpClientTransfer/*";
	Logger logger = Logger.getLogger(HttpClientTransferController.class);
	
	 @RequestMapping(value = "post.do", method = {RequestMethod.POST, RequestMethod.GET})
		public void post( final ModelMap model,String name,HttpServletRequest request,HttpServletResponse response) {
		 PrintWriter pw = null ;
		 String line = null;
		 StringBuilder sb = new StringBuilder();
		 // 读取请求内容
		 try {
		        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		        while((line = br.readLine())!=null){
		            sb.append(line);
		        }
		        System.out.println("服务器从流中读取到的信息为"+sb);
		    	//正常流程:我们会对sb.toString()进行string到TransferObj的反解析处理,并把结果返回
	    		//服务器也把请求对象封装成jsonStr统一传输
	    		Gson gson = new Gson();
	    		String responseStr = gson.toJson(new TransferObj("OK, I have received you message !"));
	    		pw = response.getWriter();
				pw.write(responseStr);
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				pw.close();
			}
		}
	 
	/** 
     * 1.发送 post请求
     * 2.去访问某个ip上的web服务器
     * 3.web服务器处理参数,返回result 
     * @author King
     * @time 2015-09-25
     */  
    public TransferObj post() {  
    	 TransferObj responseObj = null;
        // 新建可关闭的的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        // 新建httpClient实例时直接设置请求配置 
        // CloseableHttpClient httpclientAnother = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        // 新建post请求  
        HttpPost httppost = new HttpPost("http://localhost:8080/HttpComponents/httpClientTransfer/post.do");  
        //参数包装
        HttpEntity entity = null;  
        TransferObj requestObj = new TransferObj("Hellow King!");
        //封装请求对象成jsonStr以统一传输
        Gson gson = new Gson();
		String jsonStr = gson.toJson(requestObj);
        try {  
        	//给参数设置编码
            entity = EntityBuilder.create()
            	      .setContentType(ContentType.APPLICATION_JSON)
            	      .setContentEncoding("UTF-8")
            	      .setText(jsonStr)
            	      .build();
            httppost.setEntity(entity);  
            System.out.println("executing request " + httppost.getURI());  
            //真正发送请求并得到响应,在该方法内部,就算和服务器通信失败了也会返回正常的对象,因为在内部已经封装好了
            responseObj = httpclient.execute(httppost,new KingResponseHandler()); 
            System.out.println(responseObj);
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return responseObj;
        
    }
    

    
    
    public void requestConfig(){
//    	新建一个RequestConfig：
    	RequestConfig defaultRequestConfig = RequestConfig.custom()
    	    .setSocketTimeout(5000)
    	    .setConnectTimeout(5000)
    	    .setConnectionRequestTimeout(5000)
    	    .build();
    	
//    	这个超时可以设置为客户端级别,作为所有请求的默认值：
    	CloseableHttpClient httpclient = HttpClients.custom()
    	    .setDefaultRequestConfig(defaultRequestConfig)
    	    .build();
//    	 httpclient.execute(httppost);的时候可以让httppost直接享受到httpclient中的默认配置.
    	
//    	Request不会继承客户端级别的请求配置，所以在自定义Request的时候，需要将客户端的默认配置拷贝过去：
    	HttpGet httpget = new HttpGet("http://www.apache.org/");
    	RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig)
    	    .setProxy(new HttpHost("myotherproxy", 8080))
    	    .build();
    	httpget.setConfig(requestConfig);
//    	httpget可以单独地使用新copy的requestConfig请求配置,不会对别的request请求产生影响
    }
    
    public static void main(String[] args) {
    	new HttpClientTransferController().post();
	}
    
}
