package org.soner.course.unittest.courserecord;

import org.junit.jupiter.api.DisplayName;
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

        final Student ahmet = new Student("2", "Ahmet", "Yilmaz");
        assertArrayEquals(new String[]{"Soner", "Ahmet"}, Stream.of(soner, ahmet).map(Student::getName).toArray());
        //assertArrayEquals(new String[]{"Soner", "Orcun"}, Stream.of(soner, ahmet).map(Student::getName).toArray());

        Student sonerTmp = soner;
        assertSame(soner, sonerTmp);//reference compare
        assertNotSame(soner, ahmet);
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
            new Student("2", "Ahmet", "Yilmaz").addCourse(new LecturerCourseRecord());
        });


    }
}
