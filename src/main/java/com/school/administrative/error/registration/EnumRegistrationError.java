package com.school.administrative.error.registration;

public enum EnumRegistrationError {
    REGISTRATION_NOT_EXISTS("Registration does not exist."),
    REGISTRATION_EXISTS("Registration exists."),
    REGISTRATION_DEREGISTERED("Registration is already deregistered.");

    String value;

    EnumRegistrationError() {
    }

    EnumRegistrationError(String string) {
        this.value = string;
    }

    public String get() {
        return value;
    }
}
