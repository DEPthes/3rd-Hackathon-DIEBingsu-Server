package depth.hackerthon.team3.domain.user.service;

import depth.hackerthon.team3.domain.user.domain.User;
import depth.hackerthon.team3.domain.user.domain.repository.UserRepository;
import depth.hackerthon.team3.domain.user.dto.UserReq;
import depth.hackerthon.team3.global.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
}
