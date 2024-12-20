package org.yc.keycloak.security.config;

import lombok.RequiredArgsConstructor;
import org.yc.keycloak.security.providers.CustomAuthenticationProvider;
import org.yc.keycloak.security.providers.TestAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AuthenticationProvidersConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Bean
    @Profile("!test")
    public AuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(passwordEncoder, userDetailsService);
    }

    @Bean
    @Profile("test")
    public AuthenticationProvider testAuthenticationProvider() {
        return new TestAuthenticationProvider(userDetailsService);
    }
}
