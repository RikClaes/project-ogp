package hillbillies.model.expressions;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public abstract class Expression {
	
	private final SourceLocation location;

	protected Expression(SourceLocation location){
		this.location = location;
	}


	
	public SourceLocation  getLocation(){
		return this.location;
	}
	public abstract Object run(Unit unit);

}
