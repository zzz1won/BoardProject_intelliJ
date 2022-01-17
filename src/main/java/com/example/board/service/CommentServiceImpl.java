package com.example.board.service;

import com.example.board.dto.CommentDetailDTO;
import com.example.board.dto.CommentSaveDTO;
import com.example.board.entity.BoardEntity;
import com.example.board.entity.CommentEntity;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository cr;
    private final BoardRepository br;

    @Override
    public Long save(CommentSaveDTO commentSaveDTO) {
        BoardEntity boardEntity = br.findById(commentSaveDTO.getBoardId()).get();
        CommentEntity commentEntity = CommentEntity.toSaveEntity(commentSaveDTO, boardEntity);
        //갑자기 훅 나가서 길을 놓친 느낌...
        return cr.save(commentEntity).getId();

        //댓글작성 테스트코드?

    }

    @Override
    public List<CommentDetailDTO> findAll(Long boardId) {
        BoardEntity boardEntity = br.findById(boardId).get();
        List<CommentEntity> commentEntityList = boardEntity.getCommentEntityList();
        //BoardId를 조회해서 commentList를 확인한다고???? <-???????????????????

        List<CommentDetailDTO> commentList = new ArrayList<>();
        for(CommentEntity c: commentEntityList)   {
            CommentDetailDTO commentDetailDTO = CommentDetailDTO.toCommentDetailDTO(c);
            commentList.add(commentDetailDTO);
        }
        return commentList;
    }
}
