/*
package com.example.board.interceptor;

import com.example.board.dto.BoardDetailDTO;
import com.example.board.dto.BoardSaveDTO;
import com.example.board.entity.BoardEntity;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class pwCheck implements HandlerInterceptor {

    @Override   //부모가 가진 메소드를 재정의한다.(커스터마이징~)
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        String requestURI = request.getRequestURI();
        System.out.println("requestURI="+requestURI);

        String currentPassword = new BoardSaveDTO().getBoardPassword();

        if(currentPassword.)



    }
}
*/
