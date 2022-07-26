package TalCohen_ShaniAmos.Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface CompanyUI {

	public void saveToFile() throws FileNotFoundException, IOException;
	
	public boolean readFromFile(TalCohen_ShaniAmos.View.ViewInterface view);
	
	public ArrayList<Department> getAllDepartments();

	public int getIndexOfDep(String depName);

	public boolean addDepartment(String name, boolean isChangable, boolean isSynchronize);

	public boolean addRoleWithEmployee(String departmentName, String roleName, String empInThisRole,
			boolean isChangable, boolean isSynchronize, int salesPresent, int hoursNum, String salaryType,
			int payPerHour, String empPreference, int movingHours, boolean cWFromHome);

	public boolean isDepChangeable(String depName);

	public boolean isRoleChangeable(String roleName);
	
	public boolean isDepSync(String depName);

	public boolean changeWorkMethodByDep(String depName, String managerDecision, int movingHours);

	public ArrayList<Employee> getAllEmployees();

	public boolean changeWorkMethodByRole(String depName, String empName, String managerDecision, int movingHours);

	public String toStringResults();
}
