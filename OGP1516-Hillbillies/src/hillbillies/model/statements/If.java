
package hillbillies.model.statements;

import hillbillies.model.Unit;
import hillbillies.model.expressions.Expression;
import hillbillies.model.expressions.booleans.BooleanExpr;
import hillbillies.part3.programs.SourceLocation;

/**
 * 
 * @author rik
 *
 */
public class If extends Statement{
	private Expression condition;
	private Statement ifBody;
	private Statement elseBody;
	/**
	 * 
	 * @param condition the condition to be set for this if statement
	 * @param ifBody the ifbody to be set for this if statement
	 * @param elseBody the elsebody to be set for this if statement
	 * @param location the sourcelocation to be set for this if statement.
	 */
	public If(Expression condition, Statement ifBody, Statement elseBody, SourceLocation location){
		super(location);
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
	}
	/**
	 * 
	 * @param condition
	 * @post the condition is set to condition
	 */
	private void setCondition(Expression condition) {
		this.condition = condition;
		
	}
	/**
	 * 
	 * @return the condition
	 */
	private Expression getCondition(){
		return this.condition;
	}
	/**
	 * 
	 * @param ifBody
	 * @post the ifbody for this unit is set to ifbody
	 */
	private void setIfBody(Statement ifBody) {
		this.ifBody= ifBody;
		
	}
	/**
	 * 
	 * @return ifbody
	 */
	private Statement getIfBody(){
		return this.ifBody;
	}
	/**
	 * 
	 * @param elseBody
	 * @post the elsebody is set to elsebody
	 */
	private void setElseBody(Statement elseBody) {
		this.elseBody= elseBody;
		
	}
	/**
	 * 
	 * @return elsebody
	 */
	private Statement getElseBody(){
		return this.elseBody;
	}
	
	/**
	 * runs this if statement
	 */
	@Override
	public void run(Unit unit) {
		BooleanExpr exBoolean = (BooleanExpr) getCondition();
		if (exBoolean.run(unit)){
			getIfBody().run(unit);
		}
		else{
			getElseBody().run(unit);
		}
		
	}

}
