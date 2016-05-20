package hillbillies.model.expressions.positions;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class LiteralPosition extends Position{

	private int[] position;

	public LiteralPosition(int x,int y, int z ,SourceLocation location) {
		super(location);
		int[] given = {x,y,z};
		setPosition(given);
	}

	private void setPosition(int[] pos) {
		this.position =pos;
		
	}
	
	private int[] getPosition(){
		return this.position;
	}

	@Override
	public int[] run(Unit unit) {
		return getPosition();
	}
	

}
