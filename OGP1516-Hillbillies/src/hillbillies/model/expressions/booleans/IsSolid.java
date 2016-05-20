package hillbillies.model.expressions.booleans;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.model.expressions.Expression;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class IsSolid extends BooleanExpr {
	public IsSolid(Expression position,SourceLocation location){
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
		int[] toCheck = (int[]) getToCheck().run(unit);
		World world = unit.getWorld();
		return !world.passable(toCheck);
	}

}
