
package hillbillies.model.expressions.booleans;

import hillbillies.model.expressions.*;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.model.Unit;
import hillbillies.model.World;

/**
 * 
 * @author rik
 *
 */
public class IsPassable extends BooleanExpr{
	
	public IsPassable(Expression position, SourceLocation location){
		super(location);
		setToCheck(position);
	}
	
	
	
	private Expression toCheck;

	
	public Expression getToCheck() {
		return toCheck;
	}

	
	public void setToCheck(Expression pos) {
		this.toCheck = pos;
	}

	@Override
	public Boolean run(Unit unit) {
		World world = unit.getWorld();
		int[] position = (int[]) getToCheck().run(unit);
		return world.passable(position);
	}

}
