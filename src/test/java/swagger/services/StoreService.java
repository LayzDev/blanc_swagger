package swagger.services;

import io.restassured.http.ContentType;
import swagger.models.Inventory;
import swagger.models.Order;
import swagger.models.Pet;
import swagger.utils.AssertableResponse;

import static io.restassured.RestAssured.given;

public class StoreService {
    public AssertableResponse getStoreInventory(){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .get("/store/inventory")
                .then());
    }

    public AssertableResponse placeOrder(Order order){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(order)
                .post("/store/order")
                .then());
    }

    public AssertableResponse getOrderById(Order order){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .get("/store/order/" + order.getId())
                .then());
    }

    public AssertableResponse deleteOrderById(Order order){
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .delete("/store/order/" + order.getId())
                .then());
    }
}
