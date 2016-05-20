
package hillbillies.model.expressions.booleans;


import hillbillies.model.Unit;
import hillbillies.model.expressions.*;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class IsAlive extends BooleanExpr{
	
	private Expression expr;

	public IsAlive(Expression unit,SourceLocation location){
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
	public Boolean run(Unit unit) {
		return (!((Unit) getExpr().run(unit)).isTerminated());
	}

}
