package api.server.service;

import api.server.domain.User;
import api.server.domain.repository.UserInterFace;
import api.server.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserInterFace userInterFace;
    private final BCryptPasswordEncoder passwordEncoder;
    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> getUserList() {
        return userRepository.getUserList();
    }

    public User getUserByName(String name) {
        return userInterFace.findByusername(name);
    }

    public String login(String id, String pw) {
        User result = this.getUserByName(id);
        System.out.println("로그인 검증 " + result.toString());
        if ( passwordEncoder.matches( pw, result.getPassword())) {
            return "로그인 성공";
        } else{
            return "로그인 실패";
        }
    }

    public User findById(Long id) {
        return userRepository.findOne(id);
    }

}
