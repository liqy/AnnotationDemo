package com.awen.annotationdemo.test;

import android.util.Log;

/**
 * Created by liqy on 2017/11/29.
 */

@ShapeName("圆形类")
public class Circle extends Shape {

    @ShapeName("获取半径")
    public int radio() {
        return 16;
    }

    @Override
    public void shapeType() {
        Log.d(getClass().getSimpleName(),"我是圆形");
    }
}
