package hillbillies.model.expressions.positions;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class HerePosition extends Position {

	public HerePosition(SourceLocation location) {
		super(location);
	}

	@Override
	public int[] run(Unit unit) {
		return unit.getCubePosition();
	}
	

}
