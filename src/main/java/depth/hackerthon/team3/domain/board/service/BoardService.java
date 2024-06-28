package depth.hackerthon.team3.domain.board.service;


import depth.hackerthon.team3.domain.board.domain.Board;
import depth.hackerthon.team3.domain.board.domain.repository.BoardRepository;
import depth.hackerthon.team3.domain.board.dto.BoardListRes;
import depth.hackerthon.team3.domain.board.dto.DetailBoardRes;
import depth.hackerthon.team3.domain.board.dto.ReactionRes;
import depth.hackerthon.team3.domain.user.domain.User;
import depth.hackerthon.team3.domain.user.domain.repository.UserRepository;
import depth.hackerthon.team3.domain.user.dto.MyShavedIceRes;
import depth.hackerthon.team3.global.payload.ApiResponse;
import jakarta.transaction.Transactional;
import depth.hackerthon.team3.domain.s3.service.S3Uploader;
import depth.hackerthon.team3.domain.user.domain.User;
import depth.hackerthon.team3.domain.user.domain.repository.UserRepository;
import depth.hackerthon.team3.global.payload.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private  final UserRepository userRepository;
    private final S3Uploader s3Uploader;
    //빙수 목록 조회
    public ResponseEntity<?> getAllBoards() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardListRes> boardListResList = boardList.stream()
                .map(board -> BoardListRes.builder()
                        // boardId
                        .image(board.getImage())
                        .shavedIceName(board.getShavedIceName())
                        .description(board.getDescription())
                        .reaction1(board.getReaction1())
                        .reaction2(board.getReaction2())
                        .reaction3(board.getReaction3())
                        .reaction4(board.getReaction4())
                        .reaction5(board.getReaction5())
                        .build())
                .collect(Collectors.toList());

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(boardListResList)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    // 빙수 상세 조회 메서드
    public ResponseEntity<?> getBoardById(Long boardId, String deviceId) {
        User user = userRepository.findByDeviceId(deviceId);
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Board board = optionalBoard.get();
        boolean isValid = (board.getUser() == user);
        DetailBoardRes detailBoardRes = DetailBoardRes.builder()
                .boardId(board.getBoardId())
                .image(board.getImage())
                .item1(board.getItem1())
                .item2(board.getItem2())
                .item3(board.getItem3())
                .shavedIceName(board.getShavedIceName())
                .description(board.getDescription())
                .reaction1(board.getReaction1())
                .reaction2(board.getReaction2())
                .reaction3(board.getReaction3())
                .reaction4(board.getReaction4())
                .reaction5(board.getReaction5())
                .isValid(isValid)
                .build();

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(detailBoardRes)
                .build();

        return ResponseEntity.ok(apiResponse);
    }
    // 빙수 정렬 조회 메서드
    public ResponseEntity<?> getBoardsSorted(String sortBy) {
        List<Board> sortedBoards = boardRepository.findAll(Sort.by(Sort.Direction.DESC, sortBy));

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(sortedBoards)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @Transactional
    public ResponseEntity<?> reactToBoard(Long boardId, int reactionNumber) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Board board = optionalBoard.get();
        switch (reactionNumber) {
            case 1:
                board.setReaction1(board.getReaction1() + 1);
                break;
            case 2:
                board.setReaction2(board.getReaction2() + 1);
                break;
            case 3:
                board.setReaction3(board.getReaction3() + 1);
                break;
            case 4:
                board.setReaction4(board.getReaction4() + 1);
                break;
            case 5:
                board.setReaction5(board.getReaction5() + 1);
                break;
            default:
                return ResponseEntity.badRequest().body("Invalid reaction number: " + reactionNumber);
        }

        // ReactionRes 객체를 생성하여 필요한 정보를 담습니다.
        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information("반응추가됐습니다.")
                .build();

        return ResponseEntity.ok(apiResponse);
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
