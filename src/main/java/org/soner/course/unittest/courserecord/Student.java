package org.soner.course.unittest.courserecord;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Student {

    private final String id;
    private final String name;
    private final String surname;
    private Set<StudentCourseRecord> studentCourseRecords = new HashSet<>();
    private Department department;

    public Student(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Set<StudentCourseRecord> getStudentCourseRecords() {
        return Collections.unmodifiableSet(studentCourseRecords);
    }

    public void setStudentCourseRecords(Set<StudentCourseRecord> studentCourseRecords) {
        this.studentCourseRecords = studentCourseRecords;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void addCourse(LecturerCourseRecord lecturerCourseRecord) {
        if (lecturerCourseRecord == null) {
            throw new IllegalArgumentException("Cant be added null lecturer course record");
        }
        StudentCourseRecord courseRecord = new StudentCourseRecord(lecturerCourseRecord);
        studentCourseRecords.add(courseRecord);
    }

    public void dropCourse(LecturerCourseRecord lecturerCourseRecord) {

    }


}
