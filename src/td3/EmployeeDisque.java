package td3;

import td2.Employee;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class EmployeeDisque extends Employee {
    public EmployeeDisque(String nom, String prenom, double salaireMensuel, double primeAnnuelle, int id) {
        super(nom, prenom, salaireMensuel, primeAnnuelle);
        this.numeroEmp = id;
    }

    public void toDisk(String path) {
        String empPath = path + "\\" + getNomPrenom() + "-" + numeroEmp + ".dat";
        try {
            File file = new File(empPath);
            Files.deleteIfExists(file.toPath());
            if(file.createNewFile()) {
                String str = toString();

                BufferedWriter writer = new BufferedWriter(new FileWriter(file));

                writer.write(str);
                writer.close();
            }else {
                System.out.println("Create file failed");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
