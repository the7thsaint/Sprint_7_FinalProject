package ru.yandex.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;


public class CourierAssertVoid {
    @Step("Успешное создание курьера с валидными значениями")
    public void createCourier200Ok(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(201)
                .body("ok", is(true));
    }
    @Step("Ошибка при создании двух одинаковых курьеров")
    public void createIdenteficLoginCouriers(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }
    @Step("Создание курьера с невалидными данными/недостаточной информацией")
    public void createCourierWithNotValidData(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step("Удаление курьера с существующим id")
    public void deleteCourierWithExistId(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(200)
                .body("ok", is(true));
    }
    @Step("Удаление курьера с не существующим id")
    public void deleteCourierWithNotExistId(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Курьера с таким id нет."));
    }

    @Step("Успешный логин курьера и получение его id")
    public void successLoginCourierAndTakeId(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(200)
                .body("id", greaterThan(0))
                .extract()
                .path("id");
    }

    @Step("Проверка ответа сервера при попытке логина с невалидными данными")
    public void errorLoginCourierWithNotValidCredintals(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Step("Проверка ответа сервера при попытке логина без логина и пароля")
    public void errorLoginCourierWithoutCreditnals(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }


}
