package study.kfarm.user.dto;

import lombok.Getter;
import study.kfarm.user.User;

@Getter
public class ResponseUser {

    private String username;

    public ResponseUser(User user) {
        this.username = user.getUsername();
    }
}
