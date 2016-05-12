package com.test.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * This class extends {@link SimpleUrlAuthenticationFailureHandler} and is used
 * to handle unsuccessful login. This redirects the user to the login page.
 * 
 * @author mindfire
 * @version 1.0
 * @since 10/03/2016
 */
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
			String url = "/login?error=badUser";
			getRedirectStrategy().sendRedirect(request, response, url);
		} else if (exception.getClass().isAssignableFrom(DisabledException.class)) {
			String url = "/login?error=disabled";
			getRedirectStrategy().sendRedirect(request, response, url);
		}
	}
}
