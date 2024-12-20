package org.yc.keycloak.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ResponseUtil {

    public static <T> ApiResponse<T> success(T data, String message, String path) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        response.setErrors(null);
        response.setErrorCode(0); // No error
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(path);
        return response;
    }

    public static <T> ApiResponse<T> error(List<String> errors, String message, int errorCode, String path) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setData(null);
        response.setErrors(errors);
        response.setErrorCode(errorCode);
        response.setTimestamp(System.currentTimeMillis());
        response.setPath(path);
        return response;
    }

    public static <T> ApiResponse<T> error(String error, String message, int errorCode, String path) {
        return error(Arrays.asList(error), message, errorCode, path);
    }

    public static void writeErrorResponse(HttpServletResponse response, List<String> errors, String errorMessage, int statusCode, String requestUri) throws IOException {
        response.setStatus(statusCode);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponse<String> errorResponse = error(errors, errorMessage, statusCode, requestUri);
        String errorResponseJson = new ObjectMapper().writeValueAsString(errorResponse);

        response.getWriter().write(errorResponseJson);
    }
}
