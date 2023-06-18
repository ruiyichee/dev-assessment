package com.school.administrative.registration.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.school.administrative.registration.model.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Optional<List<Registration>> findByStudentId(long id);

    @Query(value = "SELECT * FROM registration u WHERE u.teacher_id = ?1 and u.registered = True", nativeQuery = true)
    Optional<List<Registration>> findAllRegisteredByTeacherId(long id);

    Optional<Registration> findByTeacherIdAndStudentId(long teacherId, long studentId);
}
