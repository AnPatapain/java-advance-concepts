package td2;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Main {
    public static void main(String args[]) {
        runTestClass();
    }
    private static void runTestClass() {
        Result result = JUnitCore.runClasses(TestNumeroEmp2.class);
        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
