package study.kfarm.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import study.kfarm.global.entity.TimeStamped;
import study.kfarm.post.dto.PostRequest;
import study.kfarm.user.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Post(PostRequest postRequest, User user) {
        this.title = postRequest.getTitle();
        this.content = postRequest.getContent();
        this.user = user;
    }

    public void updateInfo(PostRequest postRequest) {
        title = postRequest.getTitle();
        content = postRequest.getContent();
    }
}
