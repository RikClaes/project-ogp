
package hillbillies.model.statements;

import hillbillies.model.Unit;
import hillbillies.model.expressions.*;
import hillbillies.part3.programs.SourceLocation;

/**
 * 
 * @author rik
 *
 */
public class Assignment extends Statement{
	private Expression value;
	private String name;
	/**
	 * 
	 * @param name the name for this assignment 
	 * @param value the value for this assignment 
	 * @param location the sourcelocation for this assignment
	 */
	public Assignment(String name, Expression value ,SourceLocation location){
		super(location);
		setValue(value);
		setName(name);
	}
	/**
	 * 
	 * @param name
	 * @post the name is set to name
	 */
	private void setName(String name) {
		this.name = name;
		
	}
	/**
	 * 
	 * @return name
	 */
	private String getName() {
		return this.name;
	}
	/**
	 * 
	 * @param value
	 * @post the value is set to value
	 */
	private void setValue(Expression value) {
		this.value=value;
	}
	/**
	 * 
	 * @return value
	 */
	private Expression getValue() {
		return this.value;
	}

	/**
	 * runs the assignment.
	 */
	@Override
	public void run(Unit unit) {
		unit.getTask().getVars().put(getName(), getValue());
		
	}

	

}
