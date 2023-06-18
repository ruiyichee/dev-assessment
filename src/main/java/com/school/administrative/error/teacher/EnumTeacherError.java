package com.school.administrative.error.teacher;

public enum EnumTeacherError {
    TEACHER_NOT_EXISTS("Teacher with this email does not exist."),
    TEACHER_EXISTS("Teacher with this email exists.");

    String value;

    EnumTeacherError() {
    }

    EnumTeacherError(String string) {
        this.value = string;
    }

    public String get() {
        return value;
    }
}
