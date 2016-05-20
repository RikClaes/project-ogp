
package hillbillies.model.statements;

import hillbillies.model.Unit;
import hillbillies.model.expressions.Expression;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class Print extends Statement{
	private Expression value;

	/**
	 * 
	 * @param value the expression to set as value
	 * @param location the source location to set this print at.
	 */
	public Print(Expression value, SourceLocation location) {
		super(location);
		setValue(value);
	}
	/**
	 * 
	 * @param value
	 * @post the value is set to value
	 */
	private void setValue(Expression value) {
		this.value = value;
		
	}
	/**
	 * 
	 * @return the value of this print statment.
	 */
	private Expression getValue(){
		return this.value;
	}
	/**
	 * prints the value for the given unit.
	 */
	@Override
	public void run(Unit unit) {
		System.out.println(getValue().run(unit));
		
	}

}
