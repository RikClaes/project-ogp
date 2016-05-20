package hillbillies.model.expressions.booleans;

import hillbillies.model.Unit;
import hillbillies.model.expressions.Expression;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class Not extends BooleanExpr {
	private Expression Expr;

	public Not(Expression expression,SourceLocation location){
		super(location);
		setExpr(expression);
	}

	private void setExpr(Expression expression) {
		this.Expr= expression;
		
	}
	
	private Expression getExpr(){
		return this.Expr;
	}

	@Override
	public Boolean run(Unit unit) {
		return !((BooleanExpr) getExpr()).run(unit);
	}

}
