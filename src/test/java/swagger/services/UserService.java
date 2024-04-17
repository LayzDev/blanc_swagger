package swagger.services;

import io.restassured.http.ContentType;
import swagger.models.User;
import swagger.utils.AssertableResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserService {
    public AssertableResponse createUser(User user){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(user)
                .post("/user")
                .then());
    }

    public AssertableResponse createUserWithList(List<User> users){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(users)
                .post("/user/createWithList")
                .then());
    }

    public AssertableResponse getUserInfo(User user){
        return new AssertableResponse(given()
                .get("/user/" + user.getUsername())
                .then());
    }
    public AssertableResponse updateUser(User user, String username){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(user)
                .put("/user/" + username)
                .then());
    }

    public AssertableResponse deleteUser(User user){
        return new AssertableResponse(given()
                .delete("/user/" + user.getUsername())
                .then());
    }

    public AssertableResponse login(User user){
        Map<String, String> loginData = new HashMap<>();
        loginData.put("username", user.getUsername());
        loginData.put("password", user.getPassword());

        return new AssertableResponse(given()
                .queryParams(loginData)
                .get("/user/login")
                .then());
    }

    public AssertableResponse logout() {
        return new AssertableResponse(given()
                .get("/user/logout")
                .then());
    }
}
