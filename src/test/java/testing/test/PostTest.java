package testing.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testing.apis.TypicodeApi;
import testing.models.Post;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostTest {

    private static final TypicodeApi typicodeApi = new TypicodeApi();

    static ArrayList<Arguments> UserDataProvider () {
        var stream = new ArrayList<Arguments>();
        for (int i = 1; i < 11; i++) {
            stream.add(Arguments.of(i, 10));
        }
        return stream;
    }

    @Test
    void postListTest() {
        var list = typicodeApi.getPosts();
        assertEquals(100, list.size());
    }

    @Test
    void postTest() {
        var toDo = typicodeApi.getPost(3);
        assertAll(
                () -> assertEquals("ea molestias quasi exercitationem repellat qui ipsa sit aut", toDo.getTitle(), "Title"),
                () -> assertEquals("et iusto sed quo iure\n" +
                        "voluptatem occaecati omnis eligendi aut ad\n" +
                        "voluptatem doloribus vel accusantium quis pariatur\n" +
                        "molestiae porro eius odio et labore et velit aut", toDo.getBody(), "Body"),
                () -> assertEquals(1, toDo.getUserId(), "User ID"),
                () -> assertEquals(3, toDo.getId(), "ID"));
    }

    @ParameterizedTest
    @MethodSource("UserDataProvider")
    void postsForUserTest(Integer userId, Integer usersPosts) {
        assertEquals(
                usersPosts,
                typicodeApi.getPostsForUser(userId).size()
        );
    }

    @Test
    void postCreationTest() {
        var id = 101;
        var userId =  1;
        var title =  "Test Title";
        var body = "Test Body";
        assertEquals(
                id,
                typicodeApi.postPost(title, body, userId).jsonPath().getInt("id")
        );
    }


}
