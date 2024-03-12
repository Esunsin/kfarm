package study.kfarm.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.kfarm.post.dto.PostRequest;
import study.kfarm.post.dto.PostResponse;
import study.kfarm.user.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public PostResponse create(User user, PostRequest postRequest) {
        Post post = new Post(postRequest,user);
        Post findPost = postRepository.save(post);
        return new PostResponse(findPost);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> showAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public PostResponse showOne(Long id){
        Post post = postRepository.findById(id).orElseThrow(
                () ->new IllegalArgumentException("포스트가 없습니다."));
        return new PostResponse(post);
    }

    public PostResponse update(User user, Long id, PostRequest postRequest) {
        Post post = getPostById(id);
        isUserException(user, post);

        if (postRequest.getTitle().isEmpty()){
            postRequest.setTitle(post.getTitle());
        }

        post.updateInfo(postRequest);

        return new PostResponse(post);
    }

    public PostResponse delete(User user, Long id) {
        Post post = getPostById(id);
        isUserException(user, post);

        postRepository.delete(post);
        return new PostResponse(post);
    }

    private void isUserException(User user, Post post) {
        if (!(post.getUser().getUsername().equals(user.getUsername()))) {
            throw new IllegalArgumentException("작성자가 아닙니다.");
        }
    }

    private Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("포스트가 없습니다.")
        );
    }

}
