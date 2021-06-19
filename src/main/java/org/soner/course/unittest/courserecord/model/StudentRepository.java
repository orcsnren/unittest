package org.soner.course.unittest.courserecord.model;

import java.util.Optional;

public interface StudentRepository {

    Optional<Student> findById(String id);

    Student save(Student student);

    void delete(Student student);
}
