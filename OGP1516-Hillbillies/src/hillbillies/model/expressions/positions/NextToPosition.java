
package hillbillies.model.expressions.positions;

import hillbillies.model.expressions.Expression;
import hillbillies.model.expressions.positions.Position;
import hillbillies.part3.programs.SourceLocation;
import java.util.Set;
import hillbillies.model.*;

/**
 * 
 * @author rik
 *
 */
public class NextToPosition extends Position {

	private Expression Expr;

	public NextToPosition(Expression pos, SourceLocation location) {
		super(location);
		setExpr(pos);
	}

	private void setExpr(Expression pos) {
		this.Expr = pos;
	}
	
	private Expression getExpr(){
		return this.Expr;
	}

	@Override
	public int[] run(Unit unit) {
		int[] ret = null;
		int[] givenPosition = (int[]) getExpr().run(unit);
		Set<int[]> neighs = unit.neighbors(givenPosition) ;
		for (int[] pos :neighs){
			if ( unit.getWorld().passable(pos)){
				ret = pos;
				break;
			}
		}
		return ret;
	}
	
	

	
	


}
