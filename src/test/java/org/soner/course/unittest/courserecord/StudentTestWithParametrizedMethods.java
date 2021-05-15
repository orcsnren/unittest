package org.soner.course.unittest.courserecord;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentTestWithParametrizedMethods {

    private Student student;

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class ValueSourceDemo {

        private int studentCourseSize = 0;

        @BeforeAll
        void setUp() {
            student = new Student("id1", "Soner", "Eren");
        }

        @ParameterizedTest
        @ValueSource(strings = {"101", "103", "105"})
        void addCourseToStudent(String courseCode) {
            final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course(courseCode), new Semester());
            student.addCourse(lecturerCourseRecord);
            studentCourseSize++;
            Assertions.assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
        }
    }


    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class EnumSourceDemo {

        @BeforeAll
        void setUp() {
            student = new Student("id1", "Orcun", "Eren");
        }

        @ParameterizedTest
        @EnumSource(Course.CourseType.class)
        public void addCourseToStudent(Course.CourseType courseType) {
            final Course course = Course.newCourse().withCode(String.valueOf(new Random().nextInt(200))).withCourseType(courseType).course();

            final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }
    }
}
