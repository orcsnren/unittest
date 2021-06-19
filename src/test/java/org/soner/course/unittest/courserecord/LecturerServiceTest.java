package org.soner.course.unittest.courserecord;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.soner.course.unittest.courserecord.application.ILecturerService;
import org.soner.course.unittest.courserecord.application.LecturerService;
import org.soner.course.unittest.courserecord.model.Course;
import org.soner.course.unittest.courserecord.model.Lecturer;
import org.soner.course.unittest.courserecord.model.LecturerRepository;
import org.soner.course.unittest.courserecord.model.Semester;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class LecturerServiceTest {

    @Test
    void findLecturer() {
        final Course course = new Course("1");
        final Semester semester = new Semester();

        final LecturerRepository lecturerRepository = Mockito.mock(LecturerRepository.class);
        final Lecturer lecturer = new Lecturer();
        Mockito.when(lecturerRepository.findByCourseAndSemester(course, semester)).thenReturn(Optional.of(lecturer));

        final ILecturerService lecturerService = new LecturerService(lecturerRepository);
        final Optional<Lecturer> lecturerOpt = lecturerService.findLecturer(course, semester);
        assertThat(lecturerOpt).isPresent().get().isSameAs(lecturer);

        Mockito.verify(lecturerRepository).findByCourseAndSemester(course, semester);
    }
}
