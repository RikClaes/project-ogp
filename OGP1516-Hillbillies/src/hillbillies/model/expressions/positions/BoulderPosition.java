
package hillbillies.model.expressions.positions;

import hillbillies.model.Boulder;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.model.*;

/**
 * 
 * @author rik
 *
 */
public class BoulderPosition extends Position {
	
	
	
	public BoulderPosition(SourceLocation location) {
		super(location);
		
	}

	@Override
	public int[] run(Unit unit) {
		int[] ret = null;
		for (Boulder bol :unit.getWorld().getBouldersInWorld()){
			ret = bol.getCubePosition();
			break;
		}
		return ret;
	}

}
