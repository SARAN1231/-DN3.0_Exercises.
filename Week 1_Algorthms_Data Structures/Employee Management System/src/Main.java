public class Main {
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(5);

        Employee e1 = new Employee("E001", "Alice", "Manager", 75000);
        Employee e2 = new Employee("E002", "Bob", "Developer", 50000);
        Employee e3 = new Employee("E003", "Charlie", "Analyst", 60000);

        // Add employees
        ems.addEmployee(e1);
        ems.addEmployee(e2);
        ems.addEmployee(e3);

        // Traverse and display all employees
        System.out.println("All Employees:");
        ems.traverseEmployees();

        // Search for an employee by ID
        System.out.println("\nSearch Employee E002:");
        System.out.println(ems.searchEmployee("E002"));

        // Delete an employee by ID
        ems.deleteEmployee("E002");
        System.out.println("\nAfter Deleting Employee E002:");
        ems.traverseEmployees();
    }
}
