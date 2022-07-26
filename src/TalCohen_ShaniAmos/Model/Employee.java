package TalCohen_ShaniAmos.Model;

import java.io.Serializable;

public abstract class Employee implements Synchronized, Changeable , Serializable{

	protected double empHoursProfit;
	protected static int hoursNum = 160;
	protected static int numOfHoursPerDay = 8; // start working at 8:00 - end by 17:00 less one hour of break
	protected static double profitPerHour = 0.2;
	protected static double profitWorkAtHome = 0.1;
	protected ManagerDecision managerDecisionEmp;
	protected EmployeePreference employeePreference;
	protected Role roleOfEmployee;
	protected String salaryType;
	protected String empName;
	protected boolean isChangable;
	protected boolean isSynchronize;

	public Employee(Role roleOfEmployee, String salaryType, String empName, EmployeePreference employeePreference) {
		this.roleOfEmployee = roleOfEmployee;
		this.salaryType = salaryType;
		this.empName = empName;
		this.employeePreference = employeePreference;
		this.managerDecisionEmp = new ManagerDecision();
	}

	public double getEmpHoursProfit() {
		return empHoursProfit;
	}

	public void setEmpHoursProfit(double empHoursProfit) {
		this.empHoursProfit = empHoursProfit;
	}

	public static int getHoursNum() {
		return hoursNum;
	}

	public static void setHoursNum(int hoursNum) {
		Employee.hoursNum = hoursNum;
	}

	public ManagerDecision getManagerDecisionEmp() {
		return managerDecisionEmp;
	}

	public void setManagerDecisionEmp(ManagerDecision managerDecisionEmp) {
		this.managerDecisionEmp = managerDecisionEmp;
	}

	public void setEmployeePreference(EmployeePreference employeePreference) {
		this.employeePreference = employeePreference;
	}
	/// check it

	public double profitPerEmp() {
		int empMovingHours = employeePreference.getMovingHours();
		int manMovingHours = managerDecisionEmp.getMovingHours();
		String getManagerDecision = employeePreference.getEmployeePreference();
		String getEmpPreference = managerDecisionEmp.getManagerDecision();

		if (!getManagerDecision.equals(getEmpPreference)) {
			if(getEmpPreference.equalsIgnoreCase("no_simulate")) {
				empHoursProfit = 0;
				return empHoursProfit;
			}
			if (getEmpPreference.equalsIgnoreCase("early") || getEmpPreference.equalsIgnoreCase("later")
					|| getEmpPreference.equalsIgnoreCase("stay_as_is")) {
				empHoursProfit = (empMovingHours + manMovingHours) * (-profitPerHour);
				return empHoursProfit;
			}
			if (getEmpPreference.equalsIgnoreCase("work_at_home")) {
				empHoursProfit = numOfHoursPerDay * (-profitWorkAtHome);
				return empHoursProfit;
			}

		}

		if (getManagerDecision.equals(getEmpPreference)) {
			if (getEmpPreference.equalsIgnoreCase("early") || getEmpPreference.equalsIgnoreCase("later")) {
				if (empMovingHours == manMovingHours) {
					empHoursProfit = empMovingHours * (profitPerHour);
					return empHoursProfit;
				} else {
					empHoursProfit = empMovingHours * profitPerHour
							+ ((Math.abs(manMovingHours - empMovingHours) * (-profitPerHour)));
					return empHoursProfit;
				}
			}
			if (getEmpPreference.equalsIgnoreCase("work_at_home")) {
				empHoursProfit = numOfHoursPerDay * (profitWorkAtHome);
				return empHoursProfit;
			}
			if (getEmpPreference.equalsIgnoreCase("stay_as_is")) {
				return empHoursProfit = 0;
			}

		}

		return empHoursProfit;
	}

	@Override
	public boolean getIsSynchronized() {
		return isChangable;
	}

	@Override
	public boolean getIsChangeable() {
		return isSynchronize;
	}

	public void setChangable(boolean isChangable) {
		this.isChangable = isChangable;
	}

	public void setSynchronize(boolean isSynchronize) {
		this.isSynchronize = isSynchronize;
	}

	public String getEmpName() {
		return empName;
	}

	public boolean isThisIsTheCorrectRole(String roleName) {
		if(roleOfEmployee.getRoleName().equals(roleName)) {
			return true;
		}
		return false;
	}
	
	
	public Role getRoleOfEmployee() {
		return roleOfEmployee;
	}

	@Override
	public String toString() {
			return roleOfEmployee +  "\nCan change work method? " + isChangable
					+ "\nCan work from home? " + roleOfEmployee.iscWFromHome() + employeePreference.toString() + "\nSync with all of the department? " + isSynchronize + "\nSalary type: "+ salaryType ;
	}
		
	public String toStringResults() {
		return empName + "'s Profit: " + profitPerEmp();
	}

}
