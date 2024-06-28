package depth.hackerthon.team3.domain.user.service;

import depth.hackerthon.team3.domain.board.domain.Board;
import depth.hackerthon.team3.domain.board.domain.repository.BoardRepository;
import depth.hackerthon.team3.domain.user.domain.User;
import depth.hackerthon.team3.domain.user.domain.repository.UserRepository;
import depth.hackerthon.team3.domain.user.dto.MyShavedIceRes;
import depth.hackerthon.team3.domain.user.dto.CheckPasswordReq;
import depth.hackerthon.team3.domain.user.dto.UserReq;
import depth.hackerthon.team3.global.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    // 유저 정보 DB 저장
    @Transactional
    public ResponseEntity<?> registerUser(UserReq userReq) {
        User user = User.builder()
                .deviceId(userReq.getDeviceId())
                .build();
        userRepository.save(user);

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information("사용자 정보가 저장되었어요.")
                .build();

        return ResponseEntity.ok(apiResponse);
    }


    // 내 빙수 조회
    public ResponseEntity<?> getMyShavedIce(String deviceId) {
        User user = userRepository.findByDeviceId(deviceId);
        List<Board> boardList = boardRepository.findByUser(user);

        List<MyShavedIceRes> myShavedIceResList = boardList.stream()
                .map(board -> MyShavedIceRes.builder()
                        .boardId(board.getBoardId())
                        .image(board.getImage())
                        .shavedIceName(board.getShavedIceName())
                        .description(board.getDescription())
                        .reaction1(board.getReaction1())
                        .reaction2(board.getReaction2())
                        .reaction3(board.getReaction3())
                        .reaction4(board.getReaction4())
                        .reaction5(board.getReaction5())
                        .createdAt(board.getCreatedAt())
                        .build())
                .sorted(Comparator.comparing(MyShavedIceRes::getCreatedAt).reversed())
                .toList();

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(myShavedIceResList)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    // 내 빙수 상세 조회

    // 비밀번호 확인
    public ResponseEntity<?> checkPassword(Long boardId, CheckPasswordReq checkPasswordReq) {
        Board board = boardRepository.findByBoardId(boardId);
        String msg = "비밀번호가 일치하지 않습니다.";
        if (Objects.equals(board.getBoardPassword(), checkPasswordReq.getPassword())) {
            msg = "비밀번호가 일치합니다.";
        }
        ApiResponse apiResponse = ApiResponse.builder()
                    .check(true)
                    .information(msg)
                    .build();
        return ResponseEntity.ok(apiResponse);
    }

    // 내 빙수 삭제

}
