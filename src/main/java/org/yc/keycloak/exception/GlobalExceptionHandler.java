package org.yc.keycloak.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.yc.keycloak.utils.ApiResponse;
import org.yc.keycloak.utils.ResponseUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> handleGeneralException(Exception ex, HttpServletRequest request) {
        return ResponseUtil.error(Arrays.asList(ex.getMessage()), "An unexpected error occurred", 1001, request.getRequestURI());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ApiResponse<Object> handleUsernameNotFoundException(Exception ex, HttpServletRequest request) {
        return ResponseUtil.error(Arrays.asList(ex.getMessage()), "User not found", 500, request.getRequestURI());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse<Object> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        return ResponseUtil.error(Arrays.asList(ex.getMessage()), "Resource not found", 404, request.getRequestURI());
    }

    @ExceptionHandler(ResponseNotFoundException.class)
    public ApiResponse<Object> handleResponseNotFoundException(ResponseNotFoundException ex, HttpServletRequest request) {
        return ResponseUtil.error(Arrays.asList(ex.getMessage()), "Response data not found", 204, request.getRequestURI());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ApiResponse<Object> handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        return ResponseUtil.error(Arrays.asList(ex.getMessage()), "Unauthorized", 401, request.getRequestURI());
    }
}
