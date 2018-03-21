/** 
 * Copyright ® 2015 Zhejiang Shine Technology Co., Ltd.
 * All right reserved. 
 */

package com.zeal.shiyulin.common.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

import com.zeal.shiyulin.common.config.Global;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpHeaders;

/**
 * @Description REST服务调用工具类
 * @Author zhangp
 * @Date 2015年3月31日
 * @RevisionHistory <table border='1' cellspacing='0' cellpadding='0'>
 *                  <tr>
 *                  <td width='80px'><b>修改日期</b></td>
 *                  <td width='60px'><b>修改者</b></td>
 *                  <td><b>修改内容</b></td>
 *                  </tr>
 *                  <tr>
 *                  <td></td>
 *                  <td></td>
 *                  <td></td>
 *                  </tr>
 *                  </table>
 * 
 */
public class RestUtils {
	/**
	 * GET请求
	 * 
	 * @param serviceUrl
	 * @param username
	 * @param password
	 * @return response code & response body
	 */
	public static String get1(String serviceUrl, String username,
			String password) {
		try {
			// 实例一个URL资源
			URL url = new URL(serviceUrl);
			// 实例一个HTTP CONNECT
			HttpURLConnection connect = (HttpURLConnection) url
					.openConnection();
			connect.setRequestMethod(Global.REST_METHOD_GET);
			String encoded = new String(Base64.encodeBase64(new String(username
					+ ":" + password).getBytes()));
			connect.setRequestProperty("Authorization", "Basic " + encoded);
			connect.connect();
			int code = connect.getResponseCode();

			// 将返回的值存入到String中
			BufferedReader brd = new BufferedReader(new InputStreamReader(
					connect.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;

			while ((line = brd.readLine()) != null) {
				sb.append(line);
			}
			brd.close();
			connect.disconnect();

			return code + Global.REST_RESPONSE_SPLIT + sb.toString();
		} catch (Exception e) {
			return "999" + Global.REST_RESPONSE_SPLIT + e.getLocalizedMessage();
		}
	}

	/***
	 * GET请求
	 * 
	 * @param serviceUrl
	 *            服务地址
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws IOException
	 */
	public static String get(String serviceUrl, String username, String password) {
		HttpGet request = new HttpGet(serviceUrl);
		request.addHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE,
				"application/json"));
		request.addHeader(new BasicHeader(HttpHeaders.ACCEPT_CHARSET, "gbk"));

		String result = executeRequest(request, username, password);
		return result;
	}

	/***
	 * POST请求
	 * 
	 * @param serviceUrl
	 *            服务地址
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param json
	 *            json字符串,例如: "{ \"username\":\"zhangsan\" }" ;其中属性名必须带双引号
	 * @return
	 * @throws IOException
	 */
	public static String post(String serviceUrl, String username,
			String password, String json) {
		HttpPost request = new HttpPost(serviceUrl);
		request.addHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE,
				"application/json"));
		request.addHeader(new BasicHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8"));

		@SuppressWarnings("deprecation")
		StringEntity entity = new StringEntity(json, HTTP.UTF_8);
		request.setEntity(entity);

		String result = executeRequest(request, username, password);
		return result;
	}

	/***
	 * PUT请求
	 * 
	 * @param serviceUrl
	 *            服务地址
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param json
	 *            json字符串,例如: "{ \"username\":\"zhangsan\" }" ;其中属性名必须带双引号
	 * @return
	 * @throws IOException
	 */
	public static String put(String serviceUrl, String username,
			String password, String json) {
		HttpPut request = new HttpPut(serviceUrl);
		request.addHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE,
				"application/json"));
		request.addHeader(new BasicHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8"));

		@SuppressWarnings("deprecation")
		StringEntity entity = new StringEntity(json, HTTP.UTF_8);
		request.setEntity(entity);

		String result = executeRequest(request, username, password);
		return result;
	}

	/***
	 * DELETE请求
	 * 
	 * @param serviceUrl
	 *            服务地址
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @remark 因delete是javascript中的关键字，故通过dwr调用时不能使用此关键字，所以该方法命名为del           
	 * @return
	 * @throws IOException
	 */
	public static String del(String serviceUrl, String username,
			String password) {
		HttpDelete request = new HttpDelete(serviceUrl);
		request.addHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE,
				"application/json"));
		request.addHeader(new BasicHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8"));

		String result = executeRequest(request, username, password);
		return result;
	}

	public static String executeRequest(HttpUriRequest request,
			String username, String password) {
		try {
			LinkedList<CloseableHttpResponse> httpResponses = new LinkedList<CloseableHttpResponse>();
			CredentialsProvider provider = new BasicCredentialsProvider();
			UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
					username, password);
			provider.setCredentials(AuthScope.ANY, credentials);
			CloseableHttpClient client = HttpClientBuilder.create()
					.setDefaultCredentialsProvider(provider).build();

			CloseableHttpResponse response = client.execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			httpResponses.add(response);

			InputStream inputStream = null;
			String output = "";
			HttpEntity entity = response.getEntity();
			if(entity != null && entity.getContent() != null){
				inputStream = response.getEntity().getContent();
				output = IOUtils.toString(inputStream,"UTF-8");
				inputStream.close();
			}
			
			response.close();
			return statusCode + Global.REST_RESPONSE_SPLIT + output;
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR" + Global.REST_RESPONSE_SPLIT
					+ e.getLocalizedMessage();
		}
	}

}
