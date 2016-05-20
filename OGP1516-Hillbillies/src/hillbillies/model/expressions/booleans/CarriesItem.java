package hillbillies.model.expressions.booleans;

import hillbillies.model.expressions.*;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.model.Unit;
/**
 * 
 * @author rik
 *
 */
public class CarriesItem extends BooleanExpr {
	private Expression toCheck;

	public CarriesItem(Expression unit,SourceLocation location){
		super(location);
		setExpr(unit);
	}

	private void setExpr(Expression unit) {
		this.toCheck = unit;
		
	}
	private Expression getToCheck(){
		return this.toCheck;
	}

	@Override
	public Boolean run(Unit unit) {
		return (((Unit) getToCheck().run(unit)).getCarryedItem() == null);
	}

}
