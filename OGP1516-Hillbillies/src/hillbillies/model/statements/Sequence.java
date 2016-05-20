
package hillbillies.model.statements;

import java.util.Iterator;
import java.util.List;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class Sequence extends Statement{
	
	private Iterator<Statement> iterator;
	/**
	 * 
	 * @param statements the statements to be set in this sequence.
	 * @param location the sourcelocation for this sequence to be set at.
	 */
	public Sequence(List<Statement> statements, SourceLocation location) {
		super(location);
		this.iterator = statements.iterator();
		
	}

	
	/**
	 * runs the sequence
	 */
	@Override
	public void run(Unit unit) {
		if (iterator.hasNext()){
			iterator.next().run(unit);
		}
	}

}
