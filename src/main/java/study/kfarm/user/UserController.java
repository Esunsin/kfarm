package study.kfarm.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.kfarm.user.dto.UserRequest;
import study.kfarm.user.dto.UserResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public UserResponse signup(@RequestBody UserRequest requestUser){
        return userService.signup(requestUser);
    }

    @PostMapping("/users/login")
    public UserResponse login(@RequestBody UserRequest requestUser) {
        return userService.login(requestUser);
    }
}
