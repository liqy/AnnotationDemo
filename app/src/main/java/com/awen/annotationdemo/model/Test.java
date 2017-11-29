package com.awen.annotationdemo.model;

import android.util.Log;

/**
 * Created by liqy on 2017/11/28.
 */

public class Test {

    public  void  test(){
        ResponseObj<User>  responseObj=new ResponseObj<>();
        responseObj.setData(new User());
        Log.d(getClass().getSimpleName(),responseObj.data.name);

        ResponseObj<Product> productResponseObj=new ResponseObj<>();
        productResponseObj.setData(new Product());
        Log.d(getClass().getSimpleName(),productResponseObj.data.title);


    }

}
