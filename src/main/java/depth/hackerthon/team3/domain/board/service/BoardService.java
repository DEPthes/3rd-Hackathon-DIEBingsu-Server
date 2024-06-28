package depth.hackerthon.team3.domain.board.service;


import depth.hackerthon.team3.domain.board.domain.Board;
import depth.hackerthon.team3.domain.board.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    // 빙수 목록 조회 메서드
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }
    // 빙수 상세 조회 메서드
    public Optional<Board> getBoardById(Long boardId) {
        return boardRepository.findById(boardId);
    }
    // 빙수 정렬 조회 메서드
    public List<Board> getBoardsSorted(String sortBy) {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, sortBy));
    }

}
