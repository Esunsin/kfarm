package study.kfarm.user.dto;

import lombok.Getter;
import org.springframework.core.SpringVersion;

@Getter
public class RequestUser {
    private String username;
    private String password;
}
