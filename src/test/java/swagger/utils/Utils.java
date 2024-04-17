package swagger.utils;

import com.github.javafaker.Faker;
import swagger.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Utils {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();
    public User getUser() {
        return User.builder()
                .id( (long) 0)
                .username(faker.name().username())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .phone(faker.phoneNumber().cellPhone())
                .userStatus(0).build();
    }

    public Pet getPet() {
        return Pet.builder()
                .id( (long) 0)
                .category(getCategory())
                .name(faker.funnyName().name())
                .photoUrls(getPhotoUrls())
                .tags(getTags())
                .status(getRandomPetStatus())
                .build();
    }

    public Category getCategory() {
        return Category.builder()
                .id(faker.number().randomNumber())
                .name(faker.animal().name())
                .build();
    }

    public List<String> getPhotoUrls() {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            urls.add(faker.internet().url());
        }
        return urls;
    }

    public List<Tags> getTags() {
        List<Tags> tags = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tags.add(Tags.builder()
                    .id(faker.number().randomNumber())
                    .name(faker.cat().breed())
                    .build());
        }
        return tags;
    }

    public String getRandomPetStatus() {
        return switch (random.nextInt(0, 3)) {
            case 0 -> "available";
            case 1 -> "pending";
            case 2 -> "sold";
            default -> "available";
        };
    }

    public Order getOrder(Pet pet) {
        long petId = pet.getId();
        long id = 0;
        return Order.builder()
                .id(id)
                .petId(petId)
                .quantity(random.nextInt(0, 5))
                .shipDate(LocalDateTime.now().toString())
                .status(getRandomOrderStatus())
                .complete(faker.bool().bool())
                .build();
    }

    public String getRandomOrderStatus() {
        return switch (random.nextInt(0, 11)) {
            case 0 -> "sold";
            case 1 -> "approved";
            case 3 -> "placed";
            case 4 -> "booked";
            case 5 -> "string";
            case 6 -> "in stock";
            case 7 -> "pending";
            case 8 -> "available";
            case 9 -> "delivered";
            case 10 -> "peric";
            default -> "sold";
        };
    }
}
