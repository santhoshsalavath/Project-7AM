package com.usa.fedral.gov.ssa.util;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("app")
@Data
public class Constant {

	public Map<String,String> properties;
	/*public String successInsertion;
	public String failureInsertion;
	public String destFile;*/
}
