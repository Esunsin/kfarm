package study.kfarm.user;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.kfarm.global.jwt.JwtUtil;
import study.kfarm.user.dto.UserRequest;
import study.kfarm.user.dto.UserResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/users/signup")
    public UserResponse signup(@RequestBody UserRequest requestUser){
        return userService.signup(requestUser);
    }

    @PostMapping("/users/login")
    public UserResponse login(@RequestBody UserRequest userRequest, HttpServletResponse response) {
        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(userRequest.getUsername()));
        return userService.login(userRequest);
    }
}
