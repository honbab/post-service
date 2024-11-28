package startspring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startspring.dto.ResponsePostDto;
import startspring.dto.ResponsePostsDto;
import startspring.dto.ResponseRequestDto;
import startspring.dto.post.RequestPostDto;
import startspring.dto.post.UpdatePostDto;
import startspring.entity.Post;
import startspring.exception.ResourceNotExistException;
import startspring.repository.PostRepository;

import java.nio.file.AccessDeniedException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public ResponseRequestDto createPost(RequestPostDto postRequestDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Post post = Post.builder().userId(username).nickName(postRequestDto.getUserId()).title(postRequestDto.getTitle()).content(postRequestDto.getContent()).build();

        Post savedPost = postRepository.save(post);
        log.info("Saved post: {}", savedPost.getNickName());

        return new ResponseRequestDto(true);

    }

    @Override
    public ResponseRequestDto editPost(Long postId, UpdatePostDto updatePost) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotExistException("게시글이 존재하지 않습니다"));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if(!post.getUserId().equals(username)) {
            throw new SecurityException("수정 권한이 없습니다");
        }

        // title 혹은 content 둘 중 하나 수정
        updatePost.updatePost(updatePost.getTitle(), updatePost.getContent());

        return new ResponseRequestDto(true);
    }

    @Override
    public ResponsePostsDto getPosts() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        if(!authorities.contains(new SimpleGrantedAuthority("ROLE_USER")) || authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return new ResponsePostsDto(false, null);
        }

        List<Post> posts = postRepository.findAll();

        return new ResponsePostsDto(true, posts);
    }

    @Override
    public ResponsePostDto getPost(Long postId) {
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotExistException("게시글이 존재하지 않습니다"));

        Post post = postRepository.findById(postId).get();
        return new ResponsePostDto(true, post);
    }

    @Override
    public ResponseRequestDto deletePosts(List<Post> posts) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        for(Post post : posts) {
            if(post.getUserId().equals(username)) {
                return new ResponseRequestDto(false);
            }
        }

        List<Post> existingPosts = postRepository.findAllById(posts.stream().map(Post::getId) .collect(Collectors.toList()));

        if(existingPosts.size() != posts.size()) {
            return new ResponseRequestDto(false);
        }

        log.info("User {} deleted posts: {}", username, posts.stream().map(Post::getId).toList());

        postRepository.deleteAll(existingPosts);
        return new ResponseRequestDto(true);
    }


    @Override
    public ResponseRequestDto deletePostById(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!post.getUserId().equals(username)) {
            throw new SecurityException("삭제 권한이 없습니다");
        }

        postRepository.delete(post);

        return new ResponseRequestDto(true);
    }
}
