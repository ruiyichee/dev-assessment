package com.school.administrative.student.model;

import jakarta.persistence.*;

@Table // is a corresponding table that matches that entity in the database
@Entity // for specifies class is an entity and is mapped to a database table.
public class Student {
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
	private Long id;

	private String email;
	private String name;

	public Student() {

	}

	public Student(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Student[id=%d, email='%s', name='%s']", id, email, name);
	}
}