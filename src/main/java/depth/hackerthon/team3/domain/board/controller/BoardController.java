package depth.hackerthon.team3.domain.board.controller;

import depth.hackerthon.team3.domain.board.domain.Board;
import depth.hackerthon.team3.domain.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
@Tag(name = "Board", description = "게시판 관련 API입니다.")
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "빙수의 전당 목록 조회", description = "모든 빙수 게시물을 조회합니다.")
    @GetMapping("/all")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }
    @Operation(summary = "빙수 상세 조회", description = "특정 ID의 빙수 게시물을 조회합니다.")
    @GetMapping("/{boardId}")
    public Optional<Board> getBoardById(@PathVariable("boardId") Long boardId) {
        return boardService.getBoardById(boardId);
    }
    @Operation(summary = "빙수 정렬 조회", description = "특정 기준으로 빙수 게시물을 정렬하여 조회합니다.")
    @GetMapping("/sorted")
    public List<Board> getBoardsSorted(@RequestParam String sortBy) {
        return boardService.getBoardsSorted(sortBy);
    }

}
