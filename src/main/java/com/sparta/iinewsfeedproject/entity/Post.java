package com.sparta.iinewsfeedproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@Table(name="post")
@NoArgsConstructor
public class Post extends Timestamped  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment(value = "뉴스피드 게시물 고유번호")
    private Long id;

    // 기본값이 EAGER 즉시 로딩이라 N + 1 문제가 발생할 수도 있기 때문에
    // FetchType.Lazy는 지연로딩을 하기 위해 달아주었습니다.
    @ManyToOne(fetch = FetchType.LAZY) // 유저테이블과 연관관계 설정
    @JoinColumn(name="user_id" ,referencedColumnName = "id")
    private User user;

    @Column(name="content", nullable = false, length=255)
    @Comment(value = "게시물 내용")
    private String content;

    public Post(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public void modifyContent(@NotBlank(message = "내용을 입력해주세요") String content) {
        this.content = content;
    }
}
