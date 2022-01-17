package com.example.board;

import com.example.board.dto.BoardSaveDTO;
import com.example.board.dto.CommentDetailDTO;
import com.example.board.dto.CommentSaveDTO;
import com.example.board.entity.BoardEntity;
import com.example.board.entity.CommentEntity;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.service.BoardService;
import com.example.board.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class CommentTest {
    @Autowired
    private CommentService cs;
    @Autowired
    private CommentRepository cr;
    @Autowired
    private BoardService bs;
    @Autowired
    private BoardRepository br;
    /*
    @Test
    /*@Transactional //참조관계에 있을 경우 transactional을 꼭 써줘야함...*/
    //근데 왜 이거 쓰면 글이 다 사라지는?...
    @DisplayName("댓글작성 테스트코드")
    public void commentTest() {
/*
        //1. 게시글
        Long boardId = bs.save(new BoardSaveDTO("writer", "pw" , "title" , "contents"));
        Long boardId2 = bs.save(new BoardSaveDTO("writer", "pw" , "title" , "contents"));
        Long boardId5 = bs.save(new BoardSaveDTO("writer", "pw" , "title" , "contents"));
        Long boardId3 = bs.save(new BoardSaveDTO("writer", "pw" , "title" , "contents"));
        Long boardId4 = bs.save(new BoardSaveDTO("writer", "pw" , "title" , "contents"));

        //2. 댓글
        cs.save(new CommentSaveDTO(boardId, "write", "contents"));
        cs.save(new CommentSaveDTO(boardId2, "write", "contents"));
        cs.save(new CommentSaveDTO(boardId3, "write", "contents"));
        cs.save(new CommentSaveDTO(boardId3, "write", "contents"));
        cs.save(new CommentSaveDTO(boardId3, "write", "contents"));
        cs.save(new CommentSaveDTO(boardId3, "write", "contents"));
        cs.save(new CommentSaveDTO(boardId4, "write", "contents"));
*/

       /* IntStream.rangeClosed(1,3).forEach(i->{
            cs.save(new CommentSaveDTO((long) i, "작성자"+i, "내용"+i));
        });*/
        //when
        //then
    }
/*
    @Test
    @Transactional
    @Rollback(value = false) //이러면 rollback 되지않음...
    @DisplayName("댓글작성 test2")
    public void CommentSaveTest()   {
        // 1. 게시글이 존재해야함
        BoardSaveDTO boardSaveDTO = new BoardSaveDTO("writer", "pw" , "title" , "contents");
        Long boardId = bs.save(boardSaveDTO);

        // 2. 댓글쓰기
        CommentSaveDTO commentSaveDTO = new CommentSaveDTO(boardId, "댓글작성자", "댓내용");
        cs.save(commentSaveDTO);
        
    }
    
    //조회?
    @Test
    @Transactional //헐 얘 안넣어서 그런거였어... 댑악...
    @DisplayName("댓글조회 test")
    public void findByIdTest()  {
        CommentEntity commentEntity = cr.findById(1L).get();
        System.out.println("commentEntity.toString() = " + commentEntity.toString());
        System.out.println("commentEntity.getId() = " + commentEntity.getId());
        System.out.println("commentEntity.getCommentWriter() = " + commentEntity.getCommentWriter());
        System.out.println("commentEntity.getCommentContents() = " + commentEntity.getCommentContents());
        System.out.println("commentEntity.getBoardEntity() = " + commentEntity.getBoardEntity());
        System.out.println("commentEntity.getBoardEntity().getBoardTitle() = " + commentEntity.getBoardEntity().getBoardTitle());
        
                
        
    }

    @Test
    @Transactional
    @DisplayName("댓글 목록 출력")
    public void findAllTest()   {
        List<CommentDetailDTO> commentDetailDTOS = cs.findAll(1L);
        for (CommentDetailDTO c: commentDetailDTOS){
            System.out.println("c.toString() = " + c.toString());
        }
    }
*/
}
