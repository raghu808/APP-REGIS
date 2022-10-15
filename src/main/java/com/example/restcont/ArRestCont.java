package com.example.restcont; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bindings.CitizensApp;
import com.example.service.AppRegiService;

@RestController
public class ArRestCont {

	@Autowired
	private AppRegiService service;

	@PostMapping("/app")
	public ResponseEntity<String> createCitizenApp(@RequestBody CitizensApp app) {

		Integer appId = service.CreateApplication(app);

		if (appId > 0) {
			return new ResponseEntity<>("APPLICATION CREATED WITH APP ID : " + appId, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("INVALID SSN ", HttpStatus.BAD_REQUEST);

		}
	}

}
