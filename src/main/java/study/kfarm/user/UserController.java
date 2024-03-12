package study.kfarm.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.kfarm.user.dto.RequestUser;
import study.kfarm.user.dto.ResponseUser;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseUser signup(@RequestBody RequestUser requestUser){
        return userService.signup(requestUser);
    }

    @PostMapping("/users/login")
    public ResponseUser login(@RequestBody RequestUser requestUser) {
        return userService.login(requestUser);
    }
}
