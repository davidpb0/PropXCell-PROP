import org.junit.runners.Suite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import test.suit.JUnitTestSuite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        JUnitTestSuite.class
})

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(JUnitTestSuite.class);
        System.out.println("Tests ejecutados: " + result.getRunCount() + "\nTests ejecutados correctamente: " + (result.getRunCount() - result.getFailureCount()) + "\nTests fallidos: " + result.getFailureCount());
    }
}