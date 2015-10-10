package com.bobo.code.web.controller.technology.httpcomponents;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kingtool.Obj2MapTool;
import kingtool.pager.Pager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bobo.code.model.Student;
import com.bobo.code.service.impl.StudentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * 用main直接调用该controller
 * @author King
 *
 */
@Controller
@RequestMapping(value = HttpClientController.DIR)
public class HttpClientController {
	protected static final String DIR = "httpClient/*";
	Logger logger = Logger.getLogger(HttpClientController.class);

	 @RequestMapping(value = "post.do", method = {RequestMethod.POST, RequestMethod.GET})
		public void post( final ModelMap model,String name,HttpServletResponse response) {
	    	System.out.println("服务器收到内容"+name);
	    	PrintWriter pw = null ;
	    	try {
	    		pw = response.getWriter();
				pw.write(name);
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
    public void post() {  
        // 新建可关闭的的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        // 新建httpClient实例时直接设置请求配置 
        // CloseableHttpClient httpclientAnother = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        // 新建post请求  
        HttpPost httppost = new HttpPost("http://localhost:8080/HttpComponents/httpClient/post.do");  
        // 新建参数List
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
        //添加参数
        formparams.add(new BasicNameValuePair("name", "king"));  
        //参数包装
        HttpEntity uefEntity = null;  
        try {  
        	//给参数设置编码
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            System.out.println("executing request " + httppost.getURI());  
            //真正发送请求并得到响应
            CloseableHttpResponse response = httpclient.execute(httppost);  
            //判断响应代码是否为成功标志
            if(response.getStatusLine().getStatusCode() == 200) {
            	try {  
            		//取response的响应实体
                    HttpEntity entity = response.getEntity();  
                    if (entity != null) {  
                        System.out.println("-----------------解析response中的实体,开始---------------------");  
                        System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));  
                        System.out.println("-----------------解析response中的实体,完毕---------------------");  
                    }  
                } finally {  
                    response.close();  
                }  
            }else{
            	httppost.abort();
            }
            
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
    }

    public void requestConfig(){
    	//新建一个RequestConfig,请求配置,设置各种超时
    	RequestConfig defaultRequestConfig = RequestConfig.custom()
         		.setConnectTimeout(3000)//ConnectTimeout指的是连接一个url的连接等待时间
                 .setSocketTimeout(3000)//SocketTimeout指的是连接上一个url,获取response的返回等待时间
                 .setConnectionRequestTimeout(3000)//不知道什么意思... 
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
    	new HttpClientController().post();
	}
    
}
