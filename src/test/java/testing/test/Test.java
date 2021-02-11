package testing.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test extends BaseTest {

    @org.junit.jupiter.api.Test
    void simpleTest() {
        info("This should pass");
        assertTrue(true);
    }
}
