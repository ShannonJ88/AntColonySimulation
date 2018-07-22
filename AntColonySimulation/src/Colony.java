/*************************COLONY*************************************************
 * The Environment represented using a 27 x 27 square grid
 * Each square in the grid represents a discrete location in the environment
 * Eight directions of movement are possible
 ********************************************************************************/
import java.util.ArrayList;

public class Colony {
	ColonyNode[][] colonyGrid;
	ColonyView colonyView;
	ColonyNodeView colnoview;
	ColonyNode colnode;
	Environment enviro;
	public Object sim;
	
	Colony(ColonyView view, Environment enviro){
		this.enviro = enviro;
		colonyGrid = new ColonyNode[27][27];
		this.colonyView = view;
	}
	public void addColonyNode(ColonyNode nodeNumber, int x, int y){				//location x,y
		colonyGrid[x][y] = nodeNumber;
	}
	public void turn(int t_urn){
		for (int i = 0; i < 27; i++){
			for (int j = 0; j < 27; j++){
				colonyGrid[i][j].turn(t_urn);
			}
		}
	}
	public void myColony(){
		for (int i = 0; i < 27; i++){
			for (int j = 0; j < 27; j++){
				colnoview = new ColonyNodeView();
				colnode = new ColonyNode(colnoview, i, j);
				addColonyNode(colnode, i, j);
				colnoview.setID(i + "," + j);
		/***
		 * Center square
		 * 1. Queen Ant
		 * 2. 10 soldier ants
		 * 3. 50 Forager Ants
		 * 4. 4 Scout Ants
		 * 5. 1000 units of food
		 */
				if(i==13 & j ==13){		//final int QUEEN_LOCATION = 13 CENTER SQUARE
					QueenAnt q_ueen = new QueenAnt(colnode);
					colnode.setFood(1000);
					colnode.addAnt(q_ueen);           
					
					for (int k = 0; k < 10; k++){			
						q_ueen.hatchAnt(new SoldierAnt(colnode));
					}
					for (int l = 0; l < 50; l++){
						q_ueen.hatchAnt(new ForagerAnt(colnode));
					}
					for (int m = 0; m < 4; m++){
						q_ueen.hatchAnt(new ScoutAnt(colnode));
					}
				}
if ((i == 12 && j == 12) || (i == 12 && j ==13) || (i == 12 && j == 14) || (i == 13 && j == 12) || (i == 13 && j == 13) || (i == 13 && j == 14) || (i ==14 && j == 12) || (i == 14 & j == 13) || (i == 14 && j == 14)){
				colnode.setVisible(true);
}
			}
		}
	}

public ArrayList<ColonyNode> getBorderNodes(ColonyNode node){
	int x = node.getX();
	int y = node.getY();
	int xx, yy;
	ArrayList<ColonyNode> borderNodes;
	borderNodes = new ArrayList<ColonyNode>();
	for (xx = -1; xx <= 1; ++xx){
		for (yy = -1; yy <= 1; ++yy){
			if (xx != 0 || yy != 0){
				try{
					borderNodes.add(colonyGrid[x + xx][y + yy]);
				}
				catch (Exception e){
					
				}
			}
		}
}
	return borderNodes;
}
public ColonyView getView() {
	// TODO Auto-generated method stub
	return null;
}
public void canMove(int t_urn) {
	// TODO Auto-generated method stub
	
}
}//end Colony
