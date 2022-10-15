package com.example.bindings;

import java.time.LocalDate;
import lombok.Data;

@Data
public class CitizensApp {
	
	private String fullName;
	
	private String email;
	
	private Long phno;
	
	private String gender;
	
	private LocalDate dob;
	
	private Long ssn;
	
	
	
}
