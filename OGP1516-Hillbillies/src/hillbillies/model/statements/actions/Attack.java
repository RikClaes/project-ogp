
package hillbillies.model.statements.actions;

import hillbillies.model.Unit;
import hillbillies.model.expressions.Expression;
import hillbillies.model.expressions.Units.UnitsExpr;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class Attack extends Action {
	/**
	 * 
	  * @param exp the expression to be set
	 * @param location the sourcelocation to be set
	 */
	public Attack(Expression exp, SourceLocation location) {
		super(exp, location);
	}
	/**
	 * lets the unit attack the unit expression.
	 */
	@Override
	public void run(Unit unit) { 
		UnitsExpr def = (UnitsExpr) getExpression();	
		unit.attack(def.run(unit));
	}

	

}
