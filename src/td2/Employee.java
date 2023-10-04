package td2;

public class Employee {
    protected int numeroEmp;
    private static int nombreEmp;
    private String nom;
    private String prenom;
    private double salaireMensuel;
    private double primeAnnuelle;

    public Employee(String nom, String prenom, double salaireMensuel, double primeAnnuelle) {
        this.nom = nom;
        this.prenom = prenom;
        this.salaireMensuel = salaireMensuel;
        this.primeAnnuelle = primeAnnuelle;
        this.numeroEmp = nombreEmp;
        nombreEmp++;
    }

    public Employee() {
    }

    public int getNumeroEmp() {
        return numeroEmp;
    }

    public int getNombreEmployees() {
        return nombreEmp;
    }

    public String getNomPrenom() {
        return nom + prenom;
    }

    public double getSalaireAnuelle() {
        return 12 * salaireMensuel + primeAnnuelle;
    }

    public String toString() {
        return this.getNomPrenom() + ", " + this.getSalaireAnuelle();
    }
}

