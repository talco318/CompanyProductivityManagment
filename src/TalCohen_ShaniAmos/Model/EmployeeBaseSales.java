package TalCohen_ShaniAmos.Model;

import java.io.Serializable;

public class EmployeeBaseSales extends Employee implements Serializable {

	protected int salesPresent;


	
	public EmployeeBaseSales(Role roleOfEmployee, String salaryType, String empName,
			EmployeePreference employeePreference, int salesPresent) {
		super(roleOfEmployee, salaryType, empName, employeePreference);
		this.salesPresent = salesPresent;
	}



	@Override
	public String toString() {
		return super.toString()+  "\nSales precents: " +salesPresent +".\n";
	}

}
