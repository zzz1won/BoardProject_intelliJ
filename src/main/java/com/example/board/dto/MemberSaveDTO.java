package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MemberSaveDTO {
    private Long memberId; //얘를 읽는가보다
    public String memberEmail;
    public String memberPassword;
    public String memberName;

}
