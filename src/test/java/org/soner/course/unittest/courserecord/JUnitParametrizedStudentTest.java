package org.soner.course.unittest.courserecord;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitParametrizedStudentTest {

    private Student student;

    @BeforeEach
    void init(TestInfo info) {
        if (info.getTags().contains("create"))
            student = new Student("id1", "Soner", "Eren");
        else if (info.getTags().contains("addCourse"))
            student = new Student("id1", "Orcun", "Eren");

    }

    @Test
    @DisplayName("Create Student")
    @Tag("create")
    public void createStudent(TestInfo testInfo) {
        System.out.println(testInfo.toString());
        assertEquals("Soner", student.getName());

    }

    @Test
    @DisplayName("Add course to Student")
    @Tag("addCourse")
    public void addCourseToStudent(TestReporter testReporter) {
        testReporter.publishEntry("startTime", LocalDateTime.now().toString());
        assertEquals("Orcun", student.getName());
        testReporter.publishEntry("endTie", LocalDateTime.now().toString());


    }
}
