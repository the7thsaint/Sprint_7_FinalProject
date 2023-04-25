package ru.yandex.courier;

import io.qameta.allure.Step;
import net.datafaker.Faker;

public class CourierGenerateRandomData {
    static Faker faker = new Faker();

    @Step("Создание курьера со случайными данными")
    public CourierInfo createCourierWithRandomData(){
        return new CourierInfo(
                faker.name().name(),
                faker.internet().password(),
                faker.name().firstName()
        );
    }

}
