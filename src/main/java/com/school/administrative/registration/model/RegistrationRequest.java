package com.school.administrative.registration.model;

import java.util.List;

public class RegistrationRequest {
    private List<String> students;
    private String teacher;

    public List<String> getStudents() {
        return students;
    }

    public String getTeacher() {
        return teacher;
    }

    // public void setStudents(List<String> students) {
    // this.students = students;
    // }

    // public void setTeacher(String teacher) {
    // this.teacher = teacher;
    // }
}
