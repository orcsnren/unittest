package org.soner.course.unittest.courserecord;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        assertSame(soner, sonerTmp);
        assertNotSame(soner, ahmet);
    }
}
