package hillbillies.model.statements;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public abstract class Statement {
	
	public final SourceLocation sourceLocation;
	
	protected Statement(SourceLocation location) {
		this.sourceLocation = location;
	}
	/**
	 * 
	 * @return the sourcelocation of the statement.
	 */
	public SourceLocation getSouceLocation(){
		return this.sourceLocation;
	}
	/**
	 * 
	 * @param unit
	 * runs the statement for the given unit.
	 */
	public abstract void run(Unit unit);

	
	
	

}
