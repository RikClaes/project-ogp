
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
public class Work extends Action{
	/**
	 * 
	 * @param exp the expression to be set
	 * @param location the sourceLocation to be set.
	 */
	public Work(Expression exp, SourceLocation location) {
		super(exp, location);
	}
	/**
	 * lets the unit work at the position: expression.
	 */
	@Override
	public void run(Unit unit ) { 
		Position position = (Position) getExpression();
		unit.workAt(position.run(unit)[0], position.run(unit)[1], position.run(unit)[2]);
	}
	
	
	
	
}
