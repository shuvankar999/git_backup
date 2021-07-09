package com.tip.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tip.validatesession.model.SessionDetails;
import com.tip.validatesession.service.ValidateSessionService;

@Component
public class ValidateRequestFilter extends OncePerRequestFilter {

	@Autowired
	ValidateSessionService validateSessionService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,	HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if(response.getContentType() == null)
			response.setContentType(MediaType.APPLICATION_JSON + ";charset=utf-16");
		SessionDetails sessionDetails = new SessionDetails();
		sessionDetails.setSessionId(request.getHeader("sessionId"));
		sessionDetails.setSsoId(request.getHeader("ssoId"));
		sessionDetails.setAppCd(request.getHeader("appCd"));

		String sessionValidateResponse = validateSessionService.validateSessionDetails(sessionDetails);
		response.setHeader("session", sessionValidateResponse);
		if (("VALID_SESSION").equalsIgnoreCase(sessionValidateResponse)) {
			filterChain.doFilter(request, response);
		}
		else{
			response.setStatus(400);
		}		 
	}
}