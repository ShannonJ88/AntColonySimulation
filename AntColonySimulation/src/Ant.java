/*************************Instructions******************************************************************
1.Each ant should be identified by a unique integer The queen ant should have an ID value of 0. 
		Other ants should be numbered in
		ascending order as they are hatched.
2. All ant types (except for the queen) have a maximum life span of 1 year.
3. Dead ants should be removed from the simulation.
4. All ants are limited to one action per turn, with some exceptions that will be discussed later.
5. All ants except Bala ants may only move in squares that have been revealed by scout ants; 
   Bala ants may also move into squares
   that have not been revealed by scout ants.
6. When moving, all ant types should move no more than 1 square per turn.
*********************************************************************************************************/
public class Ant {
	int ID;										        //1.
	int maxLifeSpan;									//2. All ants except queen
	int hatched;								        						        
	boolean Alive;									   												
	int canMove;								        
	ColonyNode location;
							
	
	public Ant(ColonyNode node){
		ID = 0;
		maxLifeSpan = 1;
		Alive = true;
		location = node;
		canMove = 0;
		
	}
/**----------------CONSTRUCTOR--------------------**/
	
	public Ant(){
		
	}
	public void setID(int id){
		this.ID = id;
	}
	public void turn(int t_urn){
		
	}
	public void setHatched(int t_urn){
		hatched = t_urn;
	}

	public void loc(ColonyNode newLocation){
		location.removeAnt(this);
		location = newLocation;
		location.addAnt(this);
	}
	
	public void die(){
		location.removeAnt(this);
	}	

}//end Ant Class
