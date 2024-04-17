package swagger.utils;

import io.restassured.response.ValidatableResponse;

public interface Condition {
    void check(ValidatableResponse response);
    void contains(ValidatableResponse response);
}
