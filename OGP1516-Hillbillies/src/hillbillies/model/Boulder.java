package hillbillies.model;

public class Boulder extends RawMaterial {

	/**
	 * 
	 * @param position
	 * 			the position for this new boulder
	 * @throws IllegalArgumentException
	 * @post the position of this boulder is set to the given position
	 * @post the weight of this new boulder is set to a random weight between 50 and 10
	 */
	public Boulder(double[] position, World world) throws IllegalArgumentException {
		super(position,world);
		
	}
	/**
	 * terminates the boulder.
	 */
	public void terminate(){
		getWorld().removeBoulderFromWorld(this);
		setWorld(null);
	}
	

}
