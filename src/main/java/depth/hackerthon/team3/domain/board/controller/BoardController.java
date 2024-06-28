package depth.hackerthon.team3.domain.board.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
@Tag(name = "Board", description = "게시판 관련 API입니다.")
public class BoardController {
}
