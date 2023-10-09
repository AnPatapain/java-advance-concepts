package td3;

import org.junit.Assert;
import org.junit.Test;

public class TestExceptionLance {
    @Test
    public void exceptionUtiliseDoitEtreLance() {
        String path = "C:\\Users\\NGUYEN KE AN\\temp";
        GestionEmployes gestionEmployes = new GestionEmployes();
        EmployeeDisque employeeDisque1 = gestionEmployes.chargerEmployee(path + "\\" + "NGUYENKean" + "-" + 0 + ".dat");

        Assert.assertThrows(ExceptionUtilise.class, () -> {
            EmployeeDisque employeeDisque2 = gestionEmployes.chargerEmployee(path + "\\" + "NGUYENKean" + "-" + 0 + ".dat");
        });
    }
}
