package swagger.services;

import io.restassured.http.ContentType;
import swagger.models.Pet;
import swagger.utils.AssertableResponse;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetService {
    public AssertableResponse createPet(Pet pet){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(pet)
                .post("/pet")
                .then());
    }

    public AssertableResponse getPetInfoById(Pet pet){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .get("/pet/" + pet.getId())
                .then());
    }

    public AssertableResponse getPetInfoByStatus(Pet pet){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .queryParam("status", pet.getStatus())
                .get("/pet/findByStatus")
                .then());
    }

    public AssertableResponse updatePet(Pet pet){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(pet)
                .put("/pet")
                .then());
    }

    public AssertableResponse deletePet(Pet pet){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(pet)
                .delete("/pet/" + pet.getId())
                .then());
    }

    public AssertableResponse uploadImage(Pet pet){
        return new AssertableResponse(given().contentType(ContentType.MULTIPART)
                .multiPart("file", new File("src/test/resources/fish.jpg"), "image/jpeg")
                .post("/pet/" + pet.getId() + "/uploadImage")
                .then());
    }
}
