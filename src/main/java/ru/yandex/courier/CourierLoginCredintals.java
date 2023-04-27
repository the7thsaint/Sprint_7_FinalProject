package ru.yandex.courier;

public class CourierLoginCredintals {
    private String login;
    private String password;

    public CourierLoginCredintals(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierLoginCredintals from(CourierInfo courierInfo){
        return new CourierLoginCredintals(courierInfo.getLogin(), courierInfo.getPassword());
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
