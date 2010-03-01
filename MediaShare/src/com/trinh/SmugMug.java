package com.trinh;

import java.io.*;
import java.util.*;

import org.apache.http.client.*;
import org.json.*;

import android.util.*;

import com.trinh.exceptions.*;

public class SmugMug {
	private final static String API_KEY = "ggmky75M7OFLskaXu6S6wu1SD6degX1Q";
	private final static String API_VERSION = "1.2.1";
	private final static String SMUGMUG_URL = "https://api.smugmug.com/services/api/json/" + API_VERSION;
	private final static String LOGIN_URL = SMUGMUG_URL + "/?method=smugmug.login.withHash";
	private final static String ALBUMS_URL = SMUGMUG_URL + "/?method=smugmug.albums.get";
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
					.connect(LOGIN_URL
							+ "&APIKey=" + API_KEY + "&UserID=" + userId + "&PasswordHash="
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

	public ArrayList<SmugMugAlbum> getAlbums() {
		ArrayList<SmugMugAlbum> albums = new ArrayList<SmugMugAlbum>();
		try {
			JSONObject json = client.connect(ALBUMS_URL + "&sessionId=" + sessionId);
			JSONArray jsonArray = json.getJSONArray("Albums");
			for (int index = 0; index < jsonArray.length(); index++)
			{
				JSONObject jsonAlbum = jsonArray.getJSONObject(index);
				int albumId = jsonAlbum.getInt("id");
				String albumName = jsonAlbum.getString("Title");
				albums.add(new SmugMugAlbum(albumId, albumName));
			}
			System.out.println(jsonArray.toString(3));
			
		} catch (ClientProtocolException e) {
			Log.getStackTraceString(e);
		} catch (IOException e) {
			Log.getStackTraceString(e);
		} catch (JSONException e) {
			Log.getStackTraceString(e);
		}
		return albums;
	}
}
