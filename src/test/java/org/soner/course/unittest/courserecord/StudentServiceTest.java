package org.soner.course.unittest.courserecord;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.soner.course.unittest.courserecord.application.ICourseService;
import org.soner.course.unittest.courserecord.application.ILecturerService;
import org.soner.course.unittest.courserecord.application.StudentService;
import org.soner.course.unittest.courserecord.model.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private ICourseService courseService;

    @Mock
    private ILecturerService lecturerService;

    @Mock
    private Lecturer lecturer;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void addCourse() {

        final Course course = new Course("101");
        final Semester semester = new Semester();
        when(courseService.findCourse(any())).thenReturn(Optional.of(course));
        when(lecturer.lecturerCourseRecord(course, semester)).thenReturn(new LecturerCourseRecord(course, semester));
        when(lecturerService.findLecturer(course, semester)).thenReturn(Optional.of(lecturer));
        final Student studentAhmet = new Student("id1", "Ahmet", "Yilmaz");
        when(studentRepository.findById(anyString()))
                .thenReturn(Optional.of(studentAhmet)).
                thenThrow(new IllegalArgumentException("Can't find a student"))
                .thenReturn(Optional.of(studentAhmet));

        studentService.addCourse("id1", course, semester);

        assertThatThrownBy(() -> studentService.findStudent("id1")).isInstanceOf(IllegalArgumentException.class);

        final Optional<Student> studentOptional = studentService.findStudent("id1");

        assertThat(studentOptional).as("Student")
                .isPresent()
                .get()
                .matches(student -> student.isTakeCourse(course))
        ;

        verify(courseService).findCourse(course);
        verify(courseService, times(1)).findCourse(course);
        verify(courseService, atLeast(1)).findCourse(course);
        verify(courseService, atMost(1)).findCourse(course);
        verify(studentRepository, times(3)).findById("id1");
        verify(lecturerService).findLecturer(any(Course.class), any(Semester.class));
        verify(lecturer).lecturerCourseRecord(argThat(argument -> argument.getCode().equals("101")), any(Semester.class));
        verify(lecturer).lecturerCourseRecord(argThat(new MyCourseArgumentMatcher()), any(Semester.class));

    }

    class MyCourseArgumentMatcher implements ArgumentMatcher<Course> {
        @Override
        public boolean matches(Course course) {
            return course.getCode().equals("101");
        }
    }
}
