
package hillbillies.model.expressions.positions;

import hillbillies.model.*;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.model.Unit;

/**
 * 
 * @author rik
 *
 */
public class LogPosition extends Position{

	public LogPosition(SourceLocation location) {
		super(location);
	}

	@Override
	public int[] run(Unit unit) {
		int[] ret = null;
		for (Log b :unit.getWorld().getLogsInWorld()){
			ret = b.getCubePosition();
			break;
		}
		return ret;
	}
	
	

}
