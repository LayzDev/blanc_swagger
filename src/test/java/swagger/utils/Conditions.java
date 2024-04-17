package swagger.utils;

import swagger.utils.conditions.MessageCondition;
import swagger.utils.conditions.StatusCodeCondition;

public class Conditions {
    public static MessageCondition hasMessage(String expectedMessage) {
        return new MessageCondition(expectedMessage);
    }

    public static StatusCodeCondition hasStatusCode(Integer expectedStatusCode) {
        return new StatusCodeCondition(expectedStatusCode);
    }

    public static MessageCondition messageContains(String expectedMessage) {
        return new MessageCondition(expectedMessage);
    }
}
