package org.yc.keycloak.security.exceptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.yc.keycloak.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final int HTTP_STATUS = HttpStatus.UNAUTHORIZED.value();
    private static final String ERROR_MESSAGE = "Authentication failed";
    private static final String CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HTTP_STATUS);
        response.setContentType(CONTENT_TYPE);

        ResponseUtil.writeErrorResponse(response, Collections.singletonList(authException.getMessage()), ERROR_MESSAGE, HttpStatus.UNAUTHORIZED.value(), request.getRequestURI());
    }
}
