package com.trinh.test;

import org.json.*;

import com.trinh.*;

public class FakeRestClient extends RestClient {
	private boolean success;
	
	public FakeRestClient(boolean success) {
		this.success = success;
	}
	private static String loginResponse = 
		"{\"stat\":\"ok\",\"method\":\"smugmug.login.withHash\",\"Login\":{\"User\":{\"NickName\":\"fakenick\",\"DisplayName\":\"Fake Name\"},\"AccountType\":\"Power\",\"FileSizeLimit\":12582912,\"SmugVault\":false,\"Session\":{\"id\":\"abcdef\"}}}";
	@Override
	public JSONObject connect(String url)  throws JSONException
	{
		System.out.println(loginResponse);
		if (success)
			return new JSONObject(loginResponse);
		else
			throw new JSONException("Invalid Login");
	}

}
