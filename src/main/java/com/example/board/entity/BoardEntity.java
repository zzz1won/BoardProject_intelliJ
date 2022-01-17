package com.example.board.entity;

import com.example.board.dto.BoardDetailDTO;
import com.example.board.dto.BoardSaveDTO;
import com.example.board.dto.BoardUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="글번호")
    private Long id;

    @Column(length = 20, name = "작성자")
    private String boardWriter;
    //익명게시판이기때문에 writer에 유니크를 주면 글을 여러개 쓸 수 없다.

    @Column(length = 10, name = "작성자비번")
    private String boardPassword;

    @Column(name="제목")
    private String boardTitle;

    @Column(name = "내용")
    private String boardContents;

    // 0117 과제_ 멤버와 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")   //부모테이블의 pk 컬럼이름
    private MemberEntity memberEntity;
    //참조하고자 하는 테이블을 관리하는 entity 전체를 필드로 설정한다
    //(memberEntity에도 연관관계를 설정해준다)


/*    @Column(name = "작성시간")
    private LocalDateTime boardDate;*/
    // 0113 수정합니다!!!!

    // 0117 댓글 연관관계
    /*@OneToMany(mappedBy = "boardEntity", fetch = FetchType.LAZY) 기존! */
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static BoardEntity saveBoard (BoardSaveDTO boardSaveDTO) {
        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setBoardWriter(boardSaveDTO.getBoardWriter());
        boardEntity.setBoardPassword(boardSaveDTO.getBoardPassword());
        boardEntity.setBoardTitle(boardSaveDTO.getBoardTitle());
        boardEntity.setBoardContents(boardSaveDTO.getBoardContents());
        /*boardEntity.setBoardDate(LocalDateTime.now());*/

        return boardEntity;
    }



    public static BoardEntity toUpdateBoard (BoardUpdateDTO boardDetailDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId((boardDetailDTO.getBoardId()));
        boardEntity.setBoardWriter(boardDetailDTO.getBoardWriter());
        boardEntity.setBoardPassword(boardDetailDTO.getBoardPassword());
        boardEntity.setBoardTitle(boardDetailDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDetailDTO.getBoardContents());
        /*boardEntity.setBoardDate(LocalDateTime.now());*/
        return boardEntity;
    }



}
