package startspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;
import startspring.entity.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Builder
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저 아이디
    @Column(nullable = false)
    private String userId;

    // 유저 닉네임
    @Column(nullable = false)
    private String nickName;

    // 타이틀
    @Column(nullable = false)
    private String title;

    // 내용
    @Column(nullable = false)
    private String content;

    public Post(Long id, String userId, String nickName, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.nickName = nickName;
        this.title = title;
        this.content = content;
    }
}
