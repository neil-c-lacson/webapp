package com.luv2code.springboot.thymeleafdemo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
         
        System.out.println("The user " + username + " has logged in.");

		// forward to home page
		
		response.sendRedirect(request.getContextPath() + "/rooms/listRoom");
	}
}
