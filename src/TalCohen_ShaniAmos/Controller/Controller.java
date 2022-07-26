package TalCohen_ShaniAmos.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import TalCohen_ShaniAmos.Model.CompanyUI;
import TalCohen_ShaniAmos.Model.Employee;
import TalCohen_ShaniAmos.View.ViewInterface;


public class Controller {
	private ViewInterface view;
	private CompanyUI model;
	
    public void initialize(){
        model.readFromFile(view);
    }
	
	public void moveToStringResults() {
		view.addResults(model.toStringResults());
	}
	
	


	public Controller(ViewInterface view, CompanyUI model) {
		this.view = view;
		this.model = model;

		
		view.OnClickRestoreFileST(e -> {
			if(model.readFromFile(view)) {
				view.successMessage("File restored from file!");
			}
			view.closeWindow(view.getRestoreFileST());
		});

		view.OnClickDontRestoreFileST(e -> {
			view.closeWindow(view.getDontRestoreFileST());
		});
		
		view.onClickAddDep(e -> {
			boolean rbChangeable = false;
			boolean synchronize = false;
			String addDepName = view.getTfNameDep().getText();
			if (addDepName.isEmpty()) {
				view.errorMessage("You must enter name of department!");
				return;
			}
			if (view.getTgDepChangeable().getSelectedToggle() == null) {
				view.errorMessage("You must select a type of depratment!");
				return;
			}
			if (view.getTgDepChangeable().getSelectedToggle() == view.getRbChangeableY()) {
				rbChangeable = true;
			}
			if (view.getTgDepChangeable().getSelectedToggle() == view.getRbChangeableYButSync()) {
				rbChangeable = true;
				synchronize = true;
			}
			if (model.addDepartment(addDepName, rbChangeable, synchronize)) {
				view.addToArrEmpsInDepCombo();
				view.addToListviewDep(addDepName, rbChangeable, synchronize);
				view.addToComboDep(addDepName);
				view.successMessage("This department added successfully!");
			}
		});

		view.onClickAddRole(e -> {
			String depNameOfAddRole = view.getComboDepName().getSelectionModel().getSelectedItem();
			String roleNameOfAddRole = view.getTfRoleName().getText();
			String empNameOfAddRole = view.getTfEmployeeName().getText();
			boolean isChangeable = view.getcBChangeable().isSelected();
			boolean isSync = model.isDepSync(depNameOfAddRole);
			boolean cWFromHome = view.getcBCWfromHome().isSelected();
			String salaryType = "";
			String empPreference = "";
			int movingHours = 0;
			int salesPresents = 0;
			int numOfHours = 0;
			int perHoursPayment = 0;
			if (depNameOfAddRole == null) {
				view.errorMessage("Please select department name!");
				return;
			}
			if (empNameOfAddRole == null) {
				view.errorMessage("Please enter employee name!");
			}
			if (view.getTgEmployeeType().getSelectedToggle() == null) {
				view.errorMessage("You must select employee type");
				return;
			}

			if (view.getTgEmployeeType().getSelectedToggle() == view.getEmpByBase()) {
				salaryType = "base";
			}
			if (view.getTgEmployeeType().getSelectedToggle() == view.getEmpByBaseSales()) {
				salaryType = "baseSales";
				try {
					salesPresents = Integer.parseInt(view.getTfSalesPresents().getText());
				} catch (NumberFormatException nfe) {
					view.errorMessage("Enter sales presents again (without symbols)");
					return;
				}
			}
			if (view.getTgEmployeeType().getSelectedToggle() == view.getEmpByHours()) {
				salaryType = "perHours";
				try {
					numOfHours = Integer.parseInt(view.getTfnumOfHours().getText());
				} catch (NumberFormatException nfe) {
					view.errorMessage("Enter num of hours again");
					return;
				}
				try {
					perHoursPayment = Integer.parseInt(view.getTfPaymentPerHours().getText());
				} catch (NumberFormatException nfe) {
					view.errorMessage("Enter payment per hour again");
					return;
				}

			}
			if (view.getTgEmployeePref().getSelectedToggle() == null) {
				view.errorMessage("You must select employee preference");
				return;
			}
			if (view.getTgEmployeePref().getSelectedToggle() == view.getEmpPrefStayAsIs()) {
				empPreference = "stay_as_is";
				movingHours = 0;
			}
			if (view.getTgEmployeePref().getSelectedToggle() == view.getEmpPrefEarly()) {
				empPreference = "early";
				try {
					movingHours = Integer.parseInt(view.getTfEarlyorLate().getText());
				} catch (NumberFormatException nfe) {
					view.errorMessage("Hours must be whole number!");
					return;
				}
			}
			if (view.getTgEmployeePref().getSelectedToggle() == view.getEmpPrefLater()) {
				empPreference = "later";
				try {
					if (view.getTfEarlyorLate().getText().isEmpty()) {
						view.errorMessage("Please enter hours number!");
						return;
					}
					movingHours = Integer.parseInt(view.getTfEarlyorLate().getText());
				} catch (NumberFormatException nfe) {
					view.errorMessage("Hours must be whole number!");
					return;
				}

			}
			if (view.getTgEmployeePref().getSelectedToggle() == view.getEmpPrefHome()) {
				empPreference = "work_at_home";
				movingHours = 0;
			}

			if (model.addRoleWithEmployee(depNameOfAddRole, roleNameOfAddRole, empNameOfAddRole, isChangeable, isSync,
					salesPresents, numOfHours, salaryType, perHoursPayment, empPreference, movingHours, cWFromHome)) {
				view.addRoleOfDepToView(model.getIndexOfDep(depNameOfAddRole), roleNameOfAddRole);
				view.successMessage("This role and the employee added successfully!");
				view.addToComboRole(roleNameOfAddRole);
				ArrayList<Employee> emplist = model.getAllEmployees();
				view.addToListviewRoleEmp(emplist);
				return;
			}
		});

		view.onClickSimDep(e -> {
			String managerDec = "";
			int movingHours = 0;
			String depSimDepName = view.getComboDepNameSimu().getSelectionModel().getSelectedItem();
			if (view.getTgManagerDec().getSelectedToggle() == view.getManagerDepStayAsIs()) {
				managerDec = "stay_as_is";
				movingHours = 0;
			}
			if (view.getTgManagerDec().getSelectedToggle() == view.getManagerDepEarly()) {
				managerDec = "early";
				try {
					if (view.getTfEarlyorLateSimulateDep().getText().isEmpty()) {
						view.errorMessage("Please enter hours number!");
						return;
					}
					movingHours = Integer.parseInt(view.getTfEarlyorLateSimulateDep().getText());
				} catch (NumberFormatException nfe) {
					view.errorMessage("Hours must be whole number!");
					return;
				}
			}
			if (view.getTgManagerDec().getSelectedToggle() == view.getManagerDepLater()) {
				managerDec = "later";
				try {
					if (view.getTfEarlyorLateSimulateDep().getText().isEmpty()) {
						view.errorMessage("Please enter hours number!");
						return;
					}
					movingHours = Integer.parseInt(view.getTfEarlyorLateSimulateDep().getText());
				} catch (NumberFormatException nfe) {
					view.errorMessage("Hours must be whole number!");
					return;
				}
			}
			if (view.getTgManagerDec().getSelectedToggle() == view.getManagerDepHome()) {
				managerDec = "work_at_home";
				movingHours = 0;
			}
			if (depSimDepName.isEmpty()) {
				view.errorMessage("You must select a department!");
				return;
			}
			if (view.getTgManagerDec().getSelectedToggle() == null) {
				view.errorMessage("You must select a change option first!");
				return;
			}
			if (!model.isDepChangeable(depSimDepName)) {
				view.errorMessage("Sorry, you cant change work method in this department.");
				return;
			}

			if (model.changeWorkMethodByDep(depSimDepName, managerDec, movingHours)) {
				view.successMessage("The changes successfully applied!");
				return;
			}
			view.errorMessage(
					"This department contain an unchangable role, what why the whole department cannot be change.");
		});

		view.onClickSimRole(e -> {

			String simRoleDepName = view.getComboDepNameOfRoleSimu().getSelectionModel().getSelectedItem();
			String simRoleName = view.getComboRolesOfThisDep().getSelectionModel().getSelectedItem();
			String simRoleManagerDec = "";
			int simRoleMovingHours = 0;

			if (view.getTgManagerRole().getSelectedToggle() == view.getManagerRoleStayAsIs()) {
				simRoleManagerDec = "stay_as_is";
				simRoleMovingHours = 0;
			}
			if (view.getTgManagerRole().getSelectedToggle() == view.getManagerRoleEarly()) {
				simRoleManagerDec = "early";
				try {
					if (view.getTfEarlyOrLateSimulateRole().getText().isEmpty()) {
						view.errorMessage("Please enter hours number!");
						return;
					}
					simRoleMovingHours = Integer.parseInt(view.getTfEarlyOrLateSimulateRole().getText());
				} catch (NumberFormatException nfe) {
					view.errorMessage("Hours must be whole number!");
					return;
				}
			}
			if (view.getTgManagerRole().getSelectedToggle() == view.getManagerRoleLater()) {
				simRoleManagerDec = "later";
				try {
					if (view.getTfEarlyOrLateSimulateRole().getText().isEmpty()) {
						view.errorMessage("Please enter hours number!");
						return;
					}
					simRoleMovingHours = Integer.parseInt(view.getTfEarlyOrLateSimulateRole().getText());
				} catch (NumberFormatException nfe) {
					view.errorMessage("Hours must be whole number!");
					return;
				}
			}
			if (view.getTgManagerRole().getSelectedToggle() == view.getManagerRoleHome()) {
				simRoleManagerDec = "work_at_home";
				simRoleMovingHours = 0;
			}
			if (view.getComboRolesOfThisDep().getSelectionModel().getSelectedItem() == null) {
				view.errorMessage("You must select a department!");
				return;
			}
			if (view.getTgManagerRole().getSelectedToggle() == null) {
				view.errorMessage("You must select a change option first!");
				return;
			}
			if (!model.isDepChangeable(simRoleDepName)) {
				view.errorMessage("Sorry, you cant change work method to a role in this department.");
				return;
			}
			
//			if (!model.isDepSync(simRoleDepName)) {
//				view.errorMessage("Sorry, this department is sync. You have to change work method during the department simulate. ");
//				return;
//			}
			
			

			if(!model.isRoleChangeable(simRoleName)) {
				view.errorMessage("Sorry, you cant change work method to this role.");
				return;

			}
			if (model.changeWorkMethodByRole(simRoleDepName, simRoleName, simRoleManagerDec, simRoleMovingHours)) {
				view.successMessage("The changes successfully applied!");
			} else {
				view.errorMessage("Something went wrong!");
			}
		});

		view.onClickSimResults(e -> {
			moveToStringResults();
			view.resultsSimWindow();
		});
		
		view.onClickSaveToFile(e -> {
			try {
				model.saveToFile();
				view.successMessage("File saved!");
			} catch (FileNotFoundException e1) {
				view.errorMessage("File not found!");
			} catch (IOException e1) {
				view.errorMessage("You cant save the file!");
			}
			
		});

	}
}
