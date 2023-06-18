package com.school.administrative.teacher.model;

import java.util.List;

public class TeacherListResponse {
    private List<TeacherListItem> teachers;

    public TeacherListResponse() {
        this.teachers = List.of();
    }

    public List<TeacherListItem> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherListItem> teachers) {
        this.teachers = teachers;
    }
}