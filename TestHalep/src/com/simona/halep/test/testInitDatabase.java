package com.simona.halep.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.simona.halep.Database.InitDatabase;
import com.simona.halep.Database.Entities.Stats;

public class testInitDatabase {

	public static InitDatabase initDatabase = null;
	
	@Before
	public void setUp() throws Exception {
		initDatabase = initDatabase.getInstance(null);
	}

	@Test
	public void testGetStats() {
		List<Stats> stats = initDatabase.getStats();
		assertNotNull(stats);
	}

}
