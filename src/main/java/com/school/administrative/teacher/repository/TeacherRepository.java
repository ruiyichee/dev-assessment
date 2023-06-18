package com.school.administrative.teacher.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.administrative.teacher.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
  Optional<Teacher> findByEmail(String email);
}