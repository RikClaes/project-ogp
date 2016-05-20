
package hillbillies.model.statements;

import hillbillies.model.Unit;
import hillbillies.model.expressions.*;
import hillbillies.model.expressions.booleans.*;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class While extends Statement {
	
	private Expression condition;
	/**
	 * 
	 * @param condition the condition for this while loop.
	 * @param body the body for this while loop.
	 * @param sourceLocation the sourcelocation for this while loop
	 */
	public While(Expression condition, Statement body, SourceLocation sourceLocation){
		super(sourceLocation);
		setCondition(condition);
		setBody(body);
	}
	
	
	private Statement body;
	
	/**
	 * 
	 * @return the body of the while loop
	 */
	public Statement getBody() {
		return this.body;
	}
	/**
	 * 
	 * @param body
	 * @post the body of the while loop is set to body
	 */
	public void setBody(Statement body) {
		this.body = body;
	}

	/**
	 * 
	 * @return the condition of the while loop
	 */
	public Expression getCondition() {
		return condition;
	}

	/**
	 * 
	 * @param condition 
	 * @post the condition of the while loop is set to condition
	 */
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	/**
	 * runs the while loop
	 */
	@Override
	public void run(Unit unit) {
		while(((BooleanExpr) getCondition()).run(unit)){
			getBody().run(unit);
		}
	}

	
	

}
