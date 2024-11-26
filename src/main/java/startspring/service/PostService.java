package startspring.service;

import startspring.dto.ResponseRequestDto;
import startspring.dto.post.PostRequestDto;

public interface PostService {
    ResponseRequestDto createPost(PostRequestDto registerPost);
}
