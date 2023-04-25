package orderTests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.order.OrderSteps;

import static org.hamcrest.CoreMatchers.notNullValue;

public class TestGetOrdersList {
    private OrderSteps orderSteps;
    @Before
    public void setUp(){
        orderSteps = new OrderSteps();
    }

    @DisplayName("Проверка получения списка заказов")
    @Description("Тест на успешное получение списка заказов")
    @Test
    public void testGetOrdersList(){
        ValidatableResponse response = orderSteps.getOrdersList();
        response.assertThat()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}
