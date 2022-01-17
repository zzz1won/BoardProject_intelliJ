package com.example.board;

import com.example.board.dto.BoardSaveDTO;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.MemberRepository;
import com.example.board.service.BoardService;
import com.example.board.service.CommentService;
import com.example.board.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberTest {
    @Autowired
    private CommentService cs;
    @Autowired
    private CommentRepository cr;
    @Autowired
    private BoardService bs;
    @Autowired
    private BoardRepository br;
    @Autowired
    private MemberService ms;
    @Autowired
    private MemberRepository mr;

    @Test
    @DisplayName("멤버가 글을 작성test")
    public void memberWriteTest()   {
        //1. 멤버 생성

    }

}
