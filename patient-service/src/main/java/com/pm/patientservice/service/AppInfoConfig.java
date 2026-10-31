package com.pm.patientservice.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "app.info")
@Getter @Setter
public class AppInfoConfig {

    private String environment;
    private String message;
    private String version;
}
