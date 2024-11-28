package startspring.dto;

import startspring.entity.Post;

import java.util.List;

public record ResponsePostDto(boolean result, Post posts) {
}
