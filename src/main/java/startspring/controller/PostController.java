package startspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import startspring.common.GlobalURI;
import startspring.dto.ResponseRequestDto;
import startspring.dto.post.PostRequestDto;
import startspring.service.PostService;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 등록
    @PostMapping(GlobalURI.POST_URI)
    public ResponseEntity<ResponseRequestDto> registerPost(@RequestBody PostRequestDto registerPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(registerPost));
    }

    // 게시글 수정


    // 게시글 삭제

    // 게시글 조회
}
