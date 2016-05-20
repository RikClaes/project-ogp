package hillbillies.model;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.expressions.Expression;
import hillbillies.model.statements.Statement;
/**
 * 
 * @author Rik Claes
 *
 */
public class Task {
	/**
	 * 
	 * @param name the name of the new task.
	 * @param priority the priority of the new task
	 * @param activity the activities of the task
	 * @post the name of the task is set to name
	 * 			|new.getName() = name
	 * @post the priority is set to priority
	 * 			|new.getPriority()=priority
	 * @post the activities of this task are set to activities
	 * 			|new.getActivities() = activities
	 * @post the cube of this task is set to cube.
	 * 			|new.getCube() = cube;
	 */
	public Task(String name, int priority, Statement activity,int[] cube){
		setName(name);
		setPriority(priority);
		setActivities(activity);
		setCube(cube);
	}
	/**
	 * 
	 * @param name the name of the new task.
	 * @param priority the priority of the new task
	 * @param activity the activities of the task
	 * @post the name of the task is set to name
	 * 			|new.getName() = name
	 * @post the priority is set to priority
	 * 			|new.getPriority()=priority
	 * @post the activities of this task are set to activities
	 * 			|new.getActivities() = activities
	 * @post the cube of this task is set to null.
	 * 			|new.getCube() = null;
	 */
	public Task(String name, int priority,Statement activity){
		setName(name);
		setPriority(priority);
		setActivities(activity);
		setCube(null);
		
	}
	/**
	 * 
	 * @param cube the cube were the task has to be executed
	 * @post the cube of this task is set to cube
	 * 		|new.getCube() = cube
	 */
	private void setCube(int[] cube) {
		this.cube= cube;	
	}
	/**
	 * 
	 * @return the world were the task has to be executed
	 */
	public int[] getCube(){
		return this.cube;
	}

	private int[] cube;
	/**
	 * @param activity the activeties to be set in this task
	 * @post the activities are set to activities.
	 * 		|new.getActivities() = activities
	 */
	private void setActivities(Statement activity) {
		this.activities = activity;	
	}
	/**
	 * 
	 * @return the activities of this task.
	 */
	public Statement getActivities(){
		return this.activities;
	}
	
	private String name;
	
	private int priority;
	
	private Statement activities;
	/**
	 * 
	 * @return the name of this task
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * 
	 * @return the priority of this task.
	 */
	public int getPriority(){
		return this.priority;
	}
	/**
	 * 
	 * @param name the name to be given to this task
	 * @post the name of the task is set to name
	 * 		|new.getName() = name
	 */
	private void setName(String name){
		this.name = name;
	}
	/**
	 * 
	 * @return the unit where to this task is assigned
	 */
	public Unit getUnitAssignedTo(){
		return this.assignedUnit;
		
	}
	/**
	 * 
	 * @return false if assignedUnit is null true otherwise
	 * 			| result == (this.getUnitAssignedTo() != null)
	 */
	public boolean isAssigned(){
		return (getUnitAssignedTo() != null);
	}
	/**
	 * 
	 * @param unit the unit were the task is to be assigned to
	 * @post the assignedUnit is set to unit
	 * 		| new.getUnitAssigned() == unit
	 */
	void setUnitAssignedTo(Unit unit){
		this.assignedUnit = unit;
	}
	
	private Unit assignedUnit;
	/**
	 * 
	 * @param priority the priority to be set in this task
	 * @post the priority of this task is set to priority
	 */
	@Model
	private void setPriority(int priority){
		this.priority = priority;
	}
	
	/**
	 * 
	 * @return a set of the schedulers in witch the task is included.
	 */
	public Set<Scheduler> getSchedulers(){
		return this.schedulers;
	}
	/**
	 * 
	 * @param scheduler the scheduler to be added
	 * @Pre schedulers doesn't contain scheduler and the scheduler is initalized.
	 * 		|!getSchedulers().contains(scheduler)||scheduler != null
	 * @effect the scheduler is added to schedulers.
	 * 		|this.schedulers.add(scheduler)
	 */
	public void addScheduler(Scheduler scheduler){
		assert (!getSchedulers().contains(scheduler)||scheduler != null);
		this.schedulers.add(scheduler);
	}
	/**
	 * 
	 * @param scheduler the scheduler to be removed
	 * @Pre scheduler must be in the list of schedulers this task is assigned to.
	 * 		|getSchedulers().contains(scheduler)
	 * @post the scheduler is removed from the list of schedulers this task is assigned to.
	 * 		|!this.schedulers.remove(this)
	 */
	public void removeScheduler(Scheduler scheduler){
		assert (getSchedulers().contains(scheduler));
		this.schedulers.remove(scheduler);
	}
	
	private Set<Scheduler> schedulers =  new HashSet<Scheduler>();
	
	public final HashMap<String, Expression> vars = new HashMap<String,Expression>();
	
	/**
	 * 
	 * @return vars: the hashmap containing the assigned variables.
	 */
	public HashMap<String, Expression> getVars(){
		return this.vars;
	}
	
	/**
	 * @Pre the task is assigned to a unit for execution
	 * 		|getUnitAssignedTo()!= null
	 * @effect the task of the unit to wich this task was assigned is set to null
	 * 		|this.getUnitAssignedTo().setTask(null)
	 * @post the unit to which this task is assigned is set to null.
	 * 		|new.getUnitAssignedTo()==null
	 * @post the priority of this task is set to the old priority -1
	 * 		|new.getPriority() == old.getPriority-1
	 * 
	 */
	void stopExecutionOfTask(){
		assert(getUnitAssignedTo()!= null);
		this.getUnitAssignedTo().setTask(null);
		this.setUnitAssignedTo(null);
		this.setPriority(getPriority()-1);
	}
	
	/**
	 * @Pre  the task is not yet terminated 
	 * 			|assert(!this.isTerminated)
	 * @effect   The task isn't in any scheduler anymore
	 * 			|for schedulers in getSchecdulers
	 * 			|	scheduler.removeTask(this);
	 * @post   This task  is terminated.
	 *       	| new.isTerminated() == true
	 */
	 void terminate() {
		 assert(!this.isTerminated);
		 for (Scheduler scheduler : getSchedulers()) {
			scheduler.removeTask(this);
		 }
		this.isTerminated = true;
		
	 }
	 
	 /**
	  * Return a boolean indicating whether or not this task
	  * is terminated.
	  */
	 @Basic 
	 public boolean isTerminated() {
		 return this.isTerminated;
	 }
	 
	 private boolean isTerminated = false;
	 
	 
	

}
