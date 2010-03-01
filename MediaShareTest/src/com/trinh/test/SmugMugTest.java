package com.trinh.test;

import java.util.*;

import com.trinh.*;
import com.trinh.exceptions.*;

import junit.framework.TestCase;

public class SmugMugTest extends TestCase {
	public void testLoginWithHashSuccess() {
		int userId = 111;
		String hashPassword = "xxx";
		try {
			new SmugMug(new FakeRestClient(true), userId, hashPassword);
			assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception, login failed");
		}
	}
	public void testLoginWithHashFailure() {
		int userId = 111;
		String hashPassword = "xxx";
		try {
			new SmugMug(new FakeRestClient(false), userId, hashPassword);
			fail("Login should fail");
		} catch (SmugMugBadAuthentication e) {
			assertTrue(true);
		} catch (SmugMugInternalError e) {
			fail("Unexpected Exception");
		}
	}
	public void testGetAllAlbums() {
		int userId = 111;
		String hashPassword = "xxx";
		try {
			SmugMug smugmug = new SmugMug(new FakeRestClient(true), userId, hashPassword);
			ArrayList<SmugMugAlbum> albums = smugmug.getAlbums();
			assertEquals(74, albums.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception");
		}
	}
}
