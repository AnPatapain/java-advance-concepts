package td3;

import td2.Employee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class GestionEmployes {
    public List<EmployeeDisque> employees = new ArrayList<>();
    private Set<Integer> numerosUtilises = new HashSet<>();

    public boolean estDejaUtilise(int numeroEmp) {
        return numerosUtilises.contains(numeroEmp);
    }

    public EmployeeDisque chargerEmployee(String path) throws ExceptionUtilise {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            List<String> tokens = Collections.list(new StringTokenizer(line, ", ")).stream()
                    .map(token -> (String)token)
                    .toList();

            int employeeId = Integer.parseInt(tokens.get(4));
            if(!estDejaUtilise(employeeId)) {
                EmployeeDisque employeeDisque = new EmployeeDisque(
                        tokens.get(0),
                        tokens.get(1),
                        Double.parseDouble(tokens.get(2)),
                        Double.parseDouble(tokens.get(3)),
                        Integer.parseInt(tokens.get(4))
                );
                ajouterEmployee(employeeDisque);
                return employeeDisque;
            }else
                throw new ExceptionUtilise(employeeId);

        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void ajouterEmployee(EmployeeDisque e) {
        this.employees.add(e);
        this.numerosUtilises.add(e.getNumeroEmp());
    }

    public void toDiskEmployees() {
        for(EmployeeDisque employeeDisque : employees) {
            String path = employeeDisque.getNom() + "-" + employeeDisque.getPrenom() + "-" + employeeDisque.getNumeroEmp() + ".dat";
            employeeDisque.toDisk(path);
        }
    }

    public void chargerDatFiles(File dir) {
        File[] files = dir.listFiles();

        if(files == null) return;

        for(File file : files) {
            String fileName = file.getName();
            if(fileName.substring(fileName.lastIndexOf(".") + 1).equals("dat")) {
                EmployeeDisque employeeDisque = chargerEmployee(file.getPath());
                ajouterEmployee(employeeDisque);
            }
        }
    }

}
