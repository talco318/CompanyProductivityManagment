package TalCohen_ShaniAmos.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Department implements Changeable, Synchronized, Serializable {
	protected String name;
	protected ArrayList<Employee> allEmployee;
	protected double depHoursProfit;
	protected ManagerDecision ManagerDecisionDep;
	protected boolean isChangable;
	protected boolean isSynchronize;
	protected boolean blackFlag = false; // if in sync department we have got unchangeable role - all the dep is
											// unchangeable because of that the department is sync.

//	public Department(String name, ArrayList<Employee> allEmployee, boolean isChangable, boolean isSynchronize) {
//		this.name = name;
//		this.allEmployee = allEmployee;
//		this.isChangable = isChangable;
//		this.isSynchronize = isSynchronize;
//	}

	public Department(String name, boolean isChangable, boolean isSynchronize) {
		this.name = name;
		this.isChangable = isChangable;
		this.isSynchronize = isSynchronize;
		this.allEmployee = new ArrayList<Employee>();
	}

	public ArrayList<Employee> getAllEmployees() {
		return allEmployee;
	}

	public void addEmp(Employee newEmp) {
		allEmployee.add(newEmp);
	}

	public String getName() {
		return name;
	}

	public double getDepHoursProfit() {
		return depHoursProfit;
	}

	public double sumOfEmpProfit() {
		for (int i = 0; i < allEmployee.size(); i++) {
			depHoursProfit += allEmployee.get(i).profitPerEmp();
		}
		return depHoursProfit;
	}

	public boolean updateManagerDecToEmp(ManagerDecision decision) {
		boolean pass = false;
		for (int i = 0; i < allEmployee.size(); i++) {
			allEmployee.get(i).setManagerDecisionEmp(decision);
			pass = true;
		}
		return pass;
	}

	public void updateFlag(Role newRole) {
		// if(getIsSynchronized()) {
		if (!newRole.isChangable) {
			this.blackFlag = true;
			return;
		}
		// }
	}

	@Override
	public boolean getIsSynchronized() {
		return isSynchronize;
	}

	@Override
	public boolean getIsChangeable() {
		return isChangable;
	}

	public boolean isBlackFlag() {
		return blackFlag;
	}

	public void setBlackFlag(boolean blackFlag) {
		this.blackFlag = blackFlag;
	}

	public void setChangable(boolean isChangable) {
		this.isChangable = isChangable;
	}

	public void setSynchronize(boolean isSynchronize) {
		this.isSynchronize = isSynchronize;
	}

	public ManagerDecision getManagerDecisionDep() {
		return ManagerDecisionDep;
	}

	public void setManagerDecisionDep(ManagerDecision managerDecisionDep) {
		this.ManagerDecisionDep = managerDecisionDep;
		updateManagerDecToEmp(managerDecisionDep);
	}

	@Override
	public String toString() {
		return "Department name: " + name + "\nIs this role work method can be change? " + isChangable
				+ "\nIs this department synchronize? " + isSynchronize + ".";
	}

	public String toStringResults() {
		StringBuffer str = new StringBuffer("All department " +name+ " Profit: " + sumOfEmpProfit() + "\n");
		for (int i = 0; i < allEmployee.size(); i++) {
			str.append(allEmployee.get(i).toStringResults() + "\n");
		}

		return str.toString();
	}

}
