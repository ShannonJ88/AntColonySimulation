/*******************************************************************************************************************************
 Scouts are responsible for enlarging the foraging area available to the foragers. The specific requirements for the scout ant are:
Scouts should always randomly pick one of the eight possible directions of movement when it is their turn to do something.
a. If the chosen square is open, the scout should simply move into that square.
If the chosen square is closed, the scout should move into that square and the contents of that square should be
revealed.
b.
1.
Whenever a closed square is revealed, there is a chance of there being food in the square, according to the following
frequency:
a. There is a 25% chance that the square will contain a random amount of food between 500 and 1000 units.
b. The other 75% of the time the square is empty.
You can predetermine the contents of all the squares at the beginning of the simulation, or you can dynamically
determine the contents of each square as it is opened
*************************************************************************************************************************/
import java.util.ArrayList;
import java.util.Random;

public class ScoutAnt extends Ant{
	ScoutAnt(ColonyNode node){
		location = node;
		canMove = -1;
	}
	
/**---------Constructor----------**/
	public ScoutAnt(){
		
	}//end ScoutAnt()
	public void turn(int t_urn){
		if(canMove == t_urn)
			return;
		if ((t_urn - hatched) > 10 * 365){
			die();
			return;
		}
		canMove = t_urn;
		//Randomly pick direction of movement
		Random randgen = new Random();
		ArrayList<ColonyNode> borderList = location.getBorderNodes();
		ColonyNode destination;
		destination = borderList.get(randgen.nextInt(borderList.size()));
		move(destination);
	}

	public void move(ColonyNode node) {
		location.addAnt(this);
		location = node;
		location.removeAnt(this);
		if (!(location.isVisible())){
			location.setVisible(true);
		}
	}
}//end ScoutAnt
