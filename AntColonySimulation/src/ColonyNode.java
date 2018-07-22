/*****
 * Each sauare contains one or more of the following
 * a. Zero or mor enemy ants;
 * b. Zero or more friendly ants
 * c. Zero or more units of food
 * d. Zero or more units of pheromone
 */
import java.util.ArrayList;
import java.util.Random;

public class ColonyNode {

	
	int food;
	int pheromone;
	int x,y;
	boolean isVisible;
	int antCount;
	ArrayList<Ant> antList;
	ArrayList<Ant> removeList;
	ArrayList<Ant> addList;
	boolean isQueen;
	ColonyNodeView nodeView;
	boolean isEntry;
	boolean it; //iterate
	Colony colony;
	
	ColonyNode(ColonyNodeView nodeView, int x, int y){
		Random foodGenerator = new Random();
		if (foodGenerator.nextInt(4) == 0){
			food = foodGenerator.nextInt(500) + 500;
		}
		else
			food = 0;
			pheromone = 0;
			isQueen = false;
			isVisible = false;
			this.nodeView = nodeView;
			this.x = x;
			this.y = y;
			antList = new ArrayList<Ant>();
			addList = new ArrayList<Ant>();
			removeList = new ArrayList<Ant>();
			isEntry = false;	
		}
	
		public void addAnt(Ant newAnt){
			if (it){
				addList.add(newAnt);
			}
			else{
				antList.add(newAnt);
			}
			updateView();
		}
		public void removeAnt(Ant newAnt){
			if (it){
				removeList.add(newAnt);
			}
			else{
				removeList.add(newAnt);
			}
			updateView();
		}
		public void setVisible(boolean sv){
			isVisible = sv;
			if (sv == true){
				nodeView.showNode();
			}
			if (sv == false){
				nodeView.hideNode();
			}
		}
		public int countNumAnts(Ant antType){
			int antCount = 0;
			for (int i = 0; i < antList.size(); i++){
				if (antList.get(i).getClass() == antType.getClass()){
					antCount++;
				}
			}
			return antCount;
		}
		public void updateNode(){
			this.nodeView.showNode();
		}
		public void updateView(){
			int queenNum = countNumAnts(new QueenAnt());
			if (queenNum == 1){
				isQueen = true;
				nodeView.setQueen(isQueen);
				nodeView.showQueenIcon();
			}
			nodeView.setScoutCount(countNumAnts(new ScoutAnt()));
			if (countNumAnts(new ScoutAnt()) > 0)
				nodeView.showScoutIcon();
			else nodeView.hideScoutIcon();
			
			nodeView.setForagerCount(countNumAnts(new ForagerAnt()));
			if (countNumAnts(new ForagerAnt()) > 0)
				nodeView.showForagerIcon();
			else nodeView.hideForagerIcon();
			
			nodeView.setSoldierCount(countNumAnts(new SoldierAnt()));
			if (countNumAnts(new SoldierAnt()) > 0)
				nodeView.showSoldierIcon();
			else nodeView.hideSoldierIcon();
			
			nodeView.setBalaCount(countNumAnts(new BalaAnt()));
			if (countNumAnts(new BalaAnt()) > 0)
				nodeView.showBalaIcon();
			else nodeView.hideBalaIcon();
			
			nodeView.setFoodAmount(food);
			nodeView.setPheromoneLevel(pheromone);
		}
		public void updateList(){
			for (Ant i : addList){
				antList.add(i);
				
			}
			addList.clear();
			
			for (Ant i : removeList){
				antList.remove(i);
				
			}
			removeList.clear();
		}
		public void turn(int t_urn){
			if ((t_urn != 0) && (t_urn % 10 == 10)){
				this.setPheromone(getPheromone() / 2);
			}
			it = true;
			for (Ant nextAnt : antList){
				nextAnt.turn(t_urn);
			}
			it = false;
			updateList();
			updateView();
		}

	public ArrayList<ColonyNode> getBorderNodes() {
		// TODO Auto-generated method stub
		return colony.getBorderNodes(this);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public void setFood(int foodCount){
	food = foodCount;
	}
	public int getFood(){
		return food;
	}
	public void setPheromone(int pheromoneCount){
		pheromone = pheromoneCount;
	}
	public int getPheromone(){
		return pheromone;
	}
	public void setColony(Colony colony){
		this.colony = colony;
	}
	public boolean queenHere(){
		return isQueen;
	}
	public boolean isVisible(){
		return isVisible;
	}
	public ArrayList<Ant>getAntType(Ant _type){
		ArrayList<Ant> notBala = new ArrayList<Ant>();
		for(int i = 0; i < antList.size();i++){
			if (antList.get(i).getClass() != new BalaAnt().getClass()){
				notBala.add(antList.get(i));
			}
		}
		return notBala;
	}

	public ArrayList<Ant> getNotBalaAnts() {
		// TODO Auto-generated method stub
		return null;
	}
		
	}


