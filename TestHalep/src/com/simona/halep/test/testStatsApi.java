package com.simona.halep.test;

import org.junit.Test;

import com.simona.halep.Api.StatsApi;

import junit.framework.TestCase;

public class testStatsApi extends TestCase {

	public static StatsApi statsApi = null;
	
	protected void setUp() throws Exception {
		statsApi = statsApi.getInstance();
		super.setUp();
	}
	
	@Test
	public void testGetStats()
	{
		assertTrue(statsApi.getStats().size() == 7);
	}

}
