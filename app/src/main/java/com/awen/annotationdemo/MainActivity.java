package com.awen.annotationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.awen.annotationdemo.model.ResponseObj;
import com.awen.annotationdemo.model.Story;
import com.awen.annotationdemo.model.Test;
import com.awen.annotationdemo.test.Circle;
import com.awen.annotationdemo.test.MyShape;
import com.awen.annotationdemo.test.ShapeName;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        invokeAnnotation();
        invokeReflect();


        MyShape<Circle> circleMyShape = new MyShape<>();
        Circle circle = new Circle();
        circleMyShape.mShape = circle;

        Class c = circleMyShape.getClass();

        ShapeName shapeName = (ShapeName) c.getAnnotation(ShapeName.class);

        Log.d(getLocalClassName(), shapeName.value());

        Method[] methods = c.getDeclaredMethods();

        for (Method method : methods) {
            try {
                //TODO 怎么处理
                method.invoke(circleMyShape);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }


        Test test = new Test();
        test.test();

        String url = "http://v3.wufazhuce.com:8000/api/music/detail/1182?channel=wdj&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android";
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String data = response.body().string();
                Log.d(getLocalClassName(), data);

                ResponseObj<Story> storyResponseObj = new Gson().fromJson(data, ResponseObj.class);

                Log.d(getLocalClassName(), storyResponseObj.toString());


            }
        });

    }

    /**
     * 注解调用
     */
    private void invokeAnnotation() {

        //TODO 动态代理
        UserInterface userInterface = AnnotionProxy.create(UserInterface.class);
        userInterface.getUser("我之存在，因为有你。");

        try {
            Class c1 = Class.forName("com.awen.annotationdemo.User");

            Method method = c1.getDeclaredMethod("getName");//获取单个方法

            //方法上有一个注解 UserMethod
            UserMethod userMethod = method.getAnnotation(UserMethod.class);

            if (userMethod != null) {
                Log.e(TAG, "注解方法：" + userMethod.title() + "\n");
            }

            Method method1 = c1.getDeclaredMethod("print", String.class);//获取单个方法

            Annotation[][] parameterAnnotationsArray = method1.getParameterAnnotations();//拿到参数注解

            if (parameterAnnotationsArray != null) {
                for (int i = 0; i < parameterAnnotationsArray.length; i++) {
                    Annotation[] annotations = parameterAnnotationsArray[i];
                    if (annotations != null && annotations.length != 0) {
                        UserParam userParam = (UserParam) annotations[0];
                        Log.e(TAG, "注解参数2：" + userParam.name() + "," + userParam.phone() + "\n");
                    }
                }
            }
            Method[] methods = c1.getDeclaredMethods();//获取多个方法
            for (Method method2 : methods) {

                UserMethod userMethod1 = method2.getAnnotation(UserMethod.class);//获取注解
                if (userMethod1 != null) {
                    Log.e(TAG, method2.getName() + "的注解方法：" + userMethod1.title() + "\n");
                }

                Annotation[][] temp = method2.getParameterAnnotations();//拿到参数注解

                if (temp != null) {
                    for (int i = 0; i < temp.length; i++) {
                        Annotation[] annotations = temp[i];
                        if (annotations != null && annotations.length != 0) {
                            UserParam userParam = (UserParam) annotations[0];
                            Log.e(TAG, method2.getName() + "的注解参数2：" + userParam.name() + "," + userParam.phone() + "\n");
                        }
                    }
                }
            }
        } catch (Exception e) {

        }

    }

    /**
     * 反射调用
     */
    private void invokeReflect() {
        try {

            StringBuffer sb = new StringBuffer();

            /********************************反射获取类的三种方法start****************/
            //第一种方式：
            Class c = Class.forName("com.awen.annotationdemo.User");

            //第二种方式：
            Class c2 = User.class;

            //第三种方式：
            //java语言中任何一个java对象都有getClass 方法
            User user = new User();
            Class c3 = user.getClass(); //c3是运行时类 (e的运行时类是Employee)
            /********************************反射获取类的三种方法end********************************/

//            c3.getAnnotations() 获取类的注解

            //c.getModifiers:类的权限类型（public或private)   c.getSimpleName():类的名字（User)
            sb.append(Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName() + "{\n");


            Object o = c.newInstance();//对象初始化


            /************************属性变量start****************/
            Field idF = c.getDeclaredField("id");//获取单个属性
//            idF.getAnnotation()
            idF.setAccessible(true);
            idF.set(o, 1000000);//给当前属性id设置值

            Field nameF = c.getDeclaredField("name");
            nameF.setAccessible(true);
            nameF.set(o, "刘德华");//设置属性值

            Field phoneF = c.getDeclaredField("phone");
            phoneF.setAccessible(true);
            phoneF.set(o, "18665859331");//设置属性值

            Field[] fs = c.getDeclaredFields();//获取所有属性变量

            for (Field field : fs) {
                sb.append("\t");//空格
                sb.append(Modifier.toString(field.getModifiers()) + " ");//获得属性的修饰符，例如public，static等等
                sb.append(field.getType().getSimpleName() + " ");//属性的类型的名字
                sb.append(field.getName() + ";\n");//属性的名字+回车
            }
            /************************属性变量end****************/


            /************************类的方法start****************/
            Method print = c.getDeclaredMethod("print", String.class);//获取单个方法
            print.invoke(o, "中国人民解放军");//方法调用


            Method[] md = c.getDeclaredMethods();//获取类的所有方法
            for (Method method : md) {
                sb.append("\t");//空格
                sb.append(Modifier.toString(method.getModifiers()) + " ");//获得属性的修饰符，例如public，static等等
                sb.append(method.getReturnType().getSimpleName() + " ");//属性的类型的名字
                sb.append(method.getName() + "();\n");//属性的名字+回车
            }

            sb.append("}");
            Log.e(TAG, sb.toString());
            /************************类的方法end****************/

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
