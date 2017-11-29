package com.awen.annotationdemo.test;

import android.util.Log;

import java.util.logging.SocketHandler;

/**
 * Created by liqy on 2017/11/29.
 */
@ShapeName("方形类")
public abstract class Rectangle extends Shape{

    @ShapeName("获取宽")
    public int  width(){
        return 16;
    }

    @Override
    public void shapeType() {
        Log.d(getClass().getSimpleName(),"我是方形");
    }
}
