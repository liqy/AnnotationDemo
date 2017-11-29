package com.awen.annotationdemo.model;

/**
 * 定义一个泛型类
 */

//http://v3.wufazhuce.com:8000/api/music/detail/1182?channel=wdj&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android

public class ResponseObj<T> {
    public String msg;
    public int res;
    public T data;//泛型

    @Override
    public String toString() {
        return "ResponseObj{" +
                "msg='" + msg + '\'' +
                ", res=" + res +
                ", data=" + data +
                '}';
    }

    public void setData(T data) {
        this.data = data;
    }


}
