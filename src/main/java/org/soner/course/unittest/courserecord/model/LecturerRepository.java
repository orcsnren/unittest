package org.soner.course.unittest.courserecord.model;

import java.util.Optional;

public interface LecturerRepository {

    Optional<Lecturer> findByCourseAndSemester(Course course, Semester semester);
}
