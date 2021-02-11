package testing.test;

import org.junit.jupiter.api.Test;
import testing.apis.TypicodeApi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleTest extends BaseTest {

    private static final TypicodeApi typicodeApi = new TypicodeApi();

    @Test
    void simpleTest() {
        info("This should pass");
        assertTrue(true);
    }

    @Test
    void toDoListTest() {
        var list = typicodeApi.getTodos();
        assertEquals(200, list.size());
    }

    @Test
    void toDoTest() {
        var toDo = typicodeApi.getTodo(2);
        assertEquals(2, toDo.getId());
    }
}
