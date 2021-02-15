package testing.apis;

import io.restassured.RestAssured;
import testing.interfaces.Logging;

public abstract class BaseApi implements Logging {

    BaseApi() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }
}
