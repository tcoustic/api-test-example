package testing.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testing.apis.TypicodeApi;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest extends BaseTest {

    private static final TypicodeApi typicodeApi = new TypicodeApi();

    static ArrayList<Arguments> UserDataProvider () {
        var stream = new ArrayList<Arguments>();
        for (int i = 1; i < 11; i++) {
            stream.add(Arguments.of(i, 20));
        }
        return stream;
    }

    @Test
    void toDoListTest() {
        var list = typicodeApi.getTodos();
        assertEquals(200, list.size());
    }

    @Test
    void toDoTest() {
        var toDo = typicodeApi.getTodo(2);
        assertAll(
                () -> assertEquals("quis ut nam facilis et officia qui", toDo.getTitle(), "Title"),
                () -> assertFalse(toDo.isCompleted(), "Completed flag"),
                () -> assertEquals(1, toDo.getUserId(), "User ID"),
                () -> assertEquals(2, toDo.getId(), "ID"));
    }

    @ParameterizedTest
    @MethodSource("UserDataProvider")
    void toDoForUserTest(Integer userId, Integer usersTodos) {
        assertEquals(
                usersTodos,
                typicodeApi.getTodosForUser(userId).size()
        );
    }

}
