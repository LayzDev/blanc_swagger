package swagger.utils.conditions;

import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import swagger.utils.Condition;

@RequiredArgsConstructor
public class StatusCodeCondition implements Condition {
    private final Integer statusCode;
    @Override
    public void check(ValidatableResponse response) {
        int actualStatusCode = response.extract().statusCode();
        Assertions.assertEquals(statusCode, actualStatusCode);
    }

    @Override
    public void contains(ValidatableResponse response) {
    }
}
