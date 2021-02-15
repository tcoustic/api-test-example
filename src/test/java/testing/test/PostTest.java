package testing.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testing.apis.TypicodeApi;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;
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
        var post = typicodeApi.getPost(3);
        assertAll(
                () -> assertEquals("ea molestias quasi exercitationem repellat qui ipsa sit aut", post.getTitle(), "Title"),
                () -> assertEquals("et iusto sed quo iure\n" +
                        "voluptatem occaecati omnis eligendi aut ad\n" +
                        "voluptatem doloribus vel accusantium quis pariatur\n" +
                        "molestiae porro eius odio et labore et velit aut", post.getBody(), "Body"),
                () -> assertEquals(1, post.getUserId(), "User ID"),
                () -> assertEquals(3, post.getId(), "ID"));
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
                typicodeApi.postPost(userId, title, body).jsonPath().getInt("id")
        );
    }

    @Test
    void postUpdateTest() {

        var id = 2;
        var title =  "Changed Title";
        var body = "Changed Body";
        var requestBody = new HashMap<String, String>();
        requestBody.put("title", title);
        requestBody.put("body", body);
        var updatedPost =
                typicodeApi.updatePost(id, requestBody);
        assertAll(
                () -> assertEquals(title, updatedPost.getTitle(), "Title"),
                () -> assertEquals(body, updatedPost.getBody(), "Body"),
                () -> assertEquals(1, updatedPost.getUserId(), "User ID"),
                () -> assertEquals(2, updatedPost.getId(), "ID"));

    }

}
