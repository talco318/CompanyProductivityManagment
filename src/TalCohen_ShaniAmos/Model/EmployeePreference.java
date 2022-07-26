package TalCohen_ShaniAmos.Model;

import java.io.Serializable;

public class EmployeePreference implements Serializable{
	protected String employeePreference;
	protected int movingHours;

	public EmployeePreference(String employeePreference, int movingHours) {
		this.employeePreference = employeePreference;
		this.movingHours = movingHours;
	}

	public String getEmployeePreference() {
		return employeePreference;
	}

	public void setManagerDecision(String employeePreference) {
		this.employeePreference = employeePreference;
	}

	public int getMovingHours() {
		return movingHours;
	}

	public void setMovingHours(int movingHours) {
		this.movingHours = movingHours;
	}

	@Override
	public String toString() {
		if (employeePreference.equalsIgnoreCase("early")
				|| employeePreference.equalsIgnoreCase("later")) {
		return "\nEmployee's preference: " + employeePreference + "\nHow many hours? " + movingHours;
		}
		return "\nEmployee's preference: " + employeePreference;
	}

}
