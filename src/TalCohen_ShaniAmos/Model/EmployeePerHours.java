package TalCohen_ShaniAmos.Model;

import java.io.Serializable;

public class EmployeePerHours extends Employee implements Serializable {
	protected int hoursNum;
	protected int payPerHour;

	public EmployeePerHours(Role roleOfEmployee, String salaryType, String empName,
			EmployeePreference employeePreference, int hoursNum, int payPerHour) {
		super(roleOfEmployee, salaryType, empName, employeePreference);
		this.hoursNum = hoursNum;
		this.payPerHour = payPerHour;
	}

	public void employeePerHours(int hoursNum) {
		this.hoursNum = hoursNum;
	}

	@Override
	public String toString() {
		return super.toString() + "\nHours number: " + hoursNum + "\nPayment per hour: " + payPerHour + ".\n";
	}

}
