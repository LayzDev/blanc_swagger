package swagger.tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import swagger.models.Response;
import swagger.models.User;
import swagger.services.UserService;
import swagger.utils.Utils;

import java.util.*;

import static swagger.utils.Conditions.*;

public class UserTests {
    private static UserService userService;
    private static Utils utils;

    @BeforeAll
    public static void init() {
        RestAssured.baseURI ="https://petstore.swagger.io/v2/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.defaultParser = Parser.JSON;
        userService = new UserService();
        utils = new Utils();
    }

    @Test
    public void creatUserTest() {
        User user = utils.getUser();
        String message = userService.createUser(user)
                .should(hasStatusCode(200))
                .as(Response.class).getMessage();

        Assertions.assertNotNull(message);
    }


    @Test
    public void getUserByNameTest() {
        User user = utils.getUser();
        String expectedUsername = user.getUsername();

        userService.createUser(user);
        String actualUsername = userService.getUserInfo(user).as(User.class).getUsername();

        Assertions.assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void updateUserTest() {
        User originUserData = utils.getUser();
        User expectedNewUserData = utils.getUser();

        String id = userService.createUser(originUserData).as(Response.class).getMessage();
        userService.updateUser(expectedNewUserData, originUserData.getUsername());
        expectedNewUserData.setId(Long.parseLong(id));

        User actualNewUserData = userService.getUserInfo(expectedNewUserData).as(User.class);
        Assertions.assertEquals(expectedNewUserData, actualNewUserData);
    }

    @Test
    public void deleteUserTest() {
        User user = utils.getUser();

        userService.createUser(user).should(hasStatusCode(200));
        userService.deleteUser(user).should(hasStatusCode(200));
    }

    @Test
    public void userLoginTest() {
        User user = utils.getUser();

        userService.createUser(user).should(hasStatusCode(200));
        userService.login(user)
                .should(hasStatusCode(200))
                .contains(messageContains("logged in user session"));

        userService.logout();
    }

    @Test
    public void userLogoutTest() {
        User user = utils.getUser();

        userService.login(user);
        userService.logout().should(hasMessage("ok"));
    }

    @Test
    public void creatUsersFromListTest() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            users.add(utils.getUser());
        }

        userService.createUserWithList(users)
                .should(hasStatusCode(200))
                .should(hasMessage("ok"));
    }
}
