package study.kfarm.post.dto;

import lombok.Getter;
import study.kfarm.post.Post;
import study.kfarm.user.User;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private String write;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    public PostResponse(Post findPost) {
        id = findPost.getId();
        title = findPost.getTitle();
        content = findPost.getContent();
        write = findPost.getUser().getUsername();
        createdAt = findPost.getCreatedAt();
        modifiedAt = findPost.getModifiedAt();
    }
}
