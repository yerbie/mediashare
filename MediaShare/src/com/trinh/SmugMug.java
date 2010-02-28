package com.trinh;

import org.json.*;

import android.util.*;

import com.trinh.exceptions.*;

public class SmugMug {
	private final static String API_KEY = "ggmky75M7OFLskaXu6S6wu1SD6degX1Q";
	private final static String API_VERSION = "1.2.1";
	private final static String TAG = "trinh.smugmug";

	private RestClient client;
	private String sessionId;

	public SmugMug(RestClient client, int userId, String hashPassword)
			throws SmugMugBadAuthentication, SmugMugInternalError {
		this.client = client;
		loginWithHash(userId, hashPassword);
	}

	private void loginWithHash(int userId, String hashPassword)
			throws SmugMugBadAuthentication, SmugMugInternalError {
		try {
			JSONObject json = client
					.connect("https://api.smugmug.com/services/api/json/"
							+ API_VERSION
							+ "/?method=smugmug.login.withHash&APIKey="
							+ API_KEY + "&UserID=" + userId + "&PasswordHash="
							+ hashPassword);
			sessionId = json.getJSONObject("Login").getJSONObject("Session")
					.getString("id");
			Log.i(TAG, sessionId);
		} catch (JSONException e) {
			Log.getStackTraceString(e);
			throw new SmugMugBadAuthentication();
		} catch (Exception e) {
			throw new SmugMugInternalError();
		}
	}
}
