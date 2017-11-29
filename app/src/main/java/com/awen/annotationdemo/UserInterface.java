package com.awen.annotationdemo;

/**
 * 定义用户接口，类似Retrofit里面的接口类
 */

public interface UserInterface {
    @UserMethod(title = "AwenZeng")
    String getUser(@UserParam(name = "刘峰", phone = "110") String a);
}
