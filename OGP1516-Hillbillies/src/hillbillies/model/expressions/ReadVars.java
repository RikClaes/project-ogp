package hillbillies.model.expressions;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class ReadVars extends Expression {

	private String varsName;

	public ReadVars( String varsName, SourceLocation location) {
		super(location);
		setName(varsName);
		
	}

	private void setName(String varsName) {
		this.varsName = varsName;
	}
	
	private String getVarsName(){
		return this.varsName;
	}

	@Override
	public Object run(Unit unit) {
		return unit.getTask().getVars().get(getVarsName()).run(unit);
	}

}
