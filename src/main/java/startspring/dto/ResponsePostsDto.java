package startspring.dto;

import startspring.entity.Post;

import java.util.List;

public record ResponsePostsDto(boolean result, List<Post> posts) {
}
