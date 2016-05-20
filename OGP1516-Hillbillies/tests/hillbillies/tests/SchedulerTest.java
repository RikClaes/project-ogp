package hillbillies.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import hillbillies.model.Scheduler;
import hillbillies.model.Task;


public class SchedulerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	private Task task1, task2, task3;
	private Scheduler sch1,sch2;
	@Before
	public void setUp() throws Exception {
		int[] cube = {0,0,0};
		task1 = new Task("test1",1,null,cube);
		task2 = new Task("test1",2,null,cube);
		task3 = new Task("test1",3,null,cube);
		sch1 = new Scheduler();
		sch1.addTask(task1);
		sch1.addTask(task2);
		sch2 = new Scheduler();
	}
	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void containsAndAddTest(){
		List<Task> l1 = new ArrayList<Task>();
		l1.add(task1);
		List<Task> l2 = new ArrayList<Task>();
		l2.add(task3);
		assertTrue(sch1.containsTasks(l1));
		assertFalse(sch1.containsTasks(l2));
	}
	@Test
	public void getHighestPriorityTest(){
		assertEquals(sch1.highestPriorityTaskNotExcecuted(),task2);
	}
	@Test
	public void iteratorTest(){
		assertFalse(sch2.iterator().hasNext());
		assertTrue(sch1.iterator().hasNext());
	}
	@Test
	public void removeTest(){
		sch1.removeTask(task2);
		List<Task> l1 = new ArrayList<Task>();
		l1.add(task2);
		assertFalse(sch1.containsTasks(l1));
	}
	@Test
	public void replaceTest(){
		sch1.replaceAByB(task1, task3);
		assertFalse(sch1.containsTask(task1));
		assertTrue(sch1.containsTask(task3));
		
	}

}
