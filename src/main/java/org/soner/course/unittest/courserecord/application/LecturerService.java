package org.soner.course.unittest.courserecord.application;

import org.soner.course.unittest.courserecord.model.Course;
import org.soner.course.unittest.courserecord.model.Lecturer;
import org.soner.course.unittest.courserecord.model.LecturerRepository;
import org.soner.course.unittest.courserecord.model.Semester;

import java.util.Optional;

public class LecturerService implements ILecturerService {

    private final LecturerRepository lecturerRepository;

    public LecturerService(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public Optional<Lecturer> findLecturer(Course course, Semester semester) {
        if (course == null || semester == null) {
            throw new IllegalArgumentException("Can't find lecturer without course and semester");
        }
        return lecturerRepository.findByCourseAndSemester(course, semester);
    }
}
