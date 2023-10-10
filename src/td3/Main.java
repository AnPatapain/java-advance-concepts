package td3;

import td2.Employee;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        String path = "C:\\Users\\NGUYEN KE AN\\temp";
//
//        EmployeeDisque employee1 = new EmployeeDisque("NGUYEN", "Kean", 1330, 1.5, 0);
////        employee1.toDisk(path);
//
//        EmployeeDisque employee2 = new EmployeeDisque("NGUYEN", "AnhTuan", 1430, 2, 1);
////        employee2.toDisk(path);
//
//        EmployeeDisque employee3 = new EmployeeDisque("Yacine", "Said", 1530, 1.5, 2);
////        employee3.toDisk(path);
//
//        EmployeeDisque employee4 = new EmployeeDisque("NGUYEN", "KePhat", 1530, 2, 3);
////        employee4.toDisk(path);
//
//        GestionEmployes gestionEmployes = new GestionEmployes();
//        gestionEmployes.ajouterEmployee(employee1);
//        gestionEmployes.ajouterEmployee(employee2);
//        gestionEmployes.ajouterEmployee(employee4);
//
//        gestionEmployes.ecrireTousLesEmployeeDisque(path + "\\" + "employees.dat");
//
//        gestionEmployes.lireTousLesEmployeeDisque(path + "\\" + "employees.dat");

        long startTime = System.nanoTime();
        EmployeeDisque employee1 = new EmployeeDisque("NGUYEN", "Kean", 1330, 1.5, 0);
        employee1.toDisk(path);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Temp moyen pour ecrire un Employee sur le disque: " + (double)elapsedTime/1000000000 + " " + "seconds");
    }
}
