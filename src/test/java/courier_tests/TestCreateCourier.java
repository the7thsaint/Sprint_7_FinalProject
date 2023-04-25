package courier_tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.courier.*;

public class TestCreateCourier {
    int courierId;
    protected  CourierGenerateRandomData courierGenerateRandomData = new CourierGenerateRandomData();
    private CourierInfo courierInfo;
    protected CourierSteps courierSteps;
    private CourierAssertVoid courierAssertVoid;

    @Before
    @Step("Создание данных для курьера")
    public void setUp(){
        courierSteps = new CourierSteps();
        courierInfo = courierGenerateRandomData.createCourierWithRandomData();
        courierAssertVoid = new CourierAssertVoid();
    }

    @After
    @Step("Удаление ранее созданного курьера")
    public void cleanData(){
        courierSteps.courierDelete(courierId);
    }

    @DisplayName("Тест на создание нового курьера с валидными данными")
    @Description("Проверка успешного создания курьера")
    @Test
    public void successCreateCourier(){
        ValidatableResponse responseCourierCreate = courierSteps.createCourier(courierInfo);
        CourierLoginCredintals courierLoginCredintals = CourierLoginCredintals.from(courierInfo);
        courierId = courierSteps.courierAuthorization(courierLoginCredintals).extract().path("id");
        courierAssertVoid.createCourier200Ok(responseCourierCreate);
    }

    @DisplayName("Тест на создание нового курьера без логина")
    @Description("Проверка возникновения ошибки валидации из за пустого логина")
    @Test
    public void failedToCreateCourierWithoutLogin(){
        courierInfo.setLogin(null);
        ValidatableResponse response = courierSteps.createCourier(courierInfo);
        courierAssertVoid.createCourierWithNotValidData(response);
    }

    @DisplayName("Тест на создание нового курьера без пароля")
    @Description("Проверка возникновения ошибки валидации из за пустого пароля")
    @Test
    public void failedToCreateCourierWithoutPassword(){
        courierInfo.setPassword(null);
        ValidatableResponse response = courierSteps.createCourier(courierInfo);
        courierAssertVoid.createCourierWithNotValidData(response);
    }

    @DisplayName("Тест на создание нового курьера без пароля и логина")
    @Description("Проверка возникновения ошибки валидации из за пустого пароля и логина")
    @Test
    public void failedToCreateCourierWithoutPasswordAndLogin(){
        courierInfo.setPassword(null);
        courierInfo.setLogin(null);
        ValidatableResponse response = courierSteps.createCourier(courierInfo);
        courierAssertVoid.createCourierWithNotValidData(response);
    }

    @DisplayName("Тест на создание курьера с существующим логином")
    @Description("Проверка возникновения ошибки валидации из за пустого пароля")
    @Test
    public void failedToCreateCourierWithSameLogin(){
        courierSteps.createCourier(courierInfo);
        ValidatableResponse response = courierSteps.createCourier(courierInfo);
        courierAssertVoid.createIdenteficLoginCouriers(response);
    }




}
