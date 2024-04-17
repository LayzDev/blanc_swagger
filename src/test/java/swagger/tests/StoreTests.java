package swagger.tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.parsing.Parser;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import swagger.models.Order;
import swagger.models.Pet;
import swagger.services.PetService;
import swagger.services.StoreService;
import swagger.utils.Utils;

import static swagger.utils.Conditions.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class StoreTests {
    private static StoreService storeService;
    private static PetService petService;
    private static SoftAssertions softAssertions;
    private static Utils utils;

    @BeforeAll
    public static void init() {
        RestAssured.baseURI ="https://petstore.swagger.io/v2/";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.defaultParser = Parser.JSON;
        storeService = new StoreService();
        petService = new PetService();
        softAssertions = new SoftAssertions();
        utils = new Utils();
    }

    @Test
    public void getInventoryTest() {
        storeService.getStoreInventory();
    }

    @Test
    public void placeOrderTest() {
        Pet pet = utils.getPet();
        String id = petService.createPet(pet)
                .should(hasStatusCode(200))
                .as("id", String.class);
        pet.setId(Long.parseLong(id));

        Order expectedOrder = utils.getOrder(pet);
        Order actualOrder = storeService.placeOrder(expectedOrder).as(Order.class);

        softAssertions.assertThat(expectedOrder.getPetId().equals(actualOrder.getPetId()));
        softAssertions.assertThat(expectedOrder.getQuantity().equals(actualOrder.getQuantity()));
        softAssertions.assertThat(expectedOrder.getShipDate().equals(actualOrder.getShipDate()));
        softAssertions.assertThat(expectedOrder.getStatus().equals(actualOrder.getStatus()));
        softAssertions.assertThat(expectedOrder.getComplete().equals(actualOrder.getComplete()));
        softAssertions.assertThat(actualOrder.getId() != 0);
        softAssertions.assertAll();
    }

    @Test
    public void getOrderByIdTest() {
        Pet pet = utils.getPet();
        String id = petService.createPet(pet).as("id", String.class);
        pet.setId(Long.parseLong(id));
        Order order = utils.getOrder(pet);
        String orderId = storeService.placeOrder(order).as("id", String.class);
        order.setId(Long.parseLong(orderId));

        String actualOrderId = storeService.getOrderById(order).as("id", String.class);

        Assertions.assertEquals(orderId, actualOrderId);
    }

    @Test
    public void deleteOrderByIdTest() {
        Pet pet = utils.getPet();
        String id = petService.createPet(pet).as("id", String.class);
        pet.setId(Long.parseLong(id));
        Order order = utils.getOrder(pet);
        String orderId = storeService.placeOrder(order).as("id", String.class);
        order.setId(Long.parseLong(orderId));

        storeService.deleteOrderById(order)
                .should(hasStatusCode(200))
                .should(hasMessage(orderId));
    }

}
