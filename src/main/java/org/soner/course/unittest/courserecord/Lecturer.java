package org.soner.course.unittest.courserecord;

import java.util.HashSet;
import java.util.Set;

public class Lecturer {

    private String id;
    private String name;
    private String surname;
    private String title;
    private Set<LecturerCourseRecord> lecturerCourseRecords = new HashSet<>();
    private Department department;

}
