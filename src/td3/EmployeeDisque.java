package td3;

import td2.Employee;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EmployeeDisque extends Employee {
    public EmployeeDisque(String nom, String prenom, double salaireMensuel, double primeAnnuelle, int id) {
        super(nom, prenom, salaireMensuel, primeAnnuelle);
        this.numeroEmp = id;
    }

    public void toDisk(String path) throws IOException {
        File file = new File(path + "/" + getNomPrenom() + "-" + numeroEmp + ".dat");
        if(file.createNewFile()) {
            String str = toString();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            writer.write(str);
            writer.close();
        }else {
            System.out.println("Create file failed");
        }
    }
}
