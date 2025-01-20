package org.yc.keycloak.conf;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.yc.keycloak.security.JwtConverter;
import org.yc.keycloak.utils.enums.Role;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String[] PUBLIC_URLS = {"/api/users/login", "/api/users/register"};
    private static final String[] USER_URLS = {"/api/breeders/**", "/api/pigeons/**"};
    private static final String[] ORGANIZER_URLS = {"/api/rankings/**", "/api/races/**", "api/competitions/**"};
    private static final String[] ADMIN_URLS = {"/api/users/update-role/**"};

    private final AuthenticationProvider customAuthenticationProvider;
    private final AuthenticationEntryPoint customAuthenticationEntryPoint;
    private final AccessDeniedHandler customAccessDeniedHandler;
    private final JwtConverter jwtConverter;

    private void configureAuthorization(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(PUBLIC_URLS).permitAll()
                .requestMatchers(USER_URLS).hasRole(Role.USER.name())
                .requestMatchers(ORGANIZER_URLS).hasRole(Role.ORGANIZER.name())
                .requestMatchers(ADMIN_URLS).hasRole(Role.ADMIN.name())
                .anyRequest().authenticated()
                );
    }

    private void configureAuthentication(HttpSecurity http) throws Exception {
        http.httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(customAuthenticationEntryPoint));
    }

    private void configureExceptionHandling(HttpSecurity http) throws Exception {
        http.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(customAccessDeniedHandler));
    }

    private void configureSessionManagement(HttpSecurity http) throws Exception {
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
    }

    private void configureExploitProtections(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        configureAuthorization(http);
        configureAuthentication(http);
        configureSessionManagement(http);
        configureExploitProtections(http);
        configureExceptionHandling(http);
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(customAuthenticationProvider);
    }

    @Bean
    public ApplicationListener<AuthenticationSuccessEvent> authenticationSuccessListener() {
        return event -> {
            String username = event.getAuthentication().getName();
            log.info("Authentication successful for user: {}", username);
        };
    }
}
