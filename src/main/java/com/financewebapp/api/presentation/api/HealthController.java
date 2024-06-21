package com.financewebapp.api.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/status")
public class HealthController {

	@GetMapping
	public String status() {
		log.info("Checking application status");
		return "ok";
	}
}