
package hillbillies.model.statements.actions;

import hillbillies.model.Unit;
import hillbillies.model.expressions.Expression;
import hillbillies.model.expressions.positions.Position;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class MoveTo extends Action {
	/**
	 * 
	 * @param exp the 
	 * @param location the sourcelocation to be set.
	 */
	public MoveTo(Expression exp,SourceLocation location) {
		super(exp, location);
	}
	/**
	 * lets the unit move to the location: expression
	 */
	@Override
	public void run(Unit unit ) { 
		Position position = (Position) getExpression();
		int[] actPos = {position.run(unit)[0], position.run(unit)[1], position.run(unit)[2]};
		unit.moveTo(actPos);	
		
	}

}
