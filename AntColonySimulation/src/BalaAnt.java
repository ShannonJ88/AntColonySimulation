/**********************************BALA ANTS*****************************************************
Bala ants are enemies of the colony. They should enter only at the periphery of the colony 
(i.e., they should not simply pop up in
the middle of the colony). Once in the colony they may move around freely. Assume they never leave 
the colony once they enter it.
The specific requirements for the Bala ant are:
Each turn there is a 3% chance one Bala ant will appear in one of the squares at the boundary of the colony. You may choose
to have Bala ants always enter at the same square (e.g., upper left corner), or you may have them enter randomly at any of
the 106 squares on the edge of the colony.
1.
2. Once a Bala appears, it should remain in the environment until it is killed, or dies of old age.
3. Bala ants should always move randomly.
4. Bala ants may move into squares that have not yet been revealed by scout ants.

5.If a Bala ant is in a square containing one or more friendly ants (scout, forager, soldier, queen), 
the Bala should attack one of
those ants. The ant that is attacked can be selected at random
During an attack, there is a 50% chance a Bala kills the ant it attacks; otherwise, the Bala misses and the ant 
that is attacked
survives.
***********************************************************************************************************/
import java.util.ArrayList;
import java.util.Random;

public class BalaAnt extends Ant {
	BalaAnt(ColonyNode node){
		location = node;
		canMove = -1;
	}
/**-----------CONSTRUCTOR----------------**/
	public BalaAnt(){
		
	}//end BalaAnt()
	public void turn(int t_urn){						//CHECK if ants have move
		if (canMove == t_urn)
			return;
		if ((t_urn - hatched) > 10 * 365){
			die();
			return;
		}
	
	canMove = t_urn;
	ArrayList<Ant> notBala = location.getNotBalaAnts();
	if (notBala.size() > 0){
		attack();
	}
	else{
		Random randomGenerator = new Random();
		ArrayList<ColonyNode> borderList = location.getBorderNodes();
		ColonyNode destination;
		destination = borderList.get(randomGenerator.nextInt(borderList.size()));
		loc(destination);
	}
	
	}

	public void loc(ColonyNode node){
		location.removeAnt(this);
		location = node;
		location.addAnt(this);
		
	}
	public void attack(){								//Bala ant in square with other ant ATTACK 
		ArrayList<Ant> notBala = location.getNotBalaAnts();
		Random kill = new Random();						//Ant attacked selected at random
		int  half = kill.nextInt(2);					//50% chance bala ant kills attacked ant
		if (half == 0){
			notBala.get(0).die();
		
		}
	}
}//End BalaAnt

