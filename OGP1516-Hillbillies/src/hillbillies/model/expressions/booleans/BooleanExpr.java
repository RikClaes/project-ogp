package hillbillies.model.expressions.booleans;

import hillbillies.model.Unit;
import hillbillies.model.expressions.Expression;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public abstract class BooleanExpr extends Expression {
	protected BooleanExpr(SourceLocation sourceLocation) {
		super(sourceLocation);
		
	}


	
	public abstract Boolean run(Unit unit);

}
