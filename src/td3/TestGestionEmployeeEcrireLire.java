package td3;

import org.junit.Assert;
import org.junit.Test;

public class TestGestionEmployeeEcrireLire {
    @Test
    public void testIfGestionEmployeesLireAvecSuccess() {
        String path = "C:\\Users\\NGUYEN KE AN\\temp\\employees.dat";
        GestionEmployes gestionEmployes = new GestionEmployes();
        gestionEmployes.lireTousLesEmployeeDisque(path);

        Assert.assertEquals(gestionEmployes.getEmployees().size(), 3);
        Assert.assertEquals(gestionEmployes.getEmployees().get(0).getPrenom(), "Kean");
        Assert.assertEquals(gestionEmployes.getEmployees().get(1).getPrenom(), "AnhTuan");
        Assert.assertEquals(gestionEmployes.getEmployees().get(2).getPrenom(), "KePhat");
    }
}
