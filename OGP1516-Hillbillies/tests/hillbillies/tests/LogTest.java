package hillbillies.tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Log;
import hillbillies.model.World;

public class LogTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	private World world1;
	private Log log1,log2,log3;
	@Before
	public void setUp() throws Exception {
		int tertype1[][][] = new int[5][5][5];
		world1 = new World(tertype1,null);
		double pos1[] = {2,3.5,4};
		double pos2[] = {2.1,3,0};
		log1 = new Log(pos1,world1);
		log2 = new Log(pos2,world1);
		log3 = new Log(pos1,world1);
	}
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public final void testGetPosition(){
		double pos1[] = {2,3.5,4};
		assertTrue(Arrays.equals(pos1, log1.getPosition()));
		double pos2[] = {2.1,3,0};
		assertTrue(Arrays.equals(pos2, log2.getPosition()));
	}
	@Test
	public final void testSetPositionLegalCase(){
		double[] newPos = {2,2,1};
		log1.setPosition(newPos);
		assertTrue(Arrays.equals(newPos, log1.getPosition()));
	}
	@Test
	(expected = IllegalArgumentException.class)public void testSetPositionIllegalCase() throws Exception{
		double[] newPos = {2,6,1};
		log3.setPosition(newPos);
	}
	@Test
	public void testGetCubePosition(){
		int[] cpos ={2,3,0};
		assertArrayEquals(cpos,log2.getCubePosition());
	}
	@Test
	public void testGetWeight(){
		assertTrue(log2.getWeight()>= 10);
		assertTrue(log2.getWeight()<= 50);
	}
	@Test
	public void testAdvanceTimeFalling(){
		double[] initialPos = log1.getPosition();
		log1.advanceTime(0.1);
		assertTrue(initialPos[2] > log1.getPosition()[2]);
		assertEquals(initialPos[0],log1.getPosition()[0],0);
		assertEquals(initialPos[1],log1.getPosition()[1],0);
	}
	public void testAdvancerTimeNotFalling(){
		double[] initialPos = log2.getPosition();
		log2.advanceTime(0.1);
		assertTrue(Arrays.equals(initialPos , log2.getPosition()));
		
	}

}
