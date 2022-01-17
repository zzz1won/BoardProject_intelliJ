package com.example.board.service;

import com.example.board.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    /*Long save(BoardSaveDTO boardSaveDTO);*/
    Long save(BoardWriteDTO boardWriteDTO);

    List<BoardDetailDTO> findAll();

    BoardDetailDTO findById(Long boardId);

    Long update(BoardUpdateDTO boardUpdateDTO);

    Page<BoardPageingDTO> paging(Pageable pageable);

    /*boolean pwch(Long boardId, String boardPassword);*/
}
