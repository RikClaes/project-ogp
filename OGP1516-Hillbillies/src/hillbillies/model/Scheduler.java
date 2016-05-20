package hillbillies.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

import be.kuleuven.cs.som.annotate.Model;


/**
 * @Invar  the scheduledTasks never contains tasks that are terminated.
 * 		|for(Task task :scheduledTasks)
 * 		| 	!task.isTerminated() 
 * @Invar the scheduledTasks never contains a non effective task.
 * 		|for(Task task :scheduledTasks)
 * 		|	task != null
 * @Invar scheduledTask is always effective
 * 		| getScheduledTasks() != null 
 * @author Rik Claes
 * 
 *
 */
public class Scheduler {
	/**
	 * 
	 */
	public Scheduler(){
		
	}
	/**
	 * 
	 * @return the list of scheduledTasks for this scheduler.
	 */
	@Model
	ArrayList<Task> getScheduledTasks(){
		return this.scheduledTasks;
	}
	private final ArrayList<Task>  scheduledTasks = new ArrayList<Task>();
	/**
	 * 
	 * @param task the task to add to the list of scheduledTasks in this scheduler.
	 * @post the task is added to the scheduler.
	 * 		|getScheduledTasks().add(task)
	 * @post the scheduler is sorted so the task is in the proper position.
	 * 		|getScheduledTasks().sort(c)
	 * @post the scheduler is added to the list of schedulers the task is in.
	 * 		|task.addScheduler(this)
	 * @throws IllegalArgumentException
	 * 		if the task to add is teminated. or is null
	 * 		|task.isTerminated()||task == null
	 */
	public void addTask(Task task) throws IllegalArgumentException{
		if (task.isTerminated()|| task == null){
			throw new IllegalArgumentException();
		}
		getScheduledTasks().add(task);
		getScheduledTasks().sort(c);
		task.addScheduler(this);
	}
	/**
	 * creates a new Comparator<Tasks> to sort the scheduled tasks from highest to lowest priority.
	 */
	@Model
	private Comparator<Task> c = new Comparator<Task>(){
		/**
		 * @param a the first task to be compared.
		 * @param b the second task to be compared
		 * @return the difference between task a and task b.
		 * 			|result == b.getPriority()-a.getPriority()
		 * 
		 */
		@Override
	    public int compare(Task a, Task b) {
	        return b.getPriority()-a.getPriority();
	    }
	};
	/**
	 * 
	 * @param task the task to be removed from the list.
	 * @effect if the task is assigned to a unit the task will be stoped executing
	 * 		| if task.getUnitAssignedTo() != null
	 * 		|		task.stopExecutionOfTask()
	 * @post the task is removed from ScheduledTasks
	 * 		|scheduledTasks.remove(task)
	 * @post this scheduler is removed from the task
	 * 		|task.removeScheduler(this)
	 */
	public void removeTask(Task task){
		if (task.getUnitAssignedTo() != null){
			task.stopExecutionOfTask();
		}
		scheduledTasks.remove(task);
		task.removeScheduler(this);
	
	}
	/**
	 * 
	 * @param A the task to be replaced by b
	 * @param B the task to replace a with
	 * @effect if scheduled tasks contains A, A will be removed and B will be added.
	 * 			|if (getScheduledTasks().contains(A)`
	 * 			|	removeTask(A)
	 * 			|	addTask(B)
	 * @throws IllegalStateException if the scheduler doesn't contain task A.
	 * 			| if !getScheduledTasks().contains(A)
	 */
	public void replaceAByB(Task A, Task B) throws IllegalStateException{
		if (!getScheduledTasks().contains(A)){
			throw new IllegalStateException();
		}
		removeTask(A);
		addTask(B);
	}
	/**
	 * 
	 * @param tasks
	 * 			the tasks for which this method must test if they are in this scheduler.
	 * @return  false is one of a task in tasks is not in PrioritisedTasks
	 * 			|for (Task task :tasks)
	 * 			| 	if (! PrioritisedTasks.contains(task)
	 * 			|		ret = false
	 * @return  true otherwise
	 */
	public boolean containsTasks(Collection<Task>  tasks){
		boolean ret = true;
		for (Task t :tasks){
			if (!getScheduledTasks().contains(t)){
				ret = false;
				break;
			}
		}
		return ret;
	}
	/**
	 * 
	 * @param task
	 * @return whether or not the scheduler contains the task
	 * 		|result == getScheduledTasks().contains(task)
	 */
	public boolean containsTask(Task  task){
		return getScheduledTasks().contains(task);
	}
	/**
	 * 
	 * @return the highest priority task not being executed.
	 * 		|for (int i = 0 ; i < getScheduledTasks().size();i++)
	 * 		|	if (!getScheduledTasks().get(i).isAssigned())
	 * 		|		result == getScheduledTasks().get(i)
	 * @return if there is no such task in the scheduled tasks return null
	 * 		|result == null
	 * 
	 */
	public Task highestPriorityTaskNotExcecuted(){
		for (int i = 0 ; i < getScheduledTasks().size();i++){
			if (!getScheduledTasks().get(i).isAssigned()){
				Task ret = getScheduledTasks().get(i);
				return ret;
			}
		}
		return null;
	}
	/**
	 * 
	 * @return the iterator over scheduledTasks
	 * 			|result ==
	 * 			|		this.scheduledTasks.iterator()
	 */
	public Iterator<Task> iterator(){
		return this.scheduledTasks.iterator();
	}

}
