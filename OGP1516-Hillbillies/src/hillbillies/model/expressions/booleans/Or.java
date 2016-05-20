package hillbillies.model.expressions.booleans;


import hillbillies.model.Unit;
import hillbillies.model.expressions.*;
import hillbillies.part3.programs.SourceLocation;

/**
 * 
 * @author rik
 *
 */
public class Or extends BooleanExpr{
	
	private Expression exprRight;
	private Expression exprLeft;

	public Or(Expression left, Expression right,SourceLocation location){
		super(location);
		setExprLeft(left);
		setExprRight(right);
	}

	private void setExprRight(Expression right) {
		this.exprRight = right;
		
	}
	private Expression getRight(){
		return this.exprRight;
	}

	private void setExprLeft(Expression left) {
		this.exprLeft = left;
		
	}
	private Expression getLeft(){
		return this.exprLeft;
	}

	@Override
	public Boolean run(Unit unit) {
		BooleanExpr left = (BooleanExpr) getLeft();
		BooleanExpr right = (BooleanExpr) getRight();
		return(left.run(unit) || right.run(unit));
	}

}
