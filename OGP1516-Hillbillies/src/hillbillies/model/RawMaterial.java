package hillbillies.model;
import be.kuleuven.cs.som.annotate.Basic;

public abstract class RawMaterial {
	/**
	 * 
	 * @param position
	 * @throws IllegalArgumentException
	 * 
	 * @post the position of the raw material is set to position
	 * 
	 * @post the weigth is set to a valid weight
	 */
	protected  RawMaterial(double[] position,World world) throws IllegalArgumentException {
		setWorld(world);
		setPosition(position);
		setWeight();
		
	}
	protected void setWorld(World world) {
		this.world = world;
		
	}
	protected World getWorld(){
		return this.world;
	}
	private World world;
	/**
	 * 
	 * @param position
	 * @return 	false if the position given is a invalidvalid position for a RawMaterial,
	 * 			when the position is not inside the inside the gameworld or not in a passable cube
	 * 			|if(!((position[0] >= 0)&&(position[0] < 50)
	 *			|&& (position[1] >= 0)&&(position[1] < 50)&& (position[2] >= 0)&&(position[2] < 50))){
	 *			|return false;	
	 *			|if (!World.passable(cubePosition)){
	 *			|return false;
	 *@return true otherwise
	 */
	private boolean isValidPosition(double[] position){
		if(!((position[0] >= 0)&&(position[0] < getWorld().getNBXCubes())
				&& (position[1] >= 0)&&(position[1] < getWorld().getNBYCubes()&& (position[2] >= 0)&&(position[2] < world.getNBZCubes())))){
			return false;
		}
		int x = (int) position[0];
		int y = (int) position[1];
		int z = (int) position[2];
		int[] cubePosition = {x,y,z};
		if (!getWorld().passable(cubePosition)){
			return false;
		}
		else return true;
	}
	/**
	 * 
	 * @param position
	 * 
	 * @throws IllegalArgumentException
	 * 			if the given position is invalid
	 * 			|if (!isValidPosition(position)){
	 *			|throw new IllegalArgumentException();}
	 * @post if the given position is valid the position of the material wil be set to the new position
	 * 			|this.position = position;
	 */
	public void setPosition(double[] position) throws IllegalArgumentException {
		if (!isValidPosition(position)){
			throw new IllegalArgumentException();}
		this.position = position;
	}
	/**
	 * 
	 * @return the position of this RawMaterial
	 * 			|this.position
	 */
	@Basic
	public double[] getPosition(){
		return this.position;
	}
	
	public int[] getCubePosition(){
		int[] ret = {(int)getPosition()[0],(int)getPosition()[1] ,(int)getPosition()[2]};
		return ret;
	}
	
	
	private double[] position;
	
	/**
	 * @post sets the weight of this RawMaterial to a random weight between 50 and 10
	 * 			|this.weight = World.randomInt(50,10);
	 */
	private void setWeight(){
		this.weight = getWorld().randomInt(50,10);
	}
	/**
	 * 
	 * @return the weight of this RawMaterial
	 * 			|this.weight;
	 */
	public int getWeight(){
		return this.weight;
	}
	
	private int weight;
	/**
	 * 
	 * @param deltaT
	 * 
	 * @post if the RawMatarial is not resting on a solid surface it wil fall.
	 * @Throws IllegalArgumentException
	 */ 
	public void advanceTime(double deltaT) throws IllegalArgumentException{
		if (deltaT > 0.2 || deltaT < 0 ){
			throw new IllegalArgumentException();
		}
		int x = (int) getPosition()[0];
		int y = (int) getPosition()[1];
		int z = (int) getPosition()[2];
		int[] below = {x,y,z-1};
		if (!getWorld().passable(below)&& (getPosition()[2] > z)){
			double newZ = getPosition()[2] -3*deltaT ;
			if (newZ <= z){
				double[] newPos = {getPosition()[0],getPosition()[1],z};
				setPosition(newPos);
			}
			else {
				double[] newPos = {getPosition()[0],getPosition()[1],newZ};
				setPosition(newPos);
			}
			
		}
		if (getWorld().passable(below)){
			double newZ = getPosition()[2] -3*deltaT ;
			double[] newPos = {getPosition()[0],getPosition()[1],newZ};
			setPosition(newPos);
		}
		
	}
	
	

}
