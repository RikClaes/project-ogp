package hillbillies.tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Faction;
import hillbillies.model.Unit;
import hillbillies.model.World;

public class UnitTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	private Unit newUnit1, attacker1, defender1,restingUnit,workingUnit,movingUnit;
	@Before
	public void setUp() throws Exception {
		double[] pos = {3,3,0};
		double[] posA = {2,2,2};
		double[] posD = {2,2,2};
		double[] posAdvT = {2,2,0};
		int[][][] terrain = new int[5][5][5];
		World world = new World(terrain,null); 
		Faction faction = new Faction();
		newUnit1 = new Unit(pos, "Larry", 100,100,100,100,world ,faction);
		attacker1 = new Unit(posA, "Bob", 200, 200, 200, 200,world,faction);
		defender1 = new Unit(posD, "JZa", 1, 1, 1, 1,world,faction);
		restingUnit = new Unit(posAdvT, "Resty", 100,100,100,100,world ,faction);
		workingUnit = new Unit(posAdvT, "Worky", 100,100,100,100,world ,faction);
		movingUnit = new Unit(posAdvT, "Movy", 100,100,100,100,world ,faction);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPosition() {
		double[] position = {1,1,1};
		position[0] = 1;
		position[1] = 1;
		position[2] = 1;
		int[][][] terrain = new int[5][5][5];
		World world = new World(terrain,null); 
		Faction faction = new Faction();
		Unit newUnit = new Unit(position, "Larry", 50, 50, 50, 50,world,faction);
		assertEquals(position[0], newUnit.getPosition()[0], 0);
		assertEquals(position[1], newUnit.getPosition()[1], 0);
		assertEquals(position[2], newUnit.getPosition()[2], 0);
		
		
	}
	@Test
	public void testSetPosition() {
		double[] position = {2,1,0};
		
		double[] newpos = {3,4,1};
		int[][][] terrain = new int[5][5][5];
		World world = new World(terrain,null); 
		Faction faction = new Faction();
		Unit newUnit = new Unit(position, "Larry", 50, 50, 50, 50,world,faction);
		newUnit.setPosition(newpos);
		assertEquals(newpos[0], newUnit.getPosition()[0], 0);
		assertEquals(newpos[1], newUnit.getPosition()[1], 0);
		assertEquals(newpos[2], newUnit.getPosition()[2], 0);
		
			
	}
	
	
	@Test
	public final void testName(){
		newUnit1.setName("Bobby");
		assertEquals("Bobby", newUnit1.getName());
		
		
	}
	
	
	
	@Test
	public final void testWeightEqual(){
		newUnit1.setWeight(150);
		assertEquals(150, newUnit1.getWeight());
		
	}

	@Test 
	public final void testWeightNotEqualLessMinWeight(){
		newUnit1.setWeight(25);
		assertNotEquals(25, newUnit1.getWeight());
		
	}
	
	@Test 
	public final void testWeightNotEqualMoreMaxWeight(){
		newUnit1.setWeight(202);
		assertNotEquals(202,newUnit1.getWeight());
	}
	@Test
	public final void testStrengthEqual(){
		newUnit1.setStrength(150);
		assertEquals(150, newUnit1.getStrength());
		
	}

	@Test 
	public final void testStrenghtNotEqualLessMinStrength(){
		newUnit1.setStrength(0);
		assertNotEquals(0, newUnit1.getStrength());
		
	}
	
	@Test 
	public final void testStrengthNotEqualMoreMaxtrength(){
		newUnit1.setStrength(202);
		assertNotEquals(202,newUnit1.getStrength());
	
	}
	@Test
	public final void testAgilityEqual(){
		newUnit1.setAgility(150);
		assertEquals(150, newUnit1.getAgility());
		
	}

	@Test 
	public final void testAgilityNotEqualLessMinAgility(){
		newUnit1.setAgility(0);
		assertNotEquals(0, newUnit1.getAgility());
		
	}
	
	@Test 
	public final void testAgilityNotEqualMoreMaxAgility(){
		newUnit1.setAgility(202);
		assertNotEquals(202,newUnit1.getAgility());
	
	}
	
	@Test
	public final void testToughnessEqual(){
		newUnit1.setToughness(150);
		assertEquals(150, newUnit1.getToughness());
		
	}

	@Test 
	public final void testToughnessNotEqualLessMinToughness(){
		newUnit1.setToughness(0);
		assertNotEquals(0, newUnit1.getToughness());
		
	}
	
	@Test 
	public final void testToughnessNotEqualMoreMaxToughness(){
		newUnit1.setToughness(202);
		assertNotEquals(202,newUnit1.getToughness());
	
	}
	
	@Test
	public final void testMaxHit(){
		assertEquals((int)(200*(newUnit1.getWeight()/100)*(newUnit1.getToughness()/100)+1), newUnit1.getMaxHitpoints());
	}
	
	
	
	@Test
	public final void testGetHit(){
		newUnit1.setHitpoints(42);
		assertEquals(42, newUnit1.getHitpoints(),0);
		
	}
	
	
	
	@Test
	public final void testMaxStamina(){
		assertEquals((int)(200*(newUnit1.getWeight()/100)*(newUnit1.getToughness()/100)+1), newUnit1.getMaxHitpoints());
	}
	

	
	@Test
	public final void testGetStamina(){
		newUnit1.setHitpoints(42);
		assertEquals(42, newUnit1.getHitpoints(),0);
		
	}
	

	@Test
	public final void testAttackDirectHit(){
		double originalHitpoints = defender1.getHitpoints();
		attacker1.attack(defender1);
		assertTrue(originalHitpoints > defender1.getHitpoints());
		
	}
	@Test
	public final void getCubePosTest(){
		int[] pos = {3,3,0};
		assertArrayEquals(newUnit1.getCubePosition(),pos);
	}
	@Test
	public final void testMoveAdjectToandAngleandSprinting(){
		newUnit1.moveToAdject(0,1,0);
		assertTrue(newUnit1.isMoving());
		assertEquals(newUnit1.getAngle(),-Math.PI/2,0.001);
		newUnit1.startSprinting();
		assertTrue(newUnit1.isSprinting());
	}
	@Test
	public final void testResting(){
		newUnit1.setHitpoints(1);
		newUnit1.rest();
		assertTrue(newUnit1.isResting());
	}
	@Test
	public final void testWork(){
		newUnit1.workAt(3,3,0);
		assertTrue(newUnit1.isWorking());
	}
	@Test
	public final void testStartDefault(){
		newUnit1.startDefaultBehaviour();
		assertTrue(newUnit1.isDefaultBehaving());
	}
	
	@Test
	public final void testAdvanceTimeResting(){
		restingUnit.setHitpoints(0.5);
		restingUnit.setStamina(0.5);
		restingUnit.rest();
		restingUnit.advanceTime(0.1);
		restingUnit.advanceTime(0.1);
		assertTrue(  0.5 < restingUnit.getHitpoints() );
		assertTrue( 0.5 < restingUnit.getStamina());
	}
	@Test
	public final void testAdvanceTimeWorking(){
		int x = workingUnit.getCubePosition()[0];
		int y = workingUnit.getCubePosition()[1];
		int z = workingUnit.getCubePosition()[2];
		workingUnit.workAt(x, y, z);
		assertTrue(workingUnit.isWorking());
		workingUnit.advanceTime(0.1);
	}
	@Test
	public final void testAdvanceTimeMovementToAdj(){
		double[] initial = movingUnit.getPosition();
		movingUnit.moveToAdject(0,-1,0);
		movingUnit.advanceTime(0.1);
		assertFalse(Arrays.equals(initial, movingUnit.getPosition()));
	}
	@Test
	public final void testMovementTo(){
		int[] cube = {0,0,0};
		newUnit1.moveTo(cube);
		assertTrue(newUnit1.isMovingTo());
	}
}
