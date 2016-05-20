package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.World;


public class BoulderTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	private World world1;
	private Boulder boulder1,boulder2,boulder3;
	@Before
	public void setUp() throws Exception {
		int tertype1[][][] = new int[5][5][5];
		world1 = new World(tertype1,null);
		double pos1[] = {2,3.5,4};
		double pos2[] = {2.1,3,0};
		boulder1 = new Boulder(pos1,world1);
		boulder2 = new Boulder(pos2,world1);
		boulder3 = new Boulder(pos1,world1);
	}
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public final void testGetPosition(){
		double pos1[] = {2,3.5,4};
		assertEquals(pos1[0], boulder1.getPosition()[0],0.1);
		assertEquals(pos1[1], boulder1.getPosition()[1],0.1);
		assertEquals(pos1[2], boulder1.getPosition()[2],0.1);
		double pos2[] = {2.1,3,0};
		assertEquals(pos2[0], boulder2.getPosition()[0],0.1);
		assertEquals(pos2[1], boulder2.getPosition()[1],0.1);
		assertEquals(pos2[2], boulder2.getPosition()[2],0.1);
	}
	@Test
	public final void testSetPositionLegalCase(){
		double[] newPos = {2,2,1};
		boulder1.setPosition(newPos);
		assertTrue(Arrays.equals(newPos, boulder1.getPosition()));
	}
	@Test
	(expected = IllegalArgumentException.class)public void testSetPositionIllegalCase() throws Exception{
		double[] newPos = {2,6,1};
		boulder3.setPosition(newPos);
	}
	@Test
	public void testGetCubePosition(){
		int[] cpos ={2,3,0};
		assertArrayEquals(cpos,boulder2.getCubePosition());
	}
	@Test
	public void testGetWeight(){
		assertTrue(boulder2.getWeight()>= 10);
		assertTrue(boulder2.getWeight()<= 50);
	}
	@Test
	public void testAdvanceTimeFalling(){
		double[] initialPos = boulder1.getPosition();
		boulder1.advanceTime(0.1);
		assertTrue(initialPos[2] > boulder1.getPosition()[2]);
		assertEquals(initialPos[0],boulder1.getPosition()[0],0);
		assertEquals(initialPos[1],boulder1.getPosition()[1],0);
	}
	public void testAdvancerTimeNotFalling(){
		double[] initialPos = boulder2.getPosition();
		boulder1.advanceTime(0.1);
		assertTrue(Arrays.equals(initialPos , boulder2.getPosition()));
		
	}

}
