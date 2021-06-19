package org.soner.course.unittest.courserecord;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import org.soner.course.unittest.courserecord.model.Course;
import org.soner.course.unittest.courserecord.model.LecturerCourseRecord;
import org.soner.course.unittest.courserecord.model.Semester;
import org.soner.course.unittest.courserecord.model.Student;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class StudentTestWithAssertJAssertions {


    @Test
    void createStudent() {
        Student student = new Student("id1", "Soner", "Eren");
        assertThat(student.getName()).as("Student's name %s", student.getName())
                .isNotBlank()
                .isEqualToIgnoringCase("Soner")
                .isIn("Soner", "Omer", "Hakan")
                .isNotIn("Ali", "Veli")
                .startsWith("Son")
                .doesNotStartWith("On")
                .endsWith("er")
                .contains("ner")
                .contains(List.of("er", "Son", "oner"))
                .hasSize(5);
    }

    @Test
    void addCourseToStudent() {

        final Student soner = new Student("id1", "Soner", "Eren", LocalDate.of(1994, 1, 1));
        final Student orcun = new Student("id2", "Orcun", "Eren", LocalDate.of(1992, 1, 1));
        final Student canan = new Student("id3", "Canan", "Sahin", LocalDate.of(1995, 1, 1));
        final Student elif = new Student("id4", "Elif", "Oz", LocalDate.of(1991, 1, 1));
        final Student hasan = new Student("id5", "Hasan", "Kartal", LocalDate.of(1990, 1, 1));

        final List<Student> students = List.of(soner, orcun, canan, elif, hasan);

        assertThat(students)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .contains(soner, orcun)
                .contains(soner, Index.atIndex(0))
                .containsOnly(soner, orcun, canan, hasan, elif) // listedeki herkesi sırası bağımsız söylemelisin!
                .containsExactly(soner, orcun, canan, elif, hasan) // listedeki herkesi sıra önemli bir şekilde vermelisin!
                .containsExactlyInAnyOrder(soner, orcun, canan, hasan, elif);

        assertThat(students)
                .filteredOn(student -> student.getBirthDate().until(LocalDate.now(), ChronoUnit.YEARS) >= 25)
                .containsOnly(soner, orcun, canan, elif, hasan);

        assertThat(students)
                .filteredOn("birthDate", in(LocalDate.of(1994, 1, 1)))
                .hasSize(1);

        assertThat(students)
                .extracting(Student::getName)
                .filteredOn(name -> name.contains("e") || name.contains("E"))
                .hasSize(2)
                .containsOnly("Soner", "Elif");

        assertThat(students)
                .filteredOn(student -> student.getSurname().equals("Eren"))
                .extracting(Student::getName, Student::getSurname)
                .containsOnly(tuple("Soner", "Eren"), tuple("Orcun", "Eren"));


        final LecturerCourseRecord lecturerCourseRecord101 = new LecturerCourseRecord(new Course("101"), new Semester());
        final LecturerCourseRecord lecturerCourseRecord103 = new LecturerCourseRecord(new Course("103"), new Semester());
        final LecturerCourseRecord lecturerCourseRecord105 = new LecturerCourseRecord(new Course("105"), new Semester());

        soner.addCourse(lecturerCourseRecord101);
        soner.addCourse(lecturerCourseRecord103);

        canan.addCourse(lecturerCourseRecord101);
        canan.addCourse(lecturerCourseRecord103);
        canan.addCourse(lecturerCourseRecord105);

        assertThat(students)
                .filteredOn("name", in("Soner","Canan"))
                .flatExtracting(student -> student.getStudentCourseRecords()) // collection extract etmek istersek!!!
                .hasSize(5)
                .filteredOn(studentCourseRecord -> studentCourseRecord.getLecturerCourseRecord().getCourse().getCode().equals("101"))
                .hasSize(2);

    }


}
