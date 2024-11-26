package startspring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startspring.dto.ResponseRequestDto;
import startspring.dto.post.PostRequestDto;
import startspring.entity.Post;
import startspring.repository.PostRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public ResponseRequestDto createPost(PostRequestDto postRequestDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Post post = Post.builder().userId(username).nickName(postRequestDto.getNickname()).title(postRequestDto.getTitle()).content(postRequestDto.getContent()).build();

        Post savedPost = postRepository.save(post);
        log.info("Saved post: {}", savedPost.getNickName());

        return new ResponseRequestDto(true);
    }
}
