package ba.bac.server.service;

import ba.bac.server.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);
    UserDto getUser(String email);
    UserDto getUserByUserId(String userId);
    UserDto updateUser(String userId, UserDto user);
    boolean updateRegisterStep(String userId, int step);
    void deleteUser(String userId);
    boolean verifyEmailToken(String token);
    boolean requestPasswordReset(String email);
    boolean resetPassword(String token, String password);
}
