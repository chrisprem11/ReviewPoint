package com.test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationSuccessHandler successHandler;

	@Autowired
	private CustomAuthenticationFailureHandler failureHandler;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().accessDeniedPage("/error").and().authorizeRequests().antMatchers("/user/**")
				.hasAnyAuthority("ADMIN", "USER").and().authorizeRequests().antMatchers("/data/**")
				.hasAnyAuthority("ADMIN").and().authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN")
				.anyRequest().permitAll().and().formLogin().loginPage("/login").successHandler(successHandler)
				.failureHandler(failureHandler).usernameParameter("email").permitAll().and().logout()
				.logoutUrl("/logout").deleteCookies("remember-me").logoutSuccessUrl("/login?logout").permitAll().and()
				.rememberMe().and().csrf().disable();

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
