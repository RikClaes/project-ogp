package hillbillies.model.expressions.booleans;

import java.util.Set;

import hillbillies.model.Faction;
import hillbillies.model.Unit;
import hillbillies.model.expressions.Expression;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class IsEnemy extends BooleanExpr{
	private Expression toCheck;
	
	public IsEnemy(Expression unit,SourceLocation location){
		super(location);
		setToCheck(unit);
	}
	
	

	public Expression getToCheck() {
		return toCheck;
	}

	
	public void setToCheck(Expression unit) {
		this.toCheck = unit;
	}

	@Override
	public Boolean run(Unit unit) {
		Faction faction = unit.getFaction();
		Set<Unit> factions = faction.getUnitsInFaction();
		return factions.contains(getToCheck().run(unit));
	}
}
