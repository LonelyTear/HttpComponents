package com.bobo.code.web.controller.technology.httpcomponents;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class KingResponseHandler implements ResponseHandler<TransferObj> {
	private static Logger logger = Logger.getLogger(KingResponseHandler.class);

	public TransferObj handleResponse(HttpResponse response)
			throws ClientProtocolException, IOException {
		TransferObj responseObj = null;
		HttpEntity entity = null;

		System.out.println(response.getStatusLine());
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		int status = response.getStatusLine().getStatusCode();
		if ((status >= 200) && (status < 300)) {
			entity = response.getEntity();
			if (entity != null) {// 和目标系统通信成功,并返回正确(想要的)信息
				System.out.println("response 中 Entity 长度："
						+ entity.getContentLength());
				String strEntity = EntityUtils.toString(entity, "UTF-8");
				if ((strEntity == null) || (strEntity.trim().length() == 0))
					return null;

				System.out.println("response 中 Entity 内容：" + strEntity);
				Gson gson = new Gson();
				// gson反解析strEntity成响应对象;
				responseObj = gson.fromJson(strEntity, TransferObj.class);
				responseObj.setStatusCode(status);
			} else {// 和目标系统通信成功,却没有返回正确(想要的)信息
				responseObj = new TransferObj();
				responseObj.setStatusCode(9999);
				responseObj.setBody("errorBody");
			}
		} else {// 和目标系统通信失败
			responseObj = new TransferObj();
			responseObj.setStatusCode(404);
			responseObj.setBody("fatalBody");
		}
		return responseObj;
	}
}