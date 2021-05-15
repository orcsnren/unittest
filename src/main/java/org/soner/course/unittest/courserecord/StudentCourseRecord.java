package org.soner.course.unittest.courserecord;

public class StudentCourseRecord {
    private final LecturerCourseRecord lecturerCourseRecord;
    private Grade grade;
    private CourseReview courseReview;
    private Student student;


    public StudentCourseRecord(LecturerCourseRecord lecturerCourseRecord) {
        this.lecturerCourseRecord = lecturerCourseRecord;
    }

    public enum Grade {
        A1, A2, B1, B2, B3, C, D, E, F
    }

    public LecturerCourseRecord getLecturerCourseRecord() {
        return lecturerCourseRecord;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public CourseReview getCourseReview() {
        return courseReview;
    }

    public void setCourseReview(CourseReview courseReview) {
        this.courseReview = courseReview;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
