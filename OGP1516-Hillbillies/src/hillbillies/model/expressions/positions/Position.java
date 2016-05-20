
package hillbillies.model.expressions.positions;

import hillbillies.model.Unit;
import hillbillies.model.expressions.Expression;
import hillbillies.part3.programs.SourceLocation;

/**
 * 
 * @author rik
 *
 */
public abstract class Position extends Expression{

		
	protected Position(SourceLocation location) {
		super(location);
	}

	public abstract int[] run(Unit unit);
	

}
