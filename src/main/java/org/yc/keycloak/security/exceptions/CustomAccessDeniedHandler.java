package org.yc.keycloak.security.exceptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.yc.keycloak.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private static final int HTTP_STATUS = HttpStatus.FORBIDDEN.value();
    private static final String ERROR_MESSAGE = "Forbidden";
    private static final String CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HTTP_STATUS);
        response.setContentType(CONTENT_TYPE);

        ResponseUtil.writeErrorResponse(response, Collections.singletonList(accessDeniedException.getMessage()), ERROR_MESSAGE, HttpStatus.UNAUTHORIZED.value(), request.getRequestURI());
    }
}
