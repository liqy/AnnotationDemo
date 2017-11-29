package com.awen.annotationdemo.test;

/**
 * 定义一个只接收形状子类的泛型类
 * Created by liqy on 2017/11/29.
 */

@ShapeName("测试类")
public class MyShape <T extends Shape> {

    public T mShape;

    public void showShape(){
        mShape.shapeType();
    }
}
