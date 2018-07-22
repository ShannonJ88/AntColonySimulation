import java.util.Random;

/**********************************************************************************************************
 * The queen ant is responsible for hatching new ants. The specific requirements for the queen ant are:
1. The queen never moves from her square (i.e., she remains in the same square for the entire simulation).
2. The queen's maximum lifespan is 20 years.
3. The queen hatches new ants at a constant rate of 1 ant/day (i.e., 1 ant every 10 turns).
4. New ants should always be hatched on the first turn of each day.
The type of ant that is hatched should be determined randomly according to the initial frequencies listed below. You may
change these frequencies as you see fit — these are simply suggestions for a starting point.
5.
a. Forager - 50%
b. Scout - 25%
c. Soldier - 25%
6. The queen should consume 1 unit of the food in her chamber on each turn, including the turn in which she hatches a new ant.
7. If the food level in the queen's square is zero when the queen tries to eat, the queen dies of starvation.
8. If the queen dies, either by starvation or by a Bala attack, the simulation should end immediately.
************************************************************************************************************/

public class QueenAnt extends Ant{
	
	
    int ID;;									    //   Queen ID value = 0
	int canMove;									//1. Queen never moves from square
	int hatched_antsID;								//3. Queen's hatched ants(1 per day)
	

	public QueenAnt(ColonyNode node){
		ID = 0;
		canMove = 0;
		location = node;
	}//end QueenAnt()
	
	/******Constructor*********/
	QueenAnt(){
		
	}//end QueenAnt() constructor
/* Queen Ant hatches new ants @ 1 ant/day
 * Ant type hatched determined randomly
 */
	public void hatchAnt(Ant ant){
		//Forager Ant 50 %
		//Scout Ant 25 %
		//Soldier Ant 25 %
		Random randomGenerator = new Random();
		int randAnt = randomGenerator.nextInt(4);
		Ant newAnt;
		
		if (randAnt < 2){
			newAnt = new ForagerAnt(location);
		}
		else if (randAnt == 2){
			newAnt = new SoldierAnt(location);
		}
		else{
			newAnt = new ScoutAnt(location);
		}
		if (ant != null) newAnt = ant;
		newAnt.setHatched(canMove);
		hatched_antsID ++;
		newAnt.setID(hatched_antsID);
		location.addAnt(newAnt);
	}
	
	public void turn(int t_urn){
		this.canMove = t_urn;
		if ((t_urn > (20 * 10 * 365)) || (location.getFood() < 1)){			//Queen dies at 20 years
			die();
			return;
		}
	
	if ((t_urn % 10) == 0){
		this.hatchAnt(null);
	}
	createBala(); //Bala Ant 
	this.eat();
	}
	public void eat(){										//Queen Consumes 1 Unit of food per turn!
		int foodCount = location.getFood();
		if (foodCount <= 0){
			this.die();
		}
		foodCount = foodCount - 1;
		location.setFood(foodCount);
	}
	public void die(){
		System.out.println("The queen has Died! Game Over!");
		
	}
	public void createBala(){
		Random balaGenerator = new Random();
		int randomBalaGenerator = balaGenerator.nextInt(100);
		ColonyNode balaLocation = location.colony.colonyGrid[0][0];
		if (randomBalaGenerator < 30){
			BalaAnt newBala = new BalaAnt(balaLocation);
			balaLocation.addAnt(newBala);
			newBala.setHatched(hatched);
			hatched_antsID ++;
			newBala.setID(hatched_antsID);
		}
		
	}
}//end QueenAnt class
