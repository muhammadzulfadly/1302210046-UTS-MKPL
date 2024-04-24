package lib;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Employee {

	private enum Gender{
		LAKILAKI,
		PEREMPUAN
	}

	private String employeeId;
	private InfoData infoData;
	
	private LocalDate joinDate;
	private int monthWorkingInYear;
	private boolean isForeigner;
	private Gender gender;
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private Spouse spouse;
	private List<Child> children;
	
	public Employee(String employeeId, InfoData infoData, LocalDate joinDate, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.infoData = infoData;
		this.joinDate = joinDate;
		this.isForeigner = isForeigner;
		this.gender = gender;
		children = new ArrayList<>();
	}
	
	public void setMonthlySalary(int grade) {	
		int baseSalary = 0;
        switch (grade) {
            case 1:
                baseSalary = 3000000;
                break;
            case 2:
                baseSalary = 5000000;
                break;
            case 3:
                baseSalary = 7000000;
                break;
            default:
                break;
        }
        monthlySalary = isForeigner ? (int) (baseSalary * 1.5) : baseSalary; 
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}

	public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouse = new Spouse(spouseName, spouseIdNumber);
    }

    public void addChild(String childName, String childIdNumber) {
        children.add(new Child(childName, childIdNumber));
    }
	
	public int getAnnualIncomeTax() {
		LocalDate date = LocalDate.now();
		if (date.getYear() == joinDate.getYear()) {
			monthWorkingInYear = date.getMonthValue() - joinDate.getMonthValue();
		}else {
			monthWorkingInYear = 12;
		}
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouse.equals(""), children.size());
	}
}
