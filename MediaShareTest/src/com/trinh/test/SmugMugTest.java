package com.trinh.test;

import com.trinh.SmugMug;
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
}
