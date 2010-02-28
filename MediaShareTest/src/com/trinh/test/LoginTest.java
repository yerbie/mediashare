package com.trinh.test;

import com.trinh.SmugMug;

import junit.framework.TestCase;

public class LoginTest extends TestCase {
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
}
