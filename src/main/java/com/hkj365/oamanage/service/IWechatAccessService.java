package com.hkj365.oamanage.service;

public interface IWechatAccessService {
	public String getToken(String timestamp, String nonce);

}
