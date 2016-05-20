package hillbillies.model;


import java.util.HashSet;
import java.util.Set;

public class Faction {
	/**
	 * default constructor
	 */
	public Faction(){
		Scheduler scheduler = new Scheduler();
		setScheduler(scheduler);
		
	}
	/**
	 * 
	 * @return the scheduler of the unit
	 */
	public Scheduler getScheduler(){
		return this.scheduler;
	}
	/**
	 * 
	 * @param scheduler the scheduler to be assigned to the faction
	 * @post the scheduler of this faction is set to scheduler
	 */
	public void setScheduler(Scheduler scheduler){
		this.scheduler= scheduler;
	}
	
	private Scheduler scheduler;
	/**
	 * 
	 * @return a set of units in this faction
	 */
	public Set<Unit> getUnitsInFaction(){
		return this.unitsInFaction;
	}
	/**
	 * 
	 * @param unit the unit to add to this faction
	 * @post the unit is added to the faction.
	 */
	public void addUnitToFaction(Unit unit){
		this.unitsInFaction.add(unit);
	}
	
	/**
	 * 
	 * @param unit the unit to remove from the faction
	 * @throws IllegalArgumentException if the unit is not in faction.
	 */
	public void removeUnitFromFaction(Unit unit) throws IllegalArgumentException{
		if(!this.unitsInFaction.contains(unit)){
			throw new IllegalArgumentException();
		}
		this.unitsInFaction.remove(unit);
	}
	
	private final Set<Unit> unitsInFaction = new HashSet<Unit>();

}
