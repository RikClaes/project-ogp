
package hillbillies.model.expressions.Units;

import hillbillies.model.Unit;

import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class Enemy extends UnitsExpr{
	
	
	
	public Enemy(SourceLocation location) {
		super(location);
		
	}

	@Override
	public Unit run(Unit unit) {
		Unit ret = null;
		for (Unit u :unit.getWorld().listUnits()){
			if (u.getFaction()!= unit.getFaction()){
				ret = u;
				break;
			}
		}
		return ret;
	}

}
