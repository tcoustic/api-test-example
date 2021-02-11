package testing.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import testing.interfaces.Logging;


public abstract class BaseTest implements Logging {

    @BeforeAll
    static void setUp() {
    }

    @AfterAll
    static void tearDown() {
    }
}
