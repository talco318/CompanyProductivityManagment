package TalCohen_ShaniAmos.View;

import java.util.ArrayList;

import TalCohen_ShaniAmos.Model.Department;
import TalCohen_ShaniAmos.Model.Employee;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public interface ViewInterface {
	public void errorMessage(String msg);

	public void successMessage(String msg);

	public RadioButton getManagerDepStayAsIs();

	public RadioButton getManagerDepEarly();

	public RadioButton getManagerDepLater();

	public RadioButton getManagerDepHome();

	public RadioButton getManagerRoleStayAsIs();

	public RadioButton getManagerRoleEarly();

	public RadioButton getManagerRoleLater();

	public RadioButton getManagerRoleHome();

	public TextField getTfNameDep();

	public ToggleGroup getTgDepChangeable();

	public RadioButton getRbChangeableY();

	public RadioButton getRbChangeableN();

	public RadioButton getRbChangeableYButSync();

	public Button getbAddDep();

	public ComboBox<String> getComboDepName();

	public TextField getTfRoleName();

	public TextField getTfEmployeeName();

	public ToggleGroup getTgEmployeeType();

	public RadioButton getEmpByBase();

	public RadioButton getEmpByBaseSales();

	public RadioButton getEmpByHours();

	public TextField getTfPaymentPerHours();

	public CheckBox getcBChangeable();

	public CheckBox getcBCWfromHome();

	public ToggleGroup getTgEmployeePref();

	public RadioButton getEmpPrefStayAsIs();

	public RadioButton getEmpPrefEarly();

	public RadioButton getEmpPrefLater();

	public RadioButton getEmpPrefHome();

	public TextField getTfEarlyorLate();

	public Button getbAddRole();

	public ComboBox<String> getComboDepNameSimu();

	public ToggleGroup getTgManagerDec();

	public TextField getTfEarlyorLateSimulateDep();

	public Button getbSetSimulateDep();

	public ComboBox<String> getComboRolesOfThisDep();

	public ComboBox<String> getComboDepNameOfRoleSimu();

	public ToggleGroup getTgManagerRole();

	public TextField getTfEarlyOrLateSimulateRole();

	public Button getbSetSimulateRole();

	public void addToComboDep(String DepName);
	
	public void addToComboRole(String roleName);

	public TextField getTfSalesPresents();

	public TextField getTfnumOfHours();

	public void onClickAddDep(EventHandler<ActionEvent> e);

	public void onClickAddRole(EventHandler<ActionEvent> e);

	public void onClickSimDep(EventHandler<ActionEvent> e);

	public void onClickSimRole(EventHandler<ActionEvent> e);

	public void onClickSimResults(EventHandler<ActionEvent> e);
	
	public void OnClickDontRestoreFileST(EventHandler<ActionEvent> e);

	public void closeWindow(Button button);
	
	public Button getRestoreFileST();

	public Button getDontRestoreFileST();
	
	public void OnClickRestoreFileST(EventHandler<ActionEvent> e);
	
	public void onClickSaveToFile(EventHandler<ActionEvent> e);
	
	public ArrayList<ComboBox<String>> getArrEmpsInDepCombo();
	
	public void addToArrEmpsInDepCombo();
	
	public void addRoleOfDepToView(int index, String RoleName);
	
	public void addToListviewDep(String depName,  boolean isChangable, boolean isSynchronize );

	public void addToListviewRoleEmp(ArrayList<Employee> allEmp);
	
	public void addResults(String str);

	public String returnedDepName();
	
	public void resultsSimWindow();
	
}
