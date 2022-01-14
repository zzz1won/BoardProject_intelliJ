package com.example.board.service;

import com.example.board.common.PagingConst;
import com.example.board.dto.BoardDetailDTO;
import com.example.board.dto.BoardPageingDTO;
import com.example.board.dto.BoardSaveDTO;
import com.example.board.dto.BoardUpdateDTO;
import com.example.board.entity.BoardEntity;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository br;

    @Override
    public Long save(BoardSaveDTO boardSaveDTO) {
        System.out.println("BoardSerivceImpl.save");
        BoardEntity boardEntity = BoardEntity.saveBoard(boardSaveDTO);
        return br.save(boardEntity).getId();
    }

    @Override
    public List<BoardDetailDTO> findAll() {
        List<BoardEntity> boardEntityList = br.findAll();
        List<BoardDetailDTO> boardList = new ArrayList<>();

        for (BoardEntity e : boardEntityList) {
            boardList.add(BoardDetailDTO.toBoardDetailDTO(e));
        }
        return boardList;
    }

    @Override
    public BoardDetailDTO findById(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = br.findById(boardId);
        /*
            optional 객체 메서드
            - isPresent() : 데이터가 있으면 true, 없으면 false 반환
            - isEmpty() : 데이터가 없으면 true, 있으면 false 반환
            - get() : optional 객체에 들어있는 실제 데이터를 가져올 때
         */
        BoardDetailDTO boardDetailDTO = null;
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            boardDetailDTO = BoardDetailDTO.toBoardDetailDTO(boardEntity);
            //데이터가 있을때의 코드.
            //있으면 해당 데이터를 꺼내와서 보여주겠다~
        }
        return boardDetailDTO;
    }

    @Override
    public Long update(BoardUpdateDTO boardUpdateDTO) {
        System.out.println("boardServiceImpl.update");
        return br.save(BoardEntity.toUpdateBoard(boardUpdateDTO)).getId();
        // Jpa에서 save를 할 때 pk값을 같이 보내면 코드 실행시 pk값을 인지해서,
        // 계속 재생성하지않고 해당 데이터를 찾아 수정해준다.
    }

    @Override
    public Page<BoardPageingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        //요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청 페이지에서 1을 뺀다.
        // page = page - 1;
        page = (page == 1)? 0: (page - 1);
        Page<BoardEntity> boardEntities = br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        // Page<BoardPagingEntity> -> Page<BoardPagingDTO> ... 그런데 바로 적용이 되지않는다... *영상참조

        Page<BoardPageingDTO> boardList = boardEntities.map(
                board -> new BoardPageingDTO(board.getId(),
                        board.getBoardWriter(),
                        board.getBoardTitle())
        );

        return boardList;
    }

}