package com.larry.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.larry.AppConf;
import com.larry.bean.WeixinUser;
import com.larry.controller.MainController;
import com.larry.entity.UserPO;
import com.larry.service.AuthUserService;
import com.larry.service.WeixinMessageService;
import com.larry.service.WeixinUserService;
import com.larry.utils.CheckUtil;
import com.larry.utils.WeixinMessage;
import com.larry.utils.WeixinUtils;

@WebServlet(urlPatterns = "/weixin.int")
public class WeixinServlet extends HttpServlet {
	private final static Logger logger = LoggerFactory.getLogger(WeixinServlet.class);
	@Autowired
	private WeixinMessageService wxMsgService;
	@Autowired
	private AppConf appConf;
	@Autowired
	private AuthUserService userService;
	@Autowired
	private WeixinUserService weixinUserService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		// System.
		String echostr = req.getParameter("echostr");
		PrintWriter pw = resp.getWriter();
		if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
			pw.print(echostr);
		}

	}

	/**
	 * WeiChat event response
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		String message = "success";
		try {
			Map<String, String> map = WeixinUtils.xmlToMap(req);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			String eventType = map.get("Event");

			System.out.println(fromUserName);
			System.out.println(toUserName);
			System.out.println(msgType);
			System.out.println(content);
			System.out.println(eventType);

			if (WeixinMessage.MSGTYPE_EVENT.getMessageType().equals(msgType)) {
				if (WeixinMessage.MESSAGE_SUBSCIBE.getMessageType().equals(eventType)) {
					message = wxMsgService.subscribeForText(toUserName, fromUserName, appConf.getWeixinSubscribMsg());
					WeixinUser weixinUser = weixinUserService.getUserInfo(weixinUserService.getAccessToken().getToken(),
							fromUserName);
					UserPO user = new UserPO();
					user.setWeixin_openId(weixinUser.getOpenId());
					String nickName = weixinUser.getNickname();
					if(containsEmoji(nickName)) {
						user.setWeixin_nickname("emoji");
					}else {
						user.setWeixin_nickname(weixinUser.getNickname());
					}
					
					user.setProvince(weixinUser.getProvince());
					user.setCountry(weixinUser.getCountry());
					// user.setCity(weixinUser.getCity());
					try {
						userService.save(user);
					}catch(Exception e) {
						user.setWeixin_nickname("emoji");
						userService.save(user);
					}
					
				} else if (WeixinMessage.MESSAGE_CLICK.getMessageType().equals(eventType)) {
					String eventKey = map.get("EventKey");
					if (eventKey != null && "contact_hd".equals(eventKey)) {
						message = wxMsgService.direct2helpDesk(toUserName, fromUserName, "");

						out.println(new String(message.getBytes("ISO-8859-1"), "UTF-8"));
						String message2 = wxMsgService.sendTextMsg(toUserName, fromUserName,
								appConf.getHd_wel_message());
						message = message2;

					} else if (eventKey != null && "about_us".equals(eventKey)) {
						message = wxMsgService.sendTextMsg(toUserName, fromUserName, appConf.getAbout_us());
					}
				}

			} else if (WeixinMessage.MESSAGE_TEXT.getMessageType().equals(msgType)) {
				if ("1".equals(content)) {

				} else if ("2".equals(content)) {

				} else {
					message = wxMsgService.subscribeForText(toUserName, fromUserName, appConf.getWeixinSubscribMsg());
				}
			}
			out.println(new String(message.getBytes("ISO-8859-1"), "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}

	private boolean containsEmoji(String source) {
		if (source == null || "".equals(source)) {
			return false;
		}

		int len = source.length();

		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint)) {
				// do nothing，判断到了这里表明，确认有表情字符
				return true;
			}
		}

		return false;
	}

	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

}
