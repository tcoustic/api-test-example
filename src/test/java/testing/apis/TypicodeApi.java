package testing.apis;

import io.restassured.response.Response;
import testing.models.ToDo;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class TypicodeApi extends BaseApi{

    
    Response apiGet(String path) {
        return assertCodeAndExtract(200, get(path));
    }
    
    Response apiGet(String path, Map<String, String> paramsMap) {
        return assertCodeAndExtract(200, with().params(paramsMap).get(path));
    }

    Response apiPost(String path) {
        return assertCodeAndExtract(200, post(path));
    }

    Response apiPost(String path, Map<String, String> paramsMap) {
        return assertCodeAndExtract(200, with().params(paramsMap).post(path));
    }

    Response assertCodeAndExtract(int code, Response response) {
        return response
                .then()
                .assertThat()
                .statusCode(code)
                .extract().response();
    }

    private <T> List<T> getList(Class<T> model, String path) {
        return apiGet(path).jsonPath().getList("", model);
    }

    public List<ToDo> getTodos() {
        return getList(ToDo.class, "/todos");
    }

    public ToDo getTodo(int id) {
        return apiGet("/todos/" + id).as(ToDo.class);
    }



}
