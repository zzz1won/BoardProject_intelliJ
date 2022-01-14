package com.example.board.controller;

import com.example.board.common.PagingConst;
import com.example.board.dto.BoardDetailDTO;
import com.example.board.dto.BoardPageingDTO;
import com.example.board.dto.BoardSaveDTO;
import com.example.board.dto.BoardUpdateDTO;
import com.example.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j //로그를 기록할 수 있는 라이브러리?
@Controller
public class BoardController {

    private final BoardService bs;

    @GetMapping("/save")
    public String saveForm()    {
        System.out.println("BoardController.saveForm");
        return "/board/save";
    }

    @PostMapping("/save")
        public String save(@ModelAttribute BoardSaveDTO boardSaveDTO)    {
            System.out.println("BoardController.save");
            Long boardId = bs.save(boardSaveDTO);
            return "redirect:/board/";
        }


    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDetailDTO> boardList = bs.findAll();
        model.addAttribute("boardList", boardList);
        log.info("findAll 호출");
        return "/board/findAll";
    }

/*
    @GetMapping("{boardId}")
    public String findById(Model model, @PathVariable Long boardId)    {
        log.info("글보기 메서드 호출. 요청 글번호:{}", boardId);
        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board",board);
        return "board/findById";
    }
*/

    @GetMapping("/{boardId}")
    @ResponseBody
    public BoardDetailDTO findById(Model model, @PathVariable Long boardId)    {
        log.info("글보기 메서드 호출. 요청 글번호:{}", boardId);
        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board",board);
        return board;
    }

    @PostMapping("/{boardId}")
    public ResponseEntity findById2(@PathVariable Long boardId) {
        BoardDetailDTO board = bs.findById(boardId);
        return new ResponseEntity<BoardDetailDTO>(board, HttpStatus.OK);
        //ResponseEntity 객체를 갖고 가져가는 방식(ajax)
    }

    @GetMapping("/update/{boardId}")
    public String updateForm(@PathVariable Long boardId, Model model){
        BoardDetailDTO board = bs.findById(boardId);
        System.out.println("boardId: "+ boardId);
        model.addAttribute("board", board);
        log.info("updateForm 호출");
        return "board/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardUpdateDTO boardUpdateDTO) {
        Long boardId = bs.update(boardUpdateDTO);
        log.info("update 호출");

        return "redirect:/board/"+boardUpdateDTO.getBoardId();
    }

    @PutMapping("/{boardId}")
    public ResponseEntity update2(@RequestBody BoardUpdateDTO boardUpdateDTO)  {
        bs.update(boardUpdateDTO);
        return new ResponseEntity(HttpStatus.OK);
    }


    //0114 페이징처리 (페이지요청은 무조건 GET_ ✔ /board?page=5)
    //restful 주소로 했을 때... 5번 글을 보겠다면 /board/5 ... 여기서 5는 고유번호(바뀔 수 없음)
    // 주소값만으로 뭘 요청하는지 알 수 있게 하는게 restful의 방식

    @GetMapping //그냥 getMapping?
    public String paging (@PageableDefault(page = 1)Pageable pageable, Model model) {
        Page<BoardPageingDTO> boardList = bs.paging(pageable);
        model.addAttribute("boardList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        log.info("paging을 불러오자");
        return "board/paging";

    }

}
