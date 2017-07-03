package com.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import com.kistest.common.CommonUtil;
import com.kistest.common.GsonUtil;
import com.kistest.common.kis.KisDepartment;
import com.kistest.common.kis.KisUser;

public class KisTest {
	
	private static final String KIS_SERVER_URL = "http://kd.cmcloud.cn/Kisoemapi/get_server_url";
	private static final String ROUTER_URL = "https://kisgz.kingdee.com/Kisopenapi/router/";
	
	private static final String eid = "10872947";
	private static final String clientid = "1494496395";
	private static final String clientsecret = "5E3NbKhkv5FCth2V8CGUCz4yrDXr3JatEadQJjKD";
	private static final String authtoken = "506949FFFC3044999EEBFC99201009A8";

	private static final String netid = "1087294786303";
	private static final String server_url = "139.199.187.221";
	
	@Test
	public void test() throws UnsupportedEncodingException {
		String param = "eid=EID&timestamp=TIMESTAMP&state=STATE&client_id=CLIENT_ID&access_token=ACCESS_TOKEN";
		String dateStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		String timestamp = URLEncoder.encode(dateStr, "UTF-8");
		String state = CommonUtil.getRandomCode(16);
		String accesstoken = CommonUtil.getMD5(dateStr+clientid+clientsecret+authtoken+state);
		
		param = param.replace("EID", eid).replace("TIMESTAMP", timestamp).replace("STATE", state).replace("CLIENT_ID", clientid).replace("ACCESS_TOKEN", accesstoken);
		System.out.println("param:"+param);
		String result = CommonUtil.sendGet(KIS_SERVER_URL, param, null);
		System.out.println(result);
	}
	
	@Test
	public void syncUser() throws UnsupportedEncodingException {
		String param = "eid=EID&netid=NETID&client_id=CLIENT_ID&method=kis.APP004088.acctplatform.AcctController.DealAcctPlatForm&timestamp=TIMESTAMP&ver=2.0&access_token=ACCESS_TOKEN&state=STATE"
				+ "&custdata={\"ProductID\": \"OEM0317P01\", \"AccountDB\": \"AIS20170627061258\", \"Data\": {\"Action\": \"SyncUser\", \"Recordset\": RECORDSET}}";
		
		String dateStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		String timestamp = URLEncoder.encode(dateStr, "UTF-8");
		String state = CommonUtil.getRandomCode(16);
		String accesstoken = CommonUtil.getMD5(dateStr+clientid+clientsecret+authtoken+state);
		
		List<KisUser> list = new ArrayList<KisUser>();
		list.add(new KisUser("U001", "张三", "123456"));
		list.add(new KisUser("U002", "李四", "654321"));
		String json = GsonUtil.toJson(list);
		
		param = param.replace("EID", eid).replace("NETID", netid).replace("CLIENT_ID", clientid).replace("TIMESTAMP", timestamp).replace("ACCESS_TOKEN", accesstoken).replace("STATE", state).replace("RECORDSET", json);
		System.out.println("param:"+param);
		String result = CommonUtil.sendPost(ROUTER_URL, param);
		System.out.println(result);
	}
	
	@Test
	public void syncDepartment() throws UnsupportedEncodingException {//KIS_Sample
		String param = "eid=EID&netid=NETID&client_id=CLIENT_ID&method=kis.APP004088.acctplatform.AcctController.DealAcctPlatForm&timestamp=TIMESTAMP&ver=2.0&access_token=ACCESS_TOKEN&state=STATE"
				+ "&custdata={\"ProductID\": \"OEM0317P01\", \"AccountDB\": \"KIS_Sample\", \"Data\": {\"Action\": \"SyncDepartment\", \"Recordset\": RECORDSET}}";
		
		String dateStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		String timestamp = URLEncoder.encode(dateStr, "UTF-8");
		String state = CommonUtil.getRandomCode(16);
		String accesstoken = CommonUtil.getMD5(dateStr+clientid+clientsecret+authtoken+state);
		
		List<KisDepartment> list = new ArrayList<KisDepartment>();
		list.add(new KisDepartment("", "08", "测试部", "", "", ""));
		list.add(new KisDepartment("", "09", "开发部", "", "", ""));
		String json = GsonUtil.toJson(list);
		
		param = param.replace("EID", eid).replace("NETID", netid).replace("CLIENT_ID", clientid).replace("TIMESTAMP", timestamp).replace("ACCESS_TOKEN", accesstoken).replace("STATE", state).replace("RECORDSET", json);
		System.out.println("param:"+param);
		String result = CommonUtil.sendPost(ROUTER_URL, param);
		System.out.println(result);
    }
	
	@Test
	public void getAcctList() throws UnsupportedEncodingException {
		String param = "eid=EID&netid=NETID&client_id=CLIENT_ID&method=kis.APP004088.acctplatform.AcctController.DealAcctPlatForm&timestamp=TIMESTAMP&ver=2.0&access_token=ACCESS_TOKEN&state=STATE"
				+ "&custdata={\"ProductID\": \"OEM0317P01\", \"AccountDB\": \"KIS_Sample\", \"Data\": {\"Action\": \"GetAcctList\"}}";
		
		String dateStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		String timestamp = URLEncoder.encode(dateStr, "UTF-8");
		String state = CommonUtil.getRandomCode(16);
		String accesstoken = CommonUtil.getMD5(dateStr+clientid+clientsecret+authtoken+state);
		
		param = param.replace("EID", eid).replace("NETID", netid).replace("CLIENT_ID", clientid).replace("TIMESTAMP", timestamp).replace("ACCESS_TOKEN", accesstoken).replace("STATE", state);
		System.out.println("param:"+param);
		String result = CommonUtil.sendPost(ROUTER_URL, param);
		System.out.println(result);
	}
	
}
