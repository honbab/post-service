package startspring.service;

import startspring.dto.ResponsePostDto;
import startspring.dto.ResponsePostsDto;
import startspring.dto.ResponseRequestDto;
import startspring.dto.post.RequestPostDto;
import startspring.dto.post.UpdatePostDto;
import startspring.entity.Post;

import java.util.List;

public interface PostService {
    ResponseRequestDto createPost(RequestPostDto registerPost);

    ResponseRequestDto deletePostById(Long postId);

    ResponseRequestDto editPost(Long postId, UpdatePostDto updatePost);

    ResponsePostsDto getPosts();

    ResponsePostDto getPost(Long postId);

    ResponseRequestDto deletePosts(List<Post> posts);
}
