
package hillbillies.model.expressions.Units;
import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class This extends UnitsExpr{

	public This(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	

	@Override
	public Unit run(Unit unit) {
		return unit;
	}
	
	
}
