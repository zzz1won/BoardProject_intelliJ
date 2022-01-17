package com.example.board.controller;

import com.example.board.dto.MemberSaveDTO;
import com.example.board.service.BoardService;
import com.example.board.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService ms;

    @GetMapping("save")
    public String saveForm()    {
        System.out.println("MemberController.saverForm");
        return "/save";
    }

    @PostMapping("save")
    public String save(@ModelAttribute MemberSaveDTO memberSaveDTO)    {
        System.out.println("MemberController.save");
        Long memberId = ms.save(memberSaveDTO);
        return "./board/save";
    }



}
