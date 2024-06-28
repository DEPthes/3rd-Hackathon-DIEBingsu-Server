package depth.hackerthon.team3.domain.board.service;


import depth.hackerthon.team3.domain.board.domain.Board;
import depth.hackerthon.team3.domain.board.domain.repository.BoardRepository;
import depth.hackerthon.team3.domain.board.dto.RegisterMyBoardReq;
import depth.hackerthon.team3.domain.s3.service.S3Uploader;
import depth.hackerthon.team3.domain.user.domain.User;
import depth.hackerthon.team3.domain.user.domain.repository.UserRepository;
import depth.hackerthon.team3.global.payload.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;
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

    // 내 게시글 작성
    @Transactional
    public ResponseEntity<?> registerMyBoard(MultipartFile image, RegisterMyBoardReq registerMyBoardReq) {
        User user = userRepository.findByDeviceId(registerMyBoardReq.getDeviceId());
        String imageUrl = s3Uploader.uploadImage(image);
        Board board = Board.builder()
                .user(user)
                .shavedIceName(registerMyBoardReq.getShavedIceName())
                .description(registerMyBoardReq.getDescription())
                .boardPassword(registerMyBoardReq.getBoardPassword())
                .image(imageUrl)
                .item1(registerMyBoardReq.getItem1())
                .item2(registerMyBoardReq.getItem2())
                .item3(registerMyBoardReq.getItem3())
                .build();
        boardRepository.save(board);

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information("내 빙수가 등록되었습니다.")
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
