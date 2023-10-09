package td3;

import org.junit.Assert;
import org.junit.Test;

public class TestGestionEmployee {
    @Test
    public void nombreEmployeesDevraitEgale3() {
        String path = "C:\\Users\\NGUYEN KE AN\\temp";
        GestionEmployes gestionEmployes = new GestionEmployes();
        EmployeeDisque employeeDisque1 = gestionEmployes.chargerEmployee(path + "\\" + "NGUYENKean" + "-" + 0 + ".dat");
        EmployeeDisque employeeDisque2 = gestionEmployes.chargerEmployee(path + "\\" + "NGUYENAnhTuan" + "-" + 1 + ".dat");
        EmployeeDisque employeeDisque3 = gestionEmployes.chargerEmployee(path + "\\" + "NGUYENKePhat" + "-" + 3 + ".dat");

        Assert.assertEquals(employeeDisque1.getNombreEmployees(), 3);
        Assert.assertEquals(employeeDisque2.getNombreEmployees(), 3);
        Assert.assertEquals(employeeDisque3.getNombreEmployees(), 3);
    }

}
