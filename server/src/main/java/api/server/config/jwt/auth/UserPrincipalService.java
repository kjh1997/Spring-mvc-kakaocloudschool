package api.server.config.jwt.auth;

import api.server.domain.User;
import api.server.domain.repository.UserInterFace;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserPrincipalService implements UserDetailsService {
    private final UserInterFace userInterFace;

//    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("principal entry : "+ username);
        User user = userInterFace.findByusername(username);
        System.out.println(user.getPassword() + " || " + user.getUsername());
        return new UserPrincipal(user);
    }
}
