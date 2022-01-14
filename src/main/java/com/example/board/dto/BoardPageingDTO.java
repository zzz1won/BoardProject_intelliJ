package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardPageingDTO {

    private Long boardId;
    private String boardWriter;
    private String boardTitle;
}
