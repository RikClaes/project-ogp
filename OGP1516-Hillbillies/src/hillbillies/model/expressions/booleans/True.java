package hillbillies.model.expressions.booleans;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class True extends BooleanExpr {

	public True(SourceLocation location) {
		super(location);
	}

	@Override
	public Boolean run(Unit unit) {
		return true;
	}

}
