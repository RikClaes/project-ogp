
package hillbillies.model.expressions.positions;

import hillbillies.model.Unit;
import hillbillies.model.expressions.*;
import hillbillies.part3.programs.SourceLocation;

/**
 * 
 * @author rik
 *
 */
public class PositionOf extends Position {

	private Expression expr;

	public PositionOf(Expression unit,SourceLocation location) {
		super(location);
		setExpr(unit);
	}

	private void setExpr(Expression unit) {
		this.expr = unit;
		
	}
	
	private Expression getExpr(){
		return this.expr;
	}

	@Override
	public int[] run(Unit unit) {
		return ((Unit)getExpr().run(unit)).getCubePosition();
	}
	
	
	
	
	
	

}
