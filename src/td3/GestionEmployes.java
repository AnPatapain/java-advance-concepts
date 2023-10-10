package td3;

import td2.Employee;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class GestionEmployes {
    public List<EmployeeDisque> employees = new ArrayList<>();
    private Set<Integer> numerosUtilises = new HashSet<>();

    public Set<Integer> getNumerosUtilises() {
        return numerosUtilises;
    }

    public List<EmployeeDisque> getEmployees() {
        return employees;
    }

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

    public void chargerDatFiles(String path) {
        File dir = new File(path);
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

    public void ecrireTousLesEmployeeDisque(String path) {
        try {
            File file = new File(path);
            Files.deleteIfExists(file.toPath());

            FileWriter fileWriter = new FileWriter(file);
            for(EmployeeDisque employeeDisque : employees) {
                fileWriter.write(employeeDisque.toString());
                fileWriter.write("\n");
            }
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lireTousLesEmployeeDisque(String path) {
        File file = new File(path);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getPath()));
            String line = bufferedReader.readLine();
            while(line != null) {
                System.out.println(line);
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
                }
                line = bufferedReader.readLine();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
