package org.soner.course.unittest.courserecord;

public class Course {

    private String code;
    private String name;
    private String description;
    private CourseType courseType;
    private int credit;
    private Department department;


    public enum CourseType {
        MANDATORY,
        ELECTIVE
    }

}