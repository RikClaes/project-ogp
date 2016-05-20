
package hillbillies.model.expressions.positions;

import java.util.Arrays;

import hillbillies.model.Unit;
import hillbillies.part3.programs.SourceLocation;
/**
 * 
 * @author rik
 *
 */
public class WorkshopPosition extends Position{
	
	public WorkshopPosition(SourceLocation location){
		super(location);
		
	}
	


	@Override
	public int[] run(Unit unit) {
		int[] ret = null;
		int[][][] terrain = unit.getWorld().getTerrainTypes();
		int[] control = {-1,-1,-1};
		int[] position = {-1,-1,-1};
		for(int i = 0;i < unit.getWorld().getNBXCubes();i++){
			for(int j = 0;j < unit.getWorld().getNBYCubes(); j++){
				for  (int k = 0;k < unit.getWorld().getNBZCubes();k++){
					if (terrain[i][j][k] == 3){
						position[0] = i;
						position[1] = j;
						position[2] = k;
						break;
					}
					
				}
				if (!Arrays.equals(position, control)){
					break;
				}
			}
			if (!Arrays.equals(position, control)){
				break;
			}
		}
		if (!Arrays.equals(position, control)){
			ret = position;
		}
		return ret;
	}

}
