package td3;

import td2.Employee;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        EmployeeDisque employee = new EmployeeDisque("NGUYEN", "Kean", 1200, 1.5, 0);
        employee.toDisk("/temp");
    }
}
