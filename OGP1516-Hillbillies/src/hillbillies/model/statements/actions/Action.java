package hillbillies.model.statements.actions;


import hillbillies.model.expressions.*;
import hillbillies.model.statements.Statement;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public abstract class Action extends Statement{
	
	Expression expression;
	/**
	 *
	 * @param exp the expression to be set
	 * @param location the sourcelocation to be set
	 */
	Action(Expression exp, SourceLocation sourceLocation) {
		super(sourceLocation);
		setExpression(exp);
	}
	
	

	/**
	 * 
	 * @return the expression of this action
	 */
	Expression getExpression() {
		return this.expression;
	}

	/**
	 * 
	 * @param expression
	 * @post the expression of this action is set to expression.
	 */
	void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	
	
}
