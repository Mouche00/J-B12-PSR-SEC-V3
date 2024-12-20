package org.yc.keycloak.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.yc.keycloak.exception.AuthenticationException;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AuthenticationException {
        String breederId = (String) request.getSession().getAttribute("breederId");
        if(breederId != null && !breederId.isEmpty() && !breederId.isBlank()) {
            return true;
        } else {
            throw new AuthenticationException("Breeder is not logged");
        }
    }
}
