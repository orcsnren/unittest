package org.soner.course.unittest.courserecord;

import org.soner.course.unittest.courserecord.exceptions.NotActiveSemesterException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Student {

    private final String id;
    private final String name;
    private final String surname;
    private Set<StudentCourseRecord> studentCourseRecords = new HashSet<>();
    private Department department;
    private LocalDate birthDate;

    public Student(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Student(String id, String name, String surname, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public Student(String id, String name, String surname, Set<StudentCourseRecord> studentCourseRecords) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.studentCourseRecords.addAll(studentCourseRecords);
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
        return studentCourseRecords;
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

        if (lecturerCourseRecord == null) {
            throw new IllegalArgumentException("Can't drop course with null lecturer course record");
        }

        final StudentCourseRecord studentCourseRecordWillBeDropped = studentCourseRecords.stream()
                .filter(studentCourseRecord -> studentCourseRecord.getLecturerCourseRecord().equals(lecturerCourseRecord))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no student course record for given lecturer course record"));

        if (!studentCourseRecordWillBeDropped.getLecturerCourseRecord().getSemester().isAddDropAllowed()) {
            throw new NotActiveSemesterException("Add drop period is closed for the semester");
        }

        studentCourseRecords.remove(studentCourseRecordWillBeDropped);
    }

    public boolean isTakeCourse(Course course) {
        return studentCourseRecords.stream()
                .map(StudentCourseRecord::getLecturerCourseRecord)
                .map(LecturerCourseRecord::getCourse)
                .anyMatch(course1 -> course1.equals(course));
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
