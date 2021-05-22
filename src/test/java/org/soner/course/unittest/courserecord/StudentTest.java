package org.soner.course.unittest.courserecord;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {


    @Test
    @DisplayName("Student Constructor Test")
    public void studentConstructorTest() {

        Student soner = new Student("1", "Soner", "Eren");
        assertNotNull(soner.getId());
        assertEquals("Soner", soner.getName(), "Student's name");
        assertEquals("Eren", soner.getSurname());
        assertNotEquals("Eren", soner.getId());

        assertTrue(soner.getName().endsWith("er"));
        assertTrue(soner.getSurname().endsWith("en"), () -> "Student Surname ends with en");

        assertFalse(() -> {
            Student orcun = new Student("2", "Orcun", "Eren");
            return orcun.getName().endsWith("a");
        }, () -> "Student's name ends with a");

        final Student salih = new Student("2", "Salih", "Eren");
        assertArrayEquals(new String[]{"Soner", "Salih"}, Stream.of(soner, salih).map(Student::getName).toArray());
        //assertArrayEquals(new String[]{"Soner", "Orcun"}, Stream.of(soner, salih).map(Student::getName).toArray());

        Student sonerTmp = soner;
        assertSame(soner, sonerTmp);//reference compare
        assertNotSame(soner, salih);
    }

    @Test
    @DisplayName("Throws IllegalArgumentException")
    public void throwsExceptionIfLectureCourseRecordIsNull() {
        Student soner = new Student("1", "Soner", "Eren");
        assertThrows(IllegalArgumentException.class, () -> soner.addCourse(null));
        assertThrows(IllegalArgumentException.class, () -> soner.addCourse(null), "Throws IllegalArgumentException");
        assertEquals("Cant be added null lecturer course record", assertThrows(IllegalArgumentException.class, () -> soner.addCourse(null)).getMessage());
    }

    @Test
    @DisplayName("Checks Execution Time")
    public void addCourseToStudentWithTimeLimit() {

        Student soner = new Student("1", "Soner", "Eren");
        assertTimeout(Duration.ofMillis(5), () -> soner.addCourse(new LecturerCourseRecord()));

        String result = assertTimeout(Duration.ofMillis(5), () -> "String generated");
        assertEquals("String generated", result);

        // After the time has elapsed, the process is interrupted.
        assertTimeoutPreemptively(Duration.ofMillis(5), () -> {
//            Thread.sleep(10);
            new Student("2", "soner", "Yilmaz").addCourse(new LecturerCourseRecord());
        });


    }


    @Nested
    @DisplayName("Add Course to Student")
    @Tag("addCourse")
    class AddCourseToStudent {
        @Test
        @DisplayName("Add course to a student less than 10ms")
        void addCourseToStudentWithATimeConstraint() {

            assertTimeout(Duration.ofMillis(10), () -> {
                //nothing will be done and this code run under 10ms
            });

            final String result = assertTimeout(Duration.ofMillis(10), () -> {
                //return a string and this code run under 10ms
                return "some string result";
            });
            assertEquals("some string result", result);

            final Student student = new Student("1", "Soner", "Eren");
            LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(null, null);
            assertTimeout(Duration.ofMillis(10), () -> student.addCourse(lecturerCourseRecord));

            assertTimeoutPreemptively(Duration.ofMillis(10), () -> student.addCourse(lecturerCourseRecord));
        }

        @Nested
        @DisplayName("Add Course to Student(Exceptional)")
        @Tag("exceptional")
        class AddCourseToStudentExceptionScenario {
            @Test
            @DisplayName("Got an exception when add a null lecturer course record to student")
            void throwsExceptionWhenAddToNullCourseToStudent() {

                final Student soner = new Student("1", "Soner", "Eren");
                assertThrows(IllegalArgumentException.class, () -> soner.addCourse(null));
                assertThrows(IllegalArgumentException.class, () -> soner.addCourse(null), "Throws IllegalArgumentException");
                final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> soner.addCourse(null));
                assertEquals("Cant be added null lecturer course record", illegalArgumentException.getMessage());
            }
        }
    }
}
