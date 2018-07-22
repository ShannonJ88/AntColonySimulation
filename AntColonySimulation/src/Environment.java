/*******
 * Initial State of Simulation 
 * Simulation = Environment
 * Should begin with the center square of the environment and its adjacent squares
 */
import javax.swing.JOptionPane;

public class Environment implements SimulationEventListener {
	Colony colonyEnvironment;
	AntSimGUI gui;
	int t_urn = 0;
	boolean queenisAlive;
	boolean movement;
	
	Environment(AntSimGUI gui){
		
		queenisAlive = true;
		movement = true;
		this.gui = gui;
		colonyEnvironment = new Colony(new ColonyView (27,27), this);
		gui.initGUI(colonyEnvironment.getView());
	
		
	}
	Environment(){
		
	}
	
	public void turn(){
			colonyEnvironment.canMove(t_urn);
			t_urn++;
			
	}
	
	public void endEnvironment(){
		queenisAlive = false;
		System.exit(0);
	}
/*
 * 	Only need to make sure Normal Setup, Run and Step are implemented
 */
	
	
	@Override
	public void simulationEventOccurred(SimulationEvent simEvent) {
		if (simEvent.getEventType() == SimulationEvent.NORMAL_SETUP_EVENT){//Initialize the simulation 
			colonyEnvironment.myColony();								//Run prior to Run and Step below
		}
		if (simEvent.getEventType() == SimulationEvent.RUN_EVENT){		//Run simulation continously
			movement = false;
			
		}
		if (simEvent.getEventType() == SimulationEvent.STEP_EVENT){		//run simulation one turn at a time
			movement = true;
		}
	}
}//End Environment
