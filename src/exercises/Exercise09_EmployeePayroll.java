package exercises;
public class Exercise09_EmployeePayroll {
    static double netSalary(double basic, double allowance, double deduction) {
        return basic + allowance - deduction;
    }
    public static void main(String[] args) {
        System.out.println("Net salary = " + netSalary(30000, 5000, 2500));
    }
}
