package com.school.administrative.teacher.model;

import com.school.administrative.student.model.StudentListResponse;

public class TeacherListItem {
    private String email;
    private StudentListResponse students;

    public TeacherListItem(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentListResponse getStudents() {
        return students;
    }

    public void setStudents(StudentListResponse students) {
        this.students = students;
    }
}
