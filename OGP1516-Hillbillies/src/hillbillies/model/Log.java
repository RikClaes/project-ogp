package hillbillies.model;

public class Log extends RawMaterial {


	/**
	 * 
	 * @param position
	 * 			the position for this new log
	 * @throws IllegalArgumentException
	 * @post the position of this log is set to the given position
	 * @post the weight of this new log is set to a random weight between 50 and 10
	 */
	public Log(double[] position, World world) throws IllegalArgumentException {
		super(position,world);
	}
	/**
	 * terminates this log.
	 */
	public void terminate(){
		getWorld().removeLogFromWorld(this);
		setWorld(null);
	}
	
}
