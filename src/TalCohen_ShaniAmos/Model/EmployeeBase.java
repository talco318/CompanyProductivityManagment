package TalCohen_ShaniAmos.Model;

import java.io.Serializable;

public class EmployeeBase extends Employee implements Serializable{


	public EmployeeBase(Role roleOfEmployee, String salaryType, String empName, EmployeePreference employeePreference) {
		super(roleOfEmployee, salaryType, empName, employeePreference);
	}

	@Override
	public String toString() {
		return super.toString() +"\n";
	}



}
