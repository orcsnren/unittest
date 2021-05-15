package org.soner.course.unittest.courserecord;

import org.junit.jupiter.api.*;
import org.soner.course.unittest.interfaces.TestLifeCycleReporter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentTestWithRepeatedTest implements TestLifeCycleReporter {

    private Student student;

    @BeforeAll
    void init() {
        student = new Student("id1", "Soner", "Eren");
    }

    @DisplayName("Add course to Student")
    @RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME) // default short name
    public void addCourseToStudent(RepetitionInfo repetitionInfo) {
        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("COU" + repetitionInfo.getCurrentRepetition(), "Course " + repetitionInfo.getCurrentRepetition(),
                repetitionInfo.getCurrentRepetition()), new Semester());
        student.addCourse(lecturerCourseRecord);
        assertEquals(repetitionInfo.getCurrentRepetition(), student.getStudentCourseRecords().size());


    }

    @AfterAll
    @DisplayName("Check After All")
    void checkStudentCourses() {
        Assertions.assertAll("Check Lecture record count of student",
                () -> assertEquals(5, student.getStudentCourseRecords().size()),
                () -> assertNotEquals(4, student.getStudentCourseRecords().size()));
    }

}
