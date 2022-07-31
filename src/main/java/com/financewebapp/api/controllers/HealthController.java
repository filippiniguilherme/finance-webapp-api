package com.financewebapp.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class HealthController {
    private static final Logger LOG = LoggerFactory.getLogger(HealthController.class);

    @GetMapping
	public String status() {
		LOG.info("Checking application status");
		return "ok";
	}
}
