
package hillbillies.model.expressions.Units;

import hillbillies.model.expressions.Expression;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.model.Unit;
/**
 * 
 * @author rik
 *
 */
public abstract class UnitsExpr extends Expression {

	protected UnitsExpr(SourceLocation location) {
		super(location);
	}


	@Override
	public abstract Unit run(Unit unit);

}