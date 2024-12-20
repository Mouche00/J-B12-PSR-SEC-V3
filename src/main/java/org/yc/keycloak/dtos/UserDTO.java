package org.yc.keycloak.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.yc.keycloak.utils.validation.OnCreate;

@Builder
public record UserDTO(
        String id,
        @NotBlank(groups = OnCreate.class)
        String username,
        @NotBlank(groups = OnCreate.class)
        String email,
        @NotBlank(groups = OnCreate.class)
        @Size(min = 8, message = "Password must be at least 8 characters", groups = OnCreate.class)
//        @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter")
//        @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one number")
        String password,
        String role
) {
}
