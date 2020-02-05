package com.larry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@Component
public class AppConf {
	@Value("${weixin.user.url}")
	private String getWeixinUserUrl;
	@Value("${page.users.url}")
	private String pageUsersUrl;
	@Value("${webauth.url}")
	private String webAuthUrl;
	@Value("${weixinpage.code}")
	private String weixinpageCode;
	@Value("${access.token.url}")
	private String accessTokenUrl;
	@Value("${weixin.appID}")
	private String appID;
	@Value("${weixin.appsecret}")
	private String appsecret;
	@Value("${weixin.subscribe.msg}")
	private String weixinSubscribMsg;
	@Value("${weixin.message.url}")
	private String weixinMessageUrl;
//	@Value("${weixin.ordersub.msg.template}")
//	private String weixinOrderSubTemp;
	@Value("${weixin.ordercancle.msg.template}")
	private String weixinOrderCanTemp;
	@Value("${weixin.ordertake.msg.template}")
	private String weixinOrderTakeTemp;
	@Value("${weixin.ordercomplete.msg.template}")
	private String weixinOrdercompTemp;
//	@Value("${weixin.ordersub.msg.head}")
//	private String weixinOrderSubMsgHead;
	@Value("${weixin.ordercancle.msg.head}")
	private String weixinOrderCancMsgHead;
	@Value("${weixin.ordertake.msg.head}")
	private String weixinOrderTakeMsgHead;
	@Value("${weixin.ordercomplete.msg.head}")
	private String weixinOrderCompleteMsgHead;
	@Value("${weixin.ordermsg.remark}")
	private String weixinMsgRemark;
	@Value("${weixin.ordertakemsg.remark}")
	private String weixinOrderTakeRemark;
	@Value("${weixin.ordermsg.canclebyuser}")
	private String weixinmsgCacByUser;
	@Value("${weixin.ordermsg.canclebyother}")
	private String weixinmsgCacByOther;
	@Value("${score_rate}")
	private String scoreRate;
	@Value("${maintenance_flag}")
	private String maintenance_flag;
	@Value("${hd_wel_message}")
	private String hd_wel_message;
	@Value("${about_us}")
	private String about_us;
	
}
