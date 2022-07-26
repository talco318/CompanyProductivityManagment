package TalCohen_ShaniAmos.View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.awt.Checkbox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.prefs.BackingStoreException;

import TalCohen_ShaniAmos.Model.CompanyUI;
import TalCohen_ShaniAmos.Model.Department;
import TalCohen_ShaniAmos.Model.Employee;
import TalCohen_ShaniAmos.Model.Role;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

@SuppressWarnings("unused")
public class ViewModel implements ViewInterface {
	private CompanyUI model;
	Scene fourScene;

	ArrayList<ComboBox<String>> arrEmpsInDepCombo;

	// tabs:
	TabPane tPaneSimulation = new TabPane();
	Tab tPaneAddDepartment = new Tab("Add Departments");
	Tab tPaneAddRoles = new Tab("Add Roles and employees");
	Tab tPaneData = new Tab("Data");
	Tab tPaneSimulateDep = new Tab("Simulate by department");
	Tab tPaneSimulateRole = new Tab("Simulate by role");
	Tab tPaneSimulationResults = new Tab("Simulation results");

	// first window (restore from file?)
	protected GridPane gPfiles = new GridPane();
	protected Label titleFiles = new Label("Would you like to restore data from a file?");
	protected Button restoreFileST = new Button("Yes");
	protected Button dontRestoreFileST = new Button("No - close");

	// add a department
	protected GridPane gPAddDepartment = new GridPane();
	protected Label titleAddDep = new Label("Add a department:");
	protected Label lableDepName = new Label("Name of department:");
	protected TextField tfNameDep = new TextField();
	protected Label lableDepType = new Label("Is this department changeable? (Yes/ No/ Yes- but syncronized)");
	protected ToggleGroup tgDepChangeable = new ToggleGroup();
	protected RadioButton rbChangeableY = new RadioButton("Yes");
	protected RadioButton rbChangeableN = new RadioButton("No");
	protected RadioButton rbChangeableYButSync = new RadioButton("Yes- but syncronized");
	protected Button bAddDep = new Button("Add");

	// add Role Tab
	protected GridPane gPAddRole = new GridPane();
	protected Label titleAddRole = new Label("Add a Role:");
	protected Label nameOfDepOfRole = new Label("What is the department name you want to add it to? (choose) ");
	protected ComboBox<String> comboAddRoleDepName = new ComboBox<>();
	protected Label lableRoleName = new Label("What is the role name?");
	protected TextField tfRoleName = new TextField();
	protected Label lableEmployeeName = new Label("What is the employee name of this role?");
	protected TextField tfEmployeeName = new TextField();
	protected Label lableEmployeeType = new Label("Select employee type:");
	protected ToggleGroup tgEmployeeType = new ToggleGroup();
	protected RadioButton rbEmpByBase = new RadioButton("1- Employee payment by base");
	protected RadioButton rbEmpByBaseSales = new RadioButton("2- Employee payment by base + sales");
	protected RadioButton rbEmpByHours = new RadioButton("3- Employee payment by hours");
	protected TextField tfSalesPresents = new TextField();
	protected TextField tfnumOfHours = new TextField();
	protected Label lablePaynmentHour = new Label("What is your per hour payment?");
	protected Label doNotPerHour = new Label("Do not fill it if you didnt select payment per hours!!");
	protected TextField tfPaymentPerHours = new TextField();
	protected Label lbchangeble = new Label("Is this role changable or work from home??");
	protected CheckBox cbRoleChangeable = new CheckBox("Changeable?");
	protected CheckBox cbRoleCWfromHome = new CheckBox("Can work from home?");
	protected Label lableEmployeePref = new Label("Select your preference:");
	protected ToggleGroup tgEmployeePref = new ToggleGroup();
	protected RadioButton rbEmpPrefStayAsIs = new RadioButton("1- Stay as is");
	protected RadioButton rbEmpPrefEarly = new RadioButton("2- Start work early");
	protected RadioButton rbEmpPrefLater = new RadioButton("3- Start work later");
	protected RadioButton rbEmpPrefHome = new RadioButton("4- Work from home");
	protected TextField tfEarlyorLate = new TextField();
	protected Label lableMovingHours = new Label("I would like to start _____ hours later/earlier ");
	protected Button bAddRole = new Button("Add Role");

	// dataTab
	protected GridPane gPData = new GridPane();
	protected Label titleData = new Label("Data section");
	protected Label selectData = new Label("What is the list you would like to see?");
	protected Button dataEmployees = new Button("Roles and employees list");
	protected Button dataDepartment = new Button("Departments list");

	// simulate by departments
	protected GridPane gPSimulateDep = new GridPane();
	protected Label titleSimuDep = new Label("Simulate departments");
	protected Label lSelectDep = new Label("What is the department you would like to change?");
	protected ComboBox<String> comboDepNameSimu = new ComboBox<>();
	protected Label lSimulateDep = new Label("What would you like to test?");
	protected ToggleGroup tgManagerDes = new ToggleGroup();
	protected RadioButton rbManagerDepStayAsIs = new RadioButton("1- Stay as is");
	protected RadioButton rbManagerDepEarly = new RadioButton("2- Start work early");
	protected RadioButton rbManagerDepLater = new RadioButton("3- Start work later");
	protected RadioButton rbManagerDepHome = new RadioButton("4- Work from home");
	protected Label lableMovingHoursDep = new Label("start _____ hours later/earlier ");
	protected TextField tfEarlyorLateSimulateDep = new TextField();
	protected Button bSetSimulateDep = new Button("Apply");

	// simulate by roles
	protected GridPane gPSimulateRole = new GridPane();
	protected Label titleSimuRole = new Label("Simulate role");
	protected Label lSelectDepOfRole = new Label("Select the department name of the role you would like to change");
	protected ComboBox<String> comboRolesOfThisDep = new ComboBox<>();
	protected Label lSelectRole = new Label("Choose the specific role");
	protected ComboBox<String> comboDepNameOfRoleSimu = new ComboBox<>();
	protected Label lSimulateRole = new Label("What would you like to test?");
	protected ToggleGroup tgManagerRole = new ToggleGroup();
	protected RadioButton rbManagerRoleStayAsIs = new RadioButton("1- Stay as is");
	protected RadioButton rbManagerRoleEarly = new RadioButton("2- Start work early");
	protected RadioButton rbManagerRoleLater = new RadioButton("3- Start work later");
	protected RadioButton rbManagerRoleHome = new RadioButton("4- Work from home");
	protected Label lableMovingHoursRole = new Label("start _____ hours later/earlier ");
	protected TextField tfEarlyOrLateSimulateRole = new TextField();
	protected Button bSetSimulateRole = new Button("Apply");

	// toStrings Roles
	protected GridPane allRoles = new GridPane();
	protected Label allRolesLable = new Label("All the roles:");
	@SuppressWarnings("rawtypes")
	protected ListView listViewRoles = new ListView();
	protected HBox hboxRoles = new HBox(listViewRoles);

	// toStrings Employees
	protected GridPane allEmployees = new GridPane();
	protected Label allEmployeesLable = new Label("All the employees:");
	@SuppressWarnings("rawtypes")
	protected ListView listViewEmployees = new ListView();
	protected HBox hboxEmployees = new HBox(listViewEmployees);

	// toStrings Roles
	protected GridPane allDepartments = new GridPane();
	protected Label allDepLable = new Label("All the Departments:");
	@SuppressWarnings("rawtypes")
	protected ListView listViewDepartments = new ListView();
	protected HBox hboxDepartments = new HBox(listViewDepartments);

	// simulation results tab
	protected GridPane results = new GridPane();
	protected Label resultsLable = new Label("Simulation results:");
	protected Button bSimResults = new Button("Click to see the results of all the company!");
	protected Button bSaveToFile = new Button("Save to file");

	// simulation results window
	protected GridPane resultsWindow = new GridPane();
	protected Label resultsWindowLable = new Label("Simulation results:");
	@SuppressWarnings("rawtypes")
	protected ListView listViewResults = new ListView();
	protected HBox hboxResults = new HBox(listViewResults);

	public ViewModel(Stage primaryStage) {

		arrEmpsInDepCombo = new ArrayList<ComboBox<String>>();

		titleAddDep.setStyle(
				"-fx-underline: true; -fx-font-weight: bold; -fx-font-style: italic; -fx-font-size: 14; -fx-font-family: Arial;");
		titleData.setStyle(
				"-fx-underline: true; -fx-font-weight: bold; -fx-font-style: italic; -fx-font-size: 14; -fx-font-family: Arial;");
		titleAddRole.setStyle(
				"-fx-underline: true; -fx-font-weight: bold; -fx-font-style: italic; -fx-font-size: 14; -fx-font-family: Arial;");
		titleSimuDep.setStyle(
				"-fx-underline: true; -fx-font-weight: bold; -fx-font-style: italic; -fx-font-size: 14; -fx-font-family: Arial;");
		resultsWindowLable.setStyle(
				"-fx-underline: true; -fx-font-weight: bold; -fx-font-style: italic; -fx-font-size: 14; -fx-font-family: Arial;");
		titleSimuRole.setStyle(
				"-fx-underline: true; -fx-font-weight: bold; -fx-font-style: italic; -fx-font-size: 14; -fx-font-family: Arial;");
		resultsLable.setStyle(
				"-fx-underline: true; -fx-font-weight: bold; -fx-font-style: italic; -fx-font-size: 14; -fx-font-family: Arial;");

		doNotPerHour.setStyle("-fx-underline: true; -fx-font-weight: bold; -fx-font-color: red; ");
		lableDepName.setStyle("-fx-font-style: italic; -fx-font-size: 14; -fx-font-family: Arial;");
		lableDepType.setStyle("-fx-font-style: italic; -fx-font-size: 14; -fx-font-family: Arial;");

		handleRadioEmployee();
		handleCheckboxChangeable();
		handleRadioDepChangeable();
		handleRadioEmpPref();
		handleRadioEmployeePref();
		handleRadioManagerDes();
		handleRadioManagerOfRole();
		clickShowDep();
		clickShowEmployees();
		handleRadioBaseSales();
		handleRadioPerHours();
		comboDepListener();
		//OnClickDontRestoreFileST();

		gPfiles.setPadding(new Insets(10));
		gPfiles.setVgap(10);
		gPfiles.setHgap(20);
		gPfiles.paddingProperty().set(new Insets(30, 0, 0, 0));
		gPfiles.setPadding(new Insets(20));
		gPfiles.add(titleFiles, 0, 0);
		gPfiles.add(restoreFileST, 0, 1);
		gPfiles.add(dontRestoreFileST, 1, 1);

		gPAddDepartment.setPadding(new Insets(10));
		gPAddDepartment.setVgap(10);
		gPAddDepartment.setHgap(20);
		gPAddDepartment.add(titleAddDep, 0, 0);
		gPAddDepartment.add(lableDepName, 0, 1);
		gPAddDepartment.add(tfNameDep, 0, 2);
		gPAddDepartment.add(lableDepType, 0, 4);
		gPAddDepartment.add(rbChangeableY, 0, 5);
		gPAddDepartment.add(rbChangeableN, 0, 6);
		gPAddDepartment.add(rbChangeableYButSync, 0, 7);
		gPAddDepartment.add(bAddDep, 0, 9);
		gPAddDepartment.paddingProperty().set(new Insets(30, 0, 0, 0));
		gPAddDepartment.setPadding(new Insets(20));

		gPData.setVgap(10);
		gPData.setHgap(20);
		gPData.add(titleData, 0, 0);
		gPData.add(selectData, 0, 1);
		gPData.add(dataEmployees, 0, 3);
		gPData.add(dataDepartment, 0, 4);
		gPData.paddingProperty().set(new Insets(30, 0, 0, 0));
		gPData.setPadding(new Insets(20));

		gPAddRole.setVgap(10);
		gPAddRole.setHgap(20);
		gPAddRole.paddingProperty().set(new Insets(30, 0, 0, 0));
		gPAddRole.setPadding(new Insets(20));
		tfSalesPresents.setPromptText("Sales presents");
		tfnumOfHours.setPromptText("Hours number");

		gPAddRole.add(titleAddRole, 0, 0);
		gPAddRole.add(nameOfDepOfRole, 0, 1);
		gPAddRole.add(lbchangeble, 1, 2);
		gPAddRole.add(cbRoleChangeable, 1, 4);
		gPAddRole.add(cbRoleCWfromHome, 1, 5);
		gPAddRole.add(comboAddRoleDepName, 0, 2);
		gPAddRole.add(lableRoleName, 0, 3);
		gPAddRole.add(tfRoleName, 0, 4);
		gPAddRole.add(lableEmployeeName, 0, 5);
		gPAddRole.add(tfEmployeeName, 0, 6);
		gPAddRole.add(lableEmployeeType, 0, 7);
		gPAddRole.add(rbEmpByBase, 0, 8);
		gPAddRole.add(rbEmpByBaseSales, 0, 9);
		gPAddRole.add(tfSalesPresents, 1, 9);
		gPAddRole.add(rbEmpByHours, 0, 10);
		gPAddRole.add(tfnumOfHours, 1, 10);
		gPAddRole.add(lablePaynmentHour, 0, 11);
		gPAddRole.add(tfPaymentPerHours, 0, 12);
		gPAddRole.add(lableEmployeePref, 0, 13);
		gPAddRole.add(rbEmpPrefStayAsIs, 1, 13);
		gPAddRole.add(rbEmpPrefEarly, 1, 14);
		gPAddRole.add(rbEmpPrefLater, 1, 15);
		gPAddRole.add(rbEmpPrefHome, 1, 16);
		gPAddRole.add(lableMovingHours, 0, 17);
		gPAddRole.add(tfEarlyorLate, 0, 18);
		gPAddRole.add(bAddRole, 0, 19);

		gPSimulateDep.add(titleSimuDep, 0, 0);
		gPSimulateDep.add(lSelectDep, 0, 1);
		gPSimulateDep.add(comboDepNameSimu, 0, 2);
		gPSimulateDep.add(lSimulateDep, 0, 3);
		gPSimulateDep.add(rbManagerDepStayAsIs, 0, 4);
		gPSimulateDep.add(rbManagerDepEarly, 0, 5);
		gPSimulateDep.add(rbManagerDepLater, 0, 6);
		gPSimulateDep.add(rbManagerDepHome, 0, 7);
		gPSimulateDep.add(lableMovingHoursDep, 0, 8);
		gPSimulateDep.add(tfEarlyorLateSimulateDep, 0, 9);
		gPSimulateDep.add(bSetSimulateDep, 0, 10);
		gPSimulateDep.setVgap(10);
		gPSimulateDep.setHgap(20);
		gPSimulateDep.paddingProperty().set(new Insets(30, 0, 0, 0));
		gPSimulateDep.setPadding(new Insets(20));

		gPSimulateRole.add(titleSimuRole, 0, 0);
		gPSimulateRole.add(lSelectDepOfRole, 0, 1);
		gPSimulateRole.add(comboDepNameOfRoleSimu, 0, 2);
		gPSimulateRole.add(lSelectRole, 0, 3);
		gPSimulateRole.add(comboRolesOfThisDep, 0, 4);
		gPSimulateRole.add(lSimulateRole, 0, 5);
		gPSimulateRole.add(rbManagerRoleStayAsIs, 0, 6);
		gPSimulateRole.add(rbManagerRoleEarly, 0, 7);
		gPSimulateRole.add(rbManagerRoleLater, 0, 8);
		gPSimulateRole.add(rbManagerRoleHome, 0, 9);
		gPSimulateRole.add(lableMovingHoursRole, 0, 10);
		gPSimulateRole.add(tfEarlyOrLateSimulateRole, 0, 11);
		gPSimulateRole.add(bSetSimulateRole, 0, 12);
		gPSimulateRole.setVgap(10);
		gPSimulateRole.setHgap(20);
		gPSimulateRole.paddingProperty().set(new Insets(30, 0, 0, 0));
		gPSimulateRole.setPadding(new Insets(20));

		allRoles.add(allRolesLable, 0, 0);
		allRoles.paddingProperty().set(new Insets(30, 0, 0, 0));
		allRoles.setPadding(new Insets(20));
		listViewRoles.setMinWidth(450);

		allDepartments.add(allDepLable, 0, 0);
		allDepartments.paddingProperty().set(new Insets(30, 0, 0, 0));
		allDepartments.setPadding(new Insets(20));
		listViewDepartments.setMinWidth(450);

		allEmployees.add(allEmployeesLable, 0, 0);
		allEmployees.paddingProperty().set(new Insets(30, 0, 0, 0));
		allEmployees.setPadding(new Insets(20));
		listViewEmployees.setMinWidth(450);

		results.add(resultsLable, 0, 0);
		results.add(bSimResults, 0, 1);
		results.add(bSaveToFile, 0, 3);

		results.setVgap(10);
		results.setHgap(20);
		results.paddingProperty().set(new Insets(30, 0, 0, 0));
		listViewResults.setMinWidth(450);
		results.setPadding(new Insets(20));

//		resultsWindow.add(resultsWindowLable, 0, 0);
//		resultsWindow.paddingProperty().set(new Insets(30, 0, 0, 0));
//		resultsWindow.setPadding(new Insets(20));
//		resultsWindow.add(hboxResults, 0, 1);
//		listViewResults.setMinWidth(650);
//		listViewResults.setMinHeight(600);

		tPaneSimulation.getTabs().addAll(tPaneAddDepartment, tPaneAddRoles, tPaneData, tPaneSimulateDep,
				tPaneSimulateRole, tPaneSimulationResults);
		tPaneSimulation.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tPaneAddDepartment.setContent(gPAddDepartment);
		tPaneAddRoles.setContent(gPAddRole);
		tPaneData.setContent(gPData);
		tPaneSimulateDep.setContent(gPSimulateDep);
		tPaneSimulationResults.setContent(results);
		tPaneSimulateRole.setContent(gPSimulateRole);

		Scene scene = new Scene(tPaneSimulation, 680, 680);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Simulation program");
		primaryStage.setResizable(false);
		primaryStage.setAlwaysOnTop(false);
		primaryStage.show();

		Stage firstStage = new Stage();
		Scene firstscene = new Scene(gPfiles, 400, 150);
		firstStage.centerOnScreen();
		firstStage.setScene(firstscene);
		firstStage.show();

	}

	// alerts:
	public void errorMessage(String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Message");
		alert.initStyle(StageStyle.DECORATED);
		alert.setContentText(msg);
		alert.showAndWait();
	}

	public void successMessage(String msg) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("Message");
		alert.setHeaderText("Great!");
		alert.setContentText(msg);
		alert.showAndWait();
	}

	private void handleRadioEmployee() {
		tfPaymentPerHours.setDisable(true);
		rbEmpByBase.setToggleGroup(tgEmployeeType);
		rbEmpByBaseSales.setToggleGroup(tgEmployeeType);
		rbEmpByHours.setToggleGroup(tgEmployeeType);
		tgEmployeeType.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> T, Toggle empByHours, Toggle new_toggle) {
				if (tgEmployeeType.getSelectedToggle() != empByHours) {
					tfPaymentPerHours.setText("");
					tfPaymentPerHours.setDisable(false);
				}
				if (tgEmployeeType.getSelectedToggle() == rbEmpByBaseSales) {
					tfPaymentPerHours.setText("");
					tfPaymentPerHours.setDisable(true);
				}
				if (tgEmployeeType.getSelectedToggle() == rbEmpByBase) {
					tfPaymentPerHours.setDisable(true);
				}
			}
		});

	}

	private void handleCheckboxChangeable() {
		cbRoleCWfromHome.setDisable(true);
		cbRoleChangeable.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!cbRoleChangeable.isSelected()) {
					cbRoleCWfromHome.setSelected(false);
					cbRoleCWfromHome.setDisable(true);
				} else {
					cbRoleCWfromHome.setDisable(false);
				}
			}
		});

	}

	private void handleRadioDepChangeable() {
		rbChangeableY.setToggleGroup(tgDepChangeable);
		rbChangeableN.setToggleGroup(tgDepChangeable);
		rbChangeableYButSync.setToggleGroup(tgDepChangeable);
	}

	private void handleRadioEmpPref() {
		tfEarlyOrLateSimulateRole.setDisable(true);
		rbManagerRoleStayAsIs.setToggleGroup(tgManagerRole);
		rbManagerRoleEarly.setToggleGroup(tgManagerRole);
		rbManagerRoleLater.setToggleGroup(tgManagerRole);
		rbManagerRoleHome.setToggleGroup(tgManagerRole);
		tgManagerRole.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> T, Toggle old_toggle, Toggle new_toggle) {
				if (tgManagerRole.getSelectedToggle() == rbManagerRoleLater
						|| tgManagerRole.getSelectedToggle() == rbManagerRoleEarly) {
					tfEarlyOrLateSimulateRole.setDisable(false);
				} else {
					tfEarlyOrLateSimulateRole.setText("");
					tfEarlyOrLateSimulateRole.setDisable(true);
				}

			}
		});
	}

	private void handleRadioManagerDes() {
		tfEarlyorLateSimulateDep.setDisable(true);
		rbManagerDepStayAsIs.setToggleGroup(tgManagerDes);
		rbManagerDepEarly.setToggleGroup(tgManagerDes);
		rbManagerDepLater.setToggleGroup(tgManagerDes);
		rbManagerDepHome.setToggleGroup(tgManagerDes);
		tgManagerDes.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> T, Toggle oldValue, Toggle newValue) {
				if (tgManagerDes.getSelectedToggle() == rbManagerDepLater
						|| tgManagerDes.getSelectedToggle() == rbManagerDepEarly) {
					tfEarlyorLateSimulateDep.setDisable(false);
				} else {
					tfEarlyorLateSimulateDep.setText("");
					tfEarlyorLateSimulateDep.setDisable(true);
				}

			}
		});
	}

	private void handleRadioEmployeePref() {
		tfEarlyorLate.setDisable(true);
		rbEmpPrefStayAsIs.setToggleGroup(tgEmployeePref);
		rbEmpPrefEarly.setToggleGroup(tgEmployeePref);
		rbEmpPrefLater.setToggleGroup(tgEmployeePref);
		rbEmpPrefHome.setToggleGroup(tgEmployeePref);
		tgEmployeePref.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> T, Toggle oldValue, Toggle newValue) {
				if (tgEmployeePref.getSelectedToggle() == rbEmpPrefEarly
						|| tgEmployeePref.getSelectedToggle() == rbEmpPrefLater) {
					tfEarlyorLate.setDisable(false);
				} else {
					tfEarlyorLate.setText("");
					tfEarlyorLate.setDisable(true);
				}

			}
		});
	}

	private void handleRadioBaseSales() {
		tfSalesPresents.setDisable(true);
		tgEmployeeType.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> T, Toggle oldValue, Toggle newValue) {
				if (tgEmployeeType.getSelectedToggle() == rbEmpByBaseSales) {
					tfSalesPresents.setDisable(false);
				} else {
					tfSalesPresents.setText("");
					tfSalesPresents.setDisable(true);
				}

			}
		});
	}

	private void comboDepListener() {
		comboDepNameOfRoleSimu.setOnAction((event) -> {
			int indexOfDep = comboDepNameOfRoleSimu.getSelectionModel().getSelectedIndex();
			setComboOfRoleInDep(indexOfDep);
		});
	}

	private void handleRadioPerHours() {
		tfnumOfHours.setDisable(true);
		tgEmployeeType.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> T, Toggle oldValue, Toggle newValue) {
				if (tgEmployeeType.getSelectedToggle() == rbEmpByHours) {
					tfnumOfHours.setDisable(false);
				} else {
					tfnumOfHours.setText("");
					tfnumOfHours.setDisable(true);
				}

			}
		});
	}

	private void handleRadioManagerOfRole() {
		tfEarlyOrLateSimulateRole.setDisable(true);
		rbManagerDepStayAsIs.setToggleGroup(tgManagerDes);
		rbManagerDepEarly.setToggleGroup(tgManagerDes);
		rbManagerDepLater.setToggleGroup(tgManagerDes);
		rbManagerDepHome.setToggleGroup(tgManagerDes);
	}

	public RadioButton getManagerDepStayAsIs() {
		return rbManagerDepStayAsIs;
	}

	public RadioButton getManagerDepEarly() {
		return rbManagerDepEarly;
	}

	public RadioButton getManagerDepLater() {
		return rbManagerDepLater;
	}

	public RadioButton getManagerDepHome() {
		return rbManagerDepHome;
	}

	public RadioButton getManagerRoleStayAsIs() {
		return rbManagerRoleStayAsIs;
	}

	public RadioButton getManagerRoleEarly() {
		return rbManagerRoleEarly;
	}

	public RadioButton getManagerRoleLater() {
		return rbManagerRoleLater;
	}

	public RadioButton getManagerRoleHome() {
		return rbManagerRoleHome;
	}

	public ListView getListViewCitizens() {
		return listViewRoles;
	}

	public ListView getListViewResults() {
		return listViewResults;
	}

	public TextField getTfNameDep() {
		return tfNameDep;
	}

	public ToggleGroup getTgDepChangeable() {
		return tgDepChangeable;
	}

	public RadioButton getRbChangeableY() {
		return rbChangeableY;
	}

	public RadioButton getRbChangeableN() {
		return rbChangeableN;
	}

	public RadioButton getRbChangeableYButSync() {
		return rbChangeableYButSync;
	}

	public Button getbAddDep() {
		return bAddDep;
	}

	public ComboBox<String> getComboDepName() {
		return comboAddRoleDepName;
	}

	public TextField getTfRoleName() {
		return tfRoleName;
	}

	public TextField getTfEmployeeName() {
		return tfEmployeeName;
	}

	public ToggleGroup getTgEmployeeType() {
		return tgEmployeeType;
	}

	public RadioButton getEmpByBase() {
		return rbEmpByBase;
	}

	public RadioButton getEmpByBaseSales() {
		return rbEmpByBaseSales;
	}

	public RadioButton getEmpByHours() {
		return rbEmpByHours;
	}

	public TextField getTfPaymentPerHours() {
		return tfPaymentPerHours;
	}

	public CheckBox getcBChangeable() {
		return cbRoleChangeable;
	}

	public CheckBox getcBCWfromHome() {
		return cbRoleCWfromHome;
	}

	public ToggleGroup getTgEmployeePref() {
		return tgEmployeePref;
	}

	public RadioButton getEmpPrefStayAsIs() {
		return rbEmpPrefStayAsIs;
	}

	public RadioButton getEmpPrefEarly() {
		return rbEmpPrefEarly;
	}

	public RadioButton getEmpPrefLater() {
		return rbEmpPrefLater;
	}

	public RadioButton getEmpPrefHome() {
		return rbEmpPrefHome;
	}

	public TextField getTfEarlyorLate() {
		return tfEarlyorLate;
	}

	public Button getbAddRole() {
		return bAddRole;
	}

	public ComboBox<String> getComboDepNameSimu() {
		return comboDepNameSimu;
	}

	public ToggleGroup getTgManagerDec() {
		return tgManagerDes;
	}

	public TextField getTfEarlyorLateSimulateDep() {
		return tfEarlyorLateSimulateDep;
	}

	public Button getbSetSimulateDep() {
		return bSetSimulateDep;
	}

	public ComboBox<String> getComboRolesOfThisDep() {
		return comboRolesOfThisDep;
	}

	public ComboBox<String> getComboDepNameOfRoleSimu() {
		return comboDepNameOfRoleSimu;
	}

	public ToggleGroup getTgManagerRole() {
		return tgManagerRole;
	}

	public TextField getTfEarlyOrLateSimulateRole() {
		return tfEarlyOrLateSimulateRole;
	}

	public Button getbSetSimulateRole() {
		return bSetSimulateRole;
	}

	public TextField getTfSalesPresents() {
		return tfSalesPresents;
	}

	public TextField getTfnumOfHours() {
		return tfnumOfHours;
	}

	public void addToComboDep(String depName) {
		comboDepNameSimu.getItems().add(depName);
		comboAddRoleDepName.getItems().add(depName);
		comboDepNameOfRoleSimu.getItems().add(depName);

	}

	public void addToComboRole(String roleName) {
		// comboDepNameToRoleSimu.getItems().add(roleName);

	}

	private void clickShowDep() {
		Stage newWindow = new Stage();
		Scene fiveScene = new Scene(hboxDepartments, 450, 450);
		dataDepartment.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// New window (Stage)
				newWindow.setTitle("All the departments:");
				newWindow.setScene(fiveScene);
				newWindow.show();
			}
		});
	}

	private void clickShowEmployees() {
		Stage newWindow = new Stage();
		Scene fiveScene = new Scene(hboxEmployees, 450, 450);
		dataEmployees.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// New window (Stage)
				newWindow.setTitle("All the emplyees:");
				newWindow.setScene(fiveScene);
				newWindow.show();
			}
		});
	}

	public void resultsSimWindow() {
		Stage newWindow2 = new Stage();
		Scene sixScene = new Scene(hboxResults, 450, 450);
		newWindow2.setScene(sixScene);
		newWindow2.setTitle("Simulation results:");

		bSimResults.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// New window (Stage)
				newWindow2.show();
			}
		});
	}

	public void addResults(EventHandler<ActionEvent> e) {
		bSimResults.setOnAction(e);
	}

	public void addResults(String str) {
		listViewResults.getItems().removeAll();
		listViewResults.getItems().add(str);
	}

	public void onClickAddDep(EventHandler<ActionEvent> e) {
		bAddDep.setOnAction(e);
	}

	public void onClickAddRole(EventHandler<ActionEvent> e) {
		bAddRole.setOnAction(e);
	}

	public void OnClickDontRestoreFileST(EventHandler<ActionEvent> e) {
		dontRestoreFileST.setOnAction(e);
		
	}

	public void OnClickRestoreFileST(EventHandler<ActionEvent> e) {
		restoreFileST.setOnAction(e);
	}

	public void closeWindow(Button button) {
		final Stage thisStage = (Stage) button.getScene().getWindow();
		thisStage.close();
	}
	
	public void onClickSaveToFile(EventHandler<ActionEvent> e) {
		bSaveToFile.setOnAction(e);
	}

	public void onClickSimDep(EventHandler<ActionEvent> e) {
		bSetSimulateDep.setOnAction(e);
	}

	public void onClickSimRole(EventHandler<ActionEvent> e) {
		bSetSimulateRole.setOnAction(e);
	}

	public void onClickSimResults(EventHandler<ActionEvent> e) {
		bSimResults.setOnAction(e);
	}

	public ArrayList<ComboBox<String>> getArrEmpsInDepCombo() {
		return arrEmpsInDepCombo;
	}

	public void addToArrEmpsInDepCombo() {
		arrEmpsInDepCombo.add(new ComboBox<String>());
	}

	public void addRoleOfDepToView(int index, String RoleName) {
		arrEmpsInDepCombo.get(index).getItems().add(RoleName);
	}

	public void setComboOfRoleInDep(int index) {
		if (index != -1) {
			comboRolesOfThisDep.getItems().setAll(arrEmpsInDepCombo.get(index).getItems());
		}
	}

	public void addToListviewDep(String depName, boolean isChangable, boolean isSynchronize) {
		listViewDepartments.getItems().addAll(new Department(depName, isChangable, isSynchronize));
	}

	public void addToListviewRoleEmp(ArrayList<Employee> allEmp) {
		listViewEmployees.getItems().removeAll(allEmp);
		for (int i = 0; i < allEmp.size(); i++) {
			listViewEmployees.getItems().add(allEmp.get(i));
		}
	}

	public String returnedDepName() {
		return comboRolesOfThisDep.getSelectionModel().getSelectedItem();
	}

	public Button getRestoreFileST() {
		return restoreFileST;
	}

	public Button getDontRestoreFileST() {
		return dontRestoreFileST;
	}

	
}
