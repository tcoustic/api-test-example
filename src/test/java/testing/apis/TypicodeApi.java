package testing.apis;

import io.restassured.response.Response;
import testing.models.Post;
import testing.models.ToDo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class TypicodeApi extends BaseApi{

    
    Response apiGet(String path) {
        info("Requesting get from " + path);
        return assertCodeAndExtract(200, get(path));
    }

    Response apiPost(String path, Map<String, String> paramsMap) {
        info("Requesting post to " + path + " with " + paramsMap);
        return assertCodeAndExtract(201, with().body(paramsMap).post(path));
    }

    Response apiPatch(String path, Map<String, String> paramsMap) {
        info("Requesting patch to " + path + " with " + paramsMap);
        return assertCodeAndExtract(200, with().formParams(paramsMap).patch(path));
    }

    Response assertCodeAndExtract(int code, Response response) {
        return response
                .then()
                .assertThat()
                .statusCode(code)
                .extract().response();
    }

    private <T> T getElement(Class<T> element, String path, int id) {
        return apiGet(path + id).as(element);
    }

    private <T> List<T> getList(Class<T> model, String path) {
        return apiGet(path).jsonPath().getList("", model);
    }

    public ToDo getTodo(int id) {
        return getElement(ToDo.class,"/todos/", id);
    }

    public Post getPost(int id) {
        return getElement(Post.class, "/posts/", id);
    }

    public List<ToDo> getTodos() {
        return getList(ToDo.class, "/todos");
    }

    public List<ToDo> getTodosForUser(Integer userId) {
        return getList(ToDo.class, "/user/" + userId + "/todos");
    }

    public List<Post> getPosts() {
        return getList(Post.class, "/posts");
    }

    public List<Post> getPostsForUser(Integer userId) {
        return getList(Post.class, "/user/" + userId + "/posts");
    }

    public Response postPost(int userId, String title, String body) {
        var requestBody = new HashMap<String, String>();
        requestBody.put("title", title);
        requestBody.put("body", body);
        requestBody.put("userId", String.valueOf(userId));
        return apiPost(
                "/posts",
                requestBody
        );
    }

    public ToDo updateToDo(int id, boolean completed) {
        var requestBody = new HashMap<String, String>();
        requestBody.put("completed", String.valueOf(completed));
        return apiPatch("/todos/" + id, requestBody).as(ToDo.class);
    }

    public Post updatePost(int id, HashMap<String, String> requestBody) {
        return apiPatch("/posts/" + id, requestBody).as(Post.class);
    }

}
