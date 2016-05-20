package hillbillies.model.expressions.booleans;

import hillbillies.model.Unit;
import hillbillies.model.expressions.*;

import hillbillies.model.expressions.booleans.BooleanExpr;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class And extends BooleanExpr {
	private Expression exprRight;
	private Expression exprLeft;



	public And(Expression left, Expression right,SourceLocation location){
		super(location);
		setExprLeft(left);
		setExprRight(right);
	}

	
	
	private void setExprRight(Expression right) {
		this.exprRight = right;
		
	}
	private Expression getExprRight(){
		return this.exprRight;
	}



	private void setExprLeft(Expression left) {
		this.exprLeft = left;
		
	}
	private Expression getExprLeft(){
		return this.exprLeft;
	}



	@Override
	public Boolean run(Unit unit) {
		BooleanExpr exprBooleanRight = (BooleanExpr) getExprRight();
		BooleanExpr exprBooleanLeft = (BooleanExpr) getExprLeft();
		return((exprBooleanLeft.run(unit)) && (exprBooleanRight.run(unit)));
			
	}



	

	

}
