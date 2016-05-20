package tests;

import static org.junit.Assert.*;


import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;

public class WorldTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	private World world1;
	private Log log1;
	private Boulder b1;
	@Before
	public void setUp() throws Exception {
		int tertype1[][][] = new int[5][5][5];
		tertype1[4][4][4] = 1;
		world1 = new World(tertype1, null);
		double posl[] = {2,3,4};
		log1 = new Log(posl,world1);
		double posb[] = {3,2,4};
		b1 = new Boulder(posb,world1);
	
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void passableTest() {
		//TODO:setworld
		int pos1[] = {1,2,1};
		int pos2[] = {4,4,4};
		boolean result1 = world1.passable(pos1);
		assertTrue(result1);
		boolean result2 = world1.passable(pos2);
		assertFalse(result2);
			
	}
	@Test
	public final void logTest(){
		Set<Log> log = world1.getLogsInWorld();
		int length = log.size();
		world1.addStufToWorld(log1);
		assertTrue(length < world1.getLogsInWorld().size());
	}
	@Test
	public final void boulderTest(){
		Set<Boulder> b = world1.getBouldersInWorld();
		int length = b.size();
		world1.addStufToWorld(b1);
		assertTrue(length < world1.getBouldersInWorld().size());
	}
	@Test
	public final void getNBcubesTest(){
		int[][][] terrain = new int[5][5][5];
		World world = new World(terrain,null);
		assertEquals(5,world.getNBXCubes());
		assertEquals(5,world.getNBYCubes());
		assertEquals(5,world.getNBZCubes());
	}
	@Test
	public final void unitsTest(){
		Set<Unit> b = world1.listUnits();
		int length = b.size();
		double[] position = {2,2,2};
		Faction faction = new Faction();
		Unit unit1 = new Unit(position, "Larry", 50, 50, 50, 50,world1,faction);
		world1.addUnits(unit1);
		assertTrue(length < world1.listUnits().size());
	}
	@Test
	public final void getTerrainOnPosTest(){
		int[][][] terrain = new int[5][5][5];
		World world = new World(terrain,null);
		int[] position = {2,2,2};
		assertTrue(world.getTerrainOnPosition(position)==0);
	}
	@Test
	public final void factionsTest(){
		Set<Faction> b = world1.listFactions();
		int length = b.size();
		Faction fact = new Faction();
		world1.addFactions(fact);
		assertTrue(length < world1.listFactions().size());
	}
	@Test
	public final void setCubeTypeTest(){
		int[][][] terrain = new int[5][5][5];
		World world = new World(terrain,null);
		world.setCubeType(2, 2, 2, 2);
		int[] position = {2,2,2};
		assertTrue(world.getTerrainOnPosition(position)==2);
	}
	@Test
	public final void spawnRandomTest(){
		assertTrue( world1.spawnRandomUnit(false) instanceof Unit);
		assertTrue( world1.spawnRandomUnit(true) instanceof Unit);
	}
	
	
	

}
