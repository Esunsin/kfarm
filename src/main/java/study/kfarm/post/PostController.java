package study.kfarm.post;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import study.kfarm.global.security.UserDetailsImpl;
import study.kfarm.post.dto.PostRequest;
import study.kfarm.post.dto.PostResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public PostResponse create(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequest postRequest) {
        return postService.create(userDetails.getUser(), postRequest);
    }

    @GetMapping("/posts")
    public List<PostResponse> showAll() {
        return postService.showAll();
    }

    @GetMapping("/posts/{id}")
    public PostResponse showOne(@PathVariable("id") Long id) {
        return postService.showOne(id);
    }

    @PutMapping("/posts/{id}")
    public PostResponse update(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequest postRequest, @PathVariable("id") Long id) {
        return postService.update(userDetails.getUser(), id, postRequest);
    }

    @DeleteMapping("/posts/{id}")
    public PostResponse delete(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("id") Long id) {
        return postService.delete(userDetails.getUser(), id);
    }
}
