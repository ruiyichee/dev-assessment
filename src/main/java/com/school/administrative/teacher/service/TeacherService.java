package com.school.administrative.teacher.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.school.administrative.registration.service.RegistrationService;
import com.school.administrative.student.model.StudentListResponse;
import com.school.administrative.teacher.model.Teacher;
import com.school.administrative.teacher.model.TeacherListItem;
import com.school.administrative.teacher.model.TeacherListResponse;
import com.school.administrative.teacher.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final RegistrationService registrationService;

    public Teacher findTeacherByEmail(String email) {
        Optional<Teacher> teacher = teacherRepository.findByEmail(email);
        if (teacher.isPresent()) {
            return teacher.get();
        } else {
            return null;
        }
    }

    public Teacher save(String email, String name) {
        return teacherRepository
                .save(new Teacher(email, name));
    }

    public TeacherListResponse getListOfTeachersAndStudents() {
        TeacherListResponse teachersResponse = new TeacherListResponse();
        List<Teacher> teachers = teacherRepository.findAll();

        List<TeacherListItem> teacherListItems = new ArrayList<>();

        for (Teacher teacher : teachers) {
            TeacherListItem teacherListItem = new TeacherListItem(teacher.getEmail());

            List<String> studentEmails = new ArrayList<>();
            // Get students
            List<String> studentEmailsList = registrationService.getListOfStudentEmailsByTeacherId(teacher.getId());
            for (String studentEmail : studentEmailsList) {
                if (!studentEmails.contains(studentEmail)) {
                    studentEmails.add(studentEmail);
                }
            }
            teacherListItem.setStudents(new StudentListResponse(studentEmails));
            teacherListItems.add(teacherListItem);
        }
        teachersResponse.setTeachers(teacherListItems);
        return teachersResponse;
    }
}
