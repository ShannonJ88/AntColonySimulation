import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ForagerAnt extends Ant {
	boolean returnToNestMode;									//Return to NestMode
	ArrayList<ColonyNode> movementHist;							//Keep track of movement history
	int backTrack;												//backtrack whatever path it took to get to the food.
	
	public ForagerAnt(ColonyNode node){
		location = node;
		canMove = -1;
		returnToNestMode = false;
		movementHist = new ArrayList<ColonyNode>();
		movementHist.add(location);
		
	}
	/******Constructor*********/
	ForagerAnt(){
		
	}
	//n Forage Mode, foragers should always move to the adjacent square containing the highest level of pheromone,//
	public ColonyNode findHighPheromone(){
		ArrayList<ColonyNode> borderList = location.getBorderNodes();
		ArrayList<ColonyNode> randomGen = new ArrayList<ColonyNode>();
		Random randNode = new Random();
		for (Iterator<ColonyNode> iterator= borderList.iterator(); iterator.hasNext();){
			ColonyNode cn = iterator.next();
			if (movementHist.contains(cn) || !cn.isVisible()){
				iterator.remove();
			}
		}
		if (borderList.size() == 0){
			borderList = location.getBorderNodes();
		}
		ColonyNode maxPheromone = borderList.get(0);
		for(int i = 0; i < borderList.size(); i++){
			if(maxPheromone.isVisible() && maxPheromone.getPheromone() < borderList.get(i).getPheromone()){
				maxPheromone = borderList.get(i);
			}
		}
		for (int j = 0; j < borderList.size(); j++){
			if ((borderList.get(j).getPheromone() == maxPheromone.getPheromone()) && borderList.get(j).isVisible()){
				randomGen.add(borderList.get(j));
			}
		}
		maxPheromone = randomGen.get(randNode.nextInt(randomGen.size()));
		return maxPheromone;
	}
	public void loc(ColonyNode node){
		location.addAnt(this);
		location = node;
		location.removeAnt(this);
		
		if (location.queenHere() && returnToNestMode){
			location.setFood(location.getFood() + 1);
			returnToNestMode = false;
			movementHist.clear();
		}
		movementHist.add(location);
	}
	public boolean foodHere(){
		if (location.getFood() > 0 && !(location.queenHere())){
			location.setFood(location.getFood() - 1);
			returnToNestMode = true;
			backTrack = movementHist.size() -1;
			return true;
		}
		else return false;
	}
	public void turn(int t_urn){
		ColonyNode destination;
		if (canMove == t_urn)
			return;
		canMove = t_urn;
		if (returnToNestMode){
			backTrack --;
			destination = movementHist.get(backTrack);
			depositPheromones();
		}
		else{
			destination = findHighPheromone();
		}
		loc(destination);
		if (!(returnToNestMode)){
			foodHere();
		}
		if ((t_urn - hatched) > 10 * 365){
			die();
		}
	}
	
	public void depositPheromones(){
		if (!(location.queenHere())){
			if (location.getPheromone() < 1000){
				location.setPheromone(location.getPheromone() + 10);
			}
		}
	}
	public void die(){
		if (returnToNestMode == true){
			location.setFood(location.getFood() + 1);
		}
		location.removeAnt(this);
	}
}//end ForagerAnt
