package com.example.board.entity;

import com.example.board.dto.CommentSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


//0117 댓글작성을 위한 entity

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity{
    //댓글번호, 작성자, 내용, 원글

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long id;

    // 원글의 게시글 번호를 참조하기 위한 설정 n:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId") //부모테이블의 pk컬럼이름
    private BoardEntity boardEntity; //참조하고자 하는 테이블을 관리하는 entity 전체를 필드로 설정한다
    //(boardEntity에도 연관관계를 설정해준다)

    @Column(name = "comment_writer")
    private String commentWriter;

    @Column
    private String commentContents;

    // 0117 과제_ 멤버와 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")   //부모테이블의 pk 컬럼이름
    private MemberEntity memberEntity;
    //참조하고자 하는 테이블을 관리하는 entity 전체를 필드로 설정한다
    //(memberEntity에도 연관관계를 설정해준다)

    /*
    jpa에서 참조관계를 맺을 때 주의할 점!
    1:n
    1:1
    n:1
    n:n
     */

    public static CommentEntity toSaveEntity(CommentSaveDTO commentSaveDTO, BoardEntity boardEntity)    {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentSaveDTO.getCommentWriter());
        commentEntity.setCommentContents(commentSaveDTO.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);
        /*
        serviceImpl에서 넘긴 entity 데이터를 셋팅.
        (entity객체는 위에 field 선언을 했으니 값을 셋팅할 수 있다.)
        단순히 글번호1 - 댓글1 이 아니라...
        boardEntity에 있는 글1에 대한 정보에 댓글을 남기는거라서 전체적인 데이터를 끌어오는 것 같다.
        */
        return commentEntity;
    }
}
