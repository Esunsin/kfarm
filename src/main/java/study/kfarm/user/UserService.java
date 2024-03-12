package study.kfarm.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.kfarm.global.exception.PasswordNotMatchedException;
import study.kfarm.user.dto.UserRequest;
import study.kfarm.user.dto.UserResponse;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpServletRequest request;


    //회원가입
    public UserResponse signup(UserRequest requestUser) {
        if(userRepository.findByUsername(requestUser.getUsername()).isPresent()){
            throw new IllegalArgumentException("중복된 아이디 입니다.");
        }
        String encodedPassword = passwordEncoder.encode(requestUser.getPassword());
        User user = new User(requestUser.getUsername(), encodedPassword);
        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser);
    }

    //로그인
    @Transactional(readOnly = true)
    public UserResponse login(UserRequest requestUser) {

        // 현재 세션에서 이미 로그인된 사용자 확인
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("loggedInUser") != null) {
            throw new DuplicateKeyException("이미 로그인된 사용자입니다.");
        }

        User findUser = userRepository.findByUsername(requestUser.getUsername()).orElseThrow(
                () -> new NullPointerException("없는 회원입니다.")
        );

        if (!passwordEncoder.matches(requestUser.getPassword(), findUser.getPassword())) {
            throw new PasswordNotMatchedException();
        }

        return new UserResponse(findUser);
    }
}
