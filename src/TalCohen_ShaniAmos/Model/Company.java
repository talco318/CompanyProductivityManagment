package TalCohen_ShaniAmos.Model;

import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import TalCohen_ShaniAmos.View.ViewInterface;

public class Company implements CompanyUI, Serializable {
	protected String name;
	protected ArrayList<Department> allDepartments = new ArrayList<Department>();
	protected ArrayList<Employee> allEmployees = new ArrayList<Employee>();
	protected ArrayList<Role> allRoles = new ArrayList<Role>();
	protected double compHoursProfit;
	public static final String F_NAME = "Simulation.txt"; // file name

	public void saveToFile() throws FileNotFoundException, IOException {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(F_NAME));
			output.writeObject(allDepartments);
			output.writeObject(allEmployees);
			output.writeObject(allRoles);
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean readFromFile(TalCohen_ShaniAmos.View.ViewInterface view) {
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(F_NAME));
			ArrayList<Department> allDepFromFile = (ArrayList<Department>) input.readObject();
			allDepartments = allDepFromFile;
			for (int i = 0; i < allDepFromFile.size(); i++) {
				view.addToComboDep(allDepFromFile.get(i).getName());
				view.addToListviewDep(allDepartments.get(i).getName(), allDepartments.get(i).getIsChangeable(), allDepartments.get(i).getIsSynchronized());
				view.addToArrEmpsInDepCombo();
			}

			ArrayList<Employee> allEmployeesFromFile = (ArrayList<Employee>) input.readObject();
			this.allEmployees = allEmployeesFromFile;


			ArrayList<Role> allRolesFromFile = (ArrayList<Role>) input.readObject();
			this.allRoles = allRolesFromFile;
			for (int i = 0; i < allDepFromFile.size(); i++) {
				view.addToListviewRoleEmp(allDepFromFile.get(i).getAllEmployees());
				int index = i;
				view.addRoleOfDepToView(index,allDepFromFile.get(i).getName());
			}
			
			input.close();
			return true;

		} catch (FileNotFoundException e) {
			view.errorMessage("There is no file!");
			return false;
		} catch (IOException e) {
			view.errorMessage("The file is empty!");
			return false;
		} 
		catch (ClassNotFoundException e) {
			view.errorMessage("Something went wrong!");
			return false;

		}
	}

	public int getIndexOfDep(String depName) {
		for (int i = 0; i < allDepartments.size(); i++) {
			if (allDepartments.get(i).getName().equalsIgnoreCase(depName)) {
				return i;
			}
		}
		return -1;
	}

	public boolean isRoleChangeable(String roleName) {
		for (int i = 0; i < allEmployees.size(); i++) {
			if (allEmployees.get(i).getRoleOfEmployee().getRoleName().equals(roleName)) {
				return allEmployees.get(i).getRoleOfEmployee().isChangable;
			} 
		}
		return false;
	}

	public boolean isDepChangeable(String depName) {
		for (int i = 0; i < allDepartments.size(); i++) {
			if (allDepartments.get(i).getName().equalsIgnoreCase(depName)) {
				return allDepartments.get(i).getIsChangeable();
			}
		}
		return false;
	}

	public boolean isDepSync(String depName) {
		for (int i = 0; i < allDepartments.size(); i++) {
			if (allDepartments.get(i).getName().equals(depName)) {
				boolean result = allDepartments.get(i).getIsSynchronized();
				return result;
			}
		}
		return false;
	}

	public ArrayList<Department> getAllDepartments() {
		return allDepartments;
	}

	public boolean addDepartment(String name, boolean isChangable, boolean isSynchronize) {
		Department newDep = new Department(name, isChangable, isSynchronize);
		allDepartments.add(newDep);
		return true;
	}

	public boolean addRoleWithEmployee(String departmentName, String roleName, String empInThisRole,
			boolean isChangable, boolean isSynchronize, int salesPresent, int hoursNum, String salaryType,
			int payPerHour, String empPreference, int movingHours, boolean cWFromHome) {
		Role newRole = new Role(departmentName, roleName, empInThisRole, isChangable, isSynchronize, cWFromHome);
		EmployeePreference empPre = new EmployeePreference(empPreference, movingHours);
		Department currentDep = null;
		allRoles.add(newRole);
		for (int i = 0; i < allDepartments.size(); i++) {
			if (allDepartments.get(i).getName().equals(departmentName)) {
				currentDep = allDepartments.get(i);
				allDepartments.get(i).updateFlag(newRole);
			}
		}
		if (salaryType.equalsIgnoreCase("base")) {
			EmployeeBase newEmployeeBase = new EmployeeBase(newRole, salaryType, empInThisRole, empPre);
			allEmployees.add(newEmployeeBase);
			currentDep.addEmp(newEmployeeBase);
			return true;
		}
		if (salaryType.equalsIgnoreCase("baseSales")) {
			EmployeeBaseSales newEmployeeBaseSales = new EmployeeBaseSales(newRole, salaryType, empInThisRole, empPre,
					salesPresent);
			allEmployees.add(newEmployeeBaseSales);
			currentDep.addEmp(newEmployeeBaseSales);
			return true;
		}
		if (salaryType.equalsIgnoreCase("perHours")) {
			EmployeePerHours newEmployeePerHours = new EmployeePerHours(newRole, salaryType, empInThisRole, empPre,
					hoursNum, payPerHour);
			allEmployees.add(newEmployeePerHours);
			currentDep.addEmp(newEmployeePerHours);
			return true;
		}

		return false;
	}

	public boolean changeWorkMethodByDep(String depName, String managerDecision, int movingHours) {
		for (int i = 0; i < allDepartments.size(); i++) {
			if (allDepartments.get(i).getName().equals(depName)) {
				if (allDepartments.get(i).isBlackFlag()) {
					return false;
				}
				ManagerDecision decision = new ManagerDecision(managerDecision, movingHours);
				allDepartments.get(i).setManagerDecisionDep(decision);
				return true;
			}
		}
		return false;
	}

	public boolean changeWorkMethodByRole(String depName, String roleName, String managerDecision, int movingHours) {
		Department dep = null;
		for (int i = 0; i < allDepartments.size(); i++) {
			if (allDepartments.get(i).getName().equals(depName)) {
				dep = allDepartments.get(i);
				break;
			}
		}
		for (int j = 0; j < dep.getAllEmployees().size(); j++) {
			if (dep.allEmployee.get(j).isThisIsTheCorrectRole(roleName)) {
				ManagerDecision decision = new ManagerDecision(managerDecision, movingHours);
				dep.allEmployee.get(j).setManagerDecisionEmp(decision);
				return true;
			}
		}
		return false;
	}

	public ArrayList<Employee> getAllEmployees() {
		return allEmployees;
	}

	public void addEmployee(Employee newEmployee) {
		allEmployees.add(newEmployee);
	}

	public double getHoursProfit() {
		return compHoursProfit;
	}

	public void setCompHoursProfit(double compHoursProfit) {
		this.compHoursProfit = compHoursProfit;
	}

	public String getName() {
		return name;
	}

	public double sumOfDepProfit() {
		for (int i = 0; i < allDepartments.size(); i++) {
			compHoursProfit += allDepartments.get(i).getDepHoursProfit();
		}
		return compHoursProfit;
	}

	public String toStringResults() {

		StringBuffer str = new StringBuffer("");
		for (int i = 0; i < allDepartments.size(); i++) {
			str.append(allDepartments.get(i).toStringResults() + "\n");
		}
		str.append("All company Profit: " + sumOfDepProfit() + "\n");
		return str.toString();
	}

}
