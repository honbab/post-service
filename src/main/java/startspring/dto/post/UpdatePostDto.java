package startspring.dto.post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePostDto {

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 100, message = "제목은 100자를 초과할 수 없습니다.")
    String title;

    @NotBlank(message = "내용은 필수입니다.")
    String content;

    public void updatePost(String title, String content) {
        if(title != null) {
            this.title = title;
        }
        if(content != null) {
            this.content = content;
        }

    }
}
