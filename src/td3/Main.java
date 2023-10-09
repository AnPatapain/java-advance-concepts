package td3;

import td2.Employee;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        String path = "C:\\Users\\NGUYEN KE AN\\temp";

        EmployeeDisque employee1 = new EmployeeDisque("NGUYEN", "Kean", 1330, 1.5, 0);
        employee1.toDisk(path);

        EmployeeDisque employee2 = new EmployeeDisque("NGUYEN", "AnhTuan", 1430, 2, 1);
        employee2.toDisk(path);

        EmployeeDisque employee3 = new EmployeeDisque("Yacine", "Said", 1530, 1.5, 2);
        employee3.toDisk(path);

        EmployeeDisque employee4 = new EmployeeDisque("NGUYEN", "KePhat", 1530, 2, 3);
        employee4.toDisk(path);
    }
}
