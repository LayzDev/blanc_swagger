package swagger.utils.conditions;

import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import swagger.models.Response;
import swagger.utils.Condition;

@RequiredArgsConstructor
public class MessageCondition implements Condition {
    private final String expectedMessage;
    @Override
    public void check(ValidatableResponse response) {
        Response tmpResponse = response.extract().as(Response.class);
        Assertions.assertEquals(expectedMessage, tmpResponse.getMessage());
    }

    @Override
    public void contains(ValidatableResponse response) {
        Response tmpResponse  = response.extract().as(Response.class);
        Assertions.assertTrue(tmpResponse.getMessage().contains(expectedMessage));
    }
}
