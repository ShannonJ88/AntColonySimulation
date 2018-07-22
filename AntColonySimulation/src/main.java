/*
 * Shannon Julion
 * CSC 385
 * Final Semester Project
 */
public class main {
	public static void main(String[] args){
		AntSimGUI gui = new AntSimGUI();
		gui.addSimulationEventListener(new Environment(gui));
	}

}
