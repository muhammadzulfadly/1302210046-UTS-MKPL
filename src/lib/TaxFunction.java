package lib;

public class TaxFunction {
	private static final double TAX_RATE = 0.05;
    private static final int BASIC_TAX_EXEMPTION = 54000000;
    private static final int MARRIED_ADDITIONAL_EXEMPTION = 4500000;
    private static final int CHILD_EXEMPTION_PER_CHILD = 1500000;
    private static final int MAX_CHILDREN_EXEMPTION = 3;
	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        if (numberOfMonthWorking > 12) {
            System.err.println("Error: More than 12 months working per year");
            return 0;
        }
        int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int totalExemption = calculateExemption(isMarried, numberOfChildren);
        int taxableIncome = totalIncome - deductible - totalExemption;
        int tax = (int) Math.round(TAX_RATE * taxableIncome);
        return Math.max(tax, 0);
    }

    private static int calculateExemption(boolean isMarried, int numberOfChildren) {
        int totalExemption = BASIC_TAX_EXEMPTION;
        if (isMarried) {
            totalExemption += MARRIED_ADDITIONAL_EXEMPTION;
        }
        totalExemption += Math.min(numberOfChildren, MAX_CHILDREN_EXEMPTION) * CHILD_EXEMPTION_PER_CHILD;
        return totalExemption;
    }
}
