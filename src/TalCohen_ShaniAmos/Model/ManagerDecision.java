package TalCohen_ShaniAmos.Model;

import java.io.Serializable;

public class ManagerDecision implements Serializable{
	protected String managerDecision;
	protected int movingHours;

	
	public ManagerDecision(String managerDecision, int movingHours) {
		this.managerDecision = managerDecision; // early/later/home/stay as is
		this.movingHours = movingHours;
	}
	
	

	public ManagerDecision() {
		this.managerDecision = "no_simulate";
		this.movingHours = 0;
	}



	public String getManagerDecision() {
		return managerDecision;
	}

	public void setManagerDecision (String managerDecision) {
		this.managerDecision = managerDecision;
	}

	public int getMovingHours() {
		return movingHours;
	}

	public void setMovingHours(int movingHours) {
		this.movingHours = movingHours;
	}

	@Override
	public String toString() {
		return "Manager simulation decision: " + managerDecision + "\nMoving hours:" + movingHours + ".";
	}

	
	
	
	
}
