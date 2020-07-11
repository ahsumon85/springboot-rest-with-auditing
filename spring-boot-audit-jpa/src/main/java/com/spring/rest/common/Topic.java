package com.spring.rest.common;

public enum Topic {

	HOSPITAL("Hospital"),
	DOCTOR("Doctor"),
	PATIENT("Patient"),
	AMBULANCE("Ambulane"),
	PERSON("Person"),
	MEDICINE("Medicine"),
	DIVISION("Division"),
	DISTRICT("District"),
	THANA("Thana"),
	VILLAGE("Village"),
	PRESCRIPTION("Prescription");
	
	private final String name;
	
	private Topic(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
