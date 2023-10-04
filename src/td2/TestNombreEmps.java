package td2;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class TestNombreEmps {
    private static List<Employee> employeeList = new ArrayList<>();

    @BeforeClass
    public static void initEmployeeList() {
        for(int i = 0; i < 5; i++) {
           employeeList.add(new Employee("NGUYEN" + i, "An" + i, 1200 + i, 125 + i));
        }
    }
    @Test
    public void testNombreEmps() {
        System.out.println("Run test case 1");
        Assert.assertEquals(employeeList.get(0).getNombreEmployees(), employeeList.size());
    }

    @Test
    public void testNombreEmps_() {
        System.out.println("Run test case 2");
        Assert.assertNotEquals(employeeList.get(0).getNombreEmployees() + 1, employeeList.size());
    }
}
