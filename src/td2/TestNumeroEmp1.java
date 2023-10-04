package td2;
import org.junit.Assert;
import org.junit.Test;

public class TestNumeroEmp1 {
    private Employee employee = new Employee("NGUYEN", "Ke An", 1200, 125);

    @Test
    public void testNumeroEmp1() {
        Assert.assertEquals(employee.getNumeroEmp(), 0);
    }
}
