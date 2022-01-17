package com.example.board;

import com.example.board.common.PagingConst;
import com.example.board.dto.BoardPageingDTO;
import com.example.board.dto.BoardSaveDTO;
import com.example.board.dto.MemberSaveDTO;
import com.example.board.entity.BoardEntity;
import com.example.board.repository.BoardRepository;
import com.example.board.service.BoardService;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardTest {
    @Autowired
    private BoardService bs;

    @Autowired
    private BoardRepository br;

    @Test
    @DisplayName("게시글 생성test")
    public void newBoard()  {
        //IntStream을 이용하여 새글 30개 DB에 저장해놓자~
        //글 목록 출력하는 것 까지 진행하기...
        IntStream.rangeClosed(1,10).forEach( i-> {
            bs.save(new BoardSaveDTO("writer" + i, "pw" + i, "title" + i, "contents" + i));
        });
    }

    @Test
    @DisplayName("게시글 수정test")
    public void updateBoardTest(){

    }

    @Test
    @DisplayName("0114 삼항연산자...??")
    public void test1() {
        int num = 10;
        int num2 = 0;
        if (num == 10)  {
            num2 = 5;
        }   else    {
            num2 = 100;
        }
        // 위의 코드는 이렇게 삼항연산자로 표현이 되기도 한다...
        num2= (num==10)? 5: 100;
    }

    @Test
    @Transactional
    @DisplayName("페이징 테스트")
    public void pagingTest()    {
        int page = 7;
        Page<BoardEntity> boardEntities = br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        // Page 객체가 제공해주는 메서드 확인
        System.out.println("boardEntities. = " + boardEntities.getContent());   // 요청페이지에 들어있는 데이터
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements());   // 전체 글 갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // 요청페이지(jpa 기준)
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); //전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); //PAGE_LIMIT 값, 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); //이전페이지가 있는가... = false
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); //첫페이지인지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast());   // 마지막페이지인지 여부

        // 공식처럼 외우기 forEach 같은 느낌
        // Page<BoardEntity> -> Page<BoardPagingDTO>
        //map() : entity가 담긴 페이지 객체를 DTO가 담긴 페이지 객체로 변환해주는 역할
        Page<BoardPageingDTO> boardList = boardEntities.map(
                board -> new BoardPageingDTO(board.getId(),
                                                board.getBoardWriter(),
                                                board.getBoardTitle())
        );

        System.out.println("boardList. = " + boardList.getContent());   // 요청페이지에 들어있는 데이터
        System.out.println("boardList.getTotalElements() = " + boardList.getTotalElements());   // 전체 글 갯수
        System.out.println("boardList.getNumber() = " + boardList.getNumber()); // 요청페이지(jpa 기준)
        System.out.println("boardList.getTotalPages() = " + boardList.getTotalPages()); //전체 페이지 갯수
        System.out.println("boardList.getSize() = " + boardList.getSize()); //PAGE_LIMIT 값, 한 페이지에 보여지는 글 갯수
        System.out.println("boardList.hasPrevious() = " + boardList.hasPrevious()); //이전페이지가 있는가... = false
        System.out.println("boardList.isFirst() = " + boardList.isFirst()); //첫페이지인지 여부
        System.out.println("boardList.isLast() = " + boardList.isLast());   // 마지막페이지인지 여부

    }



    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("게시글 삭제")
    public void boardDelete()   {
       /* br.deleteById(1L); // 다짜고짜 이렇게 써버리면! 자식(참조하는 자료)이 있어서 삭제할 수 없음*/
        br.deleteById(2L);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("멤버가 게시글 작성")
    public void memberWriteTest()   {
        bs.save()
    }
}
