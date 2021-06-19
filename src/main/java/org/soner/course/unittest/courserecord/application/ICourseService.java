package org.soner.course.unittest.courserecord.application;

import org.soner.course.unittest.courserecord.model.Course;
import org.soner.course.unittest.courserecord.model.Department;

import java.util.Optional;

public interface ICourseService {

    Optional<Course> findCourse(Course course);

    Optional<Course> findCourse(Department department, String code, String name);
}
