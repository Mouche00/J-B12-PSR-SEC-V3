package org.yc.keycloak.services.implementations;

import lombok.RequiredArgsConstructor;
import org.yc.keycloak.dtos.UserDTO;
import org.yc.keycloak.models.User;
import org.yc.keycloak.repositories.UserRepository;
import org.yc.keycloak.services.UserService;
import org.yc.keycloak.utils.mappers.dtos.UserMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO register(UserDTO user) {
        User userToBeSaved = userMapper.toUser(user);
        userToBeSaved.setPassword(passwordEncoder.encode(userToBeSaved.getPassword()));
        return userMapper.toDTO(
                userRepository.save(userToBeSaved));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserDTO updateRole(String id, UserDTO user) {
        User userToBeUpdated = userRepository.findById(UUID.fromString(id)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userMapper.updateUserFromDto(user, userToBeUpdated);
        return userMapper.toDTO(
                userRepository.save(userToBeUpdated));
    }


}