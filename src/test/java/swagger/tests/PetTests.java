package swagger.tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import swagger.models.Pet;
import swagger.services.PetService;
import swagger.utils.Utils;

import java.util.List;

import static swagger.utils.Conditions.*;

@SuppressWarnings("Convert2MethodRef")
public class PetTests {
    private static PetService petService;
    private static Utils utils;

    @BeforeAll
    public static void init() {
        RestAssured.baseURI ="https://petstore.swagger.io/v2/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.defaultParser = Parser.JSON;
        petService = new PetService();
        utils = new Utils();
    }

    @Test
    public void addNewPetTest() {
        String id = petService.createPet(utils.getPet())
                .as("id", String.class);

        Assertions.assertTrue(Long.parseLong(id) != 0);
    }

    @Test
    public void findPetByIdTest() {
        Pet pet = utils.getPet();
        String id = petService.createPet(pet).as("id", String.class);

        pet.setId(Long.parseLong(id));
        Pet actualPet = petService.getPetInfoById(pet).as(Pet.class);

        Assertions.assertEquals(pet.getId(), actualPet.getId());
    }

    @Test
    public void updatePetTest() {
        Pet pet = utils.getPet();
        String id = petService.createPet(pet).as("id", String.class);

        Pet newPetData = utils.getPet();
        newPetData.setId(Long.parseLong(id));

        Pet actualPetData = petService.updatePet(newPetData).as(Pet.class);
        Assertions.assertEquals(newPetData, actualPetData);
    }

    @Test
    public void findPetByStatusTest() {
        Pet pet = utils.getPet();
        String id = petService.createPet(pet).as("id", String.class);
        List<Pet> pets = petService.getPetInfoByStatus(pet).asList(Pet.class);
        List<String> ids = pets.stream().map(element->element.getId().toString()).toList();
        List<String> statuses = pets.stream().map(element->element.getStatus()).toList();

        Assertions.assertTrue(ids.contains(id));
        Assertions.assertTrue(statuses.contains(pet.getStatus()));
    }

    @Test
    public void deletePetTest() {
        Pet pet = utils.getPet();

        String id = petService.createPet(pet).should(hasStatusCode(200))
                .as("id", String.class);
        pet.setId(Long.parseLong(id));

        petService.deletePet(pet)
                .should(hasStatusCode(200))
                .should(hasMessage(pet.getId().toString()));
    }

    @Test
    public void uploadImageTest() {
        Pet pet = utils.getPet();
        String id = petService.createPet(pet).as("id", String.class);
        pet.setId(Long.parseLong(id));
        petService.uploadImage(pet)
                .should(hasStatusCode(200))
                .contains(messageContains("File uploaded to ./fish.jpg"));
    }
}
