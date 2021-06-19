package org.soner.course.unittest.courserecord;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.soner.course.unittest.courserecord.model.LecturerCourseRecord;
import org.soner.course.unittest.courserecord.model.Student;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Tag("student")
@DisplayName("Student Test With Nested Tests")
public class StudentNestedTestWithAssertJAssertions {

//    @RegisterExtension
//    static TestLoggerExtension testLoggerExtension = new TestLoggerExtension();

    @Nested
    @DisplayName("Create Student")
    @Tag("createStudent")
    class CreateStudent {

        @Test
        @DisplayName("Test every student must have an id, name and surname")
        void shouldCreateStudentWithIdNameAndSurname() {

            final Student soner = new Student("2", "Soner", "Eren");
            Student orcun = new Student("1", "Orcun", "Eren");
            Student student = orcun;

            assertThat(orcun).as("Orcun")
                    .isSameAs(student)
                    .isNotSameAs(soner)
                    .extracting(Student::getName)
                    .asString()
                    .isEqualTo("Orcun")
                    .isNotEqualTo("orcunt")
                    .startsWith("O")
            ;

            assertThat(new Student("id1", "Orcun", "Erener").getName()).as("Orcun")
                    .doesNotEndWith("M")
            ;

            assertThat(List.of(orcun, soner))
                    .extracting(Student::getName)
                    .containsOnly("Orcun", "Soner")
            ;
        }

        @Test
        @DisplayName("Test every student must have an id, name and surname with grouped assertions")
        void shouldCreateStudentWithIdNameAndSurnameWithGroupedAssertions() {

            // In a grouped assertion all assertions are executed,
            Student orcun = new Student("1", "Orcun", "Eren");

            SoftAssertions softAssertions = new SoftAssertions();
            softAssertions.assertThat(orcun.getName()).as("orcun's name").isEqualTo("Orcun");
            softAssertions.assertThat(orcun.getName()).as("orcunt's name").isNotEqualTo("orcunt");
            softAssertions.assertAll();

            // and any failures will be reported together.
            assertAll("Student's name character check",
                    () -> assertTrue(orcun.getName().startsWith("O")),
                    () -> assertTrue(orcun.getName().startsWith("O"), () -> "Student's name " + "starts with Or"),
                    () -> assertFalse(() -> {
                        Student naz = new Student("id1", "Naz", "Ayhan");
                        return naz.getName().endsWith("M");
                    }, () -> "Student's name " + "ends with M")
            );

            assertAll(() -> {
                        final Student soner = new Student("2", "Soner", "Eren");

                        assertArrayEquals(new String[]{"Orcun", "Soner"}, Stream.of(orcun, soner).map(Student::getName).toArray());
                    },
                    () -> {
                        Student student = orcun;
                        final Student soner = new Student("2", "Soner", "Eren");

                        assertSame(orcun, student); // orcun == student
                        assertNotSame(student, soner);
                    });
        }
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

                assertThatIllegalArgumentException().as("Throws IllegalArgumentException").isThrownBy(() -> soner.addCourse(null));

                final Throwable throwable = catchThrowable(() -> soner.addCourse(null));
                assertThat(throwable)
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("Cant be added null lecturer course record")
                ;
            }
        }
    }

}
