package com.school.administrative.registration.model;

import jakarta.persistence.*;

@Table // is a corresponding table that matches that entity in the database
@Entity // for specifies class is an entity and is mapped to a database table.
public class Registration {
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
	private Long id;

	private Long studentId;
	private Long teacherId;

	private boolean registered;
	private String reason;

	public Registration() {

	}

	public Registration(Long teacherId, Long studentId) {
		this.studentId = studentId;
		this.teacherId = teacherId;
		this.registered = true;
	}

	public long getId() {
		return id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public boolean isRegister() {
		return registered;
	}

	public void setRegister(boolean registered) {
		this.registered = registered;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return String.format("Registration[id=%d, studentId='%s', teacherId='%s']", id, studentId, teacherId);
	}
}