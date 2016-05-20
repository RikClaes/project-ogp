package hillbillies.model;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


import hillbillies.model.Unit;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;

public class World {
	/**
	 * 
	 * @param terrainTypes
	 * @post initializes World with the given terraintypes
	 */
	public World(int[][][] terrainTypes,TerrainChangeListener modelListener){
		setTerrainTypes(terrainTypes);
		setModelListener(modelListener);
		this.ConnectedToForWorld = new ConnectedToBorder(getNBXCubes(),getNBYCubes(),getNBZCubes());
		for (int i = 0; i < getNBXCubes();i++){
			for (int j = 0; j < getNBYCubes();j++){
				for (int k = 0; k < getNBZCubes();k++){
					if (getTerrainTypes()[i][j][k] == 0||getTerrainTypes()[i][j][k] == 3){
						this.ConnectedToForWorld.changeSolidToPassable(i, j, k);
					}
				}
			}
		}
	}
	/**
	 * sets the terraintype to the givenTerrain
	 */
	private  void setTerrainTypes(int[][][]givenTerrain){
		this.terrainTypes = givenTerrain;
	}
	/**
	 * 
	 * @return the terrainTypes of the world
	 */
	 public int[][][] getTerrainTypes(){
		return this.terrainTypes;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return gets the terraintype of the specified position
	 */
	public  int getCubeType(int x, int y, int z){
		return getTerrainTypes()[x][y][z];
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param value
	 * @throws IllegalArgumentException
	 * @post changes the terraintype of the speciefied cube to value
	 */
	public void setCubeType(int x ,int y, int z, int value) throws IllegalArgumentException{
		if (value > 4 ||value <0){
			throw new IllegalArgumentException();
		}
		int [][][] terrainTypes = getTerrainTypes();
		terrainTypes[x][y][z] = value;
		setTerrainTypes(terrainTypes);
		
	}
	
	private int[][][] terrainTypes  ;
	/**
	 * 
	 * @param position
	 * 
	 * @return the terrain type occupying that cube
	 */
	
	public int getTerrainOnPosition(int[] position){
		int terrain = getTerrainTypes()[position[0]][position[1]][position[2]];	
		return terrain;
	}
	
	
	/**
	 * @return a list of al the factions in the game
	 */
	public Set<Faction> listFactions(){
		return this.factionsInWorld;
	}
	
	/**
	 * 
	 * @param faction 
	 * @post adds faction to factionsInWorld
	 */
	 public void addFactions(Faction faction) throws IllegalThreadStateException{
		if (listFactions().size() >= 5){
			throw new IllegalThreadStateException();
		}
		this.factionsInWorld.add(faction);
	}

	private final Set<Faction> factionsInWorld = new HashSet<Faction>();
	/**
	 * @return 	a list with the names of al units in the game world
	 */
	public Set<Unit> listUnits(){
		return this.UnitsInWorld;
	}
	
	/**
	 * 
	 * @param 	faction
	 * 			the faction of witch we wish to see the unit
	 * @return	a list with the name of al the unit in the given factions
	 */
	Set<Unit> listUnitInFact(Faction faction){
		Set<Unit> ret = new HashSet<Unit>();
		for(Unit unit : listUnits() ){
			if (unit.getFaction() == faction){
				ret.add(unit);
			}
		}
		return ret;
	}
	
	/**
	 * 
	 * @param unit
	 * @ adds a unit to unitsinworld
	 * 
	 * 	 
	 */
	public void addUnits(Unit unit){
		assert(listUnitInFact(unit.getFaction()).size() < 50);
		this.UnitsInWorld.add(unit);
	}
	/**
	 * 
	 * @param unit
	 * removes unit from unitsinworld
	 */
	
	void removeUnitFromWorld(Unit unit){
		 this.UnitsInWorld.remove(unit);
		
	}
	
	private final Set<Unit> UnitsInWorld = new HashSet<Unit>();
	
	/**
	 * @effect advances time for everything in the world
	 */
	
	public void advanceTime(double dt) throws IllegalArgumentException{
		this.Colapsetime += dt;
		if (this.Colapsetime >= 4.9){
			for (int i = 0 ; i < getNBXCubes(); i++){
				for (int j = 0 ; j < getNBYCubes(); j++){
					for (int k = 0 ; k < getNBZCubes(); k++){
						boolean col = !isSolidConnected( i, j, k);
						if (col){
							int type = getCubeType(i,j,k);
							int drop =randomInt(4,0);
							if (drop == 1){
								double[] pos = {i+0.5,j+0.5,k+0,5};
								if (type == 1){
									Boulder b = new Boulder(pos,this);
									addStufToWorld(b);
								}
								else if (type == 2){
									Log l = new Log(pos,this);
									addStufToWorld(l);
								}
							}
							collapse(i,j,k);
						}
					}
				}
			}	
		}
		for (Unit unit :listUnits()){
			unit.advanceTime(dt);
		}
		for(RawMaterial mat :getStufInWorld()){
			mat.advanceTime(dt);
		}
		
	}
	private double Colapsetime = 0;
	/**
	 * 
	 * @param i 
	 * @param j
	 * @param k
	 * @return returns if the cube at (i,j,k) is solid connected to the border
	 */
	public boolean isSolidConnected(int i, int j,int k){
		return this.ConnectedToForWorld.isSolidConnectedToBorder(i,j,k);
	}
	
	private ConnectedToBorder ConnectedToForWorld;

	private TerrainChangeListener modeListener;
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @param k
	 *  changes a unit on the given position to air
	 */
	 void collapse(int i, int j, int k) {
		int [][][] terrains = getTerrainTypes();
		terrains[i][j][k] = 0;
		setTerrainTypes(terrains);
		ConnectedToForWorld.changeSolidToPassable(i, j, k);
		getModelListener().notifyTerrainChanged(i,j,k);
	}
	 
	 private TerrainChangeListener getModelListener() {
		return this.modeListener;
	}
	 private void setModelListener(TerrainChangeListener modelListener){
		 this.modeListener = modelListener;
	 }
	 
	
	
	/**
	 * @effect spawns a unit with random atributes at a random position that is passable and the cube below is solid
	 */
	public Unit spawnRandomUnit(boolean enableDefaultBehavior){
		assert(listUnits().size()<50);
		ArrayList<int[]> validSpawns = new ArrayList<int[]>(getValidUnitPositions());
		int selector = randomInt(validSpawns.size()-1,0);
		int[] cubePosition =  validSpawns.get(selector);
		double[] position = {cubePosition[0]+0.5,cubePosition[1]+0.5,cubePosition[2]+0.5};
		String name = "A" + randomString(3);
		int weight = randomInt(100,25);
		int strength = randomInt(100,25);
		int agility = randomInt(100,25);
		int toughness = randomInt(100,25);
		Faction faction = new Faction();
		Integer lowestNBunitInFact = 11;
		if(!(listFactions().size() < 5)){
			for (Faction fact :listFactions()){
				if (fact.getUnitsInFaction().size()<lowestNBunitInFact){
					faction =fact;
					lowestNBunitInFact = fact.getUnitsInFaction().size();
				}
			}
		}
		Unit unit = new Unit(position, name, weight, strength, agility, toughness,this,faction);
		if (enableDefaultBehavior){
			unit.startDefaultBehaviour();
		}
		addUnits(unit);
		return unit;
	}
	
	
	

	/**
	 * 
	 * @return a list of valid positions where a unit can spawn
	 */
	private Set<int[]> getValidUnitPositions(){
		List<int[]> l =  new ArrayList<int[]>();
		for (int i = 0 ; i < getNBXCubes()-1; i++){
			for (int j = 0 ; j < getNBYCubes()-1; j++){
				for (int k = 0 ; k < getNBZCubes()-1; k++){
					int[] position = {i,j,k};
					int[] below = {i,j,k-1};
					if (passable(position)&&!passable(below)){
						l.add(position);
					}
				}
			}
		}
		Set<int[]> ret = new HashSet<int[]>(l);
		return ret;
	}

	/**
	 * 
	 * @param cubeposition
	 * @return true if the cube is air or workshop false if rock or wood or if the position is invalid
	 */
	 public boolean passable(int[] position) {
		int x = position[0];
		int y = position[1];
		int z = position[2];
		if ((x >= 0)&&(x < getNBXCubes())
				&& (y >= 0)&&(y < getNBYCubes())&& (z >= 0)&&(z < getNBZCubes())){
			if (getCubeType(x,y,z)==0||getCubeType(x,y,z)==3){
				return true;
			}
			else {
				return false;
			}
		}
		else{
			return false;
		}
	}

	/**
	 * 
	 * @param max
	 * @param min
	 * @return a random integer between max and min
	 */
	int randomInt(int max,int min) {
        Random rand = new Random();
        int range = max - min + 1;
        int interger = rand.nextInt(range) + min;
        return interger;
    }
	
	private  final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private SecureRandom rnd = new SecureRandom();
	/**
	 * 
	 * @param len
	 * @return a random string with lengt len
	 */
	private  String randomString( int len ){
		   StringBuilder sb = new StringBuilder( len );
		   for( int i = 0; i < len; i++ ) 
		      sb.append(AB.charAt( rnd.nextInt(AB.length()) ) );
		   return sb.toString();
		}
	
	private final Set<RawMaterial> stufInWorld = new HashSet<RawMaterial>();
	
	/**
	 * 
	 * @return  stufInWorld
	 */
	private Set<RawMaterial> getStufInWorld(){
		return this.stufInWorld;
	}
	
	public Set<Log> getLogsInWorld(){
		Set<Log> ret=  new HashSet<Log>();
		for (RawMaterial mat :getStufInWorld()){
			if (mat instanceof Log){
				ret.add((Log) mat);
			}
		}
		return ret;
	}
	/**
	 * 
	 * @param Cubeposition
	 * @return the logs in the cube of cubeposition
	 */
	 Set<Log> getLogsInCube(int[] Cubeposition){
		Set<RawMaterial> allstuf =  getStufInWorld();
		Set<Log> ret =  new HashSet<Log>();
		for (RawMaterial mat : allstuf){
			if ((mat.getCubePosition()[0] == Cubeposition[0])
					&&(mat.getCubePosition()[1] == Cubeposition[1])
					&&(mat.getCubePosition()[2] == Cubeposition[2])&&(mat instanceof Log)){
				ret.add((Log) mat);
			}
		}
		return ret;
	}
	
	/**
	 * 
	 * @param log
	 * @post adds log t logsInWorld
	 */
	public void addStufToWorld(RawMaterial mat){
		this.stufInWorld.add(mat);
	}
	/**
	 * 
	 * @param log
	 * @post removes log from logsInWorld
	 */
	
	void removeLogFromWorld(Log log){
		 this.stufInWorld.remove(log);
		
	}
	
	
	
	/**
	 * 
	 * @return bouldersInWorld
	 */
	public Set<Boulder> getBouldersInWorld(){
		Set<Boulder> ret =  new HashSet<Boulder>();
		for (RawMaterial mat :getStufInWorld()){
			if (mat instanceof Boulder){
				ret.add((Boulder) mat);
			}
		}
		return ret;
	}
	/**
	 * 
	 * @param Cubeposition
	 * @return returns al the boulders on cubeposition
	 */
	 Set<Boulder> getBoulderInCube(int[] Cubeposition){
		Set<Boulder> allBoulder =  getBouldersInWorld();
		Set<Boulder> ret=  new HashSet<Boulder>();
		for (Boulder boulder : allBoulder){
			if ((boulder.getCubePosition()[0] == Cubeposition[0])
					&&(boulder.getCubePosition()[1] == Cubeposition[1])
					&&(boulder.getCubePosition()[2] == Cubeposition[2])){
				ret.add(boulder);
			}
		}
		return ret;
	}
	 /**
	  * 
	  * @param x
	  * @param y
	  * @param z
	  * @return returns if the cube at (x,y,z) has a solid neighbor.
	  */
	 public boolean hasSolidNeigh(int x, int y, int z){
		 	int[] pos = {x, y, z};
			if(!isValidCube(pos)){
				throw new IllegalArgumentException();
			}
			boolean solidNeighbour = false;
			if ((x == 0) || (x == getNBXCubes()-1) || (y == 0) || (y == getNBYCubes()-1)
					|| (z == 0) || (z == getNBZCubes()-1)){
				solidNeighbour = true;
			}
			else{
				for (int i = -1; i < 2; i++){
					for (int j = -1; j < 2; j++){
						for (int k = -1; k < 2; k++){
							if ((i != 0) || (j != 0) || (k != 0)){
								int[] neigh = {x + i, y + j, z + k};
								if (!passable(neigh)){
									solidNeighbour = true;
								}
							}
						}
					}
				}
			}
			return solidNeighbour;
	}
	 
	 public boolean isValidCube(int[] pos){
		 return ((pos[0]>=0)&&(pos[0]<getNBXCubes()&&(pos[1]>=0)&&(pos[1]<getNBYCubes()&&(pos[2]>=0)&&(pos[2]<getNBZCubes()))));
	 }
	 
	
	
	/**
	 * 
	 * @param boulder
	 * @post boulder is removed from bouldersInWorld
	 */
	void removeBoulderFromWorld(Boulder boulder){
		 this.stufInWorld.remove(boulder);
	}
	/**
	 * 
	 * @return the number of x cubes the world has
	 */
	public int getNBXCubes(){
		return getTerrainTypes().length;
	}
	/**
	 * 
	 * @return the number of y cubes the world has
	 */
	public int getNBYCubes(){
		return getTerrainTypes()[0].length;
	}
	/**
	 * 
	 * @return the number of z cubes the world has
	 */
	public int getNBZCubes(){
		return getTerrainTypes()[0][0].length;
	}
}
