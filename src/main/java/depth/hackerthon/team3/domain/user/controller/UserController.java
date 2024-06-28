package depth.hackerthon.team3.domain.user.controller;

import depth.hackerthon.team3.domain.user.dto.UserReq;
import depth.hackerthon.team3.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "사용자 관련 API입니다.")
public class UserController {

    private  final UserService userService;

    @PostMapping()
    public ResponseEntity<?> registerUser(UserReq userReq) {
        return userService.registerUser(userReq);
    }
}
