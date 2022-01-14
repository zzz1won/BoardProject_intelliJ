package com.example.board.entity;

import com.example.board.dto.BoardDetailDTO;
import com.example.board.dto.BoardSaveDTO;
import com.example.board.dto.BoardUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

/*    @Column(name = "작성시간")
    private LocalDateTime boardDate;*/
    // 0113 수정합니다!!!!


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
