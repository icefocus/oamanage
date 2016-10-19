package com.hkj365.oamanage.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hkj365.oamanage.service.IWechatAccessService;

@Controller
@RequestMapping(value="/",method=RequestMethod.GET, produces=MediaType.TEXT_PLAIN_VALUE)
public class WechatAccessController{
	private final static Logger logger=LoggerFactory.getLogger(WechatAccessController.class);
	@Autowired
	private IWechatAccessService wechatAccessService;
	
	@ResponseBody
	@RequestMapping("/verify")
	public String VerifyToken(HttpServletRequest request){
		String signature = request.getParameter("signature");
		logger.info("signature:"+signature);
		String echostr = request.getParameter("echostr");
		logger.info("echostr:"+echostr);
		String timestamp = request.getParameter("timestamp");
		logger.info("timestamp:"+ timestamp);
		String nonce = request.getParameter("nonce");
		logger.info("nonce:"+nonce);
		String digest = wechatAccessService.getToken(timestamp,nonce);
		logger.info("digest:"+ digest);
		if (digest.equals(signature)){
			logger.info("return:"+echostr);
			return echostr;
		}
		else{
			logger.info("retun kong");
			return "";
		}
	}

}
