package com.example.board.service;

import com.example.board.dto.BoardDetailDTO;
import com.example.board.dto.BoardPageingDTO;
import com.example.board.dto.BoardSaveDTO;
import com.example.board.dto.BoardUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    Long save(BoardSaveDTO boardSaveDTO);

    List<BoardDetailDTO> findAll();

    BoardDetailDTO findById(Long boardId);

    Long update(BoardUpdateDTO boardUpdateDTO);

    Page<BoardPageingDTO> paging(Pageable pageable);

    /*boolean pwch(Long boardId, String boardPassword);*/
}
