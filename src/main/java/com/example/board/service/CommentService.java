package com.example.board.service;

import com.example.board.dto.CommentDetailDTO;
import com.example.board.dto.CommentSaveDTO;

import java.util.List;

public interface CommentService {
    Long save(CommentSaveDTO commentSaveDTO);

    List<CommentDetailDTO> findAll(Long boardId);
}
