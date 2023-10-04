package td2;

import org.junit.Assert;
import org.junit.Test;

public class TestNumeroEmp2 {
    private Employee employee1 = new Employee("NGUYEN", "Ke An", 1200, 125);
    private Employee employee2 = new Employee("NGUYEN", "Kan E", 1119, 150);

    @Test
    public void testNumeroEmps() {
        Assert.assertEquals(employee1.getNumeroEmp(), 0);
        Assert.assertEquals(employee2.getNumeroEmp(), 1);
    }
}
