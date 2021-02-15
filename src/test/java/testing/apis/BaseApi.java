package testing.apis;

import io.restassured.RestAssured;
import testing.interfaces.Logging;

import static io.restassured.RestAssured.config;
import static io.restassured.config.EncoderConfig.encoderConfig;

public abstract class BaseApi implements Logging {

    BaseApi() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.config = config()
                        .encoderConfig(encoderConfig()
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false));
    }
}
