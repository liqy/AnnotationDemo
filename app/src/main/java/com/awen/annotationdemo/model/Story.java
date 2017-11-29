package com.awen.annotationdemo.model;

/**
 * Created by liqy on 2017/11/28.
 */

public class Story {
    public  String id;
    public  String title;
    public  String cover;
    public  String story_title;
    public  String story;

    @Override
    public String toString() {
        return "Story{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", story_title='" + story_title + '\'' +
                ", story='" + story + '\'' +
                '}';
    }
}
