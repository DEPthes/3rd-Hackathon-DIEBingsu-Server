package depth.hackerthon.team3.domain.board.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import depth.hackerthon.team3.domain.board.domain.Board;
import depth.hackerthon.team3.domain.board.dto.GenerateShavedIceReq;
import depth.hackerthon.team3.domain.board.dto.RegisterMyBoardReq;
import depth.hackerthon.team3.domain.board.service.BoardService;
import depth.hackerthon.team3.domain.gpt.service.GptService;
import depth.hackerthon.team3.domain.s3.service.S3Uploader;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
@Tag(name = "Board", description = "게시판 관련 API입니다.")
public class BoardController {

    private final BoardService boardService;
    private final GptService gptService;
    private final S3Uploader s3Uploader;

    //빙수의 정당 목록 조회
    @GetMapping("/all")
    public ResponseEntity<?> getAllBoards() {
        ResponseEntity<?> responseEntity = boardService.getAllBoards();
        return ResponseEntity.ok(responseEntity.getBody());
    }

    //빙수 상세 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<?> getBoardId(@PathVariable("boardId") Long boardId, String deviceId) {
        return boardService.getBoardById(boardId, deviceId);
    }

    @GetMapping("/sorted")
    public ResponseEntity<?> getBoardsSorted(@RequestParam String sortBy) {
        List<Board> sortedBoards = (List<Board>) boardService.getBoardsSorted(sortBy);
        return ResponseEntity.ok(sortedBoards);
    }

    @PostMapping("/{boardId}/react/{reactionNumber}")
    public ResponseEntity<?> reactToBoard(@PathVariable("boardId") Long boardId,
                                          @PathVariable("reactionNumber") int reactionNumber) {
        return boardService.reactToBoard(boardId, reactionNumber);
    }

    @PostMapping()
    public ResponseEntity<?> postMyBoard(@RequestPart MultipartFile image, @Valid @RequestPart RegisterMyBoardReq registerMyBoardReq) {
        return boardService.registerMyBoard(image, registerMyBoardReq);
    }

    @GetMapping("/generate")
    public ResponseEntity<?> generateImage(@RequestBody GenerateShavedIceReq generateShavedIceReq) throws JsonProcessingException {
        String prompt = generateShavedIceReq.getItem1() + ", " + generateShavedIceReq.getItem2() + ", " + generateShavedIceReq.getItem3();
        return gptService.getImageFromDallE(prompt);
    }
}
