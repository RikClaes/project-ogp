package hillbillies.model.expressions.booleans;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class False extends BooleanExpr {

	public False(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Boolean run(Unit unit) {
		return false;
	}

}
