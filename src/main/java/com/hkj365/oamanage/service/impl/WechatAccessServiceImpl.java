package com.hkj365.oamanage.service.impl;

import java.security.MessageDigest;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hkj365.oamanage.service.IWechatAccessService;

@Service("wechatAccessService")
public class WechatAccessServiceImpl implements IWechatAccessService{
	
	private static final Logger logger = LoggerFactory.getLogger(WechatAccessServiceImpl.class);
	private String token="oatest";
	private final static String charset = "UTF-8";

	public String getToken(String timestamp, String nonce){
		logger.info("enty the WechatAccessServiceImpl's method getToken",token);
		String strDes = "";
		String[] str={token,timestamp,nonce};
		Arrays.sort(str);
		String OderString = str[0] + str[1] +str[2];
		try{
			byte[] bt  = OderString.getBytes(charset);
			MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
			sha1.reset();
			sha1.update(bt);
			 byte[] sha1Digest=sha1.digest();
			String tmp="";
			for(int i = 0; i<sha1Digest.length; i++){
				tmp = (Integer.toHexString(sha1Digest[i] & 0xFF));
				if (tmp.length() == 1) {
					strDes += "0";
				}
				strDes += tmp;
			}
		}catch(Exception e){
			logger.error("jsapi_ticket签名失败:", e);
		}
		logger.info("leaving the WechatAccessServiceImpl without error.");
		return strDes;	
	}
}
