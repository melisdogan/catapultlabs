package com.melisdogan.catapult_labs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

@SpringBootApplication
@EnableJms
@EnableScheduling
public class CatapultLabsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatapultLabsApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.cors().and()
					.httpBasic().and()
					.authorizeRequests()
					.antMatchers("/index.html", "/", "/home", "/login", "/token").permitAll()
					.anyRequest().authenticated().and()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
					.csrf()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

		}
	}
	@EnableRedisHttpSession
	public class HttpSessionConfig {

		@Bean
		public LettuceConnectionFactory connectionFactory() {
			return new LettuceConnectionFactory();
		}

		@Bean
		public HttpSessionIdResolver httpSessionIdResolver() {
			return HeaderHttpSessionIdResolver.xAuthToken();
		}

	}
}
