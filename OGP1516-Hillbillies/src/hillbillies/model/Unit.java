package hillbillies.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import hillbillies.model.World;
import hillbillies.model.Log;
import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import be.kuleuven.cs.som.annotate.*;


/**
 * a class that holds the information of the units
 * @Invar 	the maximal hitpoints a unit can have is a valid number of hitpoints
 * 			|getMaxHitpoints()>0
 * @Invar 	the current hitpoints of a unit must be a valid value 
 * 			|isValidHitpoints(getHitpoints(),getMaxHitpoints())
 * @Invar 	the maximal stamina a unit can have is a valid number of stamina
 * 			|getMaxStamina()>0
 * @Invar 	the current stamina of a unit must be a valid value 
 * 			|isValidStamina(getStamina(),getMaxStamina())
 * @Invar	the position of a unit must always be a valid position so inside the world and on a passable cube.
 * 			|isValidPosition(getPosition())
 * @Invar	the weight of a unit must always be between larger than getMinWeight() 
 * 			| getWeight() > getMinWeight()
 * @Invar	the strength of a unit must always be between getMinStrength() and getMaxStrength()
 * 			|getMinStrength() <getStrength()< getMaxStrength()
 * @Invar	the agility of a unit must always be between getMinAgility() and getMaxAgility()
 * 			|getMinAgility() <getAgility()< getMaxAgility()
 * @Invar	the toughness of a unit must always be between getMinToughness() and getMaxToughness()
 * 			|getMinToughness() <getToughness()< getMaxToughness()
 * @Invar 	adjDestination must be a valid Position 
 * 			| isValidPosition(getAdjDestination())
 * @Invar	moveToTarget must be a valid position
 * 			| isValidPosition(getMoveToTarget())
 * @author Rik Claes & Bart Vanmeervenne
 *
 */
public class Unit {
	/**
	 * initializes a new unit with given position, name, weight, strength, agility, toughness
	 * @param position
	 * 			the position for this new unit
	 * @param name
	 * 			the name for this new unit
	 * @param weight
	 * 			the weight for this new unit
	 * @param strength
	 * 			the strength for this new unit
	 * @param agility
	 * 			the agility for this new unit
	 * @param toughness
	 * 			the toughness for this new unit
	 * @param World
	 * 			the world the unit is set in.
	 * @param faction
	 * 			the faction to witch this new unit belongs
	 * 
	 * @post the position of the new unit is equal to the given position
	 * 			|new.position = position
	 * @post the name of the new unit is equal to the given position
	 * 			|new.position = position
	 * @post if the given weight, is greater then the maximum initial position the weight is 
	 * 			set to the maximum possible weight 
	 * 			|if (weight > getMaxInitialStat()){
	 *			|then	setWeight(getMaxInitialStat());
	 * @post if the given weight is smaller then the minimum initial position the weight is 
	 * 			set to the minimum possible weight 
	 * 			|if (weight > getMaxInitialStat()){
	 *			|then	setWeight(getMaxInitialStat());
	 * @post if the given weight is in between the minimum an maximum weight the weight 
	 * 			of the unit  will be set too the given weight
	 * 			|else:
	 * 			|setWeight(weight);
	 * @post for strength, agility, toughness the same goes as for weight
	 * @post the angle is set to pi/2
	 * 			|setAngle(pi/2)
	 * @post hitpoints is set to the maximum number of hitpoints a this unit can have
	 * 			|setHitpoints(getMaxHitpoint())
	 * @post stamina is set to the maximum number of stamina a this unit can have
	 * 			|setStamina(getMaxStamina())
	 * @post the world of this unit is set to world
	 * 			|setWorld(world)
	 * @post the faction of the unit is set to faction
	 * 			|setFaction(faction)
	 */
	public  Unit(double[] position,String name,int weight,int strength,
			int agility,int toughness,World world,Faction faction){
		setWorld(world);
		
		setPosition(position);
		setName(name);
		if (strength > getMaxInitialStat()){
			setStrength(getMaxInitialStat());
		}
		if (strength < getMinInitialStat()){
			setStrength(getMinInitialStat());
		}
		else{
			setStrength(strength);
		}
		if (agility > getMaxInitialStat()){
			setAgility(getMaxInitialStat());
		}
		if (agility < getMinInitialStat()){
			setAgility(getMinInitialStat());
		}
		else{
			setAgility(agility);
		}
		if (toughness > getMaxInitialStat()){
			setToughness(getMaxInitialStat());
		}
		if (toughness < getMinInitialStat()){
			setToughness(getMinInitialStat());
		}
		else{
			setToughness(toughness);
		}
		if (weight > getMaxInitialStat()){
			setWeight(getMaxInitialStat());
		}
		if (weight < getMinInitialStat()){
			setWeight(getMinInitialStat());
		}
		else{
			setWeight(weight);
		}
		setAngle((float) (Math.PI / 2));
		setHitpoints(getMaxHitpoints());
		setStamina(getMaxStamina());
		
		setFaction(faction);
	}
			
		
	/**
	 * 
	 * @return the exact position of a unit
	 */
	@Basic @Raw
	public double[] getPosition(){
		double[] ret = {this.posX ,this.posY ,this.posZ};
		return ret;
	}

	
/**
 * 
 * @param 	position
 * 			the position to be set of the unit
 *
 * @post 	the new x position of the unit is equal to the given x position
 * 			| new getPosition[0] = posX
 * @post 	the new y position of the unit is equal to the given y position
 * 			| new getPosition[1] = posY
 * @post 	the new z position of the unit is equal to the given z position
 * 			| new getPosition[2] = posZ
 * @throws 	IllegalArgumentExeption
 * 			|!isValidPosition(position)
 */
	public void setPosition(double[] position)throws IllegalArgumentException{
		if (!isValidPosition(position)){
			throw new IllegalArgumentException();
		}
		this.posX = position[0];
		this.posY = position[1];
		this.posZ = position[2];
	}
	
	/**
	 * 
	 * @param Position
	 * 			the position of a unit
	 * @return 
	 * @return true if end only if the given position is in the gameworld and the cubetype is passable on this position.
	 * 			|result ==
	 * 			|((position[0] >= 0)&&(position[0] < 50)
	 * 			|&& (position[1] >= 0)&&(position[1] < 50)
	 * 			|&& (position[2] >= 0)&&(position[2] < 50))
	 * 			|&& getWorld().passable(cPos))
	 */
	@Model
	private boolean isValidPosition(double[] position){
		int[] cPos = {(int)position[0], (int)position[1],(int)position[2]};
		return ((position[0] >= 0)&&(position[0] < getXupbound())
				&& (position[1] >= 0)&&(position[1] < getYupbound())&& (position[2] >= 0)&&(position[2] < getZupbound())
				&& getWorld().passable(cPos));
	}
	/**
	 * 
	 * @return the the position of the cube in witch a unit is
	 * 			| int[] ret = (int)this.posX,(int)this.posY ,(int)this.posZ
	 * 			| return ret;
	 */
	public int[] getCubePosition(){
		int[] ret = {(int)this.posX,(int)this.posY ,(int)this.posZ};
		return ret;
	}
	private double posX;
	private double posY;
	private double posZ;
	/**
	 * naam defensief progr
	 */
	/**
	 * 
	 * @param 	name
	 * 			the name for the unit
	 * @post 	the new name of the unit is equal to the given name
	 * 			|new.getName() == name
	 * @throws illegalArgumentException() if the name is an invalid name.
	 * 			| if  not isValidName(name)
	 * 			
	 */
	public void setName(String name){
		if (!isValidName(name)){
			throw new IllegalArgumentException(name);
		}
		this.name = name;
	}
	/**
	 * 
	 * @return the name of the unit
	 * 			|this.name
	 */
	@Basic
	public String getName(){
		return this.name;
	}
	/**
	 * 
	 * @param name the name to be checked.
	 * @return if the length of the name if smaller then 2 return false.
	 * 			| if letters.length < 2
	 * 			|		result == false
	 * @return	if the first letter of the string is not uppercase return false
	 * 			| if letters[0]!=letters[0].toUpperCase()
	 * 			| result == false
	 * @return if one of the symbols in the name is not in the list of allowed symbols return false.
	 *  		| for i letter in name
	 *  		| 	flag = false;
	 *  		|		for j letter in allowed symbols
	 *  		|			if  i == j
	 *  		|				flag = true;
	 *  		|	if flag == false
	 *  		| 		return false
	 *  @return true otherwise.
	 */
	@Raw 
	private static boolean isValidName(String name){
		String[] allowedsymb = {" ","\"" ,"\'","a","z","e","r","t","y","u","i","o","p","q"
				,"s","d","f","g","h","j","k","l","m","w","x","c","v","b","n","A","Z","E"
				,"R","T","Y","U","O","P","Q","S","D","F","G","H","J","K","L","M","W","X","C","V","B","N","I"};		
		String[] letters = name.split("");
		if (letters.length < 2){
			return false;
		}
		if (letters[0]!=letters[0].toUpperCase()){
			return false;
		}
		for(String i : letters){
			boolean flag = false;
				for (String j : allowedsymb){
					if (i.equals(j)){
						flag = true;
					}
				}
			if(!flag){
				return false;
			}
		}
		return true;
	}
	
	private String name;
	
	
	
	
	
	
	/**
	 * weight totaal progr
	 * returns the weight of the unit
	 */
	@Basic
	public int getWeight(){
		return this.weight;
	}
	/**
	 * 
	 * @return the lowest possible value of weight for all units is 
	 * 			(this.Strength()+this.Agility())/2
	 */
	@Basic @Model
	private int getMinWeight(){
		return (int)(((getStrength()+getAgility())/2)+1);
	}
	/**
	 * 
	 * @return the highest possible value of weight is 200
	 * 			must be greater then getMinWeight() 
	 */
	@Basic
	private int getMaxWeight(){
		return 200;
	}
	/**
	 * 
	 * @param weight
	 * 			the new weight for this unit
	 * @post if the given weight is equal to or greater then the minimum weight and equal too or smaller then maximum weight,
	 * 			the weight new of this unit is equal to the given weight
	 * 			| if ((weight >= getMinWeight())&&(weight <= getMaxWeight))
	 * 			|		then new.getWeight()= weight
	 * @post 	if the given weight is smaller then the minimum weight
	 * 			the new weight is set to the minimum weight
	 * 			|if (weight < getMinWeight())
	 * 			|	new.weight = getMinWeight()
	 * @post	if the given weight is greater then the maximum weight
	 * 			the new weight is set to the maximum weight
	 * 			|if (weight < getMaxWeight())
	 * 			|	new.weight = getMaxWeight()
	 * 
	 */
	public void setWeight(int weight){
		if (weight < getMinWeight()){
			this.weight = getMinWeight(); 
		}
		else if (weight > getMaxWeight()){
			this.weight = getMaxWeight();
		}
		else {
			this.weight = weight;
		}
	}
	private int weight;
	/**
	 * Strength totaal progr
	 * returns the strength of the unit
	 */
	public int getStrength(){
		return this.strength;
	}
	/**
	 * 
	 * @return the lowest possible value of strength of a unit is 1
	 */
	@Basic
	private int getMinStrength(){
		return 1;
	}
	/**
	 * 
	 * @return the highest possible value of strength of a unit is 200
	 * 			must be greater then getMinStrentgh()
	 */
	@Basic
	private int getMaxStrength(){
		return 200;
	}
	/**
	 * 
	 * @param strength
	 * 			the new strength for this unit
	 * @post if the given strength is equal to or greater then the minimum strength and equal too or smaller then maximum strength,
	 * 			the new strength of this unit is equal to the given strength
	 * 			| if ((strength >= getMinStrength())&&(strength <= getMaxStrength))
	 * 			|		then new.getStrength()= strength
	 * @post 	if the given strength is smaller then the minimum strength
	 * 			the new strength is set to the minimum strength
	 * 			|if (strength < getMinStrength())
	 * 			|	new.strength = getMinStrength()
	 * @post	if the given strength is greater then the maximum strength
	 * 			the new strength is set to the maximum strength
	 * 			|if (strength < getMaxStrength())
	 * 			|	new.strength = getMaxStrength()
	 * 
	 */
	public void setStrength(int strength){
		if (strength < getMinStrength()){
			this.strength = getMinStrength(); 
		}
		else if (strength > getMaxStrength()){
			this.strength = getMaxStrength();
		}
		else {
			this.strength = strength;
		}
	}
	private int strength;
	/**
	 * agility totaal progr
	 * returns the agility of the unit
	 */
	@Basic
	public int getAgility(){
		return this.agility;
	}
	/**
	 * 
	 * @return the lowest possible value of the agility of the unit is 1
	 */
	@Basic
	private int getMinAgility(){
		return 1;
	}
	/**
	 * 
	 * @return the highest possible value of agility of the unit is 200
	 * 			must be greater then getMinAgiliy()
	 */
	@Basic
	private int getMaxAgility(){
		return 200;
	}
	/**
	 * 
	 * @param agility
	 * 			the new agility for this unit
	 * @post if the given agility is equal to or greater then the minimum agility and equal too or smaller then maximum agility,
	 * 			the new agility of this unit is equal to the given agility
	 * 			| if ((agility >= getMinAgility())&&(agility <= getMaxAgility))
	 * 			|		then new.getAgility()= agility
	 * @post 	if the given agility is smaller then the minimum agility
	 * 			the new agility is set to the minimum agility
	 * 			|if (agility < getMinAgility())
	 * 			|	new.agility = getMinAgility()
	 * @post	if the given agility is greater then the maximum agility
	 * 			the new agility is set to the maximum agility
	 * 			|if (agility < getMaxAgility())
	 * 			|	new.agility = getMaxAgility()
	 * 
	 */
	public void setAgility(int agility){
		if (agility < getMinAgility()){
			this.agility = getMinAgility(); 
		}
		else if (agility > getMaxAgility()){
			this.agility = getMaxAgility();
		}
		else {
			this.agility = agility;
		}
	}
	private int agility;
	/**
	 * toughness totaal progr
	 * 
	 * returns the toughness of the unit
	 */
	@Basic
	public int getToughness(){
		return this.toughness;
	}
	/**
	 * 
	 * @return the lowest possible value of toughness of a unit is 1
	 */
	@Basic
	private int getMinToughness(){
		return 1;
	}
	/**
	 * 
	 * @return the highest value of toughness of a unit is 200
	 */
	@Basic 
	private int getMaxToughness(){
		return 200;
	}
	/**
	 * 
	 * @param toughness
	 * 			the new toughness for this unit
	 * @post if the given toughness is equal to or greater then the minimum toughness and equal too or smaller then maximum toughness,
	 * 			the new toughness of this unit is equal to the given toughness
	 * 			| if ((toughness >= getMinToughness())&&(toughness <= getMaxToughness))
	 * 			|		then new.getToughness()= toughness
	 * @post 	if the given toughness is smaller then the minimum toughness
	 * 			the new toughness is set to the minimum toughness
	 * 			|if (toughness < getMinToughness())
	 * 			|	new.toughness = getMinToughness()
	 * @post	if the given toughness is greater then the maximum toughness
	 * 			the new toughness is set to the maximum toughness
	 * 			|if (toughness < getMaxToughness())
	 * 			|	new.toughness = getMaxToughness()
	 */
	public void setToughness(int toughness){
		if (toughness < getMinToughness()){
			this.toughness = getMinToughness(); 
		}
		else if (toughness > getMaxToughness()){
			this.toughness = getMaxToughness();
		}
		else {
			this.toughness = toughness;
		}
	}
	
	private int toughness;
	/**
	 * 
	 * @return the highest possible value of as stat(agility, weight,toughness, strength)
	 *  of a unit at initialization is 100
	 */
	private int getMaxInitialStat(){
		return 100;
	}
	/**
	 * 
	 * @return the lowest possible value of as stat(agility, weight,toughness, strength)
	 *  of a unit at initialization is 25
	 */
	private int getMinInitialStat(){
		return 25;
	}
	
	/**
	 *
	 * return the maximal number of hitpoints a unit can have
	 */
	@Basic 
	public int getMaxHitpoints(){
		return(int)(200*(getWeight()/100)*(getToughness()/100)+1);
		
	}
	
	/**
	 * 
	 * @return the current amount of hitpoints of the unit
	 */
	@Basic 
	public double getHitpoints(){
		return this.hitpoints;
	}
	/**
	 * 
	 * @param 	hitpoints
	 * 			the current 
	 * @param 	maxhit
	 * 			the maximum number of hitpoints a unit can have
	 * @return 	true if hitpoints is not negative and does not exceed the maximum number of hitpoints
	 * 			| result == (hitpoints >= 0)&&(hitpoints <= maxhit)
	 */
	private static  boolean isValidHitpoints(double hitpoints, int maxHitpoints){
		return ((hitpoints >= 0) && (hitpoints <= maxHitpoints));
	}
	/**
	 * 
	 * @param 	hitpoints
	 * 			the new number of hitpoints of a unit
	 * @pre 	the given hitpoints must be a valid number of hitpoints for the unit
	 * 			| isValidHitpoints(hitpoints,getMaxHitpoints())
	 * @post	The hitpoints of this unit is equal to the given
     * 		   	hitpoints.
     *       	| new.getHitpoints() == hitpoints
     * 
	 */
	public void setHitpoints(double hitpointsIn){
		assert isValidHitpoints(hitpointsIn,getMaxHitpoints());
		if (hitpointsIn < 0){
			this.hitpoints = 0;
		}
		else{
			this.hitpoints = hitpointsIn;
		}
	}
	
	private double hitpoints;
	
	/**
	 * 
	 * @return faction of unit
	 */
	public Faction getFaction(){
		return this.faction;
		
	}
	/**
	 * 
	 * @param faction
	 * 			the faction to which this unit belongs
	 * @post the faction of this unit is set to faction
	 * 			|this.faction = faction;
	 * @post if the faction is not in the world the faction is added to the world.
	 * 			|if (!getWorld().listFactions().contains(faction)){
	 *			|	then	this.world.addFactions(faction);
	 * @post the unit is added to the units in this faction.
	 * 			|faction.addUnitToFaction(this);
	 */
	@Model
	private void setFaction(Faction faction){
		if (!getWorld().listFactions().contains(faction)){
			this.world.addFactions(faction);
		}
		this.faction = faction;
		faction.addUnitToFaction(this);
	}
	
	
	private Faction faction;
	
	
	
	/**
	 * @return the maximum stamina the unit can have
	 * 
	 */
	@Basic 
	public int getMaxStamina(){
		return (int)(200*(getWeight()/100)*(getToughness()/100)+1);
	}

	/**
	 * 
	 * @return the current amount of stamina of the unit
	 */
	@Basic @Raw
	public double getStamina(){
		return this.stamina;
	}
	/**
	 * 
	 * @param 	staminaIn
	 * 			the current 
	 * @param 	maxstam
	 * 			the maximum number of stamina a unit can have
	 * @return 	true if stamina is not negative and does not exceed the maximum number of stamina
	 * 			| result == (stamina >= 0)&&(stamina <= maxstam)
	 */
	private boolean isValidStamina(double staminaIn, int maxStamina){
		return ((staminaIn >= 1) && (staminaIn <= maxStamina));
	}
	/**
	 * 
	 * @param 	stamina
	 * 			the new number of stamina of a unit
	 * @pre 	the given stamina must be a valid number of stamina for the unit
	 * 			| isValidStamina(stamina,getMaxStamina())
	 * @post	The stamina of this unit is equal to the given
     * 		   	stamina.
     *       	| new.getStamina() == stamina
     * 
	 */
	public void setStamina(double staminaIn){
		assert isValidStamina(staminaIn,getMaxStamina());
		if (staminaIn < 0){
			this.stamina = 0;
		}
		else{
			this.stamina = staminaIn;
		}
	}
	private double stamina;
	
	/**
	 * totaal progr & float nrs
	 * returns the angle of orentation of a unit
	 */
	@Basic
	public float getAngle(){
		return this.angle;
	}
	/**
	 * 
	 * @return 	the lowest possible value an angle can have
	 * 			is -pi/2
	 * 			| result == - pi/2
	 */
	private float getMinAngle(){
		return (float) (-Math.PI/2);
	}
	/**
	 * 
	 * @return the 	highest possible value an angle can have
	 * 				is -3pi/2
	 * 				| result ==  5pi/2
	 */
	private float getMaxAngle(){
		return (float) (-3*(Math.PI)/2);
	}
	/**
	 * 
	 * @param 	atheta
	 * 			the new angle to be set
	 * @post 	if the given angle is between the mimimum and maximum angle 
	 * 			the angle of the unit is equal to the given angle
	 * 			|if ( (angle >= getMinAngle()) && (angle <= getMaxAngle()) )
	 *        	| 	then new.getAngle() == angle
	 * @post	if the given angle is greater then the maximum angle
	 * 			the angle of the unit is equal to 
	 * 			(angle - getMinAngle())%(getMaxAngle()-getMinAngle())+getMinAngle()
	 * 			|if angle > getMaxAngle
	 * 			| 		then new.angle= (angle - getMinAngle())%(getMaxAngle()-getMinAngle())+getMinAngle()
	 * @post	if the given angle is smaller then the minimum angle 
	 * 			the angle of the unit is equal to
	 * 			(angle - getMaxAngle())%(getMinAngle()-getMaxAngle())+getMaxAngle()
	 * 			|if angle < getMinAngle
	 * 			|		then new.angle= (angle - getMaxAngle())%(getMinAngle()-getMaxAngle())+getMaxAngle()
	 */
	@Model
	private void setAngle(float atheta){
		if (atheta >= getMaxAngle()){
			atheta = (atheta - getMinAngle())%(getMaxAngle()-getMinAngle())+getMinAngle();
		}
		else if (atheta < getMinAngle()){
			atheta = (atheta - getMaxAngle())%(getMinAngle()-getMaxAngle())+getMaxAngle();
		}
		this.angle = atheta;
	}
	private float angle;

	/**
	 * 
	 * @param 	deltaT
	 * 			a given duration
	 * @post all the stats for the unit are updated to a new value depending on the time
	 * @throws IllegalArgumentException
	 * 			|(deltaT > 0.2 || deltaT < 0)
	 */
	public void advanceTime(double deltaT)throws IllegalArgumentException {
		assert(this.isTerminated);
		Random rand = new Random();
		double val = 0;
		 
		if (deltaT > 0.2 || deltaT < 0){
			throw new IllegalArgumentException();
		}
		if (!isMoving()&&!isFalling()){
			checkIfFalling();
		}
		if (isFalling()){
			excecuteFalling(deltaT);
	
		}
		if (isDefaultBehaving()){
			if (getActivityStatus() == ActivityStatus.NONE)
				executeDefault(deltaT);
			if (isMoving()){
				Random random = new Random();
				double pivot = random.nextDouble();
				if (pivot >= 0.5)
					startSprinting();
					if (this.stamina <= 0)
						stopSprinting();
						rest();			
			}
		}
		if (isFollowing()){
			executeFollowing();
		}
		if (isMovingTo() && this.activityStatus==ActivityStatus.NONE){
			moveTo(getMoveToTartget());
			if (getCubePosition() == getMoveToTartget()){
				setIsMovingTo(false);
			}
		}
		if (isMovingTo()&&(!isMoving()||this.activityStatus==ActivityStatus.NONE)){
			setIsMovingTo(false);
		}
		if (isMoving()){
			excecuteMovementToAdject(deltaT);
		}
		
		
		else if (isWorking()){
			excecuteWorking(deltaT);
		}
		
		else if (isAttacking()){
			excecuteAttack(deltaT);
			
		}
		else if (isResting()){
			excecuteResting(deltaT);
		}
		if (!isMoving()){
			double[] nulV = {0,0,0};
			setVelocity(nulV);
		}
		
		this.gametime += deltaT;
		
		if (gametime % 180.0 == 0.0)
			rest();
		if (experience / 10 >= 1){
			val = rand.nextDouble();
			if (val <= 0.333){
				setStrength(getStrength() + 1);
			}
			else if ((val > 0.333) && (val < 0.666)){
				setToughness(getToughness() + 1);
			}
			else{
				setAgility(getAgility() + 1);
			}
			this.experience =0;
		}
		if(getHitpoints() == 0){
			terminate();
		}
		
		if (!isMovingTo()&&!isMoving()){
			stopSprinting();
		}
		if (getStamina()==0){
			stopSprinting();
		}
	}
	
	/**
	 * @post if the unit is carrying something it dropsall 
	 * 		|if (isCarryingLogs()||isCarryingBoulders())
	 * 		| then drop();
	 * @post the unit is removed from the world
	 * 		|world.removeUnitFromWorld(this);
	 * @post the unit is removed from its faction
	 * 		| this.faction.removeUnitFromFaction(this);
	 * @post the unit is terminated
	 * 		|new.isTerminated =true;
	 */
	private void terminate() {
		if (isCarryingLog()||isCarryingBoulder()){
			drop();
		}
		setActivityStatus(ActivityStatus.NONE);
		getWorld().removeUnitFromWorld(this);
		this.world = null;
		getFaction().removeUnitFromFaction(this);
		this.faction = null;
		this.isTerminated =true;
		
	}
	/**
	 * 
	 * @return whether or not this unit is terminated.
	 */
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	private boolean isTerminated = false;
	

	/**
	 * 
	 * @param deltaT the time over wich the attack progresses
	 * @post if the total attack time is under 1 the  stamina of the attack time will grow with deltaT
	 * 				and the stamina wil decrease by 1 and if the new stamina drops below 0 the unit wil stop the attack
	 * 				and stop the attack
	 * 		| if attackTime < 1 then
	 * 		| 	new attacktime ==  attackTime + deltaT
	 * 		|     	newStam = getStamina() - 1
	 * 		|		if newstam <= 0
	 * 		|			setActivityStatus(ActivityStatus.NONE)
	 * 	@post  	else the unit wil gain 20 experience and the activity status wil be set to None
	 * 		|		this.experience += 20
	 * 		|		setActivityStatus(ActivityStatus.NONE)
	 */
	private void excecuteAttack(double deltaT){
		if (this.attackTime < 1){
			this.attackTime  += deltaT;
			double newstam = getStamina() - 1;
			setStamina(newstam);
			if (newstam < 0){
				setActivityStatus(ActivityStatus.NONE);
			}
		}
		else{
			this.experience += 20;
			setActivityStatus(ActivityStatus.NONE);
		}
		
		
	}
	
	private double attackTime = 0;
	
	/**
	 * 
	 * @param deltaT the time over witch the mork progresses
	 * @post the working sequence is executed over a time of deltaT
	 * 			
	 */
	private void excecuteWorking(double deltaT){
		
		boolean workIsDone = false;
		if (this.workTime <= (500/getStrength())){
			this.workTime += deltaT;
		}
		else {
			if (isCarryingLog() || isCarryingBoulder()){
				drop();
				workIsDone = true;
			}
			else if (getWorld().getTerrainTypes()[getWorkPlace()[0]][getWorkPlace()[1]][getWorkPlace()[2]] == 3 
					&& !getWorld().getBoulderInCube(getWorkPlace()).isEmpty()
					&&!getWorld().getLogsInCube(getWorkPlace()).isEmpty()){
				ImproveStuf();
				workIsDone = true;
			}
			else if (!getWorld().getBoulderInCube(getWorkPlace()).isEmpty()){
				for( Boulder b :getWorld().getBoulderInCube(getWorkPlace())){
					setCarryedItem(b);
					setCarryingWeight(getWeight()+b.getWeight());
					getWorld().removeBoulderFromWorld(b);
					break;
				}
				workIsDone = true;
			}
			else if (!getWorld().getLogsInCube(getWorkPlace()).isEmpty()){
				for( Log l :getWorld().getLogsInCube(getWorkPlace())){
					setCarryedItem(l);
					setCarryingWeight(getWeight()+l.getWeight());
					getWorld().removeLogFromWorld(l);
					break;
				}
				workIsDone = true;
			}
			else if (getWorld().getTerrainTypes()[getWorkPlace()[0]][getWorkPlace()[1]][getWorkPlace()[2]] == 2){
				workIsDone = true;
				getWorld().collapse(getWorkPlace()[0],getWorkPlace()[1],getWorkPlace()[2]);
				double[] pos = {getWorkPlace()[0]+0.5,getWorkPlace()[1]+0.5,getWorkPlace()[2]+0.5};
				Log l = new Log(pos,this.world);
				getWorld().addStufToWorld(l);
			}
			else if (getWorld().getTerrainTypes()[getWorkPlace()[0]][getWorkPlace()[1]][getWorkPlace()[2]] == 1){
				workIsDone = true;
				getWorld().collapse(getWorkPlace()[0],getWorkPlace()[1],getWorkPlace()[2]);
				double[] pos = {getWorkPlace()[0]+0.5,getWorkPlace()[1]+0.5,getWorkPlace()[2]+0.5};
				Boulder b = new Boulder(pos,this.world);
				getWorld().addStufToWorld(b);
			}
			if (workIsDone){
				experience += 10;
			}
			this.workTime=0;
			stopWorking();
		}
	}
	


	/**
	 * 
	 * @return true if the carried item is a log and not null, false otherwise.
	 * 			| result ==
	 * 			| (getCarryedItem() instanceof Log && getCarryedItem() != null)
	 */
	public boolean isCarryingLog() {
		return (getCarryedItem() instanceof Log && getCarryedItem() != null);
	}
	/**
	 * 
	 * @return true if the carried item is a boulder and not null, false otherwise.
	 * 			| result ==
	 * 			| (getCarryedItem() instanceof Boulder && getCarryedItem() != null)
	 */
	public boolean isCarryingBoulder() {
		return (getCarryedItem() instanceof Boulder && getCarryedItem() != null);
	}

	/**
	 * @post a boulder from the units position is consumed.
	 * 			|for (Boulder b :getWorld().getBoulderInCube(getWorkPlace())
	 * 			|	this.world.removeBoulderFromWorld(b)
	 * 			|	break
	 * @post a log from the units position is consumed.
	 * 			|for (Log l :getWorld().getLogsInCube(getWorkPlace()))
	 * 			|	this.world.removeLogFromWorld(l)
	 * 			|	break;
	 * @post the weight of the unit is increased by 1.
	 * 			|setWeight(getWeight()+1)
	 * @post the toughness of the unit is increased by 1.
	 * 			|setToughness(getToughness()+1)
	 */
	private void ImproveStuf() {
		for (Boulder b :getWorld().getBoulderInCube(getWorkPlace())){
			 b.terminate();
			 break;
		}
		for (Log l :getWorld().getLogsInCube(getWorkPlace())){
			 l.terminate();
			 break;
		}
		setWeight(getWeight()+1);
		setToughness(getToughness()+1);	
	}

	/**
	 * 
	 * @throws IllegalThreadStateException
	 * 			|if getCarryedItem = null
	 * @effect the rawMaterial being carryed by the unit shall be droped on the position of the unit. and 
	 * 			|dropPosition = {getWorkPlace()[0]+0.5,getWorkPlace()[1]+0.5,getWorkPlace()[2]+0.5}
	 * 			| carriedItem.setPosition(dropPosition);
	 * 			|getWorld().addStufToWorld(carriedItem)
	 * 			|setWeight(getWeight()-mat.getWeight()) 
	 * 			|setCarryedItem(null)
	 *
	 */
	@Model
	private void drop()throws IllegalThreadStateException {
		if (getCarryedItem()== null){
			throw new IllegalThreadStateException();
		}
		RawMaterial mat = getCarryedItem();
		double[] dropPosition = {getWorkPlace()[0]+0.5,getWorkPlace()[1]+0.5,getWorkPlace()[2]+0.5};
		mat.setPosition(dropPosition);
		getWorld().addStufToWorld(mat);
		setWeight(getWeight()-mat.getWeight());
		setCarryedItem(null);
		
		
	}
	/**
	 * 
	 * @param i the weight of the unit while carrying stuf.
	 * @post if the weight is smaller then getMinWeight the weight is set to getMinWeight()
	 * 		|if (weight < getMinWeight()
	 * 		|	then this.weight = getMinWeight()
	 * @post otherwise the weight is set to i
	 * 		|this.weight = i;
	 */
	@Model
	private void setCarryingWeight(int i) {
		if (weight < getMinWeight()){
			this.weight = getMinWeight(); 
		}
		else{
			this.weight = i;
		}
	}

	/**
	 * 
	 * @return a set of all the logs being carried by the unit
	 */
	public RawMaterial getCarryedItem(){
		return this.CarryedItem;
		
	}
	/**
	 * 
	 * @param logs the logs that are to be carried by the unit
	 */
	@Model
	private void setCarryedItem(RawMaterial item){
		this.CarryedItem= item;
	}
	
	 RawMaterial CarryedItem ;
	

	
	
	private double workTime = 0;
	private double totalFalDist = 0;
	
	
	/**
	 * 
	 * @param deltaT the time over wich falling is executed
	 * @post the falling sequence is executed over a time deltaT
	 */
	private void excecuteFalling(double deltaT){
		int x = (int) getPosition()[0];
		int y = (int) getPosition()[1];
		int z = (int) getPosition()[2];
		int[] below = {x,y,z-1};
		if (!getWorld().passable(below)){
			double newZ = getPosition()[2] -3*deltaT ;
			if (newZ <= z){
				double[] newPos = {getPosition()[0],getPosition()[1],z};
				this.totalFalDist += (getPosition()[2]-z);
				setPosition(newPos);
				setHitpoints(getHitpoints() - 10*((int)this.totalFalDist));
				this.totalFalDist = 0;
				setActivityStatus(ActivityStatus.NONE);
			}
			else {
				double[] newPos = {getPosition()[0],getPosition()[1],newZ};
				setPosition(newPos);
				this.totalFalDist += (getPosition()[2]-newZ);
			}
			
		}
		if (getWorld().passable(below)){
			double newZ = getPosition()[2] -3*deltaT ;
			this.totalFalDist += (getPosition()[2]-newZ);
			double[] newPos = {getPosition()[0],getPosition()[1],newZ};
			setPosition(newPos);
			this.totalFalDist += (getPosition()[2]-newZ);
		}
	}
	
	/**
	 * 
	 * @param deltaT the time over wich to execute the units movement to an adject location
	 * @post the movement to adject sequence is executed for a time deltaT
	 */
	private void excecuteMovementToAdject(double deltaT){
		if (!isSprinting()){
			for (int i =0;i < 3; i++){
				if (velocity[i] < 0){
					if (getPosition()[i]+velocity[i]*deltaT < getDestination()[i]){
						setPosition(getDestination());
						double[] velo = {0,0,0};
						setVelocity(velo);
						experience += 1;
						setActivityStatus( ActivityStatus.NONE);
					}
					else {
						double[] newpos = getPosition();
						newpos[i] = getPosition()[i]+velocity[i]*deltaT;
						setPosition(newpos);			
					}
			}
				else if (velocity[i] > 0){
					if (getPosition()[i]+velocity[i]*deltaT > getDestination()[i]){
						setPosition(getDestination());
						double[] velo = {0,0,0};
						setVelocity(velo);
						experience += 1;
						setActivityStatus(ActivityStatus.NONE);
					}
					else {
						double[] newpos = getPosition();
						newpos[i] = getPosition()[i]+velocity[i]*deltaT;
						setPosition(newpos);
					}
				}
			}
		}
		
		else {
			double newstam = getStamina()- deltaT;
			if (newstam < 0){
				newstam = 0;
			}
			setStamina(newstam);
			for (int i =0;i < 3; i++){
				if (getVelocity()[i] < 0){
					if (getPosition()[i]+getVelocity()[i]*2*deltaT < getDestination()[i]){
						setPosition(getDestination());
						double[] velo = {0,0,0};
						setVelocity(velo);
						experience += 1;
						setActivityStatus( ActivityStatus.NONE);
					}
					else {
						double[] newpos = getPosition();
						newpos[i] = getPosition()[i]+getVelocity()[i]*2*deltaT;
						setPosition(newpos);
					}
				}
				else if (getVelocity()[i] > 0){
					if (getPosition()[i]+getVelocity()[i]*2*deltaT > getDestination()[i]){
						setPosition(getDestination());
						double[] velo = {0,0,0};
						setVelocity(velo);
						experience += 1;
						setActivityStatus(ActivityStatus.NONE);
					}
					else {
						double[] newpos = getPosition();
						newpos[i] = getPosition()[i]+getVelocity()[i]*2*deltaT;
						setPosition(newpos);
					}	
				}
			}
		}
	}
	
	private double gametime ;
	/**
	 * 
	 * @return the current experience gained by this unit
	 */
	public int getExperience(){
		return this.experience;
	}
	
	private int experience = 0;
	
	/**
	 * 
	 * @return the basespeed for a unit based on its strength agility and weight
	 */
	@Model
	private double getBaseSpeed(){
		return 1.5*(getStrength()+getAgility())/((200*getWeight())/100);
	}
	/**
	 * 
	 * @param initial
	 * @param destination
	 * @return if the unit goes up the walkingspeed is 0.5 times the basespeed
	 * 			|if (initial[2]-destination[2] == -1)
	 *			|	return 0.5*getBaseSpeed()
	 * @return if the unit goes up the walkingspeed is 0.5 times the basespeed
	 * 			|if (initial[2]-destination[2] == 1)
	 *			|	return 1.2*getBaseSpeed()
	 * @return if the unit stays at the same level the walkingspeed is the basespeed
	 * 			|if (initial[2]-destination[2] == 0)
	 */
	@Model
	private double getWalkingSpeed(double[]initial,double[]destination){
		if (initial[2]-destination[2] == -1){
			return 0.5*getBaseSpeed();
		}
		else if (initial[2]-destination[2] == 1){
			return 1.2*getBaseSpeed();
		}
		else{
			return getBaseSpeed();
		}
	}
	
/**
 * 
 * @param 	x
 * 			de x coordinaat van de positie naar waar de unit zich moet verplaatsten
 * @param 	y
 *  		de y coordinaat van de positie naar waar de unit zich moet verplaatsten
 * @param 	z
 *  		de z coordinaat van de positie naar waar de unit zich moet verplaatsten
 * @post	the detination is set to the given coordinates
 * 			|this.destination = {x,y,z}
 * @post 	the velocity is set to the correct speed s in the x, y and z directions		
 * 			|Vx = getWalkingSpeed(getPosition(),dest)*(x-getPosition()[0])/distance;
 *			|Vy = getWalkingSpeed(getPosition(),dest)*(x-getPosition()[0])/distance;
 *			|Vz = getWalkingSpeed(getPosition(),dest)*(x-getPosition()[0])/distance;
 * 			|this.velocity = {Vx,Vy,Vz}
 * @throws IllegalArgumentException if the given coordinates are not adjective to the curent position
 * 			| !isValidAdject()
 * @throws IllegalStateException if the unit is faling or attacking
 * 			|isAttacking() || isFalling()
 */
	public void moveToAdject(double dx, double dy, double dz)throws IllegalArgumentException ,IllegalStateException{
		if (!isValidAdject(dx,dy,dz)){
			throw new IllegalArgumentException();
		}
		if (isAttacking() || isFalling()){
			throw new IllegalStateException();
		}
		double[] dest = {getPosition()[0]+dx,getPosition()[1]+dy,getPosition()[2]+dz};
		double distance = Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2)+
				Math.pow(dz,2));
		
		double Vx = (getWalkingSpeed(getPosition(),dest)*(dx))/distance;
		double Vy = (getWalkingSpeed(getPosition(),dest)*(dy))/distance;
		double Vz = (getWalkingSpeed(getPosition(),dest)*(dz))/distance;
		double[] velo = {Vx,Vy,Vz};
		setVelocity(velo);
		setAdjDestination (dest);
		setAngle((float) Math.atan2(getVelocity()[1],getVelocity()[0]));
		setActivityStatus(ActivityStatus.MOVING);
		
		
	}
	/**
	 * 
	 * @param dx the distance to be traveled in x direction
	 * @param dy the distance to be traveled in y direction
	 * @param dz the distance to be traveled in z direction
	 * @return true if dx,dy and dz are 0,-1 or 1 and the position reched by this movement is a validPosition
	 * 		|result == (((dx <=1) && (dx >= -1)) && ((dy <=1) && (dy >= -1))
	 * 		| 			&& ((dz >= -1)&&(dz <=1))&&(isValidPosition(possibleDest)))
	 */
	@Model
	private boolean isValidAdject(double dx, double dy,double dz){ 
		double[] possibleDest = {getPosition()[0]+dx,getPosition()[1]+dy,getPosition()[2]+dz};
		return (((dx <=1) && (dx >= -1)) && ((dy <=1) && (dy >= -1))
				&& ((dz >= -1)&&(dz <=1))&&(isValidPosition(possibleDest)));				

	}
	
	/**
	 *
	 * @post the activitystatus is update to sprinting if the unit is moving and has stamina
	 * 		| if ((isMoving()) && (this.stamina > 0))
	 * 		|		new.isSprinting() = true;
	 */
	public void startSprinting(){
		assert((isMoving()) && (this.stamina > 0));
		this.sprinting = true;
			 
	}
	/**
	 * @post sprinting  is set to fals
	 * 		|new.isSprinting() = false
	 */	
	public void stopSprinting(){
		this.sprinting =false;

		
		
	}
	private boolean sprinting = false;
	/**
	 * @return true if the unit is sprinting
	 * 		   false if the unit is not sprinting
	 * 
	 */
	public boolean isSprinting(){
		return this.sprinting;
	
	}
	/**
	 * 
	 * @param status the activety status to be set for this unit.
	 * @post the ActivityStatus is set to status
	 * 		|new.getActivityStatus = status
	 */
	@Model
	private void setActivityStatus(ActivityStatus status){
		this.activityStatus = status;
	}
	/**
	 * 
	 * @return the current activity status of the unit.
	 */
	private ActivityStatus getActivityStatus(){
		return this.activityStatus;
	}
	private ActivityStatus activityStatus = ActivityStatus.NONE ;
	/**
	 * 
	 * @return the destination of a unit
	 */
	private double[] getDestination(){
		return this.adjDestination;
	}
	/**
	 * 
	 * @param destination
	 * @post sets the new destination to the given destination
	 * 		|new.adjDestination = destination
	 */
	@Model
	private void setAdjDestination(double[] destination){
		this.adjDestination = destination;
		}
	
	private double[] adjDestination = getPosition();
	/**
	 * 
	 * @return the velocity of a unit
	 */
	@Basic
	private double[] getVelocity(){
		return this.velocity;
	}
	/**
	 * 
	 * @return the current speed of a unit
	 */
	public double getCurrentSpeed() {
		return Math.sqrt(Math.pow(getVelocity()[0],2)+Math.pow(getVelocity()[1],2)+
				Math.pow(getVelocity()[2],2));
	}
	/**
	 * 
	 * @param velocity 
	 * @post the new velocity is equal to the given velocity
	 * 		|new.getVelocity = velocity
	 */
	@Basic
	private void setVelocity(double[] velocity){
		this.velocity = velocity;
	}
	private double[] velocity  ={0,0,0};
	
	
	
	
	/**
	 * 
	 * @param cube the cube to move to.
	 * @post if the target cube is not passable moveto doesn't do anything.
	 * @post else if the pathfinding algoritm is executed en movetoadject is caled to move to cube.
	 * @throws IllegalArgumentException if the given position of the new cube is not valid
	 * 			| if (!isValidPosition(Cube)
	 * @throws illegalStateExeption if the unit is currently falling.
	 * 			| if ( isFalling())
	 * 
	 */
	public void moveTo(int[] cube) throws IllegalArgumentException{
		double[] checkPos = {cube[0],cube[1],cube[2]};
		if (!isValidPosition(checkPos)){
			throw new IllegalArgumentException();
		}
		if ( isFalling()){
			throw new IllegalStateException();
		}
		else if ((!getWorld().passable(cube))){
			return;
			
		}
		else {
			setMoveToTarget(cube);
			if (getCubePosition()!= cube){
				int [][] target = {cube,{0}};
				List<int[][]> list = new ArrayList<int[][]>();
				list.add(target);
				setQ(list);
				int i = 0;
				while((!isInQ(getCubePosition()))&& (i < getQ().size())){
					int[][] pair = getQ().get(i);
					search(pair[0],pair[1][0]);
					i += 1;
				}
				if (isInQ(getCubePosition())){
					List<int[][]> neighbors = new ArrayList<int[][]>();
					for (int[][] p :getQ()){
						if (neighbors(getCubePosition()).contains(p[0])){
							neighbors.add(p);
						}
						while (neighbors.size() >  1){
							if (neighbors.get(0)[1][0] <= neighbors.get(1)[1][0]){
								neighbors.remove(1);
							}
							else{
								neighbors.remove(0);
							}
						}
					}
					int[] currentTarget = neighbors.get(0)[0];
					int dx = currentTarget[0] - getCubePosition()[0];
					int dy = currentTarget[1] - getCubePosition()[1];
					int dz = currentTarget[2] - getCubePosition()[2];
					setIsMovingTo(true);
					moveToAdject(dx,dy,dz);
				}
			}
		}
	}
	


	/**
	 * 
	 * @param cube the target for long distance movement for the unit
	 * @post the moveTo target is set to cube.
	 * 		| new.getMoveToTarget() = cube
	 */
	private void setMoveToTarget(int[] cube){
		this.moveToTartget=cube;
	}
	/**
	 * 
	 * @return the target of moveTo
	 */
	private int[] getMoveToTartget(){
		return this.moveToTartget;
	}

	private int[] moveToTartget;
	/**
	 * 
	 * @return true if the unit is moving to a long dictance location.
	 * 			|this.isMovingTo
	 * @return false otherwise
	 */
	public boolean isMovingTo(){
		return this.isMovingTo;
	}
	/**
	 * 
	 * @param bol boolean indicating if the unit is moving to a long distance location.
	 * @post isMovingTo is set to bol.
	 */
	private void setIsMovingTo(boolean bol){
		this.isMovingTo = bol;
	}
	
	private boolean isMovingTo = false;

	/**
	 * 
	 * @param cubePosition
	 * @return true if cubePosition is in a pair in Q
	 * 		|for (int[][] pair : getQ()){
	 *		|	if ((pair[0][0] == pos[0]) && (pair[0][1] == pos[1]) && (pair[0][2] == pos[2]))
	 *		|		result == true;
	 * @return false otherwise
	 * 		|result == false 
	 */
	private boolean isInQ(int[] pos){
		for (int[][] pair : getQ()){
			if ((pair[0][0] == pos[0]) && (pair[0][1] == pos[1]) && (pair[0][2] == pos[2])){
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param pos the position to search around
	 * @param N0 the search value
	 * 
	 * @effect if a cube is neighbouring the given cube , is passable and is neigbouring solid terrain
	 * 		   and isn't already in Q the cube associated with n+1 is added to Q
	 * 		    |for (int[] cube :neighbors(pos)
	 * 		    |			if passable(cube) && (getWorld().hasSolidNeigh(cube[0], cube[1], cube[2]) && (!isCubeInQ(cube)))
	 * 			|				list.add(cube)
	 * 			|for(int[] cube :list)
	 * 		    |	   		if !isInQ(cube)
	 * 		    |				Q.add({cube,{n+1}}
	 */
	
	private void search(int[] pos, int N0 ){
		List<int[]> list = new ArrayList<>();
		for (int[] cube :neighbors(pos)){
			 if ((getWorld().passable(cube))
							&& (getWorld().hasSolidNeigh(cube[0], cube[1], cube[2]))){
					list.add(cube);
			 }
			
		}
		for(int[] p : list){
			List<int[][]> q2 = getQ();
			int[][] pairToAdd = {p,{N0 + 1}};
			if (!isInQ(p)){
				q2.add(pairToAdd);
				setQ(q2);
			}
			
		}
	}
	
	
	
	/**
	 * 
	 * @return this.Q
	 */
	@Basic
	private List<int[][]> getQ(){
		return this.Q;
	}
	/**
	 * 
	 * @param pairs
	 * @post Q is set to pairs.
	 */
	@Basic
	private void setQ(List<int[][]> pairs) {
		this.Q = pairs;	
	}
	private List<int[][]> Q ;
	
	/**
	 * 
	 * @return true if the unit is moving
	 * 		   false if the unit is not moving
	 * 			|result = (this.activityStatus == ActivityStatus.MOVING)
	 */
	public boolean isMoving(){
		return (this.activityStatus == ActivityStatus.MOVING);
		
	}
	
	
	
	/**
	 * 
	 * @param x the x position to work at
	 * @param y the y position to work at
	 * @param z the z position to work at
	 * @post  if the workposition is the cubePosition of the unit or the workPositon is a neighbor
	 * 			 of the units cubeposition  the flag is true and if the flag is true  the workplace
	 * 			wil be set to workplace and the activity status wil be set to work.
	 * 		|workPosition = {x,y,z}
	 * 		|flag = false
	 * 		|if (Arrays.equals(workPosition, getCubePosition())
	 * 		| 	flag = true
	 * 		|for (int[] pos :neighbors(getCubePosition()))
	 * 		| 	if (Arrays.equals(pos, workPosition))
	 * 		| 		flag = true
	 * 		| if flag 
	 * 		| 	new getWorkPosition =workPosition 
	 * 		|	setActivityStatus(ActivityStatus.WORKING)
	 * @throws IllegalArgumentException  if the given position to work at is
	 * 			  not the position of the unit or a neighboring position
	 */
	public void workAt(int x, int y, int z) throws IllegalArgumentException{
		int[] workPosition = {x,y,z};
		boolean flag = false;
		if (Arrays.equals(workPosition, getCubePosition())){
			flag = true;
		}
		for (int[] pos :neighbors(getCubePosition())){
			if (Arrays.equals(pos, workPosition)){
				flag = true;
				break;
			}
		}
		if (flag){
			setWorkPlace(workPosition);	
			setActivityStatus(ActivityStatus.WORKING);
		}
		else{
			throw new IllegalArgumentException();
		}
		
	}
	/**
	 * 
	 * @param position
	 * @return the a set of the valid neighboring cubes of position
	 * 			| Set<int[]> ret = new HashSet<int[]>()
	 * 			| for (int i = -1; i < 2; i++)
	 * 			| 	for (int j = -1; j < 2; j++)
	 * 			|		for (int k = -1; k < 2; k++)
	 * 			|			if ((i != 0) || (j != 0) || (k != 0))
	 * 			| 				int[] neigh = {position[0] + i, position[1] + j, position[2] + k}
	 * 			|				if (getWorld().isValidCube(neigh))
	 * 			|					ret.add(neigh)
	 * 			| return ret
	 */
	@Model
	public Set<int[]> neighbors(int[] position) {
		Set<int[]> ret = new HashSet<int[]>();
		for (int i = -1; i < 2; i++){
			for (int j = -1; j < 2; j++){
				for (int k = -1; k < 2; k++){
					if ((i != 0) || (j != 0) || (k != 0)){
						int[] neigh = {position[0] + i, position[1] + j, position[2] + k};
						if (getWorld().isValidCube(neigh)){
							ret.add(neigh);
						}
					}
				}
			}
		}
		
		return ret;
	}
	/**
	 * 
	 * @param Place the plce to be the work place of the unit
	 * @post the workPlace is set to Place.
	 * 		| new getWorkPlace = place
	 */
	private void setWorkPlace(int[] Place){
		this.workPlace = Place;
	}
	/**
	 * 
	 * @return the current workplace of a unit.
	 */
	private int[] getWorkPlace(){
		return this.workPlace;
	}
	private int[] workPlace;
	

	/**
	 * @post stops the unit from working by setting the activity status to none.
	 * 		|setActivityStatus(ActivityStatus.NONE)
	 * 		
	 */
	public void stopWorking(){
		setActivityStatus(ActivityStatus.NONE);
	}
	
	
	
	/**
	 * 
	 * @return true if unit is working
	 * 		   false if unit is not working
	 */
	public boolean isWorking() {
		if (getActivityStatus() == ActivityStatus.WORKING){
			return true;
		}
		else return false;
	}

	// fighting//

	/**
	 * 
	 * @param defender, the unit that will defend the attack of the current unit
	 * 
	 *@post if the defender is not in an adjacent position, the unit moves to the cube of the defender to attack it
	 *		| if ((defender.getCubePosition()[0] != getCubePosition())[0] || (defender.getCubePosition()[0] != getCubePosition()[0] + 1) || (defender.getCubePosition()[0] != getCubePosition()[0] - 1))
	 *		|		moveTo(defender.getCubePosition())
	 *		| if ((defender.getCubePosition()[1] != getCubePosition())[1] || (defender.getCubePosition()[1] != getCubePosition()[1] + 1) || (defender.getCubePosition()[1] != getCubePosition()[1] - 1))
	 *		|		moveTo(defender.getCubePosition())
	 *		| if ((defender.getCubePosition()[2] != getCubePosition())[2] || (defender.getCubePosition()[2] != getCubePosition()[2] + 1) || (defender.getCubePosition()[2] != getCubePosition()[2] - 1))
	 *		|		moveTo(defender.getCubePosition())
	 *
	 *
	 *
	 */
	public void attack(Unit defender) {
		setActivityStatus( ActivityStatus.ATTACKING);
		int[] attack = getCubePosition();
		int[] defense = defender.getCubePosition();
		if (getFaction() == defender.getFaction())	
			if (!((attack[0] == defense[0]) || (attack[0] == (defense[0]) - 1) ||  (attack[0] == (defense[0]) + 1)))
				moveTo(defender.getCubePosition());
			if (!((attack[1] == defense[1]) || (attack[1] == (defense[1]) - 1) ||  (attack[1] == (defense[1]) + 1)))
				moveTo(defender.getCubePosition());
			if (!((attack[2] == defense[2]) || (attack[2] == (defense[2]) - 1) ||  (attack[2] == (defense[2]) + 1)))
				moveTo(defender.getCubePosition());
		
			defender.defend(this);
		
		
		this.activityStatus = ActivityStatus.NONE;
		
		
	}

	
	
	
	/**
	 * 
	 * @return true if the unit is attacking
	 * 		   false if the unit is defending
	 * 			|result ==
	 * 			| (getActivityStatus() == ActivityStatus.ATTACKING)
	 */
	public boolean isAttacking(){
		return(getActivityStatus() == ActivityStatus.ATTACKING);
			
	}
	/**
	 * 
	 * @param attacker, the unit that will affect this unit by attacking it
	 * @post  the attacked unit has dodged the attack, so it has a new position
	 * 		  | new.position[0] = position[0] + 0.1 ||
	 * 		  | new.position[0] = position[0] - 0.1
	 * 		  | new.position[1] = position[1] + 0.1 ||
	 * 		  | new.position[1] = position[1] - 0.1 
	 * 		  | new.position[2] = position[2] + 0.1 ||
	 * 		  | new.position[2] = position[2] - 0.1 
	 * 
	 * @post  the attacked unit has blocked the attack so it suffers no damage
	 * 		  | new.currentHitPoints() = this.currentHitPoints()
	 * 
	 * @post  the attacked unit has failed to either dodge or block the attack so it suffers damage
	 * 		  | new.currentHitPoints() = this.currentHitPoints() - strikepoints
	 * 
	 * @post the attacked unit and the attacker's orientation are updated so they face each other while fighting
	 * 		  | attacker.Angle = Math.atan2((this.Ypos - attacker.Ypos),(this.Xpos - attacker.Xpos))
	 * 		  | new.Angle = Math.atan2((attacker.Ypos - this.Ypos),(attacker.Xpos - this.Xpos))
	 * 		  
	 * 
	 * 
	 */
	public void defend(Unit attacker){
		assert getActivityStatus() == ActivityStatus.ATTACKING;
		boolean dodged = dodging(attacker);
		boolean blocked = blocking(attacker);
		if (dodged == true)
			this.setPosition(positionAfterDodged());
		else if (dodged == false)
			if (blocked == true)
				strikepoints = 0;
			else strikepoints = attacker.getStrength()/10;
		
		takingDamage(strikepoints);
		UpdateOrientation(attacker);
				
			
	}
	/**
	 * 
	 * @param strikepoints the value which indicates how much damage the defending unit has taken
	 * @throws IllegalArgumentException if strikepoints is lower than zero
	 * 		   | if (strikepoints < 0)
	 * 		   | 	throw exception
	 * @post the new hitpoints of a unit are the current hitpoints minus the strikepoints
	 * 		   | new.getHitpoints = old.getHitpoints - strikepoints
	 */
	private void takingDamage(double strikepoints) throws IllegalArgumentException{
		if(strikepoints < 0)
			throw new IllegalArgumentException();
		if (getHitpoints()- strikepoints < 0)
			setHitpoints(0);
		else setHitpoints(getHitpoints()- strikepoints);
		
	}
	
	/**
	 * 
	 * @param attacker who affects the chance of dodging the attack
	 * @return true if the unit has successfully dodged the attack
	 * @return false if the unit hasn't dodged the attack
	 * 
	 */
	private boolean dodging(Unit attacker){
		Random random = new Random();
		double chance = 0.20 * (getAgility() / attacker.getAgility());
		double pivot = random.nextDouble();
		if (pivot <= chance){
			experience += 20;
			return true;
			
		}
		else return false;
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return list position for which each element is added by 0.1 or substracted by 0.1
	 * @post   the new coordinates of the unit are added or substracted by 0.1
	 * 		   | new.posX = this.posX + 0.1||
	 * 		   | new.posX = this.posX - 0.1
	 * 		   | new.posY = this.posY + 0.1||
	 * 		   | new.posY = this.posY - 0.1
	 * 		   | new.posZ = this.posZ + 0.1||
	 * 		   | new.posZ = this.posZ - 0.1
	 * 
	 * 
	 */
	private double[] positionAfterDodged(){
		double position[] = getPosition();
		assert ((position[0] + 0.1 <= getXupbound()-1) && (position[0] - 0.1 >= XLOWBOUND ));
		assert ((position[1] + 0.1 <= getYupbound()-1) && (position[1] - 0.1 >= YLOWBOUND ));
		assert ((position[2] + 0.1 <= getZupbound()-1) && (position[2] - 0.1 >= ZLOWBOUND ));
		
		Random random = new Random();
		double pivot;
		for (int i = 0; i < position.length - 1; i++){
			pivot = random.nextDouble();
			if (pivot >= 0.5)
				position[i] += 0.1;
			else position[i] += -0.1;
		}
		return position;
	}
	/**
	 * 
	 * @param attacker unit which attacks the current unit
	 * @return true if the current unit has successfully blocked the attack
	 * 			|if random.nextDouble() <=0.25 * ((getStrength() + getAgility())/(attacker.getStrength()+ attacker.getAgility()))
	 * @return false if the current unit failed to block the attack
	 * 			|otherwise
	 */
	private boolean blocking(Unit attacker){
		Random random = new Random();
		double chance = 0.25 * ((getStrength() + getAgility())/(attacker.getStrength()+ attacker.getAgility()));
		double pivot = random.nextDouble();
		if (pivot <= chance){
			experience += 20;
			return true;
		}
		else return false;
		
	
	}
	
	private double strikepoints = 0;
	
	
	/**
	 * 
	 * @param attacker unit that attacks current unit
	 * @post  the orientation of both units is updated so they face each other while fighting
	 * 		  |attacker.Angle = math.atan2((this.Ypos - attacker.Ypos),(this.Xpos - attacker.Xpos))
	 *		  |new.Angle = Math.atan2((attacker.Ypos - this.Ypos),(attacker.Xpos - this.Xpos))
	 *
	 *
	 */
	private void UpdateOrientation(Unit attacker) {
		double defense[] = getPosition();
		double attack[] = attacker.getPosition();
		double atheta = Math.atan2((defense[1] - attack[1]),(defense[0] - attack[0]));
		double dtheta = Math.atan2((attack[1] - defense[1]),(attack[0] - defense[0]));
		setAngle((float)dtheta);
		attacker.setAngle((float)atheta);
	
	}
	
	

	
	/**
	 * @pre stamina or hitpoints have to be lower than their max value
	 * @post ActivityStatus is set to RESTING
	 * 		|setActivityStatus(ActivityStatus.RESTING)
	 * 
	 */
	public void rest() throws IllegalStateException {
		assert (getStamina() < getMaxStamina()||getHitpoints() < getMaxHitpoints());
		setActivityStatus(ActivityStatus.RESTING);
	}
	/**
	 * 
	 * @param deltaT the time wich to rest over.
	 * @post hitpoints have recovered until a new action has begun or it has fully recovered
	 * 		|if hitpoints < maxHitpoints 
	 * 		| 	new.hitpoints = getHitpoints() + ((getToughness()/200)*(deltaT/0.2))
	 * @post stamina has recovered until a new action was started or it fully recovered
	 * 		|if stamina < maxStamina
	 * 		|	new.stamina = getStamina() + ((getToughness()/100)*(deltaT/0.2))
	 * 
	 */
	private void excecuteResting(double deltaT){
			if (getHitpoints() < getMaxHitpoints()){
				double tempHit = getHitpoints() + ((getToughness()/200)*(deltaT/0.2));
				setHitpoints(tempHit);
	    		if (tempHit > getMaxHitpoints()){
	    			setHitpoints(getMaxHitpoints());
	    		}
			}
			if(getStamina() < getMaxStamina()){
				double tempStam = getStamina() + ((getToughness()/100)*(deltaT/0.2));
				setStamina(tempStam);
				
				if (tempStam > getMaxStamina()){
					setStamina(getMaxStamina());
				}
			}
	}
	


	/**
	 * 
	 * @return true if the unit is resting
	 * @return false if the unit is not resting
	 * 		|result ==
	 * 		|	(getActivityStatus()== ActivityStatus.RESTING)
	 */
	
	
	public boolean isResting(){
		return getActivityStatus()== ActivityStatus.RESTING;
		
	}
	

	/**
	 * @post the unit wil start its defaultbehavior.
	 * 		|new.isDefaultBehaving == true.
	 */
	public void startDefaultBehaviour(){
		//TODO make time depended executor.
		this.defaultbehaving = true;
		if (getTask()!= null){
			getTask().getActivities().run(this);
		}
		else if (getFaction().getScheduler().getScheduledTasks().size()>0) {
			Task task = getFaction().getScheduler().highestPriorityTaskNotExcecuted();
			setTask(task);
			task.setUnitAssignedTo(this);
		}
		else{
			Random random = new Random();
			double a;
			Unit defender = null;
			int cubePosition[] = new int[3];
			for (Unit otherU :this.world.listUnits()){
				if ((otherU.getFaction() != this.getFaction())
						&&(neighbors(getCubePosition()).contains(otherU.getCubePosition()))){
					defender = otherU;
					break;
				}
			}
			if (defender != null){
				a = random.nextDouble();
				if (a <= 0.250){
					cubePosition[0] = random.nextInt(getXupbound());
					cubePosition[1] = random.nextInt(getYupbound());
					cubePosition[2] = random.nextInt(getXupbound());
					moveTo(cubePosition); // random kiezen om te sprinten in moveTo zelf	
				}
				else if ((a > 0.250) && (a <= 0.500)){
					workAt(getCubePosition()[0],getCubePosition()[1],getCubePosition()[2]);		
				}
				else if ((a > 0.500) && (a <= 0.750)){ 
					rest();	
				}
				else {	
					attack(defender);
				}
			}
			else{
				a = random.nextDouble();
				if (a <= 0.333){
					cubePosition[0] = random.nextInt(getXupbound());
					cubePosition[1] = random.nextInt(getYupbound());
					cubePosition[2] = random.nextInt(getXupbound());
					moveTo(cubePosition); // random kiezen om te sprinten in moveTo zelf	
				}
				else if ((a > 0.333) && (a <= 0.666)){
					workAt(getCubePosition()[0],getCubePosition()[1],getCubePosition()[2]);					
				}
				else { 
					rest();	
				}
			}
		}
	}
	
	private void executeDefault(double deltaT){
		
		
	}
	

	

	/**
	 * @effect is ther is a task assigned to this unit the execution of the task wil be stopped.
	 * 		| if getTask != null
	 * 		| 	then getTask().stopExecutionOfTask()
	 * @post the unit has stopped doing a random activity
	 * 		|this.defaultBehaving = false
	 * @post the unit is doing nothing
	 * 		|new.getActivityStatus() = "none"
	 * 
	 * 
	 */
	public void stopDefaultBehaviour(){
		if (getTask() != null){
			getTask().stopExecutionOfTask();
		}
		setActivityStatus(ActivityStatus.NONE);
		this.defaultbehaving = false;
	}
	/**
	 * 
	 * @return the value of the variable defaultBehaving whether it is true or false
	 */
	public boolean isDefaultBehaving(){
		return this.defaultbehaving; 
			
	}
	private boolean defaultbehaving = false;
	
	
	
	/**
	 * @post else if the unit cubePosition has no solid neighbors the unit will start falling
	 * 			 or is not next to an edge of the world.
	 * 		|Set<int[]> neigs= neighbors(getCubePosition())
	 *		|boolean flag = true
	 *		|for (int[] neig :neigs)
	 *		|	if (!getWorld().passable(neig))
	 *		|		flag = false;
	 *		|for (int i = 0; i <3; i++)
	 *		| 	if (getCubePosition()[i] ==0)
	 *		|if(getCubePosition()[0] == getXupbound()-1)
	 *		| 	flag = false;
	 *		|if (getCubePosition()[1] == getYupbound()-1)
	 *		|	flag = false;
	 *		|if (getCubePosition()[2] == getZupbound()-1)
	 *		|	flag = false;
	 *		|if (flag){	
	 *		|	new.getActivityStatus == "falling"
	 *		|		flag = false
	 */
	private void checkIfFalling(){
		boolean flag = true;
		Set<int[]> neigs= neighbors(getCubePosition());
		for (int[] neig :neigs){
			if (!getWorld().passable(neig)){
				flag = false;
			}
		}
		for (int i = 0; i <3; i++){
			if (getCubePosition()[i] ==0){
				flag = false;
			}
		}
		if (getCubePosition()[0] == getXupbound()-1){
			flag = false;
		}
		if (getCubePosition()[1] == getYupbound()-1){
			flag = false;
		}
		if (getCubePosition()[2] == getZupbound()-1){
			flag = false;
		}
		if (flag){
			setActivityStatus(ActivityStatus.FALLING);
		}
	}
	


	/**
	 * 
	 * @return true if the unit is falling, false otherwise
	 * 			|this.activityStatus == "falling"
	 * 			
	 */
	private boolean isFalling(){
		return this.activityStatus == ActivityStatus.FALLING;
		
	}
	
	
	/**
	 * 
	 * @param world the world which this unit is set in
	 * @post the world of this unit is set to world.
	 */
	@Model 
	private void setWorld(World world){
		this.world= world;
	}
	/**
	 * 
	 * @return the world of this unit.
	 */
	public World getWorld(){
		return this.world;
	}
	
	World world;
	
	/**
	 * 
	 * @return the max x position the unit can have.
	 */
	@Model
	private int getXupbound(){
		return this.world.getNBXCubes();
	}
	/**
	 * 
	 * @return the max y position the unit can have.
	 */
	@Model
	private int getYupbound(){
		return this.world.getNBYCubes();
	}
	/**
	 * 
	 * @return the max z position the unit can have.
	 */
	@Model
	private int getZupbound(){
		return this.world.getNBZCubes();
	}

	private  int XLOWBOUND = 0;
	private  int YLOWBOUND = 0;
	private  int ZLOWBOUND = 0;
	
	
	
	
	/**
	 * 
	 * @param unit the unit to follow.
	 * @post isFollowing is set to true
	 * 		| new.isFollowing == true
	 * @post unit being followed is set to unit
	 * 		| new.getUnitBeingFollowed() == unit
	 */
	public void follow(Unit unit) {
		setIsFollowing(true);
		setUnitBiengFollowed(unit);
		
	}



	/**
	 * 
	 * @return the unit being followed by this unit.
	 */
	private Unit getUnitBeingFollowed() {
		return this.unitBeingFolowed;
	}

	/**
	 * 
	 * @param unit the unit to be followed
	 * @post the unitBiengFollowed is set to unit
	 * 		|new.getUnitBeingFollowed() = unit
	 */
	private void setUnitBiengFollowed(Unit unit) {
		this.unitBeingFolowed = unit;
		
	}
	private Unit unitBeingFolowed;
	private boolean isFollowing = false;

	/**
	 * 
	 * @param b boolean representing whether or not the unit is following another one
	 */
	private void setIsFollowing(boolean b) {
		this.isFollowing = b;
		
	}
	/**
	 * 
	 * @return wether or not the unit is following another one
	 */
	public boolean isFollowing() {
		return this.isFollowing;
	}
	/**
	 * @post if the unit is on a cube neighboring the cube of the unit being followed or on the same cube
	 * 		 or the unit being followed is terminated the unit will stop following
	 * 		| if ((neighbors(getCubePosition()).contains(getUnitBeingFollowed().getCubePosition()))
	 * 		|		||(Arrays.equals(getCubePosition(), getUnitBeingFollowed().getCubePosition()))
	 * 		|		|| (getUnitBeingFollowed().isTerminated()))
	 * 		| then 	new.getActivity = ActivityStatus.NONE
	 * 		| 		new.isFollowing = false
	 * @post otherwise  the unit wil move to the position the unit being followed is curently at.
	 * 		|else 	position = getUnitBeingFollowed().getCubePosition();
	 * 		|		moveTo(position);
	 */
	private void executeFollowing(){
		if ((neighbors(getCubePosition()).contains(getUnitBeingFollowed().getCubePosition()))
				||(Arrays.equals(getCubePosition(), getUnitBeingFollowed().getCubePosition()))
				|| (getUnitBeingFollowed().isTerminated())){
			setActivityStatus(ActivityStatus.NONE);
			setIsFollowing(false);
			
		}
		else{
			int[] position = getUnitBeingFollowed().getCubePosition();
			moveTo(position);
		}
	}
	
	public Task task;
	


	/**
	 * @return the task assigned to the unit
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * @param task the task to be assigned to the unit.
	 * 		| new.getTask() = task
	 */
	void setTask(Task task) throws IllegalArgumentException {
		this.task = task;
	}
	
	
	
	
	
		
	
}




