package study.kfarm.user.dto;

import lombok.Getter;
import study.kfarm.user.User;

@Getter
public class UserResponse {

    private String username;

    public UserResponse(User user) {
        this.username = user.getUsername();
    }
}
