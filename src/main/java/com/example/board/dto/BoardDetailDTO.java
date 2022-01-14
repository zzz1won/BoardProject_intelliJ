package com.example.board.dto;

import com.example.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailDTO {

    private Long boardId;
    private String boardWriter;
    private String boardPassword;
    private String boardTitle;
    private String boardContents;
    private LocalDateTime boardDate;


    public static BoardDetailDTO toBoardDetailDTO(BoardEntity boardEntity)    {
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        boardDetailDTO.setBoardId(boardEntity.getId());
        boardDetailDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDetailDTO.setBoardPassword(boardEntity.getBoardPassword());
        boardDetailDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDetailDTO.setBoardContents(boardEntity.getBoardContents());
        /*boardDetailDTO.setBoardDate(boardEntity.getBoardDate());*/
        if (boardEntity.getUpdateTime() == null)    {
            boardDetailDTO.setBoardDate(boardEntity.getCreateTime());
        }   else    {
            boardDetailDTO.setBoardDate(boardEntity.getUpdateTime());
        }
        return boardDetailDTO;

    }


}
