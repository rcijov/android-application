package com.simona.halep.test;

import org.junit.Test;

import com.simona.halep.Api.StatsApi;
import com.simona.halep.Database.Entities.Stats;

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
		int i = 0;
		for(Stats stat : statsApi.getStats())
		{
			assertNotNull(stat);
			i = i + 1;
		}
		assertTrue(i == 7);
	}

}
