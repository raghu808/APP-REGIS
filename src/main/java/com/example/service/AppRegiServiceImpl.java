package com.example.service;

import java.net.http.HttpHeaders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;

import com.example.bindings.CitizensApp;
import com.example.entity.CitizenAppEntity;
import com.example.repositary.CitizenAppRepo;

@Service
public class AppRegiServiceImpl implements AppRegiService {

	@Autowired
	private CitizenAppRepo appRepo;

	@Override
	public Integer CreateApplication(CitizensApp app) {
		// make a rest call to ssa-web api with ssn as input

		String endPointUrl = "https://ssa-web-api.herokuapp.com/ssn/{ssn}";
		/*
		 * RestTemplate rt = new RestTemplate();
		 * 
		 * ResponseEntity<String> forEntity = rt.getForEntity(endPointUrl, String.class,
		 * app.getSsn());
		 * 
		 * String stateName = forEntity.getBody();
		 * 
		 */
		WebClient webClient = WebClient.create();

		String stateName = webClient.get()
									.uri(endPointUrl, app.getSsn())
									.retrieve()
									.bodyToMono(String.class)
									.block();

		if ("New Jersey".equals(stateName)) {
			// create Application

			CitizenAppEntity entity = new CitizenAppEntity();

			BeanUtils.copyProperties(app, entity);

			entity.setStateName(stateName);

			CitizenAppEntity save = appRepo.save(entity);

			return save.getAppId();
		}
		return 0;
	}
}
