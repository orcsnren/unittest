package org.soner.course.unittest.courserecord;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.soner.course.unittest.courserecord.exceptions.NotActiveSemesterException;
import org.soner.course.unittest.courserecord.model.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Create all add course scenarios dynamically")
public class StudentTestWithDynamicTest {

    private Student student;
    private List<String> lessons;

    @BeforeAll
    public void init() {
        student = new Student("id1", "Soner", "Eren");
        lessons = new ArrayList<>(
                List.of(
                        "101",
                        "103",
                        "111"
                ));
    }


    @TestFactory
    Stream<DynamicNode> AddCourseToStudentWithCourseCodeAndCourseType() {

        return lessons.stream().map(courseCode -> dynamicContainer("Add course<" + courseCode + "> to student",
                Stream.of(Course.CourseType.MANDATORY, Course.CourseType.ELECTIVE).map(courseType -> dynamicTest("Add course<" + courseType + "> to student", () -> {
                    final Course course = Course.newCourse().withCode(courseCode).withCourseType(courseType).course();
                    final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
                    student.addCourse(lecturerCourseRecord);
                    assertTrue(student.isTakeCourse(course));
                }))
        ));
    }

    @TestFactory
    Stream<DynamicTest> addCourseToStudentWithCourseCode() {
        final Iterator<String> courseCodeGenerator = lessons.stream().iterator();
        Function<String, String> displayNameGenerator = courseCode -> "Add course<" + courseCode + "> to student";
        ThrowingConsumer<String> testExecutor = courseCode -> {
            final Course course = Course.newCourse().withCode(courseCode).withCourseType(Course.CourseType.MANDATORY).course();
            final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertTrue(student.isTakeCourse(course));

        };

        return DynamicTest.stream(courseCodeGenerator, displayNameGenerator, testExecutor);
    }


    @Nested
    @DisplayName("Drop Course from Student")
    @Tag("dropCourse")
    class DropCourseFromStudent {

        @TestFactory
        Stream<DynamicTest> dropCourseFromStudentFactory() {
            final Student studentSoner = new Student("id1", "Soner", "Eren");

            return Stream.of(
                    dynamicTest("throws illegal argument exception for null lecturer course record", () ->
                            assertThrows(IllegalArgumentException.class, () -> studentSoner.dropCourse(null))
                    ),
                    dynamicTest("throws illegal argument exception if the student didn't register course before", () -> {
                        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), new Semester());
                        assertThrows(IllegalArgumentException.class, () -> studentSoner.dropCourse(lecturerCourseRecord));
                    }),
                    dynamicTest("throws not active semester exception if the semester is not active", () -> {
                        final Semester notActiveSemester = notActiveSemester();
                        assumeTrue(!notActiveSemester.isActive());
                        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), notActiveSemester);
                        Student studentOrcun = new Student("id1", "Orcun", "Eren", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
                        assertThrows(NotActiveSemesterException.class, () -> studentOrcun.dropCourse(lecturerCourseRecord));
                    }),
                    dynamicTest("throws not active semester exception if the add drop period is closed for the semester", () -> {
                        final Semester addDropPeriodClosedSemester = addDropPeriodClosedSemester();
                        assumeTrue(!addDropPeriodClosedSemester.isAddDropAllowed());
                        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), addDropPeriodClosedSemester);
                        Student studentOrcun = new Student("id1", "Orcun", "Eren", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
                        assertThrows(NotActiveSemesterException.class, () -> studentOrcun.dropCourse(lecturerCourseRecord));
                    }),
                    dynamicTest("drop course from student", () -> {
                        Semester addDropPeriodOpenSemester = addDropPeriodOpenSemester();
                        assumeTrue(addDropPeriodOpenSemester.isAddDropAllowed());
                        LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), addDropPeriodOpenSemester);
                        Student studentOrcun = new Student("id1", "Orcun", "Eren", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
                        assertEquals(1, studentOrcun.getStudentCourseRecords().size());
                        studentOrcun.dropCourse(lecturerCourseRecord);
                        assertTrue(studentOrcun.getStudentCourseRecords().isEmpty());
                    })
            );
        }

        private Semester addDropPeriodOpenSemester() {
            final Semester activeSemester = new Semester();
            final LocalDate semesterDate = LocalDate.of(activeSemester.getYear(), activeSemester.getTerm().getStartMonth(), 1);
            final LocalDate now = LocalDate.now();
            activeSemester.setAddDropPeriodInWeek(Long.valueOf(semesterDate.until(now, ChronoUnit.WEEKS) + 1).intValue());
            return activeSemester;
        }

        private Semester addDropPeriodClosedSemester() {
            final Semester activeSemester = new Semester();
            activeSemester.setAddDropPeriodInWeek(0);
            if (LocalDate.now().getDayOfMonth() == 1) {
                activeSemester.setAddDropPeriodInWeek(-1);
            }
            return activeSemester;
        }

        private Semester notActiveSemester() {
            final Semester activeSemester = new Semester();
            return new Semester(LocalDate.of(activeSemester.getYear() - 1, 1, 1));
        }

    }

}
