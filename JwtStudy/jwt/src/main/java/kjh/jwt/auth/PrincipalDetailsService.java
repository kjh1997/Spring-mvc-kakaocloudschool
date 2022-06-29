package kjh.jwt.auth;

import kjh.jwt.model.User;
import kjh.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("login");
        User userEntity = userRepository.findByUsername(username);
        System.out.println("userEntity " + userEntity);
        return new PrincipalDetails(userEntity);
    }
}
