package startspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import startspring.common.GlobalURI;
import startspring.dto.ResponsePostDto;
import startspring.dto.ResponsePostsDto;
import startspring.dto.ResponseRequestDto;
import startspring.dto.post.RequestPostDto;
import startspring.dto.post.UpdatePostDto;
import startspring.entity.Post;
import startspring.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 등록
    @PostMapping(GlobalURI.POST_URI)
    public ResponseEntity<ResponseRequestDto> createPost(@RequestBody RequestPostDto registerPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(registerPost));
    }

    // 게시글 수정
    @PutMapping(GlobalURI.POST_PATH_URI)
    public ResponseEntity<ResponseRequestDto> editPost(@PathVariable Long postId, @RequestBody UpdatePostDto updatePost) {
        return ResponseEntity.ok().body(postService.editPost(postId, updatePost));
    }

    // 게시글 1개 글 삭제
    @DeleteMapping(GlobalURI.POST_PATH_URI)
    public ResponseEntity<ResponseRequestDto> deletePost(@PathVariable Long postId) {
        return ResponseEntity.ok().body(postService.deletePostById(postId));
    }

    // 게시글 선택 삭제
    @DeleteMapping(GlobalURI.POST_URI)
    public ResponseEntity<ResponseRequestDto> deletePosts(@RequestBody List<Post> posts) {
        return ResponseEntity.ok().body(postService.deletePosts(posts));
    }

    // 게시글 1개 글 조회
    @GetMapping(GlobalURI.POST_PATH_URI)
    public ResponseEntity<ResponsePostDto> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok().body(postService.getPost(postId));
    }

    // 게시글 전체 조회
    @GetMapping(GlobalURI.POST_URI)
    public ResponseEntity<ResponsePostsDto> getPosts() {
        return ResponseEntity.ok().body(postService.getPosts());
    }


}
