package com.larry.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.engine.TemplateData;

import com.larry.AppConf;
import com.larry.bean.WeChatTemplateData;
import com.larry.bean.WechatTemplate;
import com.larry.entity.OrderPO;
import com.larry.service.WeixinMessageService;
import com.larry.service.WeixinUserService;
import com.larry.utils.WeixinUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class WeixinMessageServiceImp implements WeixinMessageService {
	@Autowired
	private WeixinUserService weixinUserService;
	@Autowired
	private AppConf appConf;

	public String subscribeForText(String toUserName, String fromUserName, String msgContent) {

		return WeixinUtils.textMsg(toUserName, fromUserName, msgContent);
	}

	public String unsubscribe(String toUserName, String fromUserName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String sendTextMsg(String toUserName, String fromUserName, String msgContent) {
		// TODO Auto-generated method stub
		return WeixinUtils.textMsg(toUserName, fromUserName, msgContent);
	}

	public String sendTemplateMessages(String templateID, String openId, OrderPO order, String header, String msg1,
			String msg2, String msg3, String msg4) {
		// TODO Auto-generated method stub
		// get access_Token
		String ACCESS_TOKEN = weixinUserService.getAccessToken().getToken();
		String requestUrl = appConf.getWeixinMessageUrl();
		requestUrl = requestUrl.replace("ACCESS_TOKEN", ACCESS_TOKEN);
		OutputStream outputStream = null;
		BufferedReader msreader = null;
		try {
			URL messageUrl = new URL(requestUrl);
			HttpURLConnection msconn = (HttpURLConnection) messageUrl.openConnection();
			msconn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			msconn.setConnectTimeout(30000); // 将读取超时设置为指定的超时时间，以毫秒为单位。
			// conn.setReadTimeout(60000);
			// 发送POST请求必须设置如下两行
			msconn.setDoOutput(true);
			msconn.setDoInput(true);
			msconn.setRequestMethod("POST");
			// Post 请求不能使用缓存
			msconn.setUseCaches(false);
			// 建立连接
			msconn.connect();
			outputStream = msconn.getOutputStream();
			WechatTemplate wechatTemplate = new WechatTemplate();
			wechatTemplate.setTemplate_id(templateID);
			wechatTemplate.setTouser(openId);
			Map<String, WeChatTemplateData> mapdata = new HashMap<String, WeChatTemplateData>();
			WeChatTemplateData first = new WeChatTemplateData();
			first.setValue(header);
			// first.setColor("#173177");
			mapdata.put("first", first);

			WeChatTemplateData second = new WeChatTemplateData();
			second.setValue(msg1);
			second.setColor("#173177");
			mapdata.put("keyword1", second);

			WeChatTemplateData third = new WeChatTemplateData();
			third.setValue(msg2);
			third.setColor("#173177");
			mapdata.put("keyword2", third);

			WeChatTemplateData forth = new WeChatTemplateData();
			forth.setValue(msg3);
			forth.setColor("#173177");
			mapdata.put("keyword3", forth);

			WeChatTemplateData five = new WeChatTemplateData();
			five.setValue(msg4);
			five.setColor("#173177");
			mapdata.put("remark", five);
			wechatTemplate.setData(mapdata);

			JSONArray wechatTemplatearray = JSONArray.fromObject(wechatTemplate);
			String wechatTemplateStr = wechatTemplatearray.toString().replace("[", " ");
			wechatTemplateStr = wechatTemplateStr.toString().replace("]", " ");
			System.out.println(wechatTemplateStr);
			outputStream.write(wechatTemplateStr.getBytes("UTF-8"));
			// outputStream.close();
			msreader = new BufferedReader(new InputStreamReader(msconn.getInputStream()));
			String msline;
			String msresult = "";
			while ((msline = msreader.readLine()) != null) {
				msresult += msline;
			}
			msreader.close();
			msconn.disconnect();
			System.out.println("&&&&&&&&&" + msresult);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null)
				try {
					outputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

		}

		return null;

	}

	public String direct2helpDesk(String toUserName, String fromUserName, String msgContent) {
		// TODO Auto-generated method stub
		return WeixinUtils.textMsgHD(toUserName, fromUserName, msgContent);
	}

	public String sendPicMsgWithoutUrl(String toUserName, String fromUserName, String msgContent) {
		// TODO Auto-generated method stub
		return null;
	}

	public String sendCancOrderTemplateMessages(String templateID, String openId, OrderPO order, String header,
			String msg1, String msg2, String msg3, String msg4) {
		// TODO Auto-generated method stub
		// get access_Token
		String ACCESS_TOKEN = weixinUserService.getAccessToken().getToken();
		String requestUrl = appConf.getWeixinMessageUrl();
		requestUrl = requestUrl.replace("ACCESS_TOKEN", ACCESS_TOKEN);
		OutputStream outputStream = null;
		BufferedReader msreader = null;
		try {
			URL messageUrl = new URL(requestUrl);
			HttpURLConnection msconn = (HttpURLConnection) messageUrl.openConnection();
			msconn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			msconn.setConnectTimeout(30000); // 将读取超时设置为指定的超时时间，以毫秒为单位。
			// conn.setReadTimeout(60000);
			// 发送POST请求必须设置如下两行
			msconn.setDoOutput(true);
			msconn.setDoInput(true);
			msconn.setRequestMethod("POST");
			// Post 请求不能使用缓存
			msconn.setUseCaches(false);
			// 建立连接
			msconn.connect();
			outputStream = msconn.getOutputStream();
			WechatTemplate wechatTemplate = new WechatTemplate();
			wechatTemplate.setTemplate_id(templateID);
			wechatTemplate.setTouser(openId);
			Map<String, WeChatTemplateData> mapdata = new HashMap<String, WeChatTemplateData>();
			WeChatTemplateData first = new WeChatTemplateData();
			first.setValue(header);
			// first.setColor("#173177");
			mapdata.put("first", first);

			WeChatTemplateData second = new WeChatTemplateData();
			second.setValue(msg1);
			second.setColor("#173177");
			mapdata.put("keyword1", second);

			WeChatTemplateData third = new WeChatTemplateData();
			third.setValue(msg2);
			third.setColor("#173177");
			mapdata.put("keyword2", third);

			WeChatTemplateData forth = new WeChatTemplateData();
			forth.setValue(msg3);
			forth.setColor("#173177");
			mapdata.put("keyword3", forth);
			
			WeChatTemplateData forth_2 = new WeChatTemplateData();
			forth_2.setValue(order.getRejected_reason());
			forth_2.setColor("#173177");
			mapdata.put("keyword4", forth_2);

			WeChatTemplateData five = new WeChatTemplateData();
			five.setValue(msg4);
			five.setColor("#173177");
			mapdata.put("remark", five);
			wechatTemplate.setData(mapdata);

			JSONArray wechatTemplatearray = JSONArray.fromObject(wechatTemplate);
			String wechatTemplateStr = wechatTemplatearray.toString().replace("[", " ");
			wechatTemplateStr = wechatTemplateStr.toString().replace("]", " ");
			System.out.println(wechatTemplateStr);
			outputStream.write(wechatTemplateStr.getBytes("UTF-8"));
			// outputStream.close();
			msreader = new BufferedReader(new InputStreamReader(msconn.getInputStream()));
			String msline;
			String msresult = "";
			while ((msline = msreader.readLine()) != null) {
				msresult += msline;
			}
			msreader.close();
			msconn.disconnect();
			System.out.println("&&&&&&&&&" + msresult);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null)
				try {
					outputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

		}

		return null;

	}

	public String sendCloseOrderTemplateMessages(String templateID, String openId, OrderPO order, String header,
			String msg1, String msg2, String msg3, String msg4) {
		// TODO Auto-generated method stub
		// get access_Token
		String ACCESS_TOKEN = weixinUserService.getAccessToken().getToken();
		String requestUrl = appConf.getWeixinMessageUrl();
		requestUrl = requestUrl.replace("ACCESS_TOKEN", ACCESS_TOKEN);
		OutputStream outputStream = null;
		BufferedReader msreader = null;
		try {
			URL messageUrl = new URL(requestUrl);
			HttpURLConnection msconn = (HttpURLConnection) messageUrl.openConnection();
			msconn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			msconn.setConnectTimeout(30000); // 将读取超时设置为指定的超时时间，以毫秒为单位。
			// conn.setReadTimeout(60000);
			// 发送POST请求必须设置如下两行
			msconn.setDoOutput(true);
			msconn.setDoInput(true);
			msconn.setRequestMethod("POST");
			// Post 请求不能使用缓存
			msconn.setUseCaches(false);
			// 建立连接
			msconn.connect();
			outputStream = msconn.getOutputStream();
			WechatTemplate wechatTemplate = new WechatTemplate();
			wechatTemplate.setTemplate_id(templateID);
			wechatTemplate.setTouser(openId);
			Map<String, WeChatTemplateData> mapdata = new HashMap<String, WeChatTemplateData>();
			WeChatTemplateData first = new WeChatTemplateData();
			first.setValue(header);
			// first.setColor("#173177");
			mapdata.put("first", first);

			WeChatTemplateData second = new WeChatTemplateData();
			second.setValue(msg1+"["+msg2+"]");
			second.setColor("#173177");
			mapdata.put("keyword1", second);

			WeChatTemplateData third = new WeChatTemplateData();
			third.setValue(msg3);
			third.setColor("#173177");
			mapdata.put("keyword2", third);

//			WeChatTemplateData forth = new WeChatTemplateData();
//			forth.setValue(msg3);
//			forth.setColor("#173177");
//			mapdata.put("keyword3", forth);

			WeChatTemplateData five = new WeChatTemplateData();
			five.setValue(msg4);
			five.setColor("#173177");
			mapdata.put("remark", five);
			wechatTemplate.setData(mapdata);

			JSONArray wechatTemplatearray = JSONArray.fromObject(wechatTemplate);
			String wechatTemplateStr = wechatTemplatearray.toString().replace("[", " ");
			wechatTemplateStr = wechatTemplateStr.toString().replace("]", " ");
			System.out.println(wechatTemplateStr);
			outputStream.write(wechatTemplateStr.getBytes("UTF-8"));
			// outputStream.close();
			msreader = new BufferedReader(new InputStreamReader(msconn.getInputStream()));
			String msline;
			String msresult = "";
			while ((msline = msreader.readLine()) != null) {
				msresult += msline;
			}
			msreader.close();
			msconn.disconnect();
			System.out.println("&&&&&&&&&" + msresult);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null)
				try {
					outputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

		}

		return null;

	}

}
