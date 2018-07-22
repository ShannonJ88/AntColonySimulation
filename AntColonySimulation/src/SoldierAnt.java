import java.util.ArrayList;
import java.util.Random;

/************************************************************Soldier Ants**************************************************
Soldiers are responsible for protecting the colony by fighting the enemy Bala ants. Soldier ants have two primary modes of
behavior: scout mode and attack mode. The specific requirements for the soldier ant are:
Scout Mode
1. A soldier is in scout mode when it is in a square that does not contain any Bala ants.
While in scout mode:
If there are one or more Bala ants in one or more of the squares adjacent to the square the soldier is in, the
soldier should move into any one of the squares containing a Bala ant.
1.
2. If there are no Bala ants in any of the adjacent squares, the soldier should move randomly.
2.
1.
Attack Mode
A soldier is in attack mode when it is in a square that contains one or more Bala ants. Attack mode takes precedence
over scout mode.
1.
2. While in attack mode, a soldier should attack any Bala ants present.
3. If there are multiple Bala ants present, only one of them should be attacked.
During an attack, there is a 50% chance the soldier kills the enemy ant; otherwise, the soldier misses and the enemy
ant survives.
************************************************************************************************************************/

public class SoldierAnt extends Ant {
	SoldierAnt(ColonyNode node){					//Protect Colony
		location = node;
		canMove = -1;
	}
	//Scout and attack mode

	/**-----------CONSTRUCTOR-------------**/
	public SoldierAnt(){
		
	}//end SoldierAnt()
	public void turn(int t_urn){
		if (canMove == t_urn)
			return;
		if ((t_urn - hatched) > 10 * 365){
			die();
			return;
		}
		canMove = t_urn;
		if (location.getAntType(new BalaAnt()).size() > 0){
			attack();
		}
		else{
			Random numGen = new Random();
			ArrayList<ColonyNode> borderList = location.getBorderNodes();
			ArrayList<ColonyNode> showList = new ArrayList<ColonyNode>();
			ColonyNode destination;
			for (int i = 0; i < borderList.size(); i++){
				if (borderList.get(i).isVisible()){
					showList.add(borderList.get(i));
				}
			}
			destination = showList.get(numGen.nextInt(showList.size()));
			for (ColonyNode cn : showList){
				if (cn.getAntType(new BalaAnt()).size() > 0){
					destination = cn;
				}
			}
			loc(destination);
		}
		
	}
	public void loc(ColonyNode node){
		location.addAnt(this);
		location = node;
		location.removeAnt(this);
	}
	/*During an attack, there is a 50% chance the soldier kills the enemy ant; otherwise, the soldier misses and the enemy
	ant survives.*/
	public void attack(){					
		ArrayList<Ant> balaList = location.getAntType(new BalaAnt());
		Random kill = new Random();
		int half = kill.nextInt(2);		//50%
		if (half == 0){
			balaList.get(0).die();
		}
	}
}//end SoldierAnt class
