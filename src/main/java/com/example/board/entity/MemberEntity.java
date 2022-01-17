package com.example.board.entity;

import com.example.board.dto.MemberSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member_table1")
public class MemberEntity {
    //id(Long), email, password, name

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    // 멤버 : 게시글 참조하기 위한 설정
    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    public static MemberEntity toSaveMember(MemberSaveDTO memberSaveDTO)    {
        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setMemberEmail(memberSaveDTO.memberEmail);
        memberEntity.setMemberPassword(memberSaveDTO.memberPassword);
        memberEntity.setMemberName(memberSaveDTO.memberName);

        return memberEntity;
    }



}
