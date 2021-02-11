package testing.apis;

import io.restassured.RestAssured;

public abstract class BaseApi {

    BaseApi() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }
}
