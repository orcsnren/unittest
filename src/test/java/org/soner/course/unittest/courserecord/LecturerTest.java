package org.soner.course.unittest.courserecord;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.soner.course.unittest.courserecord.exceptions.NotActiveSemesterException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LecturerTest {


    private Lecturer lecturer;

    @BeforeEach
    public void setUp() {
        lecturer = new Lecturer();
    }

    @Test
    @DisplayName("When a lecturer course record is added to lecturer then lecturer course record size increase")
    void whenACourseIsAddedToLecturerThenLecturerCourseSizeIncrease() {
        assertEquals(0, lecturer.getLecturerCourseRecords().size());
        lecturer.getLecturerCourseRecords().add(getLecturerCourseRecord());
        assertEquals(1, lecturer.getLecturerCourseRecords().size());
    }

    private LecturerCourseRecord getLecturerCourseRecord() {
        return new LecturerCourseRecord(new Course(), new Semester());
    }

    @Test
    @DisplayName("Lecturer course record has lecturer when added to lecturer")
    public void lecturerCourseRecordHasLecturerWhenAddedToLecturer() {
        final LecturerCourseRecord lecturerCourseRecord = getLecturerCourseRecord();
        lecturer.addLecturerCourseRecord(lecturerCourseRecord);
        assertSame(this.lecturer, lecturerCourseRecord.getLecturer());

    }

    @Test
    @DisplayName("Throws illegal argument exception when a null course is added to lecturer")
    void throwsIllegalArgumentExceptionWhenANullCourseIsAddedToLecturer() {
        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(null, new Semester());
        final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> lecturer.addLecturerCourseRecord(lecturerCourseRecord));
        assertEquals("Can't add a null course to lecturer", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Throws not active semester exception when a course is added for not active semester")
    void throwsNotActiveSemesterExceptionWhenACourseIsAddedForNotActiveSemesterToLecturer() {

        final Semester activeSemester = new Semester();
        final LocalDate lastYear = LocalDate.of(activeSemester.getYear() - 1, 1, 1);
        final Semester notActiveSemester = new Semester(lastYear);

        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course(), notActiveSemester);
        final NotActiveSemesterException notActiveSemesterException = assertThrows(NotActiveSemesterException.class, () -> lecturer.addLecturerCourseRecord(lecturerCourseRecord));
        assertEquals(notActiveSemester.toString(), notActiveSemesterException.getMessage());
    }

}