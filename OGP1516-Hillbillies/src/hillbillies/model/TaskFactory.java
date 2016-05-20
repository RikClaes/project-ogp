package hillbillies.model;

import java.util.ArrayList;
import java.util.List;


import hillbillies.model.expressions.*;
import hillbillies.model.expressions.Units.*;
import hillbillies.model.expressions.booleans.*;
import hillbillies.model.expressions.positions.*;
import hillbillies.model.statements.*;
import hillbillies.model.statements.actions.*;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;

public class TaskFactory implements ITaskFactory <Expression, Statement, Task> {
	
	

	@Override
	public List<Task> createTasks(String name, int priority, Statement activity, List<int[]> selectedCubes) {
		List<Task> tasks = new ArrayList<Task>();
		if (selectedCubes.size()>0){
			for (int[] cube :selectedCubes){
				tasks.add(new Task(name,priority,activity,cube));
			}
		}
		else{
			tasks.add(new Task(name,priority,activity));
		}
		return tasks;
	
	}

	@Override
	public Statement createAssignment(String variableName, Expression value, SourceLocation sourceLocation) {
		return new Assignment(variableName,value,sourceLocation);
	}

	@Override
	public Statement createWhile(Expression condition, Statement body, SourceLocation sourceLocation) {
		return new While(condition,body,sourceLocation);
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		
		return new If(condition,ifBody,elseBody,sourceLocation);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return null;
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		
		return new Print(value,sourceLocation);
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
	
		return new Sequence(statements,sourceLocation);
	}

	@Override
	public Statement createMoveTo(Expression position, SourceLocation sourceLocation) {
		return new MoveTo(position,sourceLocation);
	}

	@Override
	public Statement createWork(Expression position, SourceLocation sourceLocation) {
		return new Work(position,sourceLocation);
	}

	@Override
	public Statement createFollow(Expression unit, SourceLocation sourceLocation) {
		return new Follow(unit,sourceLocation);
	}

	@Override
	public Statement createAttack(Expression unit, SourceLocation sourceLocation) {
		
		return new Attack(unit,sourceLocation);
	}

	@Override
	public Expression createReadVariable(String variableName, SourceLocation sourceLocation) {
		return new ReadVars(variableName,sourceLocation);
	}

	@Override
	public Expression createIsSolid(Expression position, SourceLocation sourceLocation) {
		return new IsSolid(position, sourceLocation);
	}

	@Override
	public Expression createIsPassable(Expression position, SourceLocation sourceLocation) {
		return new IsPassable(position, sourceLocation);
	}

	@Override
	public Expression createIsFriend(Expression unit, SourceLocation sourceLocation) {
		return new IsFriend(unit,sourceLocation);
	}

	@Override
	public Expression createIsEnemy(Expression unit, SourceLocation sourceLocation) {
		return new IsEnemy(unit,sourceLocation);
	}

	@Override
	public Expression createIsAlive(Expression unit, SourceLocation sourceLocation) {
		return new IsAlive(unit,sourceLocation);
	}

	@Override
	public Expression createCarriesItem(Expression unit, SourceLocation sourceLocation) {
		return new CarriesItem(unit,sourceLocation);
	}

	@Override
	public Expression createNot(Expression expression, SourceLocation sourceLocation) {
		return new Not(expression,sourceLocation);
	}

	@Override
	public Expression createAnd(Expression left, Expression right, SourceLocation sourceLocation) {
		return new And(left,right,sourceLocation);
	}

	@Override
	public Expression createOr(Expression left, Expression right, SourceLocation sourceLocation) {
		return new Or(left,right,sourceLocation);
	}

	@Override
	public Expression createHerePosition(SourceLocation sourceLocation) {
		return new HerePosition(sourceLocation);
	}

	@Override
	public Expression createLogPosition(SourceLocation sourceLocation) {
		return new LogPosition(sourceLocation);
	}

	@Override
	public Expression createBoulderPosition(SourceLocation sourceLocation) {
		return new BoulderPosition(sourceLocation);
	}

	@Override
	public Expression createWorkshopPosition(SourceLocation sourceLocation) {
		return new WorkshopPosition(sourceLocation);
	}

	@Override
	public Expression createSelectedPosition(SourceLocation sourceLocation) {
		return null;
	}

	@Override
	public Expression createNextToPosition(Expression position, SourceLocation sourceLocation) {
		return new NextToPosition(position,sourceLocation);
	}

	@Override
	public Expression createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		return new LiteralPosition(x,y,z,sourceLocation);
	}

	@Override
	public Expression createThis(SourceLocation sourceLocation){
		return new This(sourceLocation);
	}

	@Override
	public Expression createFriend(SourceLocation sourceLocation) {
		return new Friend(sourceLocation);
	}

	@Override
	public Expression createEnemy(SourceLocation sourceLocation) {
		return new Enemy(sourceLocation);
	}

	@Override
	public Expression createAny(SourceLocation sourceLocation) {
		return new Any(sourceLocation);
	}

	@Override
	public Expression createTrue(SourceLocation sourceLocation) {
		return new True(sourceLocation);
	}

	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		return new False(sourceLocation);
	}


}
