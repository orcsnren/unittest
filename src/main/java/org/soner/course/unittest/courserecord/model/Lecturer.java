package org.soner.course.unittest.courserecord.model;

import org.soner.course.unittest.courserecord.exceptions.NotActiveSemesterException;

import java.util.HashSet;
import java.util.Set;

public class Lecturer {

    private String id;
    private String name;
    private String surname;
    private String title;
    private Set<LecturerCourseRecord> lecturerCourseRecords = new HashSet<>();
    private Department department;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<LecturerCourseRecord> getLecturerCourseRecords() {
        return lecturerCourseRecords;
    }

    public void setLecturerCourseRecords(Set<LecturerCourseRecord> lecturerCourseRecords) {
        this.lecturerCourseRecords = lecturerCourseRecords;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void addLecturerCourseRecord(LecturerCourseRecord lecturerCourseRecord) throws NotActiveSemesterException {

        if (lecturerCourseRecord.getCourse() == null) {
            throw new IllegalArgumentException("Can't add a null course to lecturer");
        }

        if (!lecturerCourseRecord.getSemester().isActive()) {
            throw new NotActiveSemesterException(lecturerCourseRecord.getSemester().toString());
        }

        lecturerCourseRecord.setLecturer(this);
        this.lecturerCourseRecords.add(lecturerCourseRecord);
    }

    public LecturerCourseRecord lecturerCourseRecord(Course course, Semester semester) {
        return lecturerCourseRecords.stream()
                .filter(
                        lecturerCourseRecord ->
                                lecturerCourseRecord.getCourse().equals(course) && lecturerCourseRecord.getSemester().equals(semester)
                )
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Can't find lecturer course record for course<%s> and semester<%s>", course, semester)));
    }
}
