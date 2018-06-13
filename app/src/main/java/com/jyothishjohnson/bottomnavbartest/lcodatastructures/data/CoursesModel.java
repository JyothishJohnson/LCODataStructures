package com.jyothishjohnson.bottomnavbartest.lcodatastructures.data;

/**
 * Created by jyothish on 12/6/18.
 */

public class CoursesModel {

    private String courseName;
    private String courseImgUrl;
    private String courseFees;
    private String courseLessons;
    private String courseUrl;

    public CoursesModel(String courseName, String courseImgUrl,
                        String courseFees, String courseLessons,String courseUrl) {
        this.courseName = courseName;
        this.courseImgUrl = courseImgUrl;
        this.courseFees = courseFees;
        this.courseLessons = courseLessons;
        this.courseUrl = courseUrl;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseImgUrl() {
        return courseImgUrl;
    }

    public void setCourseImgUrl(String courseImgUrl) {
        this.courseImgUrl = courseImgUrl;
    }

    public String getCourseFees() {
        return courseFees;
    }

    public void setCourseFees(String courseFees) {
        this.courseFees = courseFees;
    }

    public String getCourseLessons() {
        return courseLessons;
    }

    public void setCourseLessons(String courseLessons) {
        this.courseLessons = courseLessons;
    }

    public String getCourseUrl() {
        return courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }
}
