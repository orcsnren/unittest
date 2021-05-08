package org.soner.course.unittest.courserecord;

public class LecturerCourseRecord {

    private Course course;
    private int credit;
    private Semester semester;
    private Lecturer lecturer;

    public LecturerCourseRecord() {
    }

    public LecturerCourseRecord(Course course, Semester semester) {
        this.semester = semester;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Semester getSemester() {
        return semester;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
}
