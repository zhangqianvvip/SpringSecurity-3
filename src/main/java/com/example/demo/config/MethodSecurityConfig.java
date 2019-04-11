package com.example.demo.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.stereotype.Component;

@Component
@EnableGlobalMethodSecurity(securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
//	@Override
//	protected MethodSecurityExpressionHandler createExpressionHandler() {
//		// ... create and return custom MethodSecurityExpressionHandler ...
//		return expressionHandler;
//	}
}