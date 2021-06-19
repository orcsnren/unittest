package org.soner.course.unittest.courserecord.application;

import org.soner.course.unittest.courserecord.model.Course;
import org.soner.course.unittest.courserecord.model.Lecturer;
import org.soner.course.unittest.courserecord.model.Semester;

import java.util.Optional;

public interface ILecturerService {

    Optional<Lecturer> findLecturer(Course course, Semester semester);
}
